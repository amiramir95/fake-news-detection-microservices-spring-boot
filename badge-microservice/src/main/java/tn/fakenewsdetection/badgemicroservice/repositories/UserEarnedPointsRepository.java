package tn.fakenewsdetection.badgemicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.badgemicroservice.entities.Category;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;

@Repository
public interface UserEarnedPointsRepository extends JpaRepository<UserEarnedPoints, Integer> {

	Optional<UserEarnedPoints> findByUserIdAndCategory(int userId, Category category);

	List<UserEarnedPoints> findByUserId(int userId);
}
