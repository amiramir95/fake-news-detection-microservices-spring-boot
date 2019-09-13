package tn.fakenewsdetection.commentmicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.commentmicroservice.entities.Vote;
import tn.fakenewsdetection.commentmicroservice.events.EventService;
import tn.fakenewsdetection.commentmicroservice.repositories.VoteRepository;
import tn.fakenewsdetection.commentmicroservice.services.VoteService;
import tn.fakenewsdetection.commentmicroservice.services.exceptions.UnableAddVoteException;
import tn.fakenewsdetection.commentmicroservice.services.exceptions.VoteNotFoundException;

@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Autowired
	private EventService eventService;

	// This method handles all voting action scenarios
	@Override
	@Transactional
	public void upvoteOrDownvote(Vote vote) {

		boolean isUpvote = false;

		Optional<Vote> voteExists = findByCommentIdAndUserId(vote.getComment().getId(), vote.getUpvoterId());
		// If vote doesn't exist save a new vote
		if (!voteExists.isPresent()) {
			Vote newVote = voteRepository.save(vote);
			if (newVote == null)
				throw new UnableAddVoteException("Unable to add vote");

			if (newVote.isVote())
				isUpvote = true;
		}
		// if vote exists
		else {
			Vote oldVote = voteExists.get();
			// if the old vote is different than the new vote then update the old vote
			if (oldVote.isVote() != vote.isVote()) {
				oldVote.setVote(vote.isVote());
				voteRepository.save(oldVote);

				if (vote.isVote())
					isUpvote = true;
			}
			// if the old vote is similar to the new vote then delete old vote
			else
				voteRepository.delete(oldVote);
		}

		// If Vote is an upvote send event
		if (isUpvote) {
			int commentId = vote.getComment().getId();
			int commentAuthorId = vote.getComment().getAuthor().getId();
			int upvoterId = vote.getUpvoterId();
			eventService.sendUpvoteCreatedEvent(commentId, commentAuthorId, upvoterId);
		}
	}

	@Override
	@Transactional
	public List<Vote> findByCommentId(int commentId) {
		List<Vote> votes = voteRepository.findByCommentId(commentId);
		if (votes.isEmpty())
			throw new VoteNotFoundException("No votes were found");
		return votes;
	}

	@Override
	@Transactional
	public Optional<Vote> findByCommentIdAndUserId(int commentId, int upvoterId) {
		return voteRepository.findByCommentIdAndUpvoterId(commentId, upvoterId);
	}

}
