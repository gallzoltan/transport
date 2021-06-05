package hu.webuni.transport.gallz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "transports")
@Component
public class TransportConfigProperties {
	
	private IncomeDeclinePercentage incomeDeclinePercentage = new IncomeDeclinePercentage();
	
	public static class IncomeDeclinePercentage {
		private int for30min;
		private int for60min;
		private int for120min;
		private int from120min;
		
		public int getFor30min() {
			return for30min;
		}
		public void setFor30min(int for30min) {
			this.for30min = for30min;
		}
		public int getFor60min() {
			return for60min;
		}
		public void setFor60min(int for60min) {
			this.for60min = for60min;
		}
		public int getFor120min() {
			return for120min;
		}
		public void setFor120min(int for120min) {
			this.for120min = for120min;
		}
		public int getFrom120min() {
			return from120min;
		}
		public void setFrom120min(int from120min) {
			this.from120min = from120min;
		}		
	}

	public IncomeDeclinePercentage getIncomeDeclinePercentage() {
		return incomeDeclinePercentage;
	}

	public void setIncomeDeclinePercentage(IncomeDeclinePercentage incomeDeclinePercentage) {
		this.incomeDeclinePercentage = incomeDeclinePercentage;
	}	
}
