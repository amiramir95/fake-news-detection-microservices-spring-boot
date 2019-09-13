package tn.fakenewsdetection.newsmicroservice.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fakenewsdetection.newsmicroservice.configurations.ApplicationPropertiesConfiguration;
import tn.fakenewsdetection.newsmicroservice.entities.News;
import tn.fakenewsdetection.newsmicroservice.services.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController implements HealthIndicator {

	// For testing purposes
	Logger log = LoggerFactory.getLogger(this.getClass());
	//

	@Autowired
	private NewsService newsService;

	@Autowired
	ApplicationPropertiesConfiguration appProperties;

	@PostMapping
	public ResponseEntity<News> createNews(@RequestBody News news) {
		newsService.save(news);
		return new ResponseEntity<News>(news, HttpStatus.CREATED);
	}

	@GetMapping
	public List<News> findAllNews() {
		List<News> news = newsService.findAll();
		if (news.size() > 4)
			news = news.subList(0, appProperties.getNewsLimit());
		log.info("Retrieving News List");
		return news;
		// return newsService.findAll();
	}

	@GetMapping("/{id}")
	public News findNewsById(@PathVariable int id) {
		return newsService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<News> updateNews(@RequestBody News news, @PathVariable int id) {
		news.setId(id);
		newsService.update(news);
		return new ResponseEntity<News>(news, HttpStatus.OK);
	}

	// DON'T FORGET TO HANDLE FEIGN EXCEPTIONS AND GO BACK TO OPENCLASSROOM COURSE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteNews(@PathVariable int id) {
		newsService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@Override
	public Health health() {

		/*
		 * List<News> news = newsService.findAll(); if (news.isEmpty()) return
		 * Health.down().build();
		 */

		return Health.up().build();
	}

}
