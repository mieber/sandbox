package hh.hh.hotslogs.data;

public class History {

	private String id;

	private boolean win;

	private String map;

	private String length;

	private String Hero;

	private String Lvl;

	private String mmr;

	private String mmrChange;

	private String date;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getHero() {
		return Hero;
	}

	public void setHero(String hero) {
		Hero = hero;
	}

	public String getLvl() {
		return Lvl;
	}

	public void setLvl(String lvl) {
		Lvl = lvl;
	}

	public String getMmr() {
		return mmr;
	}

	public void setMmr(String mmr) {
		this.mmr = mmr;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public String getMmrChange() {
		return mmrChange;
	}

	public void setMmrChange(String mmrChange) {
		this.mmrChange = mmrChange;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", win=" + win + ", map=" + map + ", length=" + length + ", Hero=" + Hero
				+ ", Lvl=" + Lvl + ", mmr=" + mmr + ", mmrChange=" + mmrChange + ", date=" + date + "]";
	}

}
