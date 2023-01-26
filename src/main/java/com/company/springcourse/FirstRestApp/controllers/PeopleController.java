package com.company.springcourse.FirstRestApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.springcourse.FirstRestApp.models.Person;
import com.company.springcourse.FirstRestApp.services.PeopleService;

@RestController
@RequestMapping("/people")
public class PeopleController {
	
	private final PeopleService peopleService;
	@Autowired
	public PeopleController(PeopleService peopleService) {
		super();
		this.peopleService = peopleService;
	}

	@GetMapping()
	public List<Person> getPeople(){
		return peopleService.findAll();                     //jackson converts these objects to JSON
	}
	@GetMapping("/{id}")
	public Person getPerson(@PathVariable int id) {
		return peopleService.findOne(id);                  //and here too
	}
	
}
