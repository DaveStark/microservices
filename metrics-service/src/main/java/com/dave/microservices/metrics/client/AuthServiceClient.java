package com.dave.microservices.metrics.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dave.microservices.metrics.domain.User;

//Dave: Feign is a declarative web service client. It makes writing web service clients easier.
//source; http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html#spring-cloud-feign

/**
 * this is the client that consumes the "auth-service"
 * 
 * @author davestark
 *
 */
@FeignClient(name = "auth-service")
public interface AuthServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/uaa/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	void createUser(User user);
}
