package tn.fakenewsdetection.commentmicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.commentmicroservice.entities.Comment;
import tn.fakenewsdetection.commentmicroservice.events.EventService;
import tn.fakenewsdetection.commentmicroservice.repositories.CommentRepository;
import tn.fakenewsdetection.commentmicroservice.services.CommentService;
import tn.fakenewsdetection.commentmicroservice.services.exceptions.CommentNotFoundException;
import tn.fakenewsdetection.commentmicroservice.services.exceptions.UnableAddCommentException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	EventService eventService;

	@Override
	@Transactional
	public void save(Comment comment) {
		Comment newComment = commentRepository.save(comment);
		if (newComment == null)
			throw new UnableAddCommentException("Unable to add comment");
		// Sending event to badge service to add points
		eventService.sendCommentCreatedEvent(comment.getAuthor().getId());	
		// if it's a response notify the parent comment author
		// TEsting
		newComment.setParent(commentRepository.findById(1).get());
		//
		if (newComment.getParent() != null) {
			int parentCommentId = newComment.getParent().getId();
			int parentCommentAuthorId = newComment.getParent().getAuthor().getId();
			String replyAuthorUsername = newComment.getAuthor().getUsername();
			eventService.sendReplyCreatedEvent(parentCommentId, parentCommentAuthorId, replyAuthorUsername);
		}
	}

	@Override
	@Transactional
	public List<Comment> findByNewsId(int newsId) {
		List<Comment> comments = commentRepository.findByNewsId(newsId);
		if (comments.isEmpty())
			throw new CommentNotFoundException("No Comments were found for News with Id" + " " + newsId);
		return comments;
	}

	@Override
	@Transactional
	public Comment findById(int id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (!comment.isPresent())
			throw new CommentNotFoundException("Comment with ID" + " " + id + " " + "doesn't exist");

		return comment.get();
	}

	@Override
	@Transactional
	public void update(Comment comment) {
		Optional<Comment> commentOptional = commentRepository.findById(comment.getId());
		if (!commentOptional.isPresent())
			throw new CommentNotFoundException(
					"Impossible Update Operation : Comment with ID" + " " + comment.getId() + " " + "doesn't exist");
		commentRepository.save(comment);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Optional<Comment> comment = commentRepository.findById(id);
		int userId = comment.get().getAuthor().getId();
		if (!comment.isPresent())
			throw new CommentNotFoundException(
					"Impossible Update Operation : Comment with ID" + " " + id + " " + " doesn't exist");
		commentRepository.deleteById(id);
		// Sending event to badge service to substract points
		eventService.sendCommentCreatedEvent(userId);
	}

	@Override
	@Transactional
	public void deleteByNewsId(int newsId) {
		List<Comment> comments = findByNewsId(newsId);
		commentRepository.deleteAll(comments);
	}

}
