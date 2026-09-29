public class PriceCalculator { //TODO: this class seems useless. The methods can be moved to the Club and Player classes.
    public static int calculatePriceClub(Club club){
        int total = 0;
        for (Player p :
                club.getPlayers()) {
            total += calculatePricePlayer(p);
        }

        return total;
    }


    public static int calculatePricePlayer(Player player){
        int total = 0;

        //the price is all skills combined
        total += player.getOffensiveRightSkill() + player.getOffensiveLeftSkill() + player.getDefensiveLeftSkill() +
                player.getDefensiveRightSkill() + player.getKeeperSkills(); //TODO: return can be used here.


        return total;
    }
}
