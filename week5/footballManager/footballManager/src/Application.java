import nl.saxion.app.SaxionApp;

import java.util.ArrayList;
//TODO: there are way too many new lines in this class between methods and inside methods.
public class Application implements Runnable{

    ArrayList<Player> players = new ArrayList<>(); //TODO: players are stored twice. Here and in Club.

    ArrayList<Club> clubs = new ArrayList<>();


    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1000, 1000);
    }




    @Override
    public void run() { //TODO: this method is too long. it needs to be broken down into smaller methods.
        generateMockData();
        while(true){ //TODO: too long if/else chain. A switch would be clearer.
            SaxionApp.printLine("Welcome to football manager!");
            SaxionApp.printLine("What would you like to do?");
            SaxionApp.printLine("1. Show clubs");
            SaxionApp.printLine("2. show club info");
            SaxionApp.printLine("3. Player info");
            SaxionApp.printLine("4. Play match");
            SaxionApp.printLine("5. Exit");

            int userInput = SaxionApp.readInt();
            if(userInput == 1){
                show(); //TODO: method name is not clear what it shows
            }else if(userInput == 2){
                SaxionApp.print("What is the id of the club?:");
                int clubId = SaxionApp.readInt();
                Club club = findClub(clubId);
                if(club == null){
                    SaxionApp.printLine("Club " + clubId + " could not be found");
                }

                //literal (or magic number)
                for (int i=0; i < 11; i++) {
                    SaxionApp.printLine(club.getPlayers().get(i));
                }

                SaxionApp.printLine("club total worth: " + PriceCalculator.calculatePriceClub(club));
            }else if(userInput == 3){
                SaxionApp.print("What is the id of the player?:");
                int playerId = SaxionApp.readInt();
                Player player = findPlayer(playerId);
                if(player == null){
                    SaxionApp.printLine("Club " + playerId + " could not be found"); //TODO: it should be Player instead of club.
                }else{
                    SaxionApp.printLine("Player " + player);
                }

                SaxionApp.printLine("Price player: " + PriceCalculator.calculatePricePlayer(player)); //TODO: also runs when player is null.
            }else if(userInput == 4){
                SaxionApp.print("Id of first club:"); //TODO: no null check on club1 and club2.
                Club club1 = findClub(SaxionApp.readInt());
                SaxionApp.print("Id of second club:");
                Club club2 = findClub(SaxionApp.readInt());

                playMatchBetween2ClubsBasedOnRandomTeamScores(club1, club1.getPlayers(), club1.getKeeper(), club2, club2.getPlayers(), club2.getKeeper());
                SaxionApp.printLine("score: " + scoreTeam1LastMatch + "-" +scoreTeam2LastMatch);
                club1.getPlayers().get(0).calculateWinner(this); //TODO: a random player is asked to calculate the winner.

            }else if(userInput == 5){
                break;
            }

            SaxionApp.pause();
            SaxionApp.clear();
        }
    }


    private Club findClub(int id){
        for (Club c:clubs) {
            if(c.getClubId() == id){
                c.timesInspected++;
                return c;
            }
        }
        return null;
    }

    private Player findPlayer(int id){
        for (Player p:players) {
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }


    private void show(){
        SaxionApp.printLine("clubs:");
        for (Club c : clubs) {
            SaxionApp.printLine(c);
        }
    }


    //TODO: this method name is too long, parameter list is too long and the return value is never used.
    private Club playMatchBetween2ClubsBasedOnRandomTeamScores(Club team1, ArrayList<Player> playersTeam1, Player keeperTeam1, Club team2, ArrayList<Player> playersTeam2, Player keeperTeam2){
        scoreTeam1LastMatch = 0;
        scoreTeam2LastMatch = 0;


        //TODO: duplicated code. The team1 and team2 attack loops are almost the same.
        // =================== team1 does 5-10 attacks on team2 ===================
        int numberOfAttacksTeam1 = SaxionApp.getRandomValueBetween(5,10);
        for(int attack=0; attack < numberOfAttacksTeam1; attack++){
            //get random attacker and random defender
            Player randomAttacker = playersTeam1.get(SaxionApp.getRandomValueBetween(0,11));
            Player randomDefender = team2.getPlayers().get(SaxionApp.getRandomValueBetween(0,11));

            //random attack on left side or right side
            if(SaxionApp.getRandomValueBetween(0,2) == 0){//0 = left side, 1 = right side
                //if attacker strength > defender strength => goal!
                if(randomAttacker.getOffensiveRightSkill() > randomDefender.getDefensiveRightSkill()){
                    if(!keeperTeam2.keeperBlockShot()){
                        scoreTeam1LastMatch++;
                    }
                }
            }else{
                //if attacker strength > defender strength => shot on goal!
                if(randomAttacker.getOffensiveLeftSkill() > randomDefender.getDefensiveLeftSkill()){
                    if(!keeperTeam2.keeperBlockShot()){
                        scoreTeam1LastMatch++;
                    }
                }
            }
        }



        // =================== team2 does 5-10 attacks on team1 ===================
        int numberOfAttacksTeam2 = SaxionApp.getRandomValueBetween(5,10);
        for(int attack=0; attack < numberOfAttacksTeam2; attack++){
            //get random attacker and random defender
            Player randomAttacker = playersTeam2.get(SaxionApp.getRandomValueBetween(0,11));
            Player randomDefender = team1.getPlayers().get(SaxionApp.getRandomValueBetween(0,11));

            //random attack on left side or right side
            if(SaxionApp.getRandomValueBetween(0,2) == 0){//0 = left side, 1 = right side
                //if attacker strength > defender strength => goal!
                if(randomAttacker.getOffensiveRightSkill() > randomDefender.getDefensiveRightSkill()){
                    if(!keeperTeam1.keeperBlockShot()){
                        scoreTeam2LastMatch++;
                    }
                }
            }else{
                //if attacker strength > defender strength => goal!
                if(randomAttacker.getOffensiveLeftSkill() > randomDefender.getDefensiveLeftSkill()){
                    if(!keeperTeam1.keeperBlockShot()){
                        scoreTeam2LastMatch++;
                    }
                }
            }
        }

        // =================== store winner ===================
        tieScoreLastMatch = scoreTeam1LastMatch == scoreTeam2LastMatch;
        if(scoreTeam1LastMatch > scoreTeam2LastMatch){
            winnerLastMatch = team1;
            return team1;
        }else if(scoreTeam2LastMatch > scoreTeam1LastMatch){
            winnerLastMatch = team2;
            return team2;
        }

        return null; //tie
    }


    //TODO: these variables should be at the top and private.
    public int scoreTeam1LastMatch = 0;
    public int scoreTeam2LastMatch = 0;
    public Club winnerLastMatch;
    public boolean tieScoreLastMatch = false;


    //==== note: when refactoring: this generateMockData function is out of scope ===== /
    private void generateMockData(){
        int totalClubs = 3;
        int playersPerClub = 11;

        for(int clubNr = 1; clubNr <= totalClubs; clubNr++){
            Club club = new Club(clubNr);
            clubs.add(club);
            for (int playerNr=1; playerNr <= playersPerClub; playerNr++){
                int stat1 = SaxionApp.getRandomValueBetween(1,101);
                int stat2 = SaxionApp.getRandomValueBetween(1,101);
                int stat3 = SaxionApp.getRandomValueBetween(1,101);
                int stat4 = SaxionApp.getRandomValueBetween(1,101);
                int stat5 = SaxionApp.getRandomValueBetween(1,101);
                int stat6 = SaxionApp.getRandomValueBetween(1,101);
                int stat7 = -1;
                if(playerNr==1){ //only player1 is a keeper
                    stat7 = SaxionApp.getRandomValueBetween(1,101);
                }

                Player nwPlayer = new Player(stat1, stat2,stat3,stat4,stat5,stat6, stat7); //TODO: inconsistent spacing between stats. the nwPlayer variable name should be newPlayer. it is unclear what each stat corresponds to exactly.




                players.add(nwPlayer);
                club.addPlayer(nwPlayer);


                if(playerNr==1){ //if this is the keeper also tell the club this
                    club.setKeeper(nwPlayer);
                }

                nwPlayer.setClub(club);
            }
        }
    }
}
