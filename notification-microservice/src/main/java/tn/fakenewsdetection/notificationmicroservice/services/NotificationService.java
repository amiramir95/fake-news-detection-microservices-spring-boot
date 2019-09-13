package tn.fakenewsdetection.notificationmicroservice.services;

import java.util.List;

import tn.fakenewsdetection.notificationmicroservice.entities.Notification;

public interface NotificationService {

	void save(Notification notification);

	Notification findById(int id);

	void delete(int id);

	List<Notification> findByReceiverId(int receiverId);

	List<Notification> findBySenderUsername(String senderUsername);

	void updateSenderUsername(String senderUsername);

	void deleteByReceiverId(int receiverId);

}
