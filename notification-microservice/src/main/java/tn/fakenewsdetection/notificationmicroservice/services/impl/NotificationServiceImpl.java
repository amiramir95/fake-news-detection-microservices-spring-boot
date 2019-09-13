package tn.fakenewsdetection.notificationmicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.notificationmicroservice.entities.Notification;
import tn.fakenewsdetection.notificationmicroservice.repositories.NotificationRepository;
import tn.fakenewsdetection.notificationmicroservice.services.NotificationService;
import tn.fakenewsdetection.notificationmicroservice.services.exceptions.NotificationNotFoundException;
import tn.fakenewsdetection.notificationmicroservice.services.exceptions.UnableAddNotificationException;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	@Transactional
	public void save(Notification notification) {
		Notification newNotification = notificationRepository.save(notification);
		if (newNotification == null)
			throw new UnableAddNotificationException("Unable to create notification");
	}

	@Override
	@Transactional
	public Notification findById(int id) {
		Optional<Notification> notification = notificationRepository.findById(id);
		if (!notification.isPresent())
			throw new NotificationNotFoundException("Account not found");

		return notification.get();
	}

	@Override
	@Transactional
	public List<Notification> findByReceiverId(int receiverId) {
		List<Notification> notifications = notificationRepository.findByReceiverId(receiverId);
		if (notifications.isEmpty())
			throw new NotificationNotFoundException("Empty Database : No notifications were found");

		return notifications;
	}

	@Override
	@Transactional
	public void delete(int id) {
		Optional<Notification> notification = notificationRepository.findById(id);
		if (!notification.isPresent())
			throw new NotificationNotFoundException("Delete Operation is Impossible : Notification not found");
		notificationRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public void deleteByReceiverId(int receiverId) {
		List<Notification> notifications = notificationRepository.findByReceiverId(receiverId);
		if (notifications.isEmpty())
			throw new NotificationNotFoundException("Delete Operation is Impossible : Notification not found");
		notificationRepository.deleteAll(notifications);
	}

	@Override
	@Transactional
	public List<Notification> findBySenderUsername(String senderUsername) {
		List<Notification> notifications = notificationRepository.findBySenderUsername(senderUsername);
		if (notifications.isEmpty())
			throw new NotificationNotFoundException("Empty Database : No notifications were found");

		return notifications;
	}

	@Override
	@Transactional
	public void updateSenderUsername(String senderUsername) {
		List<Notification> notifications = findBySenderUsername(senderUsername);
		if (notifications.isEmpty())
			throw new NotificationNotFoundException("Empty Database : No notifications were found");

		for (Notification notification : notifications)
			notification.setSenderUsername(senderUsername);

		notificationRepository.saveAll(notifications);
	}

}
