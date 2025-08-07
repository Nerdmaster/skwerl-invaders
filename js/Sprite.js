class Sprite {
    constructor(x, y, width, height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visible = true;
        this.active = true;
    }

    disable() {
        this.visible = false;
        this.active = false;
    }

    enable() {
        this.visible = true;
        this.active = true;
    }

    getPosition() {
        return { x: this.x, y: this.y };
    }

    setPosition(x, y) {
        this.x = x;
        this.y = y;
    }

    translate(dx, dy) {
        this.x += dx;
        this.y += dy;
    }

    // The paint method will be implemented by subclasses
    paint(ctx) {
        // Abstract method
    }
}
