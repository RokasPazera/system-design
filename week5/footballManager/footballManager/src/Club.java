import java.util.ArrayList;

public class Club {
    private int clubId;
    private ArrayList<Player> players = new ArrayList<>();
    Player keeper;

    public int timesInspected = 0;


    public Club(int clubId) {
        this.clubId = clubId;
    }

    public void addPlayer(Player player){
        if(players.size() < 11){
            players.add(player);
        }
    }

    public void setKeeper(Player player){
        keeper = player; //keeper should always be set in the first position of players.
    }


    /* this method can be used to get the club id*/
    public int getClubId() {
        return clubId;
    }

    @Override
    public String toString() {
        return "club, id: " + clubId;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }


    public Player getKeeper(){
        return keeper; //keeper is always the first player
    }
}
