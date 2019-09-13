package tn.fakenewsdetection.newsmicroservice.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.newsmicroservice.events.NewsDeletedEvent;

@Component
public class EventService {

	@Autowired
	private EventDispatcher eventDispatcher;
	
	public void sendNewsDeletedEvent(int newsId) {
		NewsDeletedEvent newsDeletedEvent = new NewsDeletedEvent(newsId);
		eventDispatcher.sendNewsDeletedEvent(newsDeletedEvent);
	}

}
