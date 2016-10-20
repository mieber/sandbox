package hh.hh.storage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "HeroWinStatistics.deleteByNotTimestamp",
            query = "delete from HeroWinStatistics where timestamp!=:timestamp")
})
public class HeroWinStatistics {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String thisHero;
	
	@Column
	private String otherHero;
	
	@Column
	private Integer played;
	
	@Column
	private Double win; 
	
	@Column
	private boolean isWith;
	
	@Column
	private Long timestamp;

	public String getThisHero() {
		return thisHero;
	}

	public void setThisHero(String thisHero) {
		this.thisHero = thisHero;
	}

	public String getOtherHero() {
		return otherHero;
	}

	public void setOtherHero(String otherHero) {
		this.otherHero = otherHero;
	}

	public Integer getPlayed() {
		return played;
	}

	public void setPlayed(Integer played) {
		this.played = played;
	}

	public Double getWin() {
		return win;
	}

	public void setWin(Double win) {
		this.win = win;
	}

	public boolean isWith() {
		return isWith;
	}

	public void setWith(boolean isWith) {
		this.isWith = isWith;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "HeroWinStatistics [id=" + id + ", thisHero=" + thisHero + ", otherHero=" + otherHero + ", played="
				+ played + ", win=" + win + ", isWith=" + isWith + ", timestamp=" + timestamp + "]";
	}

}
