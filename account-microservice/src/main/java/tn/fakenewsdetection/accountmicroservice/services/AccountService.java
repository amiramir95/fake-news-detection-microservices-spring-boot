package tn.fakenewsdetection.accountmicroservice.services;

import java.util.List;

import tn.fakenewsdetection.accountmicroservice.entities.Account;
import tn.fakenewsdetection.accountmicroservice.events.models.UserRole;

public interface AccountService {

	void save(Account account, UserRole role);

	List<Account> findAll();

	Account findById(int id);

	Account findByUsername(String username);

	void update(Account account);

	void delete(int id);

}
