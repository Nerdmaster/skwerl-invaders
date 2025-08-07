class Game {
    constructor(canvas) {
        this.canvas = canvas;
        this.ctx = canvas.getContext('2d');
        this.width = canvas.width;
        this.height = canvas.height;

        this.gameState = 'LOADING'; // Initial game state

        this.assetLoader = new AssetLoader();
        this.missileManager = new MissileManager();
        this.player = new Player(this.width, this.height, this.missileManager, null); // ShieldGenerator is null for now
        this.enemyManager = new EnemyManager(this.missileManager, this.player);
        this.missileManager.setEnemyAndPlayer(this.enemyManager, this.player);

        this.initInput();
    }

    async start() {
        await this.assetLoader.loadImages();

        // Pass the loaded images to the player and enemy classes
        this.player.setImage(this.assetLoader.getImage('player'));
        // We'll need a way to set images for different enemy types
        // For now, all enemies will have the same image
        this.enemyManager.setEnemyImage(this.assetLoader.getImage('enemy'));

        this.enemyManager.createEnemies(1, 1);
        this.gameState = 'PLAYING';
        this.gameLoop();
    }

    initInput() {
        window.addEventListener('keydown', (e) => {
            if (e.key === 'ArrowLeft') this.player.goLeft = true;
            if (e.key === 'ArrowRight') this.player.goRight = true;
            if (e.key === 'ArrowUp') this.player.goUp = true;
            if (e.key === 'ArrowDown') this.player.goDown = true;
            if (e.key === ' ') this.player.firing = true;
            if (e.key === 'c') this.player.nextWeapon();
        });

        window.addEventListener('keyup', (e) => {
            if (e.key === 'ArrowLeft') this.player.goLeft = false;
            if (e.key === 'ArrowRight') this.player.goRight = false;
            if (e.key === 'ArrowUp') this.player.goUp = false;
            if (e.key === 'ArrowDown') this.player.goDown = false;
            if (e.key === ' ') this.player.firing = false;
        });
    }

    start() {
        this.enemyManager.createEnemies(1, 1);
        this.gameState = 'PLAYING';
        this.gameLoop();
    }

    update() {
        if (this.gameState !== 'PLAYING') return;

        this.player.update();
        this.missileManager.update();
        this.enemyManager.update();

        if(this.player.spriteDead && this.gameState !== 'GAME_OVER'){
            this.gameState = 'GAME_OVER';
            const name = prompt('Game Over! Enter your name:', 'Player');
            if (name) {
                HighScore.addScore(name, this.player.totalPoints);
            }
        }
    }

    paint() {
        this.ctx.fillStyle = 'black';
        this.ctx.fillRect(0, 0, this.width, this.height);

        if (this.gameState === 'LOADING') {
            this.ctx.fillStyle = 'white';
            this.ctx.font = '20px sans-serif';
            this.ctx.fillText('Loading...', this.width / 2 - 50, this.height / 2);
            return;
        }

        if (this.gameState === 'GAME_OVER') {
            this.ctx.fillStyle = 'white';
            this.ctx.font = '40px sans-serif';
            this.ctx.fillText('GAME OVER', this.width / 2 - 120, this.height / 2 - 100);

            this.ctx.font = '20px sans-serif';
            this.ctx.fillText('High Scores:', this.width / 2 - 60, this.height / 2 - 50);

            const scores = HighScore.getScores();
            scores.forEach((score, index) => {
                this.ctx.fillText(`${index + 1}. ${score.name}: ${score.score}`, this.width / 2 - 100, this.height / 2 - 20 + index * 25);
            });

            return;
        }

        this.player.paint(this.ctx);
        this.missileManager.paint(this.ctx);
        this.enemyManager.paint(this.ctx);
    }

    gameLoop() {
        this.update();
        this.paint();
        requestAnimationFrame(() => this.gameLoop());
    }
}

const canvas = document.getElementById('gameCanvas');
const game = new Game(canvas);
(async () => {
    await game.start();
})();
