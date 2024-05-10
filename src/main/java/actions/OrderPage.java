package actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.abstractComponent;

public class OrderPage extends abstractComponent{
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderProducts;
	
	public boolean verifyOrderDisplay(String productName) {
		Boolean match = orderProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public checkoutPage checkOut(WebDriver driver) {
		checkOut.click();
		return new checkoutPage(driver);
	}

}
