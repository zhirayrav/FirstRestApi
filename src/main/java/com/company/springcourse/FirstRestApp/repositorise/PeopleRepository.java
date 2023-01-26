package com.company.springcourse.FirstRestApp.repositorise;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.springcourse.FirstRestApp.models.Person;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{

}
