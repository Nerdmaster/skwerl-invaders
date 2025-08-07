class EnemyManager {
    constructor(missileManager, player) {
        this.enemies = [];
        this.floaters = [];
        this.missileManager = missileManager;
        this.player = player;

        this.allOffX = 0;
        this.allOffY = 0;
        this.accelX = 0;
        this.directionX = 1;
        this.enemiesLeft = 0;
        this.enemyImage = null;
        this.boss = null;
    }

    setEnemyImage(image) {
        this.enemyImage = image;
    }

    createEnemies(level, difficulty) {
        if (level % 5 === 0) {
            this.boss = new Boss(level, difficulty, this.enemyImage, this.missileManager, this.player);
            this.enemies = [];
            this.enemiesLeft = 1;
        } else {
            this.boss = null;
            this.enemies = [];
            this.enemiesLeft = 20;

            for (let i = 0; i < 5; i++) {
            for (let j = 0; j < 4; j++) {
                const enemyType = Math.floor(level / 2); // Simple logic for now
                const enemyTemplate = EnemySprites.createEnemy(enemyType, null, difficulty);

                const x = 56 + i * 32 + i * 39;
                const y = 32 + j * 32 + j * 20;

                const enemy = new Enemy(x, y, enemyTemplate, this.missileManager, this.player);
                enemy.loadAnimations(this.enemyImage);
                this.enemies.push(enemy);
            }
        }
    }

    createFloater(x, y, value) {
        const cash = Math.floor(value * (this.player.cashMult / 100));
        const points = value - cash;

        const floater = new Floater(x, y, `$${cash}`, -2, 2, -1, 30);
        this.floaters.push(floater);

        this.player.cash += cash;
        this.player.totalPoints += points;
    }

    update() {
        // Update horizontal movement of the grid
        this.accelX += this.directionX;
        if (this.accelX > 2) this.accelX = 2;
        if (this.accelX < -2) this.accelX = -2;

        this.allOffX += this.accelX;
        if (this.allOffX >= 110 && this.directionX > 0) this.directionX = -1;
        if (this.allOffX <= -30 && this.directionX < 0) this.directionX = 1;

        // Update enemies or boss
        if (this.boss) {
            this.boss.update();
        } else {
            for (const enemy of this.enemies) {
                enemy.defx += this.allOffX;
                enemy.update();
            enemy.defx -= this.allOffX;

            if(enemy.action === 5){ //Dying
                this.createFloater(enemy.x, enemy.y, enemy.value);
                this.enemiesLeft --;
                enemy.action = -1; // Prevent re-triggering
            }
        }

        // Update floaters
        for(let i = this.floaters.length -1; i >= 0; i--){
            const floater = this.floaters[i];
            floater.update();
            if(floater.done){
                this.floaters.splice(i, 1);
            }
        }
    }

    paint(ctx) {
        if (this.boss) {
            this.boss.paint(ctx);
        } else {
            for (const enemy of this.enemies) {
                enemy.paint(ctx);
            }
        }
        for (const floater of this.floaters) {
            floater.paint(ctx);
        }
    }
}
