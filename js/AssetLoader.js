class AssetLoader {
    constructor() {
        this.images = {};
        this.sounds = {};
        this.imageUrls = {
            spritestrip: 'assets/images/spritestrip.gif',
            enemystrip0: 'assets/images/enemystrip0.gif',
            enemystrip1: 'assets/images/enemystrip1.gif',
            buttons: 'assets/images/buttons.gif',
            buttons2: 'assets/images/buttons2.gif',
            strip: 'assets/images/strip.gif',
            backdrop2: 'assets/images/backdrop2.gif',
            backdrop3: 'assets/images/backdrop3.gif',
            enemyboss1: 'assets/images/enemyboss1.gif',
            enemyboss2: 'assets/images/enemyboss2.gif',
            rappy: 'assets/images/rappy.gif',
            weaponstrip: 'assets/images/weaponstrip.gif'
        };
        this.soundUrls = {
            intro: 'assets/sounds/intro.au',
            theme: 'assets/sounds/theme.au',
            boss: 'assets/sounds/boss.au',
            laser1: 'assets/sounds/laser1.au',
            explosion: 'assets/sounds/explosion.au'
        };
    }

    loadAll() {
        return Promise.all([this.loadImages(), this.loadSounds()]);
    }

    loadImages() {
        const promises = [];
        for (const key in this.imageUrls) {
            const url = this.imageUrls[key];
            const promise = new Promise((resolve, reject) => {
                const img = new Image();
                img.onload = () => {
                    this.images[key] = img;
                    resolve(img);
                };
                img.onerror = () => {
                    console.error(`Failed to load image: ${url}`);
                    // Resolve with a placeholder if image fails to load
                    this.images[key] = this.createPlaceholder(32, 32, 'red');
                    resolve(this.images[key]);
                };
                img.src = url;
            });
            promises.push(promise);
        }
        return Promise.all(promises);
    }

    createPlaceholder(width, height, color) {
        const canvas = document.createElement('canvas');
        canvas.width = width;
        canvas.height = height;
        const ctx = canvas.getContext('2d');
        ctx.fillStyle = color;
        ctx.fillRect(0, 0, width, height);
        return canvas;
    }

    getImage(key) {
        return this.images[key];
    }

    loadSounds() {
        const promises = [];
        for (const key in this.soundUrls) {
            const url = this.soundUrls[key];
            const promise = new Promise((resolve) => {
                const audio = new Audio();
                audio.oncanplaythrough = () => {
                    this.sounds[key] = audio;
                    resolve(audio);
                };
                audio.onerror = () => {
                    console.error(`Failed to load sound: ${url}`);
                    this.sounds[key] = null;
                    resolve(null);
                };
                audio.src = url;
            });
            promises.push(promise);
        }
        return Promise.all(promises);
    }

    getSound(key) {
        return this.sounds[key];
    }
}
