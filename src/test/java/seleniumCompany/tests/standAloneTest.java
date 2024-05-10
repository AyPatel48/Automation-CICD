package seleniumCompany.tests;

import java.io.File;
import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import actions.OrderPage;
import actions.cartPage;
import actions.checkoutPage;
import actions.confirmationPage;
import actions.productCatalog;
import seleniumCompany.TestComponents.BaseTest;
import seleniumCompany.data.dataReader;

public class standAloneTest extends BaseTest{
	
	//Submit Order Test
	@Test(dataProvider="getData", groups="purchaseOrder")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		//Login
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		productCatalog pCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
				
		List<WebElement> products = pCatalogue.getProdList();
		
		pCatalogue.getProdByName(input.get("product"));
		pCatalogue.addToCart(input.get("product"));
		Thread.sleep(2000);	
		cartPage cPage = pCatalogue.goToCartPage();
		Boolean match = cPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		checkoutPage checkoutPage = cPage.checkOut(driver);
		checkoutPage.selectCountry("India");
		confirmationPage confirmationPage = checkoutPage.submitOrder(driver);
		String message = confirmationPage.getConfirmationMsg();
		
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	//Verify the required product is displaying in the orders page
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData", groups="purchaseOrder")
	public void orderHistoryTest(HashMap<String, String> input) {
		productCatalog pCatalogue = landingPage.loginApplication("email@eg.com", "RahulShetty#1234");
		OrderPage ordersPage = pCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(input.get("product")));
	}
	//Extent reports - 
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//HashMap<String, String> map = new HashMap<String, String>();
		//map.put("email", "email@eg.com");
		//map.put("password", "RahulShetty#1234");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String, String> map1 = new HashMap<String, String>();
		//map1.put("email", "email@eg.com");
		//map1.put("password", "RahulShetty#1234");
		//map1.put("product", "ADIDAS ORIGINAL");
		
		//System.getProperty("user.dir")+"\\src\\test\\java\\seleniumCompany\\data\\purchaseOrder.json"
		dataReader dataRead = new dataReader();
		List<HashMap<String, String>> data = dataRead.getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\seleniumCompany\\data\\purchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
