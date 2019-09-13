package tn.fakenewsdetection.notificationmicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fakenewsdetection.notificationmicroservice.entities.Notification;
import tn.fakenewsdetection.notificationmicroservice.services.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	/*
	 * @PostMapping public ResponseEntity<Notification>
	 * createNotification(@RequestBody Notification notification) {
	 * notificationService.save(notification); return new
	 * ResponseEntity<Notification>(notification, HttpStatus.CREATED); }
	 */

	/*
	 * @GetMapping("/{id}") public Notification findNotificationById(@PathVariable
	 * int id) { return notificationService.findById(id); }
	 */

	@GetMapping("/{id}")
	public List<Notification> findByReceiverId(@PathVariable int receiverId) {
		return notificationService.findByReceiverId(receiverId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
		notificationService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
