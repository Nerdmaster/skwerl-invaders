const Weapon = {
    // Weapon fire types
    FIRE_NORMAL: 0,
    FIRE_DOUBLE: 1,
    FIRE_W: 2,
    FIRE_LASER: 3,
    FIRE_FLAME: 4,
    FIRE_SHIELD: 5,
    FIRE_EXPLODE: 6,
    FIRE_MIRV: 7,
    FIRE_ICE: 8,
    FIRE_DRAIN: 9,

    weaponArray: [],

    createWeapons() {
        this.weaponArray.push({ name: "Laser", cost: 0, damage: 1, uses: -1, fireTime: 15, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Laser II", cost: 125, damage: 2, uses: -1, fireTime: 20, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Laser III", cost: 625, damage: 5, uses: -1, fireTime: 25, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Laser IV", cost: 1600, damage: 8, uses: -1, fireTime: 25, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Laser V", cost: 2080, damage: 10, uses: -1, fireTime: 30, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Laser VI", cost: 3125, damage: 10, uses: -1, fireTime: 20, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Blaster", cost: 250, damage: 1, uses: -1, fireTime: 10, special: this.FIRE_DOUBLE });
        this.weaponArray.push({ name: "Blaster mk. 2", cost: 650, damage: 2, uses: -1, fireTime: 15, special: this.FIRE_DOUBLE });
        this.weaponArray.push({ name: "Blaster mk. 3", cost: 1125, damage: 3, uses: -1, fireTime: 20, special: this.FIRE_DOUBLE });
        this.weaponArray.push({ name: "Blaster mk. 4", cost: 2500, damage: 5, uses: -1, fireTime: 25, special: this.FIRE_DOUBLE });
        this.weaponArray.push({ name: "Blaster mk. 5", cost: 8000, damage: 8, uses: -1, fireTime: 20, special: this.FIRE_DOUBLE });
        this.weaponArray.push({ name: "L-III Laser", cost: 375, damage: 1, uses: -1, fireTime: 15, special: this.FIRE_W });
        this.weaponArray.push({ name: "L-III Blaster", cost: 1125, damage: 2, uses: -1, fireTime: 20, special: this.FIRE_W });
        this.weaponArray.push({ name: "L-III Phaser", cost: 2025, damage: 3, uses: -1, fireTime: 25, special: this.FIRE_W });
        this.weaponArray.push({ name: "L-III Cannon", cost: 3600, damage: 4, uses: -1, fireTime: 25, special: this.FIRE_W });
        this.weaponArray.push({ name: "L-III Death ray", cost: 10125, damage: 6, uses: -1, fireTime: 20, special: this.FIRE_W });
        this.weaponArray.push({ name: "Annihilator", cost: 360, damage: 6, uses: -1, fireTime: 50, special: this.FIRE_LASER });
        this.weaponArray.push({ name: "Devastator", cost: 2050, damage: 12, uses: -1, fireTime: 35, special: this.FIRE_LASER });
        this.weaponArray.push({ name: "Decimator", cost: 11520, damage: 24, uses: -1, fireTime: 25, special: this.FIRE_LASER });
        this.weaponArray.push({ name: "Freeze Ray", cost: 4000, damage: 30, uses: -1, fireTime: 10, special: this.FIRE_ICE });
        this.weaponArray.push({ name: "Ice Beam", cost: 8000, damage: 60, uses: -1, fireTime: 10, special: this.FIRE_ICE });
        this.weaponArray.push({ name: "Subzero Cannon", cost: 12000, damage: 90, uses: -1, fireTime: 10, special: this.FIRE_ICE });
        this.weaponArray.push({ name: "L-V Laser", cost: 780, damage: 1, uses: -1, fireTime: 20, special: this.FIRE_MIRV });
        this.weaponArray.push({ name: "L-V Blaster", cost: 2500, damage: 2, uses: -1, fireTime: 25, special: this.FIRE_MIRV });
        this.weaponArray.push({ name: "L-V Phaser", cost: 4680, damage: 3, uses: -1, fireTime: 30, special: this.FIRE_MIRV });
        this.weaponArray.push({ name: "L-V Cannon", cost: 10000, damage: 4, uses: -1, fireTime: 25, special: this.FIRE_MIRV });
        this.weaponArray.push({ name: "L-V Death Ray", cost: 15625, damage: 5, uses: -1, fireTime: 25, special: this.FIRE_MIRV });
        this.weaponArray.push({ name: "Flamethrower", cost: 1250, damage: 1, uses: 20, fireTime: 40, special: this.FIRE_FLAME });
        this.weaponArray.push({ name: "Plasma Coil", cost: 5000, damage: 2, uses: 20, fireTime: 40, special: this.FIRE_FLAME });
        this.weaponArray.push({ name: "Disintegrator", cost: 15000, damage: 3, uses: 20, fireTime: 30, special: this.FIRE_FLAME });
        this.weaponArray.push({ name: "Hellfire", cost: 20000, damage: 2, uses: -1, fireTime: 50, special: this.FIRE_FLAME });
        this.weaponArray.push({ name: "Rocket", cost: 900, damage: 6, uses: 10, fireTime: 25, special: this.FIRE_EXPLODE });
        this.weaponArray.push({ name: "Missile", cost: 3750, damage: 10, uses: 15, fireTime: 25, special: this.FIRE_EXPLODE });
        this.weaponArray.push({ name: "Torpedo", cost: 12800, damage: 16, uses: 20, fireTime: 25, special: this.FIRE_EXPLODE });
        this.weaponArray.push({ name: "Photon Cannon", cost: 40000, damage: 16, uses: -1, fireTime: 40, special: this.FIRE_EXPLODE });
        this.weaponArray.push({ name: "Energy Shield", cost: 500, damage: 10, uses: 2, fireTime: 100, special: this.FIRE_SHIELD });
        this.weaponArray.push({ name: "Barrier Shield", cost: 2500, damage: 20, uses: 5, fireTime: 100, special: this.FIRE_SHIELD });
        this.weaponArray.push({ name: "Power Shield", cost: 12500, damage: 50, uses: 10, fireTime: 100, special: this.FIRE_SHIELD });
        this.weaponArray.push({ name: "Protector", cost: 50000, damage: 100, uses: 20, fireTime: 100, special: this.FIRE_SHIELD });
        this.weaponArray.push({ name: "Laser VII", cost: 28125, damage: 15, uses: -1, fireTime: 5, special: this.FIRE_NORMAL });
        this.weaponArray.push({ name: "Supreme Cannon", cost: 80000, damage: 8, uses: -1, fireTime: 5, special: this.FIRE_EXPLODE });
        this.weaponArray.push({ name: "Blaster se", cost: 12500, damage: 5, uses: -1, fireTime: 5, special: this.FIRE_DOUBLE });
        this.weaponArray.push({ name: "L-III Gold", cost: 28125, damage: 5, uses: -1, fireTime: 5, special: this.FIRE_W });
        this.weaponArray.push({ name: "L-V xl", cost: 78125, damage: 5, uses: -1, fireTime: 5, special: this.FIRE_MIRV });
        this.weaponArray.push({ name: "Mammoth Laser", cost: 57600, damage: 64, uses: -1, fireTime: 20, special: this.FIRE_LASER });
    },

    getWeapon(index) {
        return this.weaponArray[index];
    },

    getArraySize() {
        return this.weaponArray.length;
    }
};

// Initialize the weapons
Weapon.createWeapons();
