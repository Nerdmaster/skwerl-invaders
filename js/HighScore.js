const HighScore = {
    getScores() {
        const scores = localStorage.getItem('squirrelInvadersHighScores');
        return scores ? JSON.parse(scores) : [];
    },

    addScore(name, score) {
        const scores = this.getScores();
        scores.push({ name, score });
        scores.sort((a, b) => b.score - a.score);
        // Keep top 10 scores
        scores.splice(10);
        localStorage.setItem('squirrelInvadersHighScores', JSON.stringify(scores));
    }
};
