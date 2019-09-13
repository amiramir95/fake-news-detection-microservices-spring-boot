package tn.fakenewsdetection.accountmicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fakenewsdetection.accountmicroservice.entities.Account;
import tn.fakenewsdetection.accountmicroservice.events.models.UserRole;
import tn.fakenewsdetection.accountmicroservice.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/register-member")
	public ResponseEntity<Account> registerMember(@RequestBody Account memberAccount) {
		accountService.save(memberAccount, UserRole.MEMBER);
		return new ResponseEntity<Account>(memberAccount, HttpStatus.CREATED);
	}

	@PostMapping("/add-admin")
	public ResponseEntity<Account> addAdmin(@RequestBody Account adminAccount) {
		accountService.save(adminAccount, UserRole.ADMIN);
		return new ResponseEntity<Account>(adminAccount, HttpStatus.CREATED);
	}

	@GetMapping
	public List<Account> findAllAccount() {
		return accountService.findAll();
	}

	@GetMapping("/{id}")
	public Account findAccountById(@PathVariable int id) {
		return accountService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable int id) {
		account.setId(id);
		accountService.update(account);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
		accountService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
