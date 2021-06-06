package hu.webuni.transport.gallz.config;

import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "transports")
@Component
public class TransportConfigProperties {
	
	private IncomeDeclinePercentage incomeDeclinePercentage = new IncomeDeclinePercentage();
	
	public static class IncomeDeclinePercentage {
		
		private TreeMap<Integer, Integer> limits;
				
		public TreeMap<Integer, Integer> getLimits() {
			return limits;
		}
		
		public void setLimits(TreeMap<Integer, Integer> limits) {
			this.limits = limits;
		}
		
	}

	public IncomeDeclinePercentage getIncomeDeclinePercentage() {
		return incomeDeclinePercentage;
	}

	public void setIncomeDeclinePercentage(IncomeDeclinePercentage incomeDeclinePercentage) {
		this.incomeDeclinePercentage = incomeDeclinePercentage;
	}	
}
