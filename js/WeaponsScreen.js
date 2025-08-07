class WeaponsScreen {
    constructor(player, weaponsScreenBack, buttons, buttons2) {
        this.player = player;
        this.weaponsScreenBack = weaponsScreenBack;
        this.buttons = buttons;
        this.buttons2 = buttons2;
        this.done = false;

        this.weaponStart = 0;
        this.weaponsPerPage = 6;

        this.btnNext = new GifButton(416, 350, 64, 32, 'Next', () => this.nextPage());
        this.btnPrev = new GifButton(32, 350, 64, 32, 'Prev', () => this.prevPage());

        this.buyButtons = [];
        for (let i = 0; i < this.weaponsPerPage; i++) {
            const y = 96 + i * 32;
            this.buyButtons.push(new GifButton(352, y, 64, 32, 'Buy', () => this.buyWeapon(i)));
        }
    }

    update() {
        // No update logic needed for this simple version
    }

    nextPage() {
        if (this.weaponStart + this.weaponsPerPage < Weapon.getArraySize()) {
            this.weaponStart += this.weaponsPerPage;
        }
    }

    prevPage() {
        this.weaponStart -= this.weaponsPerPage;
        if (this.weaponStart < 0) {
            this.weaponStart = 0;
        }
    }

    buyWeapon(index) {
        const weaponIndex = this.weaponStart + index;
        const weapon = Weapon.getWeapon(weaponIndex);
        if (this.player.cash >= weapon.cost) {
            this.player.cash -= weapon.cost;
            this.player.weaponUses[weaponIndex] = weapon.uses;
        }
    }

    paint(ctx) {
        // Draw the background
        if (this.weaponsScreenBack) {
            ctx.drawImage(this.weaponsScreenBack, 0, 0);
        }

        // Draw player cash
        ctx.fillStyle = 'white';
        ctx.font = '18px "Courier New", Courier, monospace';
        ctx.fillText(`Cash: $${this.player.cash}`, 380, 40);

        // Draw weapon list
        for (let i = 0; i < this.weaponsPerPage; i++) {
            const weaponIndex = this.weaponStart + i;
            if (weaponIndex >= Weapon.getArraySize()) break;

            const weapon = Weapon.getWeapon(weaponIndex);
            const y = 118 + i * 32;

            ctx.fillStyle = 'cyan';
            ctx.fillText(weapon.name, 64, y);
            ctx.fillText(`$${weapon.cost}`, 208, y);

            const uses = this.player.weaponUses[weaponIndex];
            let usesText = 'N/A';
            if (uses === -1) usesText = 'Inf.';
            else if (uses > -2) usesText = uses;
            ctx.fillText(usesText, 288, y);
        }

        // Draw buttons
        this.btnNext.paint(ctx);
        this.btnPrev.paint(ctx);
        for(const btn of this.buyButtons) {
            btn.paint(ctx);
        }
    }

    handleMouseClick(event) {
        this.btnNext.checkClick(event);
        this.btnPrev.checkClick(event);
        for(const btn of this.buyButtons) {
            btn.checkClick(event);
        }
    }
}
