package scoreboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scoreboard.exception.MatchAlreadyExistsException;
import scoreboard.exception.MatchNotFoundException;

import java.util.*;

public class InMemoryScoreboard implements Scoreboard {
    private static final Logger logger = LoggerFactory.getLogger(InMemoryScoreboard.class);
    private final Map<Fixture, Match> matches = new HashMap<>();

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        Fixture fixture = new Fixture(homeTeam, awayTeam);
        if (matches.containsKey(fixture)) {
            logger.warn("Match already exists: {}", fixture);
            throw new MatchAlreadyExistsException("Match already started: " + fixture);
        }
        Match match = new Match(fixture);
        matches.put(fixture, match);
        logger.info("Match started: {}", fixture);
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Fixture fixture = new Fixture(homeTeam, awayTeam);
        Match match = matches.get(fixture);
        if (match == null) {
            logger.error("Cannot update score, match not found: {}", fixture);
            throw new MatchNotFoundException("Match not in progress: " + fixture);
        }
        match.updateScore(homeScore, awayScore);
        logger.info("Score updated for {}: {}-{}", fixture, homeScore, awayScore);
    }

    @Override
    public void finishMatch(String homeTeam, String awayTeam) {
        Fixture fixture = new Fixture(homeTeam, awayTeam);
        if (matches.remove(fixture) == null) {
            logger.error("Cannot finish, match not found: {}", fixture);
            throw new MatchNotFoundException("Match not found: " + fixture);
        }
        logger.info("Match finished: {}", fixture);
    }

    @Override
    public List<Match> getSummary() {
        Comparator<Match> byScoreDesc = Comparator.comparingInt(Match::getTotalScore).reversed();
        Comparator<Match> byStartDesc = Comparator.comparing(Match::getStartTime).reversed();

        return matches.values().stream()
                .sorted(byScoreDesc.thenComparing(byStartDesc))
                .toList();
    }
}
