class AssetLoader {
    constructor() {
        this.images = {};
        this.sounds = {};
        this.imageUrls = {
            player: 'assets/player.png',
            enemy: 'assets/enemy.png',
            missile: 'assets/missile.png'
        };
        this.soundUrls = {}; // Add sound urls here
    }

    loadImages() {
        const promises = [];
        for (const key in this.imageUrls) {
            const url = this.imageUrls[key];
            const promise = new Promise((resolve, reject) => {
                const img = new Image();
                img.onload = () => {
                    this.images[key] = img;
                    resolve(img);
                };
                img.onerror = () => {
                    console.error(`Failed to load image: ${url}`);
                    // Resolve with a placeholder if image fails to load
                    this.images[key] = this.createPlaceholder(32, 32, 'red');
                    resolve(this.images[key]);
                };
                img.src = url;
            });
            promises.push(promise);
        }
        return Promise.all(promises);
    }

    createPlaceholder(width, height, color) {
        const canvas = document.createElement('canvas');
        canvas.width = width;
        canvas.height = height;
        const ctx = canvas.getContext('2d');
        ctx.fillStyle = color;
        ctx.fillRect(0, 0, width, height);
        return canvas;
    }

    getImage(key) {
        return this.images[key];
    }
}
