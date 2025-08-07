class GifButton {
    constructor(x, y, width, height, text = '', onClick = null) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.onClick = onClick;
        this.enabled = true;
    }

    paint(ctx) {
        if (!this.enabled) return;

        ctx.fillStyle = 'gray';
        ctx.fillRect(this.x, this.y, this.width, this.height);

        ctx.fillStyle = 'white';
        ctx.font = '14px sans-serif';
        const textWidth = ctx.measureText(this.text).width;
        ctx.fillText(this.text, this.x + (this.width - textWidth) / 2, this.y + this.height / 2 + 5);
    }

    checkClick(event) {
        if (!this.enabled) return false;

        const rect = event.target.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY - rect.top;

        if (x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height) {
            if (this.onClick) {
                this.onClick();
            }
            return true;
        }
        return false;
    }

    enable() {
        this.enabled = true;
    }

    disable() {
        this.enabled = false;
    }
}
