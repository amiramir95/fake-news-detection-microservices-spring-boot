package tn.fakenewsdetection.commentmicroservice.services;

import tn.fakenewsdetection.commentmicroservice.entities.Author;

public interface AuthorService {

	void save(Author author);

	void update(Author author);

	void delete(int id);
}
