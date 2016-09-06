package hh.hh.ui;

public class ScreenUpdate {

	private String content;

	private String other;

	private String[] enemies;

	public ScreenUpdate(String content, String other, String[] enemies) {
		this.content = content;
		this.other = other;
		this.enemies = enemies;
	}

	public String getContent() {
		return content;
	}

	public String getOther() {
		return other;
	}

	public String[] getEnemies() {
		return enemies;
	}

}