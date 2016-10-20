package hh.hh.ocr;

import java.util.List;

public class ScreenGrabResult {

	private List<SingleWordResult> friends;

	private List<SingleWordResult> enemies;

	private List<SingleWordResult> friendHeroes;

	private List<SingleWordResult> enemyHeroes;

	private SingleWordResult map;

	public List<SingleWordResult> getFriends() {
		return friends;
	}

	public void setFriends(List<SingleWordResult> friends) {
		this.friends = friends;
	}

	public List<SingleWordResult> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<SingleWordResult> enemies) {
		this.enemies = enemies;
	}

	public SingleWordResult getMap() {
		return map;
	}

	public void setMap(SingleWordResult map) {
		this.map = map;
	}

	public List<SingleWordResult> getFriendHeroes() {
		return friendHeroes;
	}

	public void setFriendHeroes(List<SingleWordResult> friendHeroes) {
		this.friendHeroes = friendHeroes;
	}

	public List<SingleWordResult> getEnemyHeroes() {
		return enemyHeroes;
	}

	public void setEnemyHeroes(List<SingleWordResult> enemyHeroes) {
		this.enemyHeroes = enemyHeroes;
	}

	@Override
	public String toString() {
		return "ScreenGrabResult [friends=" + friends + ", enemies=" + enemies + ", friendHeroes=" + friendHeroes
				+ ", enemyHeroes=" + enemyHeroes + ", map=" + map + "]";
	}

	

}
