package tn.fakenewsdetection.newsmicroservice.services;

import java.util.List;

import tn.fakenewsdetection.newsmicroservice.entities.News;

public interface NewsService {

	void save(News news);

	List<News> findAll();

	News findById(int id);

	void update(News news);

	void delete(int id);
}
