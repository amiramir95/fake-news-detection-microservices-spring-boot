package tn.fakenewsdetection.newsmicroservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("my-configs")
public class ApplicationPropertiesConfiguration {

	private int newsLimit;

	public ApplicationPropertiesConfiguration() {
		// TODO Auto-generated constructor stub
	}

	public int getNewsLimit() {
		return newsLimit;
	}

	public void setNewsLimit(int newsLimit) {
		this.newsLimit = newsLimit;
	}

}
