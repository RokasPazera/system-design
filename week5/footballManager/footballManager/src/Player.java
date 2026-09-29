import nl.saxion.app.SaxionApp;

public class Player {

    public static int LASTID_USED = 0; //TODO: should be just private and not named like a constant.

    private int id;
    private int offensiveLeftSkill;
    private int offensiveRightSkill;
    private int midRightSkill; //TODO: unused variable.
    private int midLeftSkill; //TODO: unused variable.
    private int defensiveLeftSkill;
    private int defensiveRightSkill;
    private int keeperSkills; //set to -1 if this player is not a keeper //TODO: use a boolean isKeeper.
    private Club club;



    public Player(int offensiveLeftSkill, int offensiveRightSkill, int midRightSkill, int midLeftSkill, int defensiveLeftSkill, int defensiveRightSkill, int keeperSkills) {
        this.id = LASTID_USED;
        LASTID_USED++;
        this.offensiveLeftSkill = offensiveLeftSkill;
        this.offensiveRightSkill = offensiveRightSkill;
        this.midRightSkill = midRightSkill;
        this.midLeftSkill = midLeftSkill;
        this.defensiveLeftSkill = defensiveLeftSkill;
        this.defensiveRightSkill = defensiveRightSkill;
        this.keeperSkills = keeperSkills;
    }



    //TODO: dead code, this method is never used.
    public void changeStats(int offensiveLeftSkill, int offensiveRightSkill, int midRightSkill, int midLeftSkill, int defensiveLeftSkill, int defensiveRightSkill, int keeperSkills) {
        this.offensiveLeftSkill = offensiveLeftSkill;
        this.offensiveRightSkill = offensiveRightSkill;
        this.midRightSkill = midRightSkill;
        this.midLeftSkill = midLeftSkill;
        this.defensiveLeftSkill = defensiveLeftSkill;
        this.defensiveRightSkill = defensiveRightSkill;
        this.keeperSkills = keeperSkills;
    }

    public Club getClub() { //TODO: dead code, this getter is never used.
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", OffL=" + offensiveLeftSkill +
                ", OffR=" + offensiveRightSkill +
                ", DefL=" + defensiveLeftSkill +
                ", DefR=" + defensiveRightSkill +
                ", Keeper=" + keeperSkills +
                ", clubId=" + club.getClubId() +
                '}';
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

    public int getKeeperSkills() {
        return keeperSkills;
    }

    public boolean keeperBlockShot(){
        //the keeper can block a shot, the higher the keeperskill the higher the chance that he is successfull!
        return SaxionApp.getRandomValueBetween(1,100) < this.keeperSkills; //TODO: inconsistent range, everywhere else uses 1,101.
    }


//TODO: bad spacing and new lines. This method only uses Application data and does not belong in Player.
    public String calculateWinner(Application application){
        String result = "";
        if(application.tieScoreLastMatch){
            result =  "Its a tie!";
        }else{
            result =  application.winnerLastMatch + " is the winner!";
        }

        SaxionApp.printLine(result);

        return result;
    }
}
