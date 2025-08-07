class SoundManager {
    constructor(assetLoader) {
        this.assetLoader = assetLoader;
        this.currentMusic = null;
    }

    playMusic(key, loop = true) {
        if (this.currentMusic) {
            this.currentMusic.pause();
        }
        const audio = this.assetLoader.getSound(key);
        if (audio) {
            audio.loop = loop;
            audio.play();
            this.currentMusic = audio;
        }
    }

    playSound(key) {
        const audio = this.assetLoader.getSound(key);
        if (audio) {
            audio.currentTime = 0;
            audio.play();
        }
    }

    stopMusic() {
        if (this.currentMusic) {
            this.currentMusic.pause();
            this.currentMusic.currentTime = 0;
        }
    }
}
