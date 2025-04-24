package scoreboard;

import java.time.Instant;
import java.util.Objects;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private  int homeScore;
    private  int awayScore;
    private  final Instant startTime;

    public Match (String homeTeam, String awayTeam) {
        this.homeTeam = Objects.requireNonNull(homeTeam, "Home team must not be null");
        this.awayTeam = Objects.requireNonNull(awayTeam, "Away team must not be null");
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = Instant.now();
    }

    public String getHomeTeam() { return homeTeam; }
    public String getAwayTeam() { return awayTeam; }
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
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam,awayScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return homeTeam.equals(match.homeTeam) && awayTeam.equals(match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }
}