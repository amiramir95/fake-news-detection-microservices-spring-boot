package tn.fakenewsdetection.commentmicroservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.commentmicroservice.entities.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

	List<Vote> findByCommentId(int commentId);

	Optional<Vote> findByCommentIdAndUpvoterId(int commentId, int upvoterId);

}
