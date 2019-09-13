package tn.fakenewsdetection.notificationmicroservice.entities;

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

@Entity(name = "Notification")
@Table(name = "notification")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

	private int receiverId;

	private String senderUsername;

	private boolean isRead;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private NotificationType type;

	@Column(name = "resource_id")
	private int resourceId;

	public Notification() {
		// TODO Auto-generated constructor stub
	}

	public Notification(int receiverId, NotificationType type, int resourceId) {
		super();
		this.receiverId = receiverId;
		this.type = type;
		this.resourceId = resourceId;
		this.createdAt = new Date();
		this.isRead = false;
	}

	public Notification(int receiverId, String senderUsername, NotificationType type, int resourceId) {
		super();
		this.receiverId = receiverId;
		this.senderUsername = senderUsername;
		this.type = type;
		this.resourceId = resourceId;
		this.createdAt = new Date();
		this.isRead = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", createdAt=" + createdAt + ", receiverId=" + receiverId
				+ ", senderUsername=" + senderUsername + ", isRead=" + isRead + ", type=" + type + ", resourceId="
				+ resourceId + "]";
	}

}
