class MissileManager {
    constructor(soundManager) {
        this.missiles = [];
        this.enemyManager = null;
        this.player = null;
        this.spriteSheet = null;
        this.soundManager = soundManager;
    }

    setSpriteSheet(spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    setEnemyAndPlayer(enemyManager, player){
        this.enemyManager = enemyManager;
        this.player = player;
    }

    launchFriendlyMissile(x, y, damage, weapon) {
        this.soundManager.playSound('laser1');
        const yvel = -5;
        let missileType = 0; // Default to friendly ball

        switch (weapon.special) {
            case Weapon.FIRE_ICE: missileType = 2; break;
            case Weapon.FIRE_FLAME: missileType = 4; break;
            case Weapon.FIRE_EXPLODE: missileType = 5; break;
            case Weapon.FIRE_MIRV: missileType = 5; break;
        }

        switch (weapon.special) {
            case Weapon.FIRE_NORMAL:
            case Weapon.FIRE_LASER:
            case Weapon.FIRE_ICE:
            case Weapon.FIRE_FLAME:
            case Weapon.FIRE_EXPLODE:
                this.missiles.push(new Missile(x, y, 0, yvel, damage, true, missileType, this.spriteSheet));
                break;
            case Weapon.FIRE_DOUBLE:
                this.missiles.push(new Missile(x - 5, y, 0, yvel, damage, true, missileType, this.spriteSheet));
                this.missiles.push(new Missile(x + 5, y, 0, yvel, damage, true, missileType, this.spriteSheet));
                break;
            case Weapon.FIRE_W:
                this.missiles.push(new Missile(x, y, 0, yvel, damage, true, missileType, this.spriteSheet));
                this.missiles.push(new Missile(x - 10, y, -1, yvel, damage, true, missileType, this.spriteSheet));
                this.missiles.push(new Missile(x + 10, y, 1, yvel, damage, true, missileType, this.spriteSheet));
                break;
            // Other weapon types will be implemented later
        }
    }

    launchEnemyMissile(x, y, damage, type, targetX, targetY) {
        // Simple aiming logic
        const dx = targetX - x;
        const dy = targetY - y;
        const dist = Math.sqrt(dx * dx + dy * dy);
        const yvel = (dy / dist) * 3; // Slower than player missiles
        const xvel = (dx / dist) * 3;

        const missileType = 1; // Enemy ball

        this.missiles.push(new Missile(x, y, xvel, yvel, damage, false, missileType, this.spriteSheet));
    }

    update() {
        for (let i = this.missiles.length - 1; i >= 0; i--) {
            const missile = this.missiles[i];
            missile.update();

            if (!missile.active) {
                this.missiles.splice(i, 1);
                continue;
            }

            // Collision detection
            if(missile.friendly){
                // Check against all enemies
                for(const enemy of this.enemyManager.enemies){
                    if(missile.checkCollision(enemy)){
                        enemy.hit(missile.damage);
                        missile.active = false;
                        this.soundManager.playSound('explosion');
                        break; // Missile can only hit one enemy
                    }
                }
            } else {
                // Check against player
                if(missile.checkCollision(this.player)){
                    this.player.hit(missile.damage);
                    missile.active = false;
                    this.soundManager.playSound('explosion');
                }
            }
        }
    }

    paint(ctx) {
        for (const missile of this.missiles) {
            missile.paint(ctx);
        }
    }

    clearMissiles() {
        this.missiles = [];
    }
}
