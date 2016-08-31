package hh.hh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerFilter {
    
    private List<Player> input;
    private List<Player> possible = new ArrayList<>();
    
    public PlayerFilter(List<Player> input) {
        this.input = input;
    }
    
    private void filter() {
        for (Player player : input) {
            if ("EU".equalsIgnoreCase(player.getRegion())) {
                possible.add(player);
            }
        }
        for (Player player : possible) {
            player.setNumberOfMatches(possible.size());
        }
    }
    
    public Player getBestMatch() {
        filter();
        if (possible.size() == 0) {
            return null;
        }
        Collections.sort(possible, new ComparatorChain<Player>(Player.NUMBER_OF_GAMES, Player.MMR));
        return possible.get(0);
    }

}
