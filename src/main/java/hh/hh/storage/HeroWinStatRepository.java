package hh.hh.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeroWinStatRepository extends JpaRepository<HeroWinStatistics, Long> {
	
	@Modifying
    @Query(name = "HeroWinStatistics.deleteByNotTimestamp")
    void deleteOldRecords(@Param("timestamp") long timestamp);

}
