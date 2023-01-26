package com.company.springcourse.FirstRestApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.springcourse.FirstRestApp.models.Person;
import com.company.springcourse.FirstRestApp.repositorise.PeopleRepository;

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
		return peopleRepository.findById(id).orElse(null);
	}
	
	
}
