package hh.hh.hotslogs.data;

public class HeroStat {

	private double winPercentage;

	private String hero;

	public HeroStat(String hero, double winPercentage) {
		super();
		this.winPercentage = winPercentage;
		this.hero = hero;
	}

	public HeroStat() {
		super();
	}

	public double getWinPercentage() {
		return winPercentage;
	}

	public void setWinPercentage(double winPercentage) {
		this.winPercentage = winPercentage;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	@Override
	public String toString() {
		return "HeroStat [winPercentage=" + winPercentage + ", hero=" + hero + "]";
	}

}
