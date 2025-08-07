// A simple class to hold animation frames and delay, similar to ImgObj
class Animation {
    constructor(frames, frameDelay) {
        this.images = new Array(frames);
        this.frameDelay = frameDelay;
        this.frames = frames;
    }
}

class StatedSprite extends Sprite {
    constructor(x, y, width, height) {
        super(x, y, width, height);

        this.states = [];
        this.currentState = 0;
        this.nextState = 0;
        this.defaultState = 0;
        this.currentFrame = 0;
        this.frameCount = 0;
        this.action = 0; // Used to signal when an animation sequence has finished
    }

    initMain(numStates, defaultState) {
        this.states = new Array(numStates);
        this.currentState = defaultState;
        this.defaultState = defaultState;
        this.nextState = defaultState;
    }

    initState(stateIndex, frames, frameDelay) {
        if (frames > 0) {
            this.states[stateIndex] = new Animation(frames, frameDelay);
        }
    }

    // This will be more complex. We need a way to load images.
    // For now, we'll assume images are pre-loaded and passed in.
    loadImages(stateIndex, images) {
        if (this.states[stateIndex]) {
            this.states[stateIndex].images = images;
        }
    }

    // A helper for loading from a spritesheet
    loadImagesFromSpriteSheet(stateIndex, spriteSheet, y, frameWidth, frameHeight) {
        const state = this.states[stateIndex];
        if (!state) return;

        for (let i = 0; i < state.frames; i++) {
            const canvas = document.createElement('canvas');
            canvas.width = frameWidth;
            canvas.height = frameHeight;
            const context = canvas.getContext('2d');
            context.drawImage(spriteSheet, i * frameWidth, y, frameWidth, frameHeight, 0, 0, frameWidth, frameHeight);
            state.images[i] = canvas;
        }
    }

    setNewState(newState) {
        this.currentFrame = 0;
        this.frameCount = 0;
        this.currentState = newState;
    }

    update() {
        if (!this.active) return;

        const state = this.states[this.currentState];
        if (!state || state.frameDelay === -1) return;

        this.frameCount++;
        if (this.frameCount >= state.frameDelay) {
            this.frameCount = 0;
            this.currentFrame++;
            if (this.currentFrame >= state.frames) {
                this.currentFrame = 0;
                this.action = this.currentState;
                this.currentState = this.nextState;
                if (this.defaultState !== -1) {
                    this.nextState = this.defaultState;
                }
            }
        }
    }

    paint(ctx) {
        if (!this.visible) return;

        const state = this.states[this.currentState];
        if (state && state.images[this.currentFrame]) {
            ctx.drawImage(state.images[this.currentFrame], this.x, this.y);
        }
    }
}
