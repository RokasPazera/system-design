package application;

import domain.Club;
import domain.FootballManagerFacade;
import domain.Match;
import domain.Player;
import nl.saxion.app.SaxionApp;

public class Application implements Runnable {

    private final FootballManagerFacade facade = new FootballManagerFacade();

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1000, 1000);
    }

    @Override
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            switch (SaxionApp.readInt()) {
                case 1 -> showAllClubs();
                case 2 -> showClubInfo();
                case 3 -> showPlayerInfo();
                case 4 -> playMatch();
                case 5 -> running = false;
                default -> SaxionApp.printLine("That is not a valid option.");
            }

            if (running) {
                SaxionApp.pause();
                SaxionApp.clear();
            }
        }
    }

    private void printMenu() {
        SaxionApp.printLine("Welcome to football manager!");
        SaxionApp.printLine("What would you like to do?");
        SaxionApp.printLine("1. Show all clubs");
        SaxionApp.printLine("2. Show club info");
        SaxionApp.printLine("3. Show player info");
        SaxionApp.printLine("4. Play match");
        SaxionApp.printLine("5. Exit");
    }

    private void showAllClubs() {
        SaxionApp.printLine("Clubs:");
        for (Club club : facade.getAllClubs()) {
            SaxionApp.printLine(club);
        }
    }

    private void showClubInfo() {
        SaxionApp.print("What is the id of the club?: ");
        int clubId = SaxionApp.readInt();
        Club club = facade.findClub(clubId);
        if (club == null) {
            SaxionApp.printLine("Club " + clubId + " could not be found");
            return;
        }

        for (Player player : club.getPlayers()) {
            SaxionApp.printLine(player);
        }
        SaxionApp.printLine("Club total worth: " + club.calculateTotalPrice());
    }

    private void showPlayerInfo() {
        SaxionApp.print("What is the id of the player?: ");
        int playerId = SaxionApp.readInt();
        Player player = facade.findPlayer(playerId);
        if (player == null) {
            SaxionApp.printLine("Player " + playerId + " could not be found");
            return;
        }

        SaxionApp.printLine("Player " + player);
        SaxionApp.printLine("Price player: " + player.calculatePrice());
    }

    private void playMatch() {
        SaxionApp.print("Id of the home club: ");
        Club homeClub = facade.findClub(SaxionApp.readInt());
        SaxionApp.print("Id of the away club: ");
        Club awayClub = facade.findClub(SaxionApp.readInt());
        if (homeClub == null || awayClub == null) {
            SaxionApp.printLine("One of the clubs could not be found");
            return;
        }

        Match match = facade.playMatch(homeClub, awayClub);
        SaxionApp.printLine("Score: " + match.getHomeScore() + "-" + match.getAwayScore());
        if (match.isTie()) {
            SaxionApp.printLine("It's a tie!");
        } else {
            SaxionApp.printLine(match.getWinner() + " is the winner!");
        }
    }
}
