package hh.hh.hotslogs.data;

import java.util.List;

public class HistoryResult {
	
	private List<History> rows;
	
	private List<Statistic> statistics;

	public List<History> getRows() {
		return rows;
	}

	public void setRows(List<History> rows) {
		this.rows = rows;
	}

	public List<Statistic> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<Statistic> statistics) {
		this.statistics = statistics;
	}
	
	

}
