package tn.fakenewsdetection.badgemicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.badgemicroservice.entities.Badge;
import tn.fakenewsdetection.badgemicroservice.entities.Category;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {
	List<Badge> findByCategory(Category category);
}
