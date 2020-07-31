package com.example.course.entities;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Categoria")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	 @Column(name="nome")
	private String name;
	
	// @Transient //Especifica que a propriedade ou o campo não é persistente.
	 @JsonIgnore
	 @ManyToMany(mappedBy = "categories")
	 private Set<Product> products = new HashSet<>();
	
	//Constructor
	public Category() {
		
	}
	
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	//Getter's and Setter's
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setName(String nome) {
		this.name = nome;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Set<Product> getProducts() {
		return products;
	}

	//Method's
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
}
