package scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scoreboard.exception.MatchAlreadyExistsException;
import scoreboard.exception.MatchNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryScoreboardTest {
    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new InMemoryScoreboard();
    }

    @Test
    void testStartAndFinishMatch() {
        scoreboard.startMatch("A", "B");
        assertFalse(scoreboard.getSummary().isEmpty());
        scoreboard.finishMatch("A", "B");
        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void testUpdateScore() {
        scoreboard.startMatch("A", "B");
        scoreboard.updateScore("A", "B", 5, 8);
        Match match = scoreboard.getSummary().get(0);
        assertEquals(5, match.getHomeScore());
        assertEquals(8, match.getAwayScore());
    }

    @Test
    void testGetSummaryOrder() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.startMatch("Germany", "France");
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<Match> summary = scoreboard.getSummary();
        assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString());
        assertEquals("Spain 10 - Brazil 2", summary.get(1).toString());
        assertEquals("Mexico 0 - Canada 5", summary.get(2).toString());
        assertEquals("Argentina 3 - Australia 1", summary.get(3).toString());
        assertEquals("Germany 2 - France 2", summary.get(4).toString());
    }

    @Test
    void testDuplicateAndNullFixtures() {
        assertThrows(NullPointerException.class, () -> scoreboard.startMatch(null, "B"));
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("A","A"));
        scoreboard.startMatch("C","D");
        assertThrows(MatchAlreadyExistsException.class, () -> scoreboard.startMatch("C","D"));
    }

    @Test
    void testUpdateFinishErrors() {
        assertThrows(MatchNotFoundException.class, () -> scoreboard.updateScore("X","Y",1,1));
        assertThrows(MatchNotFoundException.class, () -> scoreboard.finishMatch("X","Y"));
    }

    @Test
    void testSummaryImmutable() {
        scoreboard.startMatch("A","B");
        List<Match> list = scoreboard.getSummary();
        assertThrows(UnsupportedOperationException.class, () -> list.add(null));
    }
}
