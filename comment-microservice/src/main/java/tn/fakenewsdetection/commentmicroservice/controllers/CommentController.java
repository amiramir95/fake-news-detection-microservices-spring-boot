package tn.fakenewsdetection.commentmicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fakenewsdetection.commentmicroservice.entities.Comment;
import tn.fakenewsdetection.commentmicroservice.entities.Vote;
import tn.fakenewsdetection.commentmicroservice.services.CommentService;
import tn.fakenewsdetection.commentmicroservice.services.VoteService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private VoteService voteService;

	@PostMapping("/{newsId}")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable("newsId") int newsId) {
		comment.setNewsId(newsId);
		commentService.save(comment);
		return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
	}

	@GetMapping("/{newsId}")
	public List<Comment> findAllCommentByNewsId(@PathVariable int newsId) {
		return commentService.findByNewsId(newsId);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable int id) {
		comment.setId(id);
		commentService.update(comment);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable int id) {
		commentService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value ="/news/{newsId}")
	public ResponseEntity<Void> deleteAllByNewsId(@PathVariable("newsId") int newsId) {
		commentService.deleteByNewsId(newsId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@PostMapping("/votes/{commentId}")
	public ResponseEntity<Vote> upvoteOrDownvote(@RequestBody Vote vote, @PathVariable int commentId) {
		vote.setCommentaire(commentService.findById(commentId));
		voteService.upvoteOrDownvote(vote);
		return new ResponseEntity<Vote>(vote, HttpStatus.CREATED);
	}

	@GetMapping("/votes/{commentId}/numberOfUpvotes")
	public int getNumberOfUpvoteByCommentId(@PathVariable int commentId) {
		List<Vote> votes = voteService.findByCommentId(commentId);
		int numberOfUpvotes = 0;

		for (Vote vote : votes)
			if (vote.isVote())
				numberOfUpvotes++;

		return numberOfUpvotes;
	}

	@GetMapping("/{commentId}/numberOfDownvotes")
	public int getNumberOfDownvoteByCommentId(@PathVariable int commentId) {
		List<Vote> votes = voteService.findByCommentId(commentId);
		int numberOfDownvotes = 0;

		for (Vote vote : votes)
			if (!vote.isVote())
				numberOfDownvotes++;

		return numberOfDownvotes;
	}

}
