class Game {
    constructor(canvas) {
        this.canvas = canvas;
        this.ctx = canvas.getContext('2d');
        this.width = canvas.width;
        this.height = canvas.height;

        this.gameState = 'LOADING'; // Initial game state

        this.assetLoader = new AssetLoader();
        this.soundManager = new SoundManager(this.assetLoader);
        this.missileManager = new MissileManager(this.soundManager);
        this.player = new Player(this.width, this.height, this.missileManager, null); // ShieldGenerator is null for now
        this.enemyManager = new EnemyManager(this.missileManager, this.player);
        this.missileManager.setEnemyAndPlayer(this.enemyManager, this.player);

        this.weaponsScreen = null;

        this.introText = [
            "It was a time of chaos, a time of war. Acorn and nut reserves were depleted",
            "all over the galaxy. Earthlings were forced to fight hordes of rabid extra-",
            "terrestrial squirrels in order to preserve their own rations. Rappy, once a",
            "kind and generous squirrel, was unable to control his need for good acorns.",
            "Rappy assembled an army of squirrels and other beasts to fight back against the",
            "earthlings. With his friends and their deadly arsenal of unripe green acorns,",
            "they were unstoppable. Rumors tore apart nations. Governments allied with the",
            "squirrels and began secret projects to create evil squirrel-human hybrids. These",
            "unspeakable events brought you to investigate.",
            "After discovering the horrifying truth of the situation you've realized that you,",
            "and ONLY you, will be able to destroy the alien squirrels and reclaim earth..."
        ];
        this.introScrollY = this.height;

        this.background = null;
        this.backgroundY = 0;

        this.initInput();
    }

    async start() {
        await this.assetLoader.loadAll();
        this.soundManager.playMusic('intro');
        this.missileManager.setSpriteSheet(this.assetLoader.getImage('spritestrip'));

        // Pass the loaded images to the player and enemy classes
        this.player.loadAnimations(this.assetLoader.getImage('spritestrip'));
        // We'll need a way to set images for different enemy types
        // For now, all enemies will have the same image
        this.enemyManager.setEnemyImage(this.assetLoader.getImage('enemystrip0'));

        this.background = this.assetLoader.getImage('strip');

        this.weaponsScreen = new WeaponsScreen(this.player, this.assetLoader.getImage('weaponstrip'), this.assetLoader.getImage('buttons'), this.assetLoader.getImage('buttons2'));

        this.enemyManager.createEnemies(1, 1);
        this.gameState = 'PLAYING';
        this.gameLoop();
    }

    initInput() {
        window.addEventListener('keydown', (e) => {
            if (this.gameState === 'INTRO') {
                this.gameState = 'MAIN_MENU';
                this.soundManager.playMusic('theme');
                return;
            }
            if (e.key === 'ArrowLeft') this.player.goLeft = true;
            if (e.key === 'ArrowRight') this.player.goRight = true;
            if (e.key === 'ArrowUp') this.player.goUp = true;
            if (e.key === 'ArrowDown') this.player.goDown = true;
            if (e.key === ' ') this.player.firing = true;
            if (e.key === 'c') this.player.nextWeapon();
            if (e.key === 'w') {
                if (this.gameState === 'PLAYING') this.gameState = 'WEAPONS_SCREEN';
                else if (this.gameState === 'WEAPONS_SCREEN') this.gameState = 'PLAYING';
            }
        });

        window.addEventListener('keyup', (e) => {
            if (e.key === 'ArrowLeft') this.player.goLeft = false;
            if (e.key === 'ArrowRight') this.player.goRight = false;
            if (e.key === 'ArrowUp') this.player.goUp = false;
            if (e.key === 'ArrowDown') this.player.goDown = false;
            if (e.key === ' ') this.player.firing = false;
        });

        this.canvas.addEventListener('click', (e) => {
            if (this.gameState === 'WEAPONS_SCREEN') {
                this.weaponsScreen.handleMouseClick(e);
            }
        });
    }

    update() {
        if (this.gameState === 'INTRO') {
            this.introScrollY -= 1;
            if (this.introScrollY < -this.introText.length * 25) {
                this.gameState = 'MAIN_MENU';
                this.soundManager.playMusic('theme');
            }
        } else if (this.gameState === 'WEAPONS_SCREEN') {
            this.weaponsScreen.update();
        } else if (this.gameState === 'PLAYING') {
            this.backgroundY = (this.backgroundY + 1) % this.background.height;
            this.player.update();
            this.missileManager.update();
            this.enemyManager.update();

            if(this.player.spriteDead && this.gameState !== 'GAME_OVER'){
                this.gameState = 'GAME_OVER';
                this.soundManager.stopMusic();
                const name = prompt('Game Over! Enter your name:', 'Player');
                if (name) {
                    HighScore.addScore(name, this.player.totalPoints);
                }
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

        if (this.gameState === 'INTRO') {
            this.ctx.fillStyle = 'white';
            this.ctx.font = '20px "Courier New", Courier, monospace';
            this.introText.forEach((line, index) => {
                const y = this.introScrollY + index * 25;
                this.ctx.fillText(line, this.width / 2 - this.ctx.measureText(line).width / 2, y);
            });
            return;
        }

        if (this.gameState === 'MAIN_MENU') {
            this.ctx.fillStyle = 'white';
            this.ctx.font = '30px sans-serif';
            this.ctx.fillText('Main Menu', this.width / 2 - 80, 100);
            // Buttons will be drawn here later
            this.ctx.font = '20px sans-serif';
            this.ctx.fillText('Press "S" to Start', this.width / 2 - 80, 200);
            // A temporary way to start the game
            window.addEventListener('keydown', (e) => {
                if (this.gameState === 'MAIN_MENU' && e.key === 's') {
                    this.gameState = 'PLAYING';
                }
            }, { once: true });
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

        if (this.gameState === 'WEAPONS_SCREEN') {
            this.weaponsScreen.paint(this.ctx);
        }

        if (this.gameState === 'PLAYING') {
            if (this.background) {
                this.ctx.drawImage(this.background, 0, this.backgroundY, this.width, this.height, 0, 0, this.width, this.height);
                this.ctx.drawImage(this.background, 0, this.backgroundY - this.background.height, this.width, this.height, 0, 0, this.width, this.height);
            }
            this.player.paint(this.ctx);
            this.missileManager.paint(this.ctx);
            this.enemyManager.paint(this.ctx);
        }
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
