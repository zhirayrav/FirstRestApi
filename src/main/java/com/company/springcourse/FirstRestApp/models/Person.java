package com.company.springcourse.FirstRestApp.models;



import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Entity
@Table(name = "Person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	  
	    @Column(name = "name")
	    @NotEmpty(message = "Name shoud not be empty")
	    @Size(min = 2,max = 100,message = "Name shoud be between 2 and 100 characters ")
	    private String name;

	    @Column(name = "age")
	    @Min(value = 0,message = "age shoud be greater than 0")
	    private int age;
	    
	    @Column(name = "email")
	    @Email
	    @NotEmpty(message = "Email shoud not be empty")
	    private String email;
	    @Column(name = "created_at")
	    private LocalDateTime createdAt;
	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;
	    @Column(name = "created_who")
	    private String createdWho;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

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

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDateTime getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
		}

		public String getCreatedWho() {
			return createdWho;
		}

		public void setCreatedWho(String createdWho) {
			this.createdWho = createdWho;
		}
		

	
	
	
	
}
