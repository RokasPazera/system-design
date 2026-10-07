package domain;

import java.util.ArrayList;

public class Club {

    public static final int MAX_PLAYERS = 11;

    private final int clubId;
    private final ArrayList<Player> players = new ArrayList<>();
    private Player keeper;

    public Club(int clubId) {
        this.clubId = clubId;
    }

    public void addPlayer(Player player) {
        if (players.size() < MAX_PLAYERS) {
            players.add(player);
            player.setClub(this);
        }
    }

    public void setKeeper(Player keeper) {
        this.keeper = keeper;
    }

    public int getClubId() {
        return clubId;
    }

    public ArrayList<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public Player getKeeper() {
        return keeper;
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Player player : players) {
            totalPrice += player.calculatePrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "club, id: " + clubId;
    }
}
