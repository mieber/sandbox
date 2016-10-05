package hh.hh.ocr;

import java.util.Arrays;

public class ScreenGrabResult {

	private String[] friends;

	private String[] enemies;

	private String[] friendHeroes;

	private String[] enemyHeroes;

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

	public String[] getFriendHeroes() {
		return friendHeroes;
	}

	public void setFriendHeroes(String[] friendHeroes) {
		this.friendHeroes = friendHeroes;
	}

	public String[] getEnemyHeroes() {
		return enemyHeroes;
	}

	public void setEnemyHeroes(String[] enemyHeroes) {
		this.enemyHeroes = enemyHeroes;
	}

	@Override
	public String toString() {
		return "ScreenGrabResult [friends=" + Arrays.toString(friends) + ", enemies=" + Arrays.toString(enemies)
				+ ", friendHeroes=" + Arrays.toString(friendHeroes) + ", enemyHeroes=" + Arrays.toString(enemyHeroes)
				+ ", map=" + map + "]";
	}

}
