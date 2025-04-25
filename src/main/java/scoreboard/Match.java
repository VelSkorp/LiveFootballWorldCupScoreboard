package scoreboard;

import java.time.Instant;
import java.util.Objects;

public class Match {
    private final Fixture fixture;
    private  int homeScore;
    private  int awayScore;
    private  final Instant startTime;

    public Match (Fixture fixture) {
        this.fixture = Objects.requireNonNull(fixture, "Fixture must not be null");
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = Instant.now();
    }

    public int getHomeScore() { return homeScore; }
    public int getAwayScore() { return awayScore; }
    public Instant getStartTime() { return startTime; }
    public int getTotalScore() { return homeScore + awayScore; }

    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores must be non-negative");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", fixture.getHomeTeam(), homeScore, fixture.getAwayTeam(),awayScore);
    }
}
