package tn.fakenewsdetection.accountmicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.accountmicroservice.entities.Account;
import tn.fakenewsdetection.accountmicroservice.events.EventService;
import tn.fakenewsdetection.accountmicroservice.events.models.UserRole;
import tn.fakenewsdetection.accountmicroservice.repositories.AccountRepository;
import tn.fakenewsdetection.accountmicroservice.services.AccountService;
import tn.fakenewsdetection.accountmicroservice.services.exceptions.AccountNotFoundException;
import tn.fakenewsdetection.accountmicroservice.services.exceptions.UnableCreateAccountException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	EventService eventService;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	@Transactional
	public void save(Account account, UserRole role) {
		Account newAccount = accountRepository.save(account);
		if (newAccount == null)
			throw new UnableCreateAccountException("Unable to create account");

		eventService.sendAccountCreatedEvent(newAccount, role);
	}

	@Override
	@Transactional
	public List<Account> findAll() {
		List<Account> accounts = accountRepository.findAll();
		if (accounts.isEmpty())
			throw new AccountNotFoundException("Empty database: No accounts were found");
		return accounts;
	}

	@Override
	@Transactional
	public Account findById(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if (!account.isPresent())
			throw new AccountNotFoundException("Account not found");

		return account.get();
	}

	@Override
	@Transactional
	public Account findByUsername(String username) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (!account.isPresent())
			throw new AccountNotFoundException("Account not found");

		return account.get();
	}

	@Override
	@Transactional
	public void update(Account account) {
		Optional<Account> accountOptional = accountRepository.findById(account.getId());

		if (!accountOptional.isPresent())
			throw new AccountNotFoundException("Update Operation Impossible :Account not found");

		String oldUsername = accountOptional.get().getUsername();
		accountRepository.save(account);

		if (!account.getUsername().equals(oldUsername))
			eventService.sendAccountUpdatedEvent(account.getId(), account.getUsername());

	}

	@Override
	@Transactional
	public void delete(int id) {
		Optional<Account> account = accountRepository.findById(id);
		if (!account.isPresent())
			throw new AccountNotFoundException("Delete Operation is Impossible : Account not found");
		accountRepository.deleteById(id);
		eventService.sendAccountDeletedEvent(id);
	}

}
