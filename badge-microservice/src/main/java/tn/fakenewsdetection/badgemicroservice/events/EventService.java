package tn.fakenewsdetection.badgemicroservice.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.badgemicroservice.events.models.BadgeAwardedEvent;

@Component
public class EventService {

	@Autowired
	private EventDispatcher eventDispatcher;

	public void sendBadgeAwardedEvent(int userId, int badgeId) {
		BadgeAwardedEvent badgeAwardedEvent = new BadgeAwardedEvent(userId, badgeId);
		eventDispatcher.sendBadgeAwardedEvent(badgeAwardedEvent);
	}

}
