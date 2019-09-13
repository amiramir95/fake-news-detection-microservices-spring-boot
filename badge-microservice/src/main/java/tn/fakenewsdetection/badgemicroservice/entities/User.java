package tn.fakenewsdetection.badgemicroservice.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "User")
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_badge", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "badge_id"))
	private List<Badge> awardedBadges;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<UserEarnedPoints> earnedPoints;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Badge> getAwardedBadges() {
		return awardedBadges;
	}

	public void removeAwardedBadge(Badge awardedBadge) {
		this.awardedBadges.remove(awardedBadge);
		awardedBadge.getAwardedUsers().remove(this);
	}

	public void remove() {
		for (Badge awardedBadge : this.awardedBadges) {
			removeAwardedBadge(awardedBadge);
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", awardedBadges=" + awardedBadges + "]";
	}

}
