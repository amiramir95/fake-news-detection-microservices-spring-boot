package tn.fakenewsdetection.newsmicroservice.events;

import java.io.Serializable;

public class NewsDeletedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int newsId;

	public NewsDeletedEvent() {
	}

	public NewsDeletedEvent(int newsId) {
		super();
		this.newsId = newsId;
	}

	public int getNewsId() {
		return newsId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + newsId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsDeletedEvent other = (NewsDeletedEvent) obj;
		if (newsId != other.newsId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsDeletedEvent [newsId=" + newsId + "]";
	}

}
