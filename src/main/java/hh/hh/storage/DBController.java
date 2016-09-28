package hh.hh.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

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
		return repo.findByMap(map);
	}

}
