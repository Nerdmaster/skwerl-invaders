class Enemy extends StatedSprite {
    constructor(x, y, type, missileManager, player) {
        super(x, y, 32, 32); // Assuming standard enemy size for now

        this.missileManager = missileManager;
        this.player = player;

        // These properties will be set based on the enemy type
        this.health = 10;
        this.maxHealth = 10;
        this.damage = 5;
        this.value = 100; // Points/cash
        this.weaponType = 0;
        this.smart = false; // Does it aim at the player?

        // Enemy-specific state machine
        this.initMain(8, 0); // 8 states, default 0 (dance)
        this.initState(0, 4, 5); // Dance
        this.initState(1, 1, 10); // Wait
        this.initState(2, 8, 3); // Attack
        this.initState(3, 4, 3); // Hurt
        this.initState(4, 4, 3); // Dive
        this.initState(5, 5, 3); // Dying
        this.initState(6, 1, 1); // Dead
        this.initState(7, 1, 1); // Frozen

        this.defx = x;
        this.defy = y;
    }

    loadAnimations(spriteSheet) {
        // These are just placeholders, the actual y-coordinates will depend on the enemy type
        this.loadImagesFromSpriteSheet(0, spriteSheet, 0, 32, 32); // Dance
        this.loadImagesFromSpriteSheet(1, spriteSheet, 32, 32, 32); // Wait
        this.loadImagesFromSpriteSheet(2, spriteSheet, 64, 32, 32); // Attack
        this.loadImagesFromSpriteSheet(3, spriteSheet, 96, 32, 32); // Hurt
        this.loadImagesFromSpriteSheet(4, spriteSheet, 128, 32, 32); // Dive
        this.loadImagesFromSpriteSheet(5, spriteSheet, 160, 32, 32); // Dying
    }

    hit(damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.setNewState(5); // Dying
        } else {
            this.setNewState(3); // Hurt
        }
    }

    update() {
        super.update();

        if (!this.active) return;

        if (this.action === 5) { // Finished dying animation
            this.active = false;
            this.visible = false;
            // Here we would add a "floater" for the score
            // And decrement the enemy count
            return;
        }

        // Basic AI: randomly decide to attack
        if (this.currentState === 0 && Math.random() < 0.01) {
            this.setNewState(2); // Attack
        }

        // When attacking, fire a missile at a certain frame
        if(this.currentState === 2 && this.currentFrame === 5 && this.frameCount === 0) {
            let targetX = this.x + 16;
            let targetY = 500; // Fire straight down
            if(this.smart){
                targetX = this.player.x + 16;
                targetY = this.player.y + 16;
            }
            this.missileManager.launchEnemyMissile(this.x + 16, this.y + 36, this.damage, this.weaponType, targetX, targetY);
        }

        if(this.action === 2 || this.action === 3){ // Finished attacking or hurt animation
            this.setNewState(0); // Return to dance
        }
    }

    paint(ctx) {
        super.paint(ctx);

        if (this.visible) {
            // Draw health bar
            const x = this.x + this.width / 2 - 10;
            const y = this.y + this.height + 4;
            ctx.fillStyle = 'yellow';
            ctx.fillRect(x, y, 21, 4);
            ctx.fillStyle = 'red';
            ctx.fillRect(x + 1, y + 1, (this.health / this.maxHealth) * 20, 3);
        }
    }
}
