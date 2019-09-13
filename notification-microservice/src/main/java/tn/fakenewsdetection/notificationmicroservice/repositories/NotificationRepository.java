package tn.fakenewsdetection.notificationmicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.notificationmicroservice.entities.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	List<Notification> findByReceiverId(int receiverId);

	List<Notification> findBySenderUsername(String senderUsername);
}
