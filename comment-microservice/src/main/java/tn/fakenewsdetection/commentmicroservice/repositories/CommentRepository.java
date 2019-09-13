package tn.fakenewsdetection.commentmicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.commentmicroservice.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByNewsId(int newsId);
}
