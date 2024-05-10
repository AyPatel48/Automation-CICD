package seleniumCompany.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumCompany.TestComponents.BaseTest;
import actions.cartPage;
import actions.checkoutPage;
import actions.confirmationPage;
import actions.landingPage;
import actions.productCatalog;

//Tidy Pretty Gherkin website creates this automatically from your scenario
public class StepDefinitionImpl extends BaseTest {
	public landingPage LandingPage;
	public productCatalog pCatalogue;
	public confirmationPage confirmationPage;

	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		LandingPage = launchApplication();
	}

	@Given("^I logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String userName, String password) {
		pCatalogue = landingPage.loginApplication(userName, password);
	}

	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = pCatalogue.getProdList();
		pCatalogue.getProdByName(productName);
		pCatalogue.addToCart(productName);
		Thread.sleep(2000);
	}

	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) {
		cartPage cPage = pCatalogue.goToCartPage();
		Boolean match = cPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		checkoutPage checkoutPage = cPage.checkOut(driver);
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder(driver);
	}

	@Then("Confirmation message is verified {String}")
	public void message_displayed_confirmation_page(String message) {
		String messageDisplayed = confirmationPage.getConfirmationMsg();

		Assert.assertTrue(messageDisplayed.equalsIgnoreCase(message));
		driver.close();
	}

	@Then("Verify the message ^\"([^\"]*)\"$")
	public void verify_the_message(String message) {
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
		driver.close();
	}
}
