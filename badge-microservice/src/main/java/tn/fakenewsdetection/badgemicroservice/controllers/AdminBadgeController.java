package tn.fakenewsdetection.badgemicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fakenewsdetection.badgemicroservice.entities.Badge;
import tn.fakenewsdetection.badgemicroservice.services.BadgeService;

@RestController
@RequestMapping("/badges/admin")
public class AdminBadgeController {

	@Autowired
	private BadgeService badgeService;

	@PostMapping
	public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {
		badgeService.save(badge);
		return new ResponseEntity<Badge>(badge, HttpStatus.CREATED);
	}

	@GetMapping
	public List<Badge> findAll() {
		return badgeService.findAll();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Badge> updateBadge(@RequestBody Badge badge, @PathVariable int id) {
		badge.setId(id);
		badgeService.update(badge);
		return new ResponseEntity<Badge>(badge, HttpStatus.OK);
	}

}
