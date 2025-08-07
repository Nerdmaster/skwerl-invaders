class Boss extends StatedSprite {
    constructor(level, difficulty, spriteSheet, missileManager, player) {
        let width = 164;
        let height = 68;
        let x = 174;
        let y = 30;

        super(x, y, width, height);

        this.level = level;
        this.difficulty = difficulty;
        this.spriteSheet = spriteSheet;
        this.missileManager = missileManager;
        this.player = player;

        this.health = 40 * (level / 5) * difficulty;
        this.maxHealth = this.health;

        this.initMain(5, 0); // 5 sprite states, default 0 (move)
        this.initState(0, 6, 2); // Move
        this.initState(1, 8, 2); // Pre-attack
        this.initState(2, 1, 4); // Attack - Not used in spaceship boss
        this.initState(3, 2, 3); // Dying
        this.initState(4, 1, -1); // Dead

        this.loadAnimations(spriteSheet);
    }

    loadAnimations(spriteSheet) {
        this.loadImagesFromSpriteSheet(0, spriteSheet, 0, 164, 68);
        this.loadImagesFromSpriteSheet(1, spriteSheet, 68, 164, 68);
        this.loadImagesFromSpriteSheet(3, spriteSheet, 136, 164, 68);
    }

    update() {
        super.update();
        // Boss AI and movement patterns will be implemented here
    }

    paint(ctx) {
        super.paint(ctx);
        if (this.visible) {
            // Draw health bar
            const x = this.x + this.width / 2 - 51;
            const y = this.y + this.height + 10;
            ctx.fillStyle = 'yellow';
            ctx.drawRect(x, y, 101, 5);
            ctx.fillStyle = 'red';
            ctx.fillRect(x + 1, y + 1, (this.health / this.maxHealth) * 100, 4);
        }
    }
}
