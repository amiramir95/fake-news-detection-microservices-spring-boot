package tn.fakenewsdetection.notificationmicroservice.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;


//Deprecated
//@Entity(name = "User")
//@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@OneToMany(mappedBy = "user")
	private List<Notification> notifications;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username, List<Notification> notifications) {
		super();
		this.id = id;
		this.username = username;
		this.notifications = notifications;
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

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", notifications=" + notifications + "]";
	}

}
