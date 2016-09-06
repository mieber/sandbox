package hh.hh.hotslogs.data;

import java.util.Comparator;

public class Player {

    public final static Comparator<Player> NUMBER_OF_GAMES = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p2.numberOfGames - p1.numberOfGames;
        }
    };

    public final static Comparator<Player> MMR = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p2.mmr - p1.mmr;
        }
    };

    private String name;
    private int id;
    private int mmr;
    private String region;
    private int numberOfGames;
    private int numberOfMatches;

    public Player(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMmr() {
        return mmr;
    }

    public void setMmr(int mmr) {
        this.mmr = mmr;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", id=" + id + ", mmr=" + mmr + ", region=" + region + ", numberOfGames=" + numberOfGames
                + ", numberOfMatches=" + numberOfMatches + "]";
    }

}
