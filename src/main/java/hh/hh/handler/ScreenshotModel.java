package hh.hh.handler;

import java.util.List;

public class ScreenshotModel {

	private List<String> friends;

	private List<String> enemies;

	private List<String> friendHeroes;

	private List<String> enemyHeroes;

	private String map;

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public List<String> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<String> enemies) {
		this.enemies = enemies;
	}

	public List<String> getFriendHeroes() {
		return friendHeroes;
	}

	public void setFriendHeroes(List<String> friendHeroes) {
		this.friendHeroes = friendHeroes;
	}

	public List<String> getEnemyHeroes() {
		return enemyHeroes;
	}

	public void setEnemyHeroes(List<String> enemyHeroes) {
		this.enemyHeroes = enemyHeroes;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "ScreenshotModel [friends=" + friends + ", enemies=" + enemies + ", friendHeroes=" + friendHeroes
				+ ", enemyHeroes=" + enemyHeroes + ", map=" + map + "]";
	}

}
