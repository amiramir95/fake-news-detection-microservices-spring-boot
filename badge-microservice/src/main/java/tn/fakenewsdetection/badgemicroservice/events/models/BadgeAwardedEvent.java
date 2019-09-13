package tn.fakenewsdetection.badgemicroservice.events.models;

import java.io.Serializable;

public class BadgeAwardedEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;

	private int badgeId;

	public BadgeAwardedEvent() {

	}

	public BadgeAwardedEvent(int userId, int badgeId) {
		super();
		this.userId = userId;
		this.badgeId = badgeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(int badgeId) {
		this.badgeId = badgeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + badgeId;
		result = prime * result + userId;
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
		BadgeAwardedEvent other = (BadgeAwardedEvent) obj;
		if (badgeId != other.badgeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BadgeAwardedEvent [userId=" + userId + ", badgeId=" + badgeId + "]";
	}

}
