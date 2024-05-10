package seleniumCompany.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import actions.cartPage;
import actions.checkoutPage;
import actions.confirmationPage;
import actions.productCatalog;
import seleniumCompany.TestComponents.BaseTest;
import seleniumCompany.TestComponents.RetryTest;


public class ErrorValidations extends BaseTest{
	@Test(groups= {"ErrorHandling"}, retryAnalyzer = RetryTest.class)
	public void loginErrorValidation() throws InterruptedException, IOException{
		landingPage.loginApplication("email@gmail.com", "email");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}	
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
	
		//Login
		productCatalog pCatalogue = landingPage.loginApplication("email@eg.com", "RahulShetty#1234");
			
		pCatalogue.getProdByName(productName);
		pCatalogue.addToCart(productName);
		Thread.sleep(2000);	
		cartPage cPage = pCatalogue.goToCartPage();
		Boolean match = cPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}