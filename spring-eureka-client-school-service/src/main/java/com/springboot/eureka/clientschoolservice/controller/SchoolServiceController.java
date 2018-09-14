package com.springboot.eureka.clientschoolservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SchoolServiceController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private RestTemplate springBeanRestTemplate;

	/*@Autowired
	private RestTemplate standardRestTemplate;

	@RequestMapping(value = "/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
	public String getStudents(@PathVariable String schoolname) {
		System.out.println("Getting School details for " + schoolname);
		String response = standardRestTemplate
				.exchange("http://student-service/getStudentDetailsForSchool/{schoolname}", HttpMethod.GET, null,
						new ParameterizedTypeReference<String>() {
						}, schoolname)
				.getBody();
		System.out.println("Response Received as " + response);
		return "School Name -  " + schoolname + " \n Student Details " + response;
	}*/

	@RequestMapping(value = "/getSchoolDetailsDis/{schoolname}", method = RequestMethod.GET)
	public String getStudentsDis(@PathVariable String schoolname) {
		System.out.println("Getting School details for " + schoolname);
		List<ServiceInstance> instances = discoveryClient.getInstances("student-service");
		URI uri = instances.get(0).getUri();
		String url = uri.toString() + "/getStudentDetailsForSchool/" + schoolname;
		System.out.println("getStudentsDis URI :: " + url);
		ResponseEntity<String> result = springBeanRestTemplate.getForEntity(url, String.class);
		if (result.getStatusCode() == HttpStatus.OK) {
			return "School Name -  " + schoolname + " \n Student Details " + result.getBody();
		} else {
			return null;
		}
	}

}
