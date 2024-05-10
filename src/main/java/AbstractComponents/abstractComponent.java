package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import actions.OrderPage;
import actions.cartPage;

public class abstractComponent {
	WebDriver driver;
	
	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;

	public abstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementVisibility(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForWebElementVisibility(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public cartPage goToCartPage() {
		cartHeader.click();
		cartPage cPage = new cartPage(driver);
		return cPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
