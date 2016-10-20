package hh.hh.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import hh.hh.hotslogs.mapstats.MapStatistics;

@Controller
public class DBController {

	@Autowired
	private HeroMapStatRepository heroMapRepo;

	@Autowired
	private HeroWinStatRepository heroWinRepo;

	@Transactional
	public void storeAndDropOldMapStats(List<HeroMapStat> records) {

		heroMapRepo.save(records);
		heroMapRepo.deleteOldRecords(records.get(0).getTimestamp());
	}

	@Transactional
	public void storeAndDropOldHeroWinStats(List<HeroWinStatistics> records) {

		heroWinRepo.save(records);
		heroWinRepo.deleteOldRecords(records.get(0).getTimestamp());
	}

	public List<HeroMapStat> load(String map, int minPopularity) {
		List<HeroMapStat> findByMap = heroMapRepo.findByMapContainingIgnoreCase(map);
		List<HeroMapStat> result = new ArrayList<>();
		for (HeroMapStat i : findByMap) {
			if (i.getPopularity() != null && i.getPopularity().doubleValue() > minPopularity) {
				result.add(i);
			}
		}
		return result;
	}

	public List<String> getAllHeroNames() {
		try {
			List<HeroMapStat> r = heroMapRepo.findByMapContainingIgnoreCase(MapStatistics.GENERIC_STATS);
			return r.stream().map(HeroMapStat::getHero).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<HeroWinStatistics> getHeroStats(String hero) {
		try {
			return heroWinRepo.findByThisHeroContainingIgnoreCase(hero);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
