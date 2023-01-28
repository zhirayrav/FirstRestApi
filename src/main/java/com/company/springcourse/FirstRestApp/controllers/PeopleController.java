package com.company.springcourse.FirstRestApp.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.company.springcourse.FirstRestApp.dto.PersonDTO;
import com.company.springcourse.FirstRestApp.models.Person;
import com.company.springcourse.FirstRestApp.services.PeopleService;
import com.company.springcourse.FirstRestApp.utils.PersonErrorResponse;
import com.company.springcourse.FirstRestApp.utils.PersonNotCreatedException;
import com.company.springcourse.FirstRestApp.utils.PersonNotFoundException;

@RestController
@RequestMapping("/people")
public class PeopleController {
	
	private final PeopleService peopleService;
	private final ModelMapper modelMapper;
	@Autowired
	public PeopleController(PeopleService peopleService,ModelMapper modelMapper) {
		super();
		this.peopleService = peopleService;
		this.modelMapper = modelMapper;
	}

	@GetMapping()
	public List<PersonDTO> getPeople(){
		return peopleService.findAll().stream().map(this::convertToPersonDTO)
				.collect(Collectors.toList());                    //jackson converts these objects to JSON
	}
	@GetMapping("/{id}")
	public PersonDTO getPerson(@PathVariable int id) {
		return convertToPersonDTO(peopleService.findOne(id));                  //and here too
	}
	@ExceptionHandler
	private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
		PersonErrorResponse response = new PersonErrorResponse("Person with this id wasn't found!"
				,System.currentTimeMillis());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@PostMapping()
	public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,BindingResult br){
		if(br.hasErrors()) {
			StringBuilder errorMess = new StringBuilder();
			List<FieldError> errors = br.getFieldErrors();
			for(FieldError error:errors) {
				errorMess.append(error.getField()).append("- ").append(error.getDefaultMessage()).append(", ");
			}
			throw new PersonNotCreatedException(errorMess.toString());
		}
		peopleService.save(convertToPerson(personDTO));
		//standard way(empty body and with status 200),or could return Person
		return ResponseEntity.ok(HttpStatus.OK); 
	}
	private Person convertToPerson(PersonDTO personDTO) {
		return modelMapper.map(personDTO, Person.class);
	}
	private PersonDTO convertToPersonDTO(Person person) {
		return modelMapper.map(person, PersonDTO.class);
	
	}
	@ExceptionHandler
	private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
		PersonErrorResponse response = new PersonErrorResponse(e.getMessage(),System.currentTimeMillis());
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
}
