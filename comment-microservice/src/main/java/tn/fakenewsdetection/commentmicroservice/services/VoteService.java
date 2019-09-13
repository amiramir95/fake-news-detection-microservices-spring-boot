package tn.fakenewsdetection.commentmicroservice.services;

import java.util.List;
import java.util.Optional;

import tn.fakenewsdetection.commentmicroservice.entities.Vote;

public interface VoteService {

	void upvoteOrDownvote(Vote vote);

	List<Vote> findByCommentId(int commentId);

	Optional<Vote> findByCommentIdAndUserId(int commentId, int upvoterId);
}
