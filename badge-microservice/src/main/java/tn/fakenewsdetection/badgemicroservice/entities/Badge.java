package tn.fakenewsdetection.badgemicroservice.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Badge")
@Table(name = "badge")
public class Badge implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Enumerated(EnumType.STRING)
	private Category category;

	@Enumerated(EnumType.STRING)
	@Column(name = "rank")
	private BadgeRank rank;

	@Column(name = "required_points")
	private int requirePoints;

	@Column(name = "description")
	private String description;

	@Column(name = "picture")
	private String picture;

	@ManyToMany(mappedBy = "awardedBadges", fetch=FetchType.EAGER)
	private List<User> awardedUsers;

	public Badge() {

	}

	public Badge(String name, Category category, BadgeRank rank, int requirePoints, String description,
			String picture) {
		super();
		this.name = name;
		this.category = category;
		this.rank = rank;
		this.requirePoints = requirePoints;
		this.description = description;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BadgeRank getRank() {
		return rank;
	}

	public void setRank(BadgeRank rank) {
		this.rank = rank;
	}

	public int getRequirePoints() {
		return requirePoints;
	}

	public void setRequirePoints(int requirePoints) {
		this.requirePoints = requirePoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<User> getAwardedUsers() {
		return awardedUsers;
	}

	public void addAwardedUser(User awardedUser) {
		this.awardedUsers.add(awardedUser);
		awardedUser.getAwardedBadges().add(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awardedUsers == null) ? 0 : awardedUsers.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + requirePoints;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Badge other = (Badge) obj;
		if (awardedUsers == null) {
			if (other.awardedUsers != null)
				return false;
		} else if (!awardedUsers.equals(other.awardedUsers))
			return false;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
			return false;
		if (rank != other.rank)
			return false;
		if (requirePoints != other.requirePoints)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Badge [id=" + id + ", name=" + name + ", category=" + category + ", rank=" + rank + ", requirePoints="
				+ requirePoints + ", description=" + description + ", picture=" + picture + ", awardedUsers="
				+ awardedUsers + "]";
	}

}
