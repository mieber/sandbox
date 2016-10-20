package hh.hh.hotslogs.herostats;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import hh.hh.handler.ScreenshotModel;
import hh.hh.hotslogs.data.HeroStat;
import hh.hh.hotslogs.data.StatsHeroResult;
import hh.hh.storage.DBController;
import hh.hh.storage.HeroMapStat;
import hh.hh.storage.HeroWinStatistics;

public class HeroStatistics {

	public static StatsHeroResult create(DBController db, ScreenshotModel model) {

		List<String> allyHeroes = model.getFriendHeroes().stream().filter(Objects::nonNull).map(String::toUpperCase)
				.collect(Collectors.toList());
		List<String> enemyHeroes = model.getEnemyHeroes().stream().filter(Objects::nonNull).map(String::toUpperCase)
				.collect(Collectors.toList());

		if (allyHeroes.isEmpty() && enemyHeroes.isEmpty()) {
			return new StatsHeroResult();
		}

		List<HeroMapStat> load = db.load(model.getMap(), 5);

		// create map with hero name to overall map stats
		Map<String, HeroMapStat> globalStatsMap = load.stream().collect(Collectors.toMap(HeroMapStat::getHero, c -> c,
				(first, second) -> first, () -> new TreeMap<String, HeroMapStat>(String.CASE_INSENSITIVE_ORDER)));

		// 2 x create result map with hero to empty object
		Map<String, HeroStat> resultAllyMap = load.stream()
				.filter(x -> !allyHeroes.contains(x.getHero().toUpperCase()) && !enemyHeroes.contains(x.getHero().toUpperCase()))
				.collect(Collectors.toMap(HeroMapStat::getHero, c -> new HeroStat(c.getHero(), 0d),
						(first, second) -> first, () -> new TreeMap<String, HeroStat>(String.CASE_INSENSITIVE_ORDER)));
		Map<String, HeroStat> resultEnemyMap = load.stream()
				.filter(x -> !allyHeroes.contains(x.getHero().toUpperCase()) && !enemyHeroes.contains(x.getHero().toUpperCase()))
				.collect(Collectors.toMap(HeroMapStat::getHero, c -> new HeroStat(c.getHero(), 0d),
						(first, second) -> first, () -> new TreeMap<String, HeroStat>(String.CASE_INSENSITIVE_ORDER)));

		handleHeroes(db, allyHeroes, globalStatsMap, resultAllyMap, resultEnemyMap, true);
		handleHeroes(db, enemyHeroes, globalStatsMap, resultAllyMap, resultEnemyMap, false);

		StatsHeroResult result = new StatsHeroResult();
		result.setAllyStats(resultAllyMap.values());
		result.setEnemyStats(resultEnemyMap.values());

		return result;
	}

	private static void handleHeroes(DBController db, List<String> heroes, Map<String, HeroMapStat> globalStatsMap,
			Map<String, HeroStat> resultAllyMap, Map<String, HeroStat> resultEnemyMap, boolean ally) {
		for (String hero : heroes) {

			// example: jaina = thisHero , rehgar = otherHero
			// Jaina has a 52 winchange globaly
			HeroMapStat globalStat = globalStatsMap.get(hero);
			if (globalStat == null) {
				// invalid hero name...
				continue;
			}
			double globalWin = globalStat.getWinPercentage();

			// Jaina: -50 + 52 = +2
			double globalWinAdjustment = -50d + globalWin;

			// get win stats when jaina plays with or against a hero
			List<HeroWinStatistics> stats = db.getHeroStats(hero);
			for (HeroWinStatistics winStat : stats) {

				// this is the stat Jaina vs / with Rehgar
				String otherHero = winStat.getOtherHero();

				HeroStat heroMapStat;
				if (ally && winStat.isWith() || !ally && winStat.isWith()) {
					heroMapStat = resultAllyMap.get(otherHero);
				} else {
					heroMapStat = resultEnemyMap.get(otherHero);
				}

				if (heroMapStat == null) {
					// adjustment for a hero that is below our popularity level
					continue;
				}

				// stat with rehgar: 47
				double winRate = winStat.getWin();

				// win adjustment: -50 + 47 = -3
				double winRateAdjustment = -50 + winRate;

				// stat adjustment from jaina to rehgar:
				// jaina has 52, with rehgar 47: we loose 5
				// -3 - +2 = -5
				// if jaina had 44, this would be: -50 + 44 = -6 for jaina
				// -3 - -6 = +3 -> we win 3 by playing with rehgar
				winRateAdjustment = winRateAdjustment - globalWinAdjustment;

				// until now we have +2
				double winPercentage = heroMapStat.getWinPercentage();

				// we add the -5 = -3
				winPercentage = winPercentage + winRateAdjustment;
				heroMapStat.setWinPercentage(round(winPercentage, 2));

				 log(hero, otherHero, winStat, winRateAdjustment, ally);

			}
		}
	}

	private static void log(String hero, String otherHero, HeroWinStatistics s, Double winRateAdjustment,
			boolean ally) {
		if ("Zagara".equals(otherHero)) {

			System.out.println(((ally && s.isWith() || !ally && !s.isWith()) ? "ALLY\t" : "ENEMY\t") + hero + "\t "
					+ (s.isWith() ? "WITH " : " VS  ") + otherHero + "\t by " + round(winRateAdjustment, 1));

		}
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
