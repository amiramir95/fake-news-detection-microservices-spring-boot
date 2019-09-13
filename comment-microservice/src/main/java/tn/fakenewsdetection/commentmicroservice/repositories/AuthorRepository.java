package tn.fakenewsdetection.commentmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.commentmicroservice.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
