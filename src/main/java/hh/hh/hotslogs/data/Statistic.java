package hh.hh.hotslogs.data;

public class Statistic {
	
	private String hero;
	
	private int lvl;
	
	private double percentage;
	
	private double winrate;
	
	private long number;
	
	private int threat;

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}

	public int getThreat() {
		return threat;
	}

	public void setThreat(int threat) {
		this.threat = threat;
	}

	@Override
	public String toString() {
		return "Statistic [hero=" + hero + ", lvl=" + lvl + ", percentage=" + percentage + ", winrate=" + winrate
				+ ", number=" + number + ", threat=" + threat + "]";
	}

}
