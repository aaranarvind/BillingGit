package com.billing.Billing.restMethods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.billing.Billing.bean.Buyer;
import com.billing.Billing.bean.Product;
import com.billing.Billing.bean.User;
import com.billing.Billing.services.BuyerService;
import com.billing.Billing.services.ProductService;
import com.billing.Billing.services.UserService;

@RestController
public class adminRest {
	@Autowired
	public ProductService productService;
	@Autowired
	public BuyerService buyerService;
	@Autowired
	public UserService userService;

	// REST service for getting all the products from DB
	@RequestMapping(method = RequestMethod.GET, value = "/products")
	// @GetMapping("/products")
	public @ResponseBody List<Product> getProducts() {
		System.out.println("getting Product method");
		List<Product> productList = productService.getProducts();
		return productList;
	}

	// REST service for adding or updating a product
	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public String addProduct(@ModelAttribute Product product) {
		System.out.println("adding Product method " + product.toString());
		productService.addProduct(product);
		return "Successfully added<br><a href=\"http://localhost:8080/\">home</a>";
	}

	// REST service for deleting a product
	@RequestMapping(method = RequestMethod.POST, value = "/delProducts")
	public String deleteProduct(@ModelAttribute Product product) {
		System.out.println("deleting Product method " + product.toString());
		productService.deleteProduct(product);
		return "Deleted Product (Id = " + product.getProductId()
				+ ")successfully<br><a href=\"http://localhost:8080/\">home</a>";
	}

	// REST service for getting all the buyers from DB
	@GetMapping("/buyers")
	public @ResponseBody List<Buyer> getBuyers() {
		System.out.println("getting Buyer method");
		List<Buyer> buyerList = buyerService.getBuyers();
		return buyerList;
	}

	// REST service for adding or updating a Buyer
	@RequestMapping(method = RequestMethod.POST, value = "/buyers")
	public String addBuyers(@ModelAttribute Buyer buyer) {
		System.out.println("adding buyer method " + buyer.toString());
		buyerService.addBuyer(buyer);
		return "Successfully added<br><a href=\"http://localhost:8080/\">home</a>";
	}

	// REST service for deleting a buyer
	@PostMapping("/delBuyers")
	public String deleteBuyer(@ModelAttribute Buyer buyer) {
		System.out.println("deleting a buyer method " + buyer.toString());
		buyerService.deleteBuyer(buyer);
		return "Deleted Product (Id = " + buyer.getBuyerId()
				+ " + )successfully<br><a href=\"http://localhost:8080/\">home</a>";
	}

	// REST service for validating a Billing user
	@PostMapping("/userAuthenticate")
	public String authenticateUser(@ModelAttribute User user) {

		System.out.println("userAuthenticate called with user " + user.toString());
		String retStatus = "<p align=\"center\">" + "failure" + "<br><a href=\"index.html\">Home</a></p>";
		boolean status = userService.validate(user);
		if (status) {
			retStatus = "<p align=\"center\">" + "Authentication Success "
					+ "<br><a href=\"billingPage.html\">launch</a></p>";
		}
		
		return retStatus;
	}
}