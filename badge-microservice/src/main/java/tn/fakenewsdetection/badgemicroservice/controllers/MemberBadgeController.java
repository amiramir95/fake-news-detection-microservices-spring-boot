package tn.fakenewsdetection.badgemicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fakenewsdetection.badgemicroservice.entities.Badge;
import tn.fakenewsdetection.badgemicroservice.services.BadgeService;
import tn.fakenewsdetection.badgemicroservice.services.UserService;

@RestController
@RequestMapping("/badges/member")
public class MemberBadgeController {

	@Autowired
	private BadgeService badgeService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<Badge> findAll() {
		return badgeService.findAll();
	}
	
	@GetMapping("/{id}")
	public Badge findById(int id) {
		return badgeService.findById(id);
	}

	@GetMapping("/user/{id}")
	public List<Badge> findByUser(int id) {
		return userService.find(id).getAwardedBadges();
	}
	
}
