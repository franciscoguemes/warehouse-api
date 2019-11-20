package com.franciscoguemes.warehouseapi.error;

public class OrderException extends Exception{

	private static final long serialVersionUID = 6655997257105484756L;

	public OrderException(String message) {
		super(message);
	}

}
