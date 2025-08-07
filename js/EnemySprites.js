const EnemySprites = {
    createEnemy(typeIndex, imageExtractors, difficulty) {
        // For now, return a generic enemy configuration.
        // In the future, we can load this from a data file.
        const enemy = {
            Health: 10 * difficulty,
            Damage: 5 * difficulty,
            Value: 100,
            DefState: 0,
            State1: 2, // Attack
            State2: 1, // Wait
            State3: 4, // Dive
            Percent1: 100,
            Percent2: 200,
            Percent3: 300,
            WeaponType: 0,
            Smart: false,
            MultiFire: 0,
            StateSpriteInfo: {
                width: 32,
                height: 32,
                DefaultState: 0,
                StateSprites: [] // This would be populated with actual sprite data
            }
        };
        return enemy;
    }
};
