package com.siri.proj.java.onlinevegetablesale.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;




@Entity(name="cart_Table")
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int customerId;
	
	
	@NotNull(message="cartId should be mandatory")
	
	@Min(value=1)
	@Max(value=100000)
	private int cartId;
	
	@OneToMany
	@JoinColumn(name="vegId")
	
	@NotNull(message="vegetable list should not be null")
	private List<VegetableDTO>  vegetable;
	
	public Cart() {
		super();
		
	}
	public Cart(int cartId, int customerId, List<VegetableDTO> vegetable) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.vegetable = vegetable;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public List<VegetableDTO> getVegetable() {
		return vegetable;
	}
	public void setVegetable(List<VegetableDTO> vegetable) {
		this.vegetable = vegetable;
	}
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customerId=" + customerId + "]";
	}
	

}
