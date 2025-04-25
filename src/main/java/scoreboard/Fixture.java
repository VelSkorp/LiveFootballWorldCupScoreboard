package scoreboard;

import java.util.Objects;

public class Fixture {
    private final String homeTeam;
    private final String awayTeam;

    public Fixture(String homeTeam, String awayTeam) {
        this.homeTeam = Objects.requireNonNull(homeTeam, "Home team must not be null");
        this.awayTeam = Objects.requireNonNull(awayTeam, "Away team must not be null");
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home and away teams must differ");
        }
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fixture)) return false;
        Fixture f = (Fixture) o;
        return homeTeam.equals(f.homeTeam) && awayTeam.equals(f.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam);
    }

    @Override
    public String toString() {
        return homeTeam + " vs " + awayTeam;
    }
}
