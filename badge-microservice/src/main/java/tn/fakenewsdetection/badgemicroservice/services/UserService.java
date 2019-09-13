package tn.fakenewsdetection.badgemicroservice.services;

import org.springframework.stereotype.Service;

import tn.fakenewsdetection.badgemicroservice.entities.User;

@Service
public interface UserService {

	User save(User user);

	void update(User user);

	void delete(int id);

	User find(int id);

}
