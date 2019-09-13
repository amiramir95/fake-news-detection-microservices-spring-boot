package tn.fakenewsdetection.newsmicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.fakenewsdetection.newsmicroservice.entities.News;
import tn.fakenewsdetection.newsmicroservice.events.EventService;
import tn.fakenewsdetection.newsmicroservice.proxies.CommentMicroserviceProxy;
import tn.fakenewsdetection.newsmicroservice.repositories.NewsRepository;
import tn.fakenewsdetection.newsmicroservice.services.NewsService;
import tn.fakenewsdetection.newsmicroservice.services.exceptions.NewsNotFoundException;
import tn.fakenewsdetection.newsmicroservice.services.exceptions.UnableAddNewsException;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	EventService eventService;
	
	@Autowired
	CommentMicroserviceProxy commentMicroserviceProxy;

	@Override
	@Transactional
	public void save(News news) {
		News newNews = newsRepository.save(news);
		if (newNews == null)
			throw new UnableAddNewsException("Unable to add news");

	}

	@Override
	@Transactional
	public List<News> findAll() {
		List<News> allNews = newsRepository.findAll();
		if (allNews.isEmpty())
			throw new NewsNotFoundException("Empty database: No news were found");
		return allNews;
	}

	@Override
	@Transactional
	public News findById(int id) {
		Optional<News> news = newsRepository.findById(id);
		if (!news.isPresent())
			throw new NewsNotFoundException("News with ID" + " " + id + " " + "doesn't exist");

		return news.get();
	}

	@Override
	@Transactional
	public void update(News news) {
		Optional<News> newsOptional = newsRepository.findById(news.getId());

		if (!newsOptional.isPresent())
			throw new NewsNotFoundException(
					"Impossible Update Operation : News with ID" + " " + news.getId() + " " + "doesn't exist");

		newsRepository.save(news);
	}

	@Override
	@Transactional
	public void delete(int id) {
		Optional<News> news = newsRepository.findById(id);
		if (!news.isPresent())
			throw new NewsNotFoundException(
					"Impossible Update Operation : News with ID" + " " + id + " " + " doesn't exist");
		newsRepository.deleteById(id);
		commentMicroserviceProxy.deleteAllByNewsId(id);
		//eventService.sendNewsDeletedEvent(id);
	}

}
