package tn.fakenewsdetection.commentmicroservice.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fakenewsdetection.commentmicroservice.entities.Author;
import tn.fakenewsdetection.commentmicroservice.repositories.AuthorRepository;
import tn.fakenewsdetection.commentmicroservice.services.AuthorService;
import tn.fakenewsdetection.commentmicroservice.services.exceptions.AuthorNotFoundException;
import tn.fakenewsdetection.commentmicroservice.services.exceptions.UnableAddAuthorException;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	@Transactional
	public void save(Author author) {
		Author newAuthor = authorRepository.save(author);
		if (newAuthor == null)
			throw new UnableAddAuthorException("Unable to add Author");
	}

	@Override
	@Transactional
	public void update(Author author) {
		Optional<Author> authorOptional = authorRepository.findById(author.getId());
		if (!authorOptional.isPresent())
			throw new AuthorNotFoundException(
					"Impossible Update Operation : Author with ID" + " " + author.getId() + " " + "doesn't exist");
		authorRepository.save(author);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Optional<Author> author = authorRepository.findById(id);
		if (!author.isPresent())
			throw new AuthorNotFoundException(
					"Impossible Delete Operation : Author with ID" + " " + id + " " + " doesn't exist");
		authorRepository.deleteById(id);
	}

}
