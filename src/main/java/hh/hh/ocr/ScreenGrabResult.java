package hh.hh.ocr;

import java.util.Arrays;

public class ScreenGrabResult {
	
	private String[] friends;
	
	private String[] enemies;
	
	private String map;

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	public String[] getEnemies() {
		return enemies;
	}

	public void setEnemies(String[] enemies) {
		this.enemies = enemies;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "ScreenGrabResult [friends=" + Arrays.toString(friends) + ", enemies=" + Arrays.toString(enemies)
				+ ", map=" + map + "]";
	}

}
