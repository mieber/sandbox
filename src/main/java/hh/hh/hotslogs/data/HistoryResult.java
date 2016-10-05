package hh.hh.hotslogs.data;

import java.util.List;

public class HistoryResult {
	
	private List<History> rows;
	
	private List<Statistic> statistics;
	
	private double winrate;

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

	public double getWinrate() {
		return winrate;
	}

	public void setWinrate(double winrate) {
		this.winrate = winrate;
	}

	@Override
	public String toString() {
		return "HistoryResult [rows=" + rows + ", statistics=" + statistics + ", winrate=" + winrate + "]";
	}
	
	

}
