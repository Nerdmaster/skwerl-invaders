class Floater {
    constructor(x, y, text, xvel, yvel, gravity, lifetime) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.xvel = xvel;
        this.yvel = yvel;
        this.gravity = gravity;
        this.lifetime = lifetime;
        this.done = false;
    }

    update() {
        this.x += this.xvel;
        this.y += this.yvel;
        this.yvel += this.gravity;
        this.lifetime--;
        if (this.lifetime <= 0) {
            this.done = true;
        }
    }

    paint(ctx) {
        if (this.done) return;
        ctx.fillStyle = 'white';
        ctx.font = '12px sans-serif';
        ctx.fillText(this.text, this.x, this.y);
    }
}
