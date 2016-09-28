package hh.hh.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "HeroMapStat.deleteByNotTimestamp",
            query = "delete from HeroMapStat where timestamp!=:timestamp")
})
public class HeroMapStat {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String map;

	@Column
	private String hero;

	@Column
	private Integer gamesPlayed;

	@Column
	private Integer gamesBanned;

	@Column
	private Double popularity;

	@Column
	private Double winPercentage;

	@Column
	private Double winChange;

	@Column
	private Long timestamp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(Integer gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public Integer getGamesBanned() {
		return gamesBanned;
	}

	public void setGamesBanned(Integer gamesBanned) {
		this.gamesBanned = gamesBanned;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public Double getWinPercentage() {
		return winPercentage;
	}

	public void setWinPercentage(Double winPercentage) {
		this.winPercentage = winPercentage;
	}

	public Double getWinChange() {
		return winChange;
	}

	public void setWinChange(Double winChange) {
		this.winChange = winChange;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "HeroMapStat [id=" + id + ", map=" + map + ", hero=" + hero + ", gamesPlayed=" + gamesPlayed
				+ ", gamesBanned=" + gamesBanned + ", popularity=" + popularity + ", winPercentage=" + winPercentage
				+ ", winChange=" + winChange + ", timestamp=" + timestamp + "]";
	}

}
