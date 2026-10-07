package domain;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private static int nextId = 0;

    private final int id;
    private final int offensiveLeftSkill;
    private final int offensiveRightSkill;
    private final int defensiveLeftSkill;
    private final int defensiveRightSkill;
    private int keeperSkill;
    private boolean isKeeper;
    private Club club;

    public Player(int offensiveLeftSkill, int offensiveRightSkill, int defensiveLeftSkill, int defensiveRightSkill) {
        this.id = nextId++;
        this.offensiveLeftSkill = offensiveLeftSkill;
        this.offensiveRightSkill = offensiveRightSkill;
        this.defensiveLeftSkill = defensiveLeftSkill;
        this.defensiveRightSkill = defensiveRightSkill;
    }

    public Player(int offensiveLeftSkill, int offensiveRightSkill, int defensiveLeftSkill, int defensiveRightSkill, int keeperSkill) {
        this(offensiveLeftSkill, offensiveRightSkill, defensiveLeftSkill, defensiveRightSkill);
        this.keeperSkill = keeperSkill;
        this.isKeeper = true;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getId() {
        return id;
    }

    public int getOffensiveLeftSkill() {
        return offensiveLeftSkill;
    }

    public int getOffensiveRightSkill() {
        return offensiveRightSkill;
    }

    public int getDefensiveLeftSkill() {
        return defensiveLeftSkill;
    }

    public int getDefensiveRightSkill() {
        return defensiveRightSkill;
    }

    // The higher the keeper skill, the bigger the chance that the keeper blocks the shot.
    public boolean blockShot() {
        return isKeeper && ThreadLocalRandom.current().nextInt(1, 100) < keeperSkill;
    }

    // The price of a player is all skills combined.
    public int calculatePrice() {
        return offensiveLeftSkill + offensiveRightSkill + defensiveLeftSkill + defensiveRightSkill + keeperSkill;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", OffL=" + offensiveLeftSkill +
                ", OffR=" + offensiveRightSkill +
                ", DefL=" + defensiveLeftSkill +
                ", DefR=" + defensiveRightSkill +
                ", Keeper=" + keeperSkill +
                ", clubId=" + club.getClubId() +
                '}';
    }
}
