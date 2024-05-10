package actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.abstractComponent;

public class productCatalog extends abstractComponent {
	WebDriver driver;

	public productCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public void loginApplication(String email, String pass) {
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public List<WebElement> getProdList() {
		waitForElementVisibility(productsBy);
		List<WebElement> productsList = driver.findElements(productsBy);
		return productsList;
	}

	public WebElement getProdByName(String productName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);		
		return prod;
	}
	
	public void addToCart(String productName) {
		WebElement prod = getProdByName(productName);
		prod.findElement(addToCart).click();
		waitForElementVisibility(toastMessage);
		waitForElementInvisibility(spinner);
		
	}

}
