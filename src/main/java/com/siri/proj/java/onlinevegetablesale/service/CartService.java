package com.siri.proj.java.onlinevegetablesale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.siri.proj.java.onlinevegetablesale.entity.Cart;
import com.siri.proj.java.onlinevegetablesale.repository.CartRepository;


@Service
public class CartService {
	@Autowired
	CartRepository  cartRepository;
	public Cart createCart(Cart cart) {
		return cartRepository.save(cart);
	}

	public List<Cart> getCart() {
		return cartRepository.findAll();
	}

	public boolean deleteAllCarts() {
		try {
			cartRepository.deleteAll();
		}
		catch(Exception e) {
		return false;
	}
		return true;
	}

	public Cart updateCart(int customerId, Cart obj) {
		Optional<Cart>  cartContainer=cartRepository.findById(customerId);
		if(cartContainer.isPresent())
		{
			Cart oldObj=cartContainer.get();
			oldObj.setCartId(obj.getCartId());
			System.out.println("Succesfully updated!!!!");
			return cartRepository.saveAndFlush(oldObj);
		}
		System.out.println("No object found with these id");
		return obj;
	}
	}

