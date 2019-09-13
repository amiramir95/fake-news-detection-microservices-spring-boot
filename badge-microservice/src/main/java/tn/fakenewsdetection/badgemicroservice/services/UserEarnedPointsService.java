package tn.fakenewsdetection.badgemicroservice.services;

import java.util.List;

import tn.fakenewsdetection.badgemicroservice.entities.Category;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;;

public interface UserEarnedPointsService {

	void save(UserEarnedPoints userPointCategory);

	UserEarnedPoints findByUserIdAndCategory(int userId, Category category);

	UserEarnedPoints updatePoints(int userId, Category category, boolean isAdd);

	List<UserEarnedPoints> findByUserId(int userId);

}
