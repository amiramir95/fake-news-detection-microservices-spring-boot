package tn.fakenewsdetection.newsmicroservice.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "News")
@Table(name = "news")
public class News implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private NewsCategory category;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private NewsStatus status;

	@Column(name = "number_of_views")
	private int numberOfViews;

	public News() {
		// TODO Auto-generated constructor stub
	}

	public News(String title, String content, NewsCategory category, Date createdAt, NewsStatus status,
			int numberOfViews) {
		super();
		this.title = title;
		this.content = content;
		this.category = category;
		this.createdAt = createdAt;
		this.status = status;
		this.numberOfViews = numberOfViews;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NewsCategory getCategory() {
		return category;
	}

	public void setCategory(NewsCategory category) {
		this.category = category;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public NewsStatus getStatus() {
		return status;
	}

	public void setStatus(NewsStatus status) {
		this.status = status;
	}

	public int getNumberOfViews() {
		return numberOfViews;
	}

	public void setNumberOfViews(int numberOfViews) {
		this.numberOfViews = numberOfViews;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", createdAt=" + createdAt + ", status=" + status + ", numberOfViews=" + numberOfViews + "]";
	}
	
	

	

}
