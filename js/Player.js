class Player extends StatedSprite {
    constructor(appWidth, appHeight, missileManager, shieldGenerator) {
        super(appWidth / 2, appHeight - 80, 32, 32); // Initial position

        this.appWidth = appWidth;
        this.appHeight = appHeight;
        this.missileManager = missileManager;
        this.shieldGenerator = shieldGenerator;

        this.cash = 0;
        this.totalPoints = 0;
        this.currentWeapon = 0;
        this.power = 100;
        this.maxPower = 100;
        this.speed = 5;

        this.weaponUses = new Array(Weapon.getArraySize()).fill(-2);
        this.weaponUses[0] = -1; // Default weapon has infinite uses

        this.goLeft = false;
        this.goRight = false;
        this.goUp = false;
        this.goDown = false;
        this.firing = false;
        this.fireDelay = 0;

        this.spriteDead = false;

        // Player sprite states
        this.initMain(4, 0); // 4 states, default is 0 (normal)
        this.initState(0, 6, 2); // Normal
        this.initState(1, 4, 1); // Firing
        this.initState(2, 6, 2); // Hit
        this.initState(3, 2, 3); // Dying
    }

    loadAnimations(spriteSheet) {
        this.loadImagesFromSpriteSheet(0, spriteSheet, 0, 32, 32); // Normal
        this.loadImagesFromSpriteSheet(1, spriteSheet, 32, 32, 32); // Firing
        this.loadImagesFromSpriteSheet(2, spriteSheet, 64, 32, 32); // Hit
        this.loadImagesFromSpriteSheet(3, spriteSheet, 96, 32, 32); // Dying
    }

    reset() {
        this.cash = 0;
        this.totalPoints = 0;
        this.currentWeapon = 0;
        this.power = 100;
        this.maxPower = 100;
        this.spriteDead = false;

        this.weaponUses = new Array(Weapon.getArraySize()).fill(-2);
        this.weaponUses[0] = -1;

        this.setNewState(0);
        this.enable();
    }

    hit(damage) {
        if (this.active) {
            this.power -= damage;
            this.setNewState(2); // Hit state
            if (this.power <= 0) {
                this.power = 0;
                this.setNewState(3); // Dying state
                this.active = false;
            }
        }
    }

    update() {
        super.update();

        if (this.action === 3) { // Finished dying animation
            this.spriteDead = true;
            this.disable();
            return;
        }

        if (!this.active) return;

        // Movement
        if (this.goLeft) this.x -= this.speed;
        if (this.goRight) this.x += this.speed;
        if (this.goUp) this.y -= this.speed;
        if (this.goDown) this.y += this.speed;

        // Keep player in bounds
        if (this.x < 0) this.x = 0;
        if (this.x > this.appWidth - this.width) this.x = this.appWidth - this.width;
        if (this.y < 280) this.y = 280;
        if (this.y > 320) this.y = 320;

        // Firing
        if (this.fireDelay > 0) {
            this.fireDelay--;
        }

        if (this.firing && this.fireDelay === 0) {
            this.setNewState(1); // Firing state
            const weapon = Weapon.getWeapon(this.currentWeapon);
            this.fireDelay = weapon.fireTime;

            // Actually fire the weapon
            this.missileManager.launchFriendlyMissile(this.x + this.width / 2, this.y, weapon.damage, weapon);

            if (this.weaponUses[this.currentWeapon] > 0) {
                this.weaponUses[this.currentWeapon]--;
            }
        }

        if(this.action === 1) { // Finished firing animation
            this.setNewState(0);
        }

        if(this.action === 1) { // Finished firing animation
            this.setNewState(0);
        }
    }

    nextWeapon() {
        this.currentWeapon++;
        if (this.currentWeapon >= Weapon.getArraySize()) {
            this.currentWeapon = 0;
        }
        // For now, we have all weapons. In the future, we'll check if the player owns the weapon.
    }

    paint(ctx) {
        super.paint(ctx);

        // Draw UI
        ctx.fillStyle = 'white';
        ctx.font = '16px sans-serif';

        // Score
        ctx.fillText(`Score: ${this.totalPoints}`, 10, 20);

        // Cash
        ctx.fillText(`Cash: $${this.cash}`, 10, 40);

        // Health
        ctx.fillText(`Health: ${this.power}/${this.maxPower}`, this.appWidth - 150, 20);

        // Current Weapon
        const weapon = Weapon.getWeapon(this.currentWeapon);
        const uses = this.weaponUses[this.currentWeapon] === -1 ? '∞' : this.weaponUses[this.currentWeapon];
        ctx.fillText(`Weapon: ${weapon.name} (${uses})`, this.appWidth - 150, 40);
    }
}
