package tn.fakenewsdetection.newsmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.fakenewsdetection.newsmicroservice.entities.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

}
