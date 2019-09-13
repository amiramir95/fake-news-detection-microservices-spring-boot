package tn.fakenewsdetection.badgemicroservice.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.badgemicroservice.entities.User;
import tn.fakenewsdetection.badgemicroservice.repositories.UserRepository;
import tn.fakenewsdetection.badgemicroservice.services.UserService;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.UnableAddUserException;
import tn.fakenewsdetection.badgemicroservice.services.exceptions.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User save(User user) {
		User newUser = userRepository.save(user);
		if (newUser == null)
			throw new UnableAddUserException("Unable to add user");
		return newUser;
	}

	@Override
	@Transactional
	public void update(User user) {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findById(user.getId());
		if (!userOptional.isPresent())
			throw new UserNotFoundException(
					"Impossible Update Operation : Comment with ID" + " " + user.getId() + " " + "doesn't exist");
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(int id) {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException(
					"Impossible Delete Operation : User with ID" + " " + id + " " + " doesn't exist");

		User user = userOptional.get();
		user.remove(); // remove entries from the join table
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public User find(int id) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

}
