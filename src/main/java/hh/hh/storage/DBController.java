package hh.hh.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import hh.hh.hotslogs.grab.MapStatistics;

@Controller
public class DBController {

	@Autowired
	private HeroMapStatRepository repo;

	@Transactional
	public void storeAndDropOld(List<HeroMapStat> records) {

		repo.save(records);
		repo.deleteOldRecords(records.get(0).getTimestamp());
	}

	public List<HeroMapStat> load(String map) {
		List<HeroMapStat> findByMap = repo.findByMapContainingIgnoreCase(map);
		List<HeroMapStat> result = new ArrayList<>();
		for (HeroMapStat i : findByMap) {
			if (i.getPopularity() != null && i.getPopularity().doubleValue() > 15) {
				result.add(i);
			}
		}
		return result;
	}
	
	public List<String> getAllHeroNames() {
		try {
			List<HeroMapStat> r = repo.findByMapContainingIgnoreCase(MapStatistics.GENERIC_STATS);
			return r.stream().map(HeroMapStat::getHero).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
