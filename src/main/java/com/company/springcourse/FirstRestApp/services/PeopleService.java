package com.company.springcourse.FirstRestApp.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.springcourse.FirstRestApp.dto.PersonDTO;
import com.company.springcourse.FirstRestApp.models.Person;
import com.company.springcourse.FirstRestApp.repositorise.PeopleRepository;
import com.company.springcourse.FirstRestApp.utils.PersonNotFoundException;

@Service
@Transactional(readOnly = true)
public class PeopleService {
	private final PeopleRepository peopleRepository;
	
	@Autowired
	public PeopleService(PeopleRepository peopleRepository) {
		super();
		this.peopleRepository = peopleRepository;
	}

	public List<Person> findAll(){
		return peopleRepository.findAll();
	}
	public Person findOne(int id){
		return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
	}
	@Transactional
	public void save(Person person) {
		enrichPerson(person);
		peopleRepository.save(person);
	}
	public void enrichPerson(Person person) {
		person.setCreatedAt(LocalDateTime.now());
		person.setUpdatedAt(LocalDateTime.now());
		person.setCreatedWho("ADMIN");
	}
	
	
}
