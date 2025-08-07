const Utils = {
    // We don't need to initialize this like in Java, but we can have a config object
    config: {
        ctx: null,
        canvas: null,
    },

    init(canvas, ctx) {
        this.config.canvas = canvas;
        this.config.ctx = ctx;
    },

    getRandom(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + min;
    },

    centerString(text, y, color = 'white', font = '16px sans-serif') {
        const { ctx, canvas } = this.config;
        ctx.fillStyle = color;
        ctx.font = font;
        const textWidth = ctx.measureText(text).width;
        const x = (canvas.width - textWidth) / 2;
        ctx.fillText(text, x, y);
    },

    clearCanvas() {
        const { ctx, canvas } = this.config;
        ctx.clearRect(0, 0, canvas.width, canvas.height);
    },

    // This is a simplified version of the word filter.
    // The original logic was a bit convoluted.
    filterWords(text, badWords) {
        const upperText = text.toUpperCase();
        for (const word of badWords) {
            if (upperText.includes(word)) {
                return true;
            }
        }
        return false;
    },

    // The AN function is not critical for the game logic, so I will omit it for now.
    // The ShowString function is also omitted as it's tied to the Java Applet's repaint lifecycle.
    // We will handle rendering in the main game loop.
};

// In game.js, we will call Utils.init(canvas, ctx);
