package tn.fakenewsdetection.badgemicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.badgemicroservice.entities.Category;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;
import tn.fakenewsdetection.badgemicroservice.repositories.UserEarnedPointsRepository;
import tn.fakenewsdetection.badgemicroservice.services.UserEarnedPointsService;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.UnableAddUserEarnedPointsException;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.UserEarnedPointsNotFoundException;

@Service
public class UserEarnedPointsServiceImpl implements UserEarnedPointsService {

	@Autowired
	private UserEarnedPointsRepository userEarnedPointsRepository;

	@Override
	@Transactional
	public void save(UserEarnedPoints userEarnedPoints) {
		UserEarnedPoints newUserEarnedPoints = userEarnedPointsRepository.save(userEarnedPoints);
		if (newUserEarnedPoints == null)
			throw new UnableAddUserEarnedPointsException("Unable to add UserEarnedPoints");
	}

	@Override
	public UserEarnedPoints findByUserIdAndCategory(int userId, Category category) {
		Optional<UserEarnedPoints> userEarnedPoints = userEarnedPointsRepository.findByUserIdAndCategory(userId,
				category);
		if (!userEarnedPoints.isPresent())
			throw new UserEarnedPointsNotFoundException("UserEarnedPoints with UserId" + " " + userId + " " + "Category"
					+ " " + category + " " + "doesn't exist");

		return userEarnedPoints.get();
	}

	@Override
	@Transactional
	public UserEarnedPoints updatePoints(int userId, Category category, boolean isAdd) {
		UserEarnedPoints userEarnedPoints = findByUserIdAndCategory(userId, category);
		if (isAdd)
			userEarnedPoints.setPoints(userEarnedPoints.getPoints() + category.getPointsPerAction());
		else
			userEarnedPoints.setPoints(userEarnedPoints.getPoints() - category.getPointsPerAction());
		return userEarnedPointsRepository.save(userEarnedPoints);
	}

	@Override
	@Transactional
	public List<UserEarnedPoints> findByUserId(int userId) {

		List<UserEarnedPoints> userEarnedPointsList = userEarnedPointsRepository.findByUserId(userId);

		if (userEarnedPointsList.isEmpty())
			throw new UserEarnedPointsNotFoundException("Empty database: No UserEarnedPoints were found");
		return userEarnedPointsList;
	}

}
