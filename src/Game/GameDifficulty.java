package Game;

public enum GameDifficulty {
        Easy(0),
        Normal(1),
        Hard(2),
        Extreme(3),
        Ultra(4);

        int difficultyOrdinal;

        GameDifficulty (int order) {
                this.difficultyOrdinal = order;
        }
}
