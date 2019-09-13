package tn.fakenewsdetection.badgemicroservice.entities;

public enum BadgeRank {

	CHALLENGER(1), GRANDMASTER(2), MASTER(3), DIAMOND(4), PLATINUM(5), GOLD(6), SILVER(7), BRONZE(8), IRON(9);

	private int priority;

	private BadgeRank(int priority) {
		this.priority = priority;
	}

	public int getPriority() {
		return priority;
	}
}
