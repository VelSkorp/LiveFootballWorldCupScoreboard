# Live Football World Cup Scoreboard

A simple Java library to track live football matches and their scores in-memory.

## Features

- Start a new match (initial score 0 - 0)
- Update the score of an ongoing match
- Finish (remove) a match
- Get a summary of all matches ordered by total score (descending), with ties resolved by most recent start

## Assumptions

- **`Fixture`** value type instead of string keys for clearer code.
- **`Scoreboard`** interface with `InMemoryScoreboard` implementation.
- **Custom exceptions** (`MatchAlreadyExistsException`, `MatchNotFoundException`).
- **Immutable summaries** returned vi~~~~a `Collections.unmodifiableList`.
- **SLF4J logging** for lifecycle events.

## Usage

```java
Scoreboard scoreboard = new Scoreboard();
scoreboard.startMatch("Mexico", "Canada");
scoreboard.updateScore("Mexico", "Canada", 0, 5);
List<Match> summary = scoreboard.getSummary();
```

## Building and Testing

```bash
mvn clean test
```