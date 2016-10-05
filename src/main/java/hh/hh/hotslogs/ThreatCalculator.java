package hh.hh.hotslogs;

import java.util.List;

import hh.hh.hotslogs.data.Statistic;
import hh.hh.storage.HeroMapStat;

public class ThreatCalculator {

	private Statistic s;
	List<HeroMapStat> stats;

	public static ThreatCalculator get(Statistic s, List<HeroMapStat> stats) {
		ThreatCalculator t = new ThreatCalculator();
		t.s = s;
		t.stats = stats;
		return t;
	}

	public int calculateThreatLevel() {
		int threat = getNormalized(s.getWinrate(), 40, 60, 20, false);
		threat += getNormalized(s.getPercentage(), 40, 60, 20, false);
		threat += getNormalized(s.getLvl(), 5, 12, 10, false);
		threat += getHeroThreat();

		return threat;
	}

	private int getHeroThreat() {
		if (stats == null || stats.isEmpty()) {
			return 0;
		}

		String hero = s.getHero();
		if (hero == null || hero.isEmpty()) {
			return 0;
		}

		for (HeroMapStat stat : stats) {
			if (hero.equalsIgnoreCase(stat.getHero())) {
				// higher popularity: generaly likelier to be taken
				int threat = getNormalized(stat.getPopularity(), 40, 70, 10, false);
				// first do the add threat case (> 50%)
				threat += getNormalized(stat.getWinPercentage(), 50, 55, 10, false);
				// now the detract threat case (< 50%)
				threat += getNormalized(stat.getWinPercentage(), 45, 50, -10, true);
				return threat;
			}
		}
		return 0;
	}

	private int getNormalized(double in, int min, int max, int lvl, boolean inverse) {
		return getNormalized((int) in, min, max, lvl, inverse);
	}

	private int getNormalized(int in, int min, int max, int lvl, boolean inverse) {

		int threat = Math.max(in, min);
		threat = Math.min(threat, max);

		double normalized = (double) (threat - min) / (double) (max - min);

		if (inverse) {
			normalized = 1 - normalized;
		}

		return (int) (lvl * normalized);
	}

}
