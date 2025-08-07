class Missile extends StatedSprite {
    constructor(x, y, xvel, yvel, damage, friendly, type, spriteSheet) {
        let width = 10, height = 10;
        // Adjust width and height based on type
        super(x, y, width, height);

        this.xvel = xvel;
        this.yvel = yvel;
        this.damage = damage;
        this.friendly = friendly;
        this.type = type;

        this.loadAnimations(spriteSheet);
        this.setNewState(type);
    }

    loadAnimations(spriteSheet) {
        // State 0: Friendly Ball
        this.initState(0, 2, 4);
        this.loadImagesFromSpriteSheet(0, spriteSheet, 144, 10, 14);
        // State 1: Enemy Ball
        this.initState(1, 2, 4);
        this.loadImagesFromSpriteSheet(1, spriteSheet, 160, 6, 9);
        // State 2: Ice Beam
        this.initState(2, 4, 0);
        this.loadImagesFromSpriteSheet(2, spriteSheet, 192, 16, 32);
        // State 3: Ring
        this.initState(3, 6, 2);
        this.loadImagesFromSpriteSheet(3, spriteSheet, 240, 8, 8);
        // State 4: Fire
        this.initState(4, 4, 1);
        this.loadImagesFromSpriteSheet(4, spriteSheet, 272, 32, 32);
        // State 5: Fireball
        this.initState(5, 3, 1);
        this.loadImagesFromSpriteSheet(5, spriteSheet, 304, 16, 16);
    }

    update() {
        super.update();
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
