package hh.hh.hotslogs;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import hh.hh.SettingsService;
import hh.hh.hotslogs.data.Player;

public class PlayerFilter {

	private List<Player> input;
	private SettingsService settings;
	private String originalName;

	public PlayerFilter(List<Player> input, SettingsService settings, String originalName) {
		this.input = input;
		this.settings = settings;
		this.originalName = originalName;
	}

	private void regionFilter() {

		ListIterator<Player> listIterator = input.listIterator();
		while (listIterator.hasNext()) {
			Player player = (Player) listIterator.next();
			if (!settings.getRegion().equalsIgnoreCase(player.getRegion())) {
				listIterator.remove();
			}
		}
		for (Player player : input) {
			player.setHits(input.size());
		}
	}

	private void gameCountFilter() {

		ListIterator<Player> listIterator = input.listIterator();
		while (listIterator.hasNext()) {
			Player player = (Player) listIterator.next();
			if (player.getNumberOfGames() < 10) {
				listIterator.remove();
			}
		}
		for (Player player : input) {
			player.setHits(input.size());
		}
	}

	private void mmrNotSetFilter() {
		ListIterator<Player> listIterator = input.listIterator();
		while (listIterator.hasNext()) {
			Player player = (Player) listIterator.next();
			if (player.getMmr() < 10) {
				listIterator.remove();
			}
		}
		for (Player player : input) {
			player.setHits(input.size());
		}
	}

	private void nameCaseFilter() {
		ListIterator<Player> listIterator = input.listIterator();
		while (listIterator.hasNext()) {
			Player player = (Player) listIterator.next();
			if (!player.getName().equals(originalName)) {
				listIterator.remove();
			}
		}
	}
	
	private void mmrRangeFilter() {
		int userMmr = settings.getMmr();
		
		ListIterator<Player> listIterator = input.listIterator();
		while (listIterator.hasNext()) {
			Player player = (Player) listIterator.next();
			
			if (player.getMmr() > userMmr + 400) {
				listIterator.remove();
			} else if (player.getMmr() < userMmr - 400) {
				listIterator.remove();
			}
		}
	}

	public Player getBestMatch() {
		regionFilter();
		if (input.size() == 0) {
			return null;
		}
		Collections.sort(input, new ComparatorChain<Player>(Player.NUMBER_OF_GAMES, Player.MMR));
		Player currentBestMatch = input.get(0);

		gameCountFilter();

		if (input.size() == 0) {
			return currentBestMatch;
		}

		Collections.sort(input, new ComparatorChain<Player>(Player.NUMBER_OF_GAMES, Player.MMR));
		currentBestMatch = input.get(0);

		mmrNotSetFilter();

		if (input.size() == 0) {
			return currentBestMatch;
		}

		Collections.sort(input, new ComparatorChain<Player>(Player.NUMBER_OF_GAMES, Player.MMR));
		currentBestMatch = input.get(0);
		
		nameCaseFilter();

		if (input.size() == 0) {
			return currentBestMatch;
		}

		Collections.sort(input, new ComparatorChain<Player>(Player.NUMBER_OF_GAMES, Player.MMR));
		currentBestMatch = input.get(0);
		
		mmrRangeFilter();
		
		if (input.size() == 0) {
			return currentBestMatch;
		}

		Collections.sort(input, new ComparatorChain<Player>(Player.NUMBER_OF_GAMES, Player.MMR));
		currentBestMatch = input.get(0);
		
		return currentBestMatch;
	}



}
