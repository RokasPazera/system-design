package domain;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Match {

    private final Club homeClub;
    private final Club awayClub;
    private int homeScore;
    private int awayScore;

    public Match(Club homeClub, Club awayClub) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
    }

    public void play() {
        homeScore = simulateAttacks(homeClub, awayClub);
        awayScore = simulateAttacks(awayClub, homeClub);
    }

    // The attacking club attacks 5 to 9 times and returns the number of goals it scored.
    private int simulateAttacks(Club attackingClub, Club defendingClub) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ArrayList<Player> attackers = attackingClub.getPlayers();
        ArrayList<Player> defenders = defendingClub.getPlayers();
        Player keeper = defendingClub.getKeeper();

        int goals = 0;
        int numberOfAttacks = random.nextInt(5, 10);
        for (int attack = 0; attack < numberOfAttacks; attack++) {
            Player attacker = attackers.get(random.nextInt(attackers.size()));
            Player defender = defenders.get(random.nextInt(defenders.size()));

            boolean attackOnLeftSide = random.nextBoolean();
            int attackSkill = attackOnLeftSide ? attacker.getOffensiveLeftSkill() : attacker.getOffensiveRightSkill();
            int defenceSkill = attackOnLeftSide ? defender.getDefensiveLeftSkill() : defender.getDefensiveRightSkill();

            if (attackSkill > defenceSkill && (keeper == null || !keeper.blockShot())) {
                goals++;
            }
        }
        return goals;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public boolean isTie() {
        return homeScore == awayScore;
    }

    // Returns null when the match is a tie.
    public Club getWinner() {
        if (homeScore > awayScore) {
            return homeClub;
        }
        if (awayScore > homeScore) {
            return awayClub;
        }
        return null;
    }
}
