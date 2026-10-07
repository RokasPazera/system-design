package domain;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class FootballManagerFacade {

    private final ArrayList<Club> clubs = new ArrayList<>();

    public FootballManagerFacade() {
        generateMockData();
    }

    public ArrayList<Club> getAllClubs() {
        return new ArrayList<>(clubs);
    }

    public Club findClub(int clubId) {
        for (Club club : clubs) {
            if (club.getClubId() == clubId) {
                return club;
            }
        }
        return null;
    }

    public Player findPlayer(int playerId) {
        for (Club club : clubs) {
            for (Player player : club.getPlayers()) {
                if (player.getId() == playerId) {
                    return player;
                }
            }
        }
        return null;
    }

    public Match playMatch(Club homeClub, Club awayClub) {
        Match match = new Match(homeClub, awayClub);
        match.play();
        return match;
    }

    private void generateMockData() {
        int totalClubs = 3;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int clubId = 1; clubId <= totalClubs; clubId++) {
            Club club = new Club(clubId);
            clubs.add(club);
            for (int playerNr = 1; playerNr <= Club.MAX_PLAYERS; playerNr++) {
                int offensiveLeftSkill = random.nextInt(1, 101);
                int offensiveRightSkill = random.nextInt(1, 101);
                int defensiveLeftSkill = random.nextInt(1, 101);
                int defensiveRightSkill = random.nextInt(1, 101);

                if (playerNr == 1) { // the first player of each club is the keeper
                    int keeperSkill = random.nextInt(1, 101);
                    Player keeper = new Player(offensiveLeftSkill, offensiveRightSkill, defensiveLeftSkill, defensiveRightSkill, keeperSkill);
                    club.addPlayer(keeper);
                    club.setKeeper(keeper);
                } else {
                    club.addPlayer(new Player(offensiveLeftSkill, offensiveRightSkill, defensiveLeftSkill, defensiveRightSkill));
                }
            }
        }
    }
}
