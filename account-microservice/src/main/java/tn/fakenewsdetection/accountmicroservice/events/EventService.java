package tn.fakenewsdetection.accountmicroservice.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.fakenewsdetection.accountmicroservice.entities.Account;
import tn.fakenewsdetection.accountmicroservice.events.models.AccountCreatedEvent;
import tn.fakenewsdetection.accountmicroservice.events.models.AccountDeletedEvent;
import tn.fakenewsdetection.accountmicroservice.events.models.AccountUpdatedEvent;
import tn.fakenewsdetection.accountmicroservice.events.models.UserRole;

@Component
public class EventService {

	@Autowired
	private EventDispatcher eventDispatcher;

	// private static Logger logger = LoggerFactory.getLogger(EventService.class);

	public void sendAccountCreatedEvent(Account newAccount, UserRole role) {
		if (role == UserRole.MEMBER) {
			AccountCreatedEvent memberAccountCreatedEvent = new AccountCreatedEvent(newAccount.getId(),
					newAccount.getUsername(), newAccount.getPassword(), UserRole.MEMBER);
			eventDispatcher.sendAccountCreatedEvent(memberAccountCreatedEvent);
		} else {
			AccountCreatedEvent adminAccountCreatedEvent = new AccountCreatedEvent(newAccount.getId(),
					newAccount.getUsername(), newAccount.getPassword(), UserRole.ADMIN);
			eventDispatcher.sendAccountCreatedEvent(adminAccountCreatedEvent);
		}
	}

	public void sendAccountUpdatedEvent(int accountId, String username) {
		AccountUpdatedEvent accountUpdatedEvent = new AccountUpdatedEvent(accountId, username);
		eventDispatcher.sendAccountUpdatedEvent(accountUpdatedEvent);
	}

	public void sendAccountDeletedEvent(int accountId) {
		AccountDeletedEvent accountDeletedEvent = new AccountDeletedEvent(accountId);
		eventDispatcher.sendAccountDeletedEvent(accountDeletedEvent);
	}

}
