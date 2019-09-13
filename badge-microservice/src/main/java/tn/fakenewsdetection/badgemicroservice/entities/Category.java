package tn.fakenewsdetection.badgemicroservice.entities;

public enum Category {
	//CategoryName(PointsPerAction)
	COMMENT(10), UPVOTE(5);

	private int pointsPerAction;

	private Category(int pointsPerAction) {
		this.pointsPerAction = pointsPerAction;
	}

	public int getPointsPerAction() {
		return pointsPerAction;
	}

}
