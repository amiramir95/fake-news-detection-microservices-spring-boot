package tn.fakenewsdetection.badgemicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.badgemicroservice.entities.Badge;
import tn.fakenewsdetection.badgemicroservice.entities.User;
import tn.fakenewsdetection.badgemicroservice.entities.UserEarnedPoints;
import tn.fakenewsdetection.badgemicroservice.repositories.BadgeRepository;
import tn.fakenewsdetection.badgemicroservice.services.BadgeService;
import tn.fakenewsdetection.badgemicroservice.services.UserService;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.BadgeNotFoundException;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.InvalidBadgeUserInputException;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.UnableAddBadgeException;

@Service
public class BadgeServiceImpl implements BadgeService {

	@Autowired
	private BadgeRepository badgeRepository;
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public void save(Badge badge) {
		List<Badge> badges = badgeRepository.findByCategory(badge.getCategory());

		if (!badges.isEmpty()) {
			for (Badge tempBadge : badges) {
				if (badge.getRequirePoints() == tempBadge.getRequirePoints())
					throw new InvalidBadgeUserInputException(
							"Invalid Input : 2 Badges in the same category can't have the same number of required points");

				if (badge.getRank() == tempBadge.getRank())
					throw new InvalidBadgeUserInputException(
							"Invalid Input : 2 Badges in the same category can't have the same rank");

				if (badge.getRank().getPriority() > tempBadge.getRank().getPriority()) // Priority is inversed e.g 1 > 2
					if (badge.getRequirePoints() < tempBadge.getRequirePoints())
						throw new InvalidBadgeUserInputException(
								"Unable to add badge : A badge with a higher rank and less required points already exists");
			}
		}

		Badge newBadge = badgeRepository.save(badge);
		if (newBadge == null)
			throw new UnableAddBadgeException("Unable to add badge");
	}

	@Override
	@Transactional
	public List<Badge> findAll() {
		// TODO Auto-generated method stub
		List<Badge> badges = badgeRepository.findAll();
		if (badges.isEmpty())
			throw new BadgeNotFoundException("Empty database: No badge were found");
		return badges;
	}

	@Override
	@Transactional
	public Badge checkBadgeWon(UserEarnedPoints userEarnedPoints) {

		List<Badge> availableBadges = badgeRepository.findByCategory(userEarnedPoints.getCategory());
		if (availableBadges.isEmpty())
			return null;

		User user = userService.find(userEarnedPoints.getUser().getId());
		List<Badge> awardedBadges = user.getAwardedBadges();

		if (!awardedBadges.isEmpty())
			availableBadges.removeAll(awardedBadges);

		if (!availableBadges.isEmpty())
			for (Badge badge : availableBadges) {
				if (userEarnedPoints.getPoints() >= badge.getRequirePoints()) {
					badge.addAwardedUser(user);
					return badgeRepository.save(badge);
				}
			}

		return null;
	}

	@Override
	@Transactional
	public Badge findById(int id) {
		// TODO Auto-generated method stub
		Optional<Badge> badge = badgeRepository.findById(id);
		if (!badge.isPresent())
			throw new BadgeNotFoundException("Badge with ID" + " " + id + " " + "doesn't exist");

		return badge.get();
	}

	@Override
	@Transactional
	public void update(Badge badge) {
		// TODO Auto-generated method stub
		Optional<Badge> badgeOptional = badgeRepository.findById(badge.getId());
		if (!badgeOptional.isPresent())
			throw new BadgeNotFoundException(
					"Impossible Update Operation : Comment with ID" + " " + badge.getId() + " " + "doesn't exist");
		badgeRepository.save(badge);
	}

}
