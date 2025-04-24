package scoreboard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Scoreboard {
    private final Map<String, Match> matches = new HashMap<>();

    public void startMatch(String homeTeam, String awayTeam) {
        String key = key(homeTeam, awayTeam);
        if (matches.containsKey(key)) {
            throw new IllegalArgumentException(
                    String.format("Match already started: %s", key));
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.put(key, match);
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = getMatch(homeTeam, awayTeam);
        match.updateScore(homeScore, awayScore);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        String key = key(homeTeam, awayTeam);
        if (matches.remove(key) == null) {
            throw new IllegalArgumentException(
                    String.format("Match not found: %s", key));
        }
    }

    public List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Match::getStartTime).reversed())
                .collect(Collectors.toList());
    }

    private Match getMatch(String homeTeam, String awayTeam) {
        String key = key(homeTeam, awayTeam);
        Match match = matches.get(key);
        if (match == null) {
            throw new IllegalArgumentException(
                    String.format("Match not in progress: %s", key));
        }
        return match;
    }

    private String key(String homeTeam, String awayTeam) {
        return String.format("%s vs %s", homeTeam, awayTeam);
    }
}