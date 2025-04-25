# Live Football World Cup Scoreboard

A simple Java library to track live football matches and their scores in-memory.

## Features

- Start a new match (initial score 0 - 0)
- Update the score of an ongoing match
- Finish (remove) a match
- Get a summary of all matches ordered by total score (descending), with ties resolved by most recent start

## Assumptions

- Team names are unique per match; no duplicate fixtures.
- Scores are non-negative and absolute; updating to lower than current score is invalid.
- Operations on non-existent matches throw `IllegalArgumentException`.

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