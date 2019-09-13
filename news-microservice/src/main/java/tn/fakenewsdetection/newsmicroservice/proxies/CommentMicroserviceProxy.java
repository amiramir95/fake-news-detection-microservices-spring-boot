package tn.fakenewsdetection.newsmicroservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "comment-microservice", url = "localhost:9002")
public interface CommentMicroserviceProxy {

	@DeleteMapping(value = "/comments/news/{newsId}")
	void deleteAllByNewsId(@PathVariable("newsId") int newsId);
}
