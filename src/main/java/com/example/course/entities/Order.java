package com.example.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="Pedido")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="Data", nullable=false, length=512)
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus; 
	
	@ManyToOne
	@JoinColumn(name="Client_Id")
	private User client; // Associação a Classe Usuário. Chave estrangeira.
	
	
	@OneToMany(mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	// Constructor
	public Order() {
		
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus ,User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}
	
	//Seter's and Getter's
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public void setClient(User cliente) {
		this.client = cliente;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(this.orderStatus);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Instant getMoment() {
		return this.moment;
	}
	
	public User getClient() {
		return this.client;
	}
	
	public Set<OrderItem> getItems(){
		return this.items;
	}

	//Method's
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
	
}
