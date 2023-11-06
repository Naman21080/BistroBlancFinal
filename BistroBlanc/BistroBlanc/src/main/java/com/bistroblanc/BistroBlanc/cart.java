package com.bistroblanc.BistroBlanc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cartId;
	private int item_id;
	private int id;
	private String address;
	
	public cart() {
		super();
	}

	public cart(int cartId, int item_id, int id, String address) {
		super();
		this.cartId = cartId;
		this.item_id = item_id;
		this.id = id;
		this.address = address;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
