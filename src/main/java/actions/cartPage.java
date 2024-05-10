package actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.abstractComponent;

public class cartPage extends abstractComponent{
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	@FindBy(id=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(id="login")
	WebElement submit;
	
	public boolean verifyProductDisplay(String productName) {
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public checkoutPage checkOut(WebDriver driver) {
		checkOut.click();
		return new checkoutPage(driver);
	}

}
