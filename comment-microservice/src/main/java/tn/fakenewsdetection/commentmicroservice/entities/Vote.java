package tn.fakenewsdetection.commentmicroservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "Vote")
@Table(name = "vote")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "vote")
	private boolean vote;

	@Column(name = "upvoter_id")
	private int upvoterId;

	@ManyToOne
	private Comment comment;

	public Vote() {
		// TODO Auto-generated constructor stub
	}

	public Vote(int id, boolean vote, int upvoterId, Comment comment) {
		super();
		this.id = id;
		this.vote = vote;
		this.upvoterId = upvoterId;
		this.comment = comment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVote() {
		return vote;
	}

	public void setVote(boolean vote) {
		this.vote = vote;
	}

	public int getUpvoterId() {
		return upvoterId;
	}

	public void setUpvoterId(int upvoterId) {
		this.upvoterId = upvoterId;
	}

	public Comment getComment() {
		return comment;
	}

	public void setCommentaire(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", vote=" + vote + ", upvoterId=" + upvoterId + ", comment=" + comment + "]";
	}

}
