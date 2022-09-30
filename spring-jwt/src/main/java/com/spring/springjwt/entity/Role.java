package com.spring.springjwt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name="role_table")
public class Role {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer id;
	
	@Column(nullable = false,length = 50,unique = true)
	private String role;

	public Role(String string) {
      role=string;
	}

	@Override
	public String toString() {
		return this.role;
	}

	public Role(int id) {
		this.id=id;
	}

	
}
