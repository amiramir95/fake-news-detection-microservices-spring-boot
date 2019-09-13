package tn.fakenewsdetection.badgemicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.badgemicroservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
