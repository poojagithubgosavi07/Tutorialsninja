package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	public WebDriver driver;
	HomePage hp;
	SearchPage sp;
	
	@BeforeMethod
	public void setup() 
	{
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		hp = new HomePage(driver);
		//hp.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));
		
		//sp = new SearchPage(driver);
		//sp = hp.clickOnSearchButton();
		
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() 
	{
		sp = hp.searchForAProduct(dataProp.getProperty("validProduct"));
		
		/*hp.enterProductIntoSearchBoxField(dataProp.getProperty("validProduct"));  //driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		sp = hp.clickOnSearchButton();  //driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		//div[@id='search']/span/button*/
		
		Assert.assertTrue(sp.displayStatusOfHPValidProduct(),"Valid product HP is not displayed in the search results");
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct()
	{
		sp = hp.searchForAProduct(dataProp.getProperty("invalidProduct"));
		
		/*hp.enterProductIntoSearchBoxField(dataProp.getProperty("invalidProduct"));  //driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		sp = hp.clickOnSearchButton();  //driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();*/
		
		String actualSearchMessage = sp.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"),"No product meassage in search details is not displayed");
		
	}
	
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct()
	{
		//driver.findElement(By.name("search")).sendKeys("");
		sp = hp.clickOnSearchButton();  //driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String actualSearchMessage = sp.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"),"No product meassage in search details is not displayed");
		
	}
}
