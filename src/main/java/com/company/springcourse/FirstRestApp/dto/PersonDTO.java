package com.company.springcourse.FirstRestApp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name shoud not be empty")
    @Size(min = 2,max = 100,message = "Name shoud be between 2 and 100 characters ")
    private String name;

    @Min(value = 0,message = "age shoud be greater than 0")
    private int age;
    
    @Email
    @NotEmpty(message = "Email shoud not be empty")
    private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
