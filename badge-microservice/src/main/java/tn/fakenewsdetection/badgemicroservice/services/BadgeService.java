package tn.fakenewsdetection.badgemicroservice.services;

import java.util.List;

import tn.fakenewsdetection.badgemicroservice.entities.Badge;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;

public interface BadgeService {

	void save(Badge badge);

	List<Badge> findAll();

	Badge findById(int id);

	void update(Badge badge);

	Badge checkBadgeWon(UserEarnedPoints userEarnedPoints);

	// void SetAsExpired(int id);
}
