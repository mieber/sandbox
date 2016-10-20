package hh.hh.hotslogs.data;

import java.util.Collection;

public class StatsHeroResult {
	
	private Collection<HeroStat> enemyStats;
	
	private Collection<HeroStat> allyStats;

	public Collection<HeroStat> getEnemyStats() {
		return enemyStats;
	}

	public void setEnemyStats(Collection<HeroStat> enemyStats) {
		this.enemyStats = enemyStats;
	}

	public Collection<HeroStat> getAllyStats() {
		return allyStats;
	}

	public void setAllyStats(Collection<HeroStat> allyStats) {
		this.allyStats = allyStats;
	}

	

}
