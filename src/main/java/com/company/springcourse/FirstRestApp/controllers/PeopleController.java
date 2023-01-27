package com.company.springcourse.FirstRestApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.springcourse.FirstRestApp.models.Person;
import com.company.springcourse.FirstRestApp.services.PeopleService;
import com.company.springcourse.FirstRestApp.utils.PersonErrorResponse;
import com.company.springcourse.FirstRestApp.utils.PersonNotCreatedException;
import com.company.springcourse.FirstRestApp.utils.PersonNotFoundException;

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
	@ExceptionHandler
	private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
		PersonErrorResponse response = new PersonErrorResponse("Person with this id wasn't found!"
				,System.currentTimeMillis());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@PostMapping()
	public ResponseEntity<HttpStatus> create(@RequestBody @Valid Person person,BindingResult br){
		if(br.hasErrors()) {
			StringBuilder errorMess = new StringBuilder();
			List<FieldError> errors = br.getFieldErrors();
			for(FieldError error:errors) {
				errorMess.append(error.getField()).append("- ").append(error.getDefaultMessage()).append(", ");
			}
			throw new PersonNotCreatedException(errorMess.toString());
		}
		peopleService.save(person);
		//standard way(empty body and with status 200),or could return Person
		return ResponseEntity.ok(HttpStatus.OK); 
	}
	@ExceptionHandler
	private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
		PersonErrorResponse response = new PersonErrorResponse(e.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
}
