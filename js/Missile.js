class Missile {
    constructor(x, y, xvel, yvel, damage, friendly, type) {
        this.x = x;
        this.y = y;
        this.xvel = xvel;
        this.yvel = yvel;
        this.damage = damage;
        this.friendly = friendly;
        this.type = type;

        this.width = 10; // Default width
        this.height = 10; // Default height

        this.active = true;
    }

    update() {
        this.x += this.xvel;
        this.y += this.yvel;

        // Deactivate missile if it goes off-screen
        if (this.y < 0 || this.y > 448) { // Assuming canvas height of 448
            this.active = false;
        }
    }

    paint(ctx) {
        if (!this.active) return;

        ctx.fillStyle = this.friendly ? 'cyan' : 'red';

        // Simple line for now, will add sprites later
        ctx.beginPath();
        ctx.moveTo(this.x, this.y);
        ctx.lineTo(this.x - this.xvel, this.y - this.yvel);
        ctx.strokeStyle = ctx.fillStyle;
        ctx.lineWidth = 2;
        ctx.stroke();
    }

    checkCollision(sprite) {
        if (!this.active || !sprite.active) return false;

        return (
            this.x < sprite.x + sprite.width &&
            this.x + this.width > sprite.x &&
            this.y < sprite.y + sprite.height &&
            this.y + this.height > sprite.y
        );
    }
}
