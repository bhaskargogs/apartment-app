package com.apartment.soap.provider;

import java.util.Date;
import java.util.HashMap;

import javax.jws.WebService;

import com.apartment.soap.provider.fault.PizzaErrorMessage;
import com.apartment.soap.provider.fault.PizzaNotValidException;
import com.apartment.soap.provider.model.Pizza;

@WebService
public class PizzaHutProvider {
	private HashMap<String, Pizza> pizzaStore = new HashMap<>();

	public PizzaHutProvider() {
		System.out.println("_______________________________________________________________________________");
		System.out.println("_______________________________________________________________________________");
		System.out.println("_______________Pizza Store is opened at " + new Date() + "______________________________");
		System.out.println("_______________________________________________________________________________");
		System.out.println("_______________________________________________________________________________");
	}

	public String orderPizza(Pizza pizza, String customerName) throws PizzaNotValidException {
		if ("nagendra".equalsIgnoreCase(customerName)) {
			PizzaErrorMessage errorMessage = new PizzaErrorMessage();
			errorMessage.setMessage(
					"You are balck listed since you do not have good credit with us, we cannot proccess your order!!!!!!!!!!!");
			errorMessage.setCode("E0393939");
			errorMessage.setDescription("NA");
			PizzaNotValidException exception = new PizzaNotValidException("Sorry we cannot proccess your oder!", errorMessage);
			throw exception;
		}

		if (pizzaStore.containsKey(customerName)) {
			return "Hey " + customerName + " you have already ordered the pizza , have patience!!!!";
		} else {
			pizzaStore.put(customerName, pizza);
			return "Hey " + customerName
					+ " your pizza is ordered successfully , it will be delivered in next 30 minutes ";
		}
	}

	public String cancelPizzaOrder(String customerName) {
		if (pizzaStore.containsKey(customerName)) {
			pizzaStore.remove(customerName);
			return "Hey " + customerName
					+ " your pizza order has been cancelled successfully!!!!, you will miss for pizza now.";
		} else {
			return "Hey " + customerName + "  Sorry you have not order any pizza here! ";
		}
	}

	public HashMap<String, Pizza> findAllOderedPizzas() {
		return pizzaStore;
	}

}
