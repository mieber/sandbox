package hh.hh.storage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroMapStatRepository extends JpaRepository<HeroMapStat, Long> {
	
	@Modifying
    @Query(name = "HeroMapStat.deleteByNotTimestamp")
    void deleteOldRecords(@Param("timestamp") long timestamp);
	
	List<HeroMapStat> findByMap(@Param("map") String map);

}
