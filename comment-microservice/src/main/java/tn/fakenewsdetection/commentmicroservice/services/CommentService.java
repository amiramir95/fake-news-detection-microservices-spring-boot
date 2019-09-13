package tn.fakenewsdetection.commentmicroservice.services;

import java.util.List;

import tn.fakenewsdetection.commentmicroservice.entities.Comment;

public interface CommentService {

	void save(Comment comment);

	List<Comment> findByNewsId(int newsId);
	
	public Comment findById(int id);
	
	void update(Comment comment);

	void delete(int id);

	void deleteByNewsId(int newsId);
}
