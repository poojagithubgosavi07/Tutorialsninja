package com.tutorialsninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public WebDriver driver;
	LoginPage lp;
	HomePage hp;
	AccountPage ap;
	
	@BeforeMethod
	public void setup()
	{
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		hp = new HomePage(driver);
		lp = hp.navigateToLoginPage();
		//lp = new LoginPage(driver);
		//ap = new AccountPage(driver);
	}
	
	
	//String scenario, String email, String password
	
	@Test(priority=1,dataProvider="credentialsSupplier")
	public void verifyLoginWithValidCredentials(String scenario,String email, String password)
	{
		//driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		//driver.findElement(By.cssSelector("input[name='password']")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		ap = lp.login(email, password);
		//Assert.assertTrue(ap.getDisplayStatusOfEditYourAccountInformationOption(),"Edit your account information is not displayed"); 
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information is not displayed");

		
		if (scenario.equals("bothcorrect"))
		{
			Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information is not displayed");

		}
		
		if (scenario.equals("bothwrong"))
		{
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWariningMessage = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		}
		
		if (scenario.equals("correctusername"))
		{
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWariningMessage = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		}
		
		if (scenario.equals("correctpassword"))
		{
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWariningMessage = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		}
		
		if (scenario.equals("withoutusername&password"))
		{
			String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWariningMessage = "Warning: No match for E-Mail Address and/or Password.";
			Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		}
	
	}
	
	//from dataprovider
	/*@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData1()
	{
		Object[][] data = {{"poojagosavi25@gmail.com","Testing123456"},
				{"poojagosavi252@gmail.com","Testing123456"}};
		return data;
	}*/
	
    @DataProvider(name="credentialsSupplier")
	public Object[][] supplyTestData2()
	{
		return new Object[][] {
				{"bothcorrect","poojagosavi25@gmail.com","Testing123456"},
				{"bothwrong","poojagosavi2@gmail.com","Testing1234"},
				{"correctusername","poojagosavi25@gmail.com","Testing12345678"},
				{"correctpassword","poojagosavi2526@gmail.com","Testing123456"},
				{"withoutusername&password"," "," "}
			};
				
	}
	
	
    //from excel
	/*@DataProvider(name="credentialsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("ValidLogin");
		return data;
	}*/
	
	/*@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{   lp.login(Utilities.genearteEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.genearteEmailWithTimeStamp());
		//driver.findElement(By.cssSelector("input[name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		//lp.clickOnLoginButton();
		
		String actualWarningMessage = lp.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWariningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		//Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{
		lp.login(Utilities.genearteEmailWithTimeStamp(), prop.getProperty("validPassword"));
		//driver.findElement(By.id("input-email")).sendKeys(Utilities.genearteEmailWithTimeStamp());
		//driver.findElement(By.cssSelector("input[name='password']")).sendKeys(prop.getProperty("validPassword"));
		//lp.clickOnLoginButton();
		
		String actualWarningMessage = lp.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWariningMessage = dataProp.getProperty("InvalidPassword");
		Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		//Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		//lp.enterEmailAddress(prop.getProperty("validEmail")); //driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		//lp.enterPassword(dataProp.getProperty("invalidPassword"));   //driver.findElement(By.cssSelector("input[name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		//lp.clickOnLoginButton(); 
		lp.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage = lp.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWariningMessage = dataProp.getProperty("InvalidPassword");
		Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");
		//Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.");
		
	}
	
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		//lp.enterEmailAddress(prop.getProperty("validEmail"));  //driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		//lp.enterPassword(dataProp.getProperty("invalidPassword"));  //driver.findElement(By.cssSelector("input[name='password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		//lp.clickOnLoginButton();
		
		lp.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage = lp.retrieveEmailPasswordNotMatchingWarningMessageText();
		String expectedWariningMessage = dataProp.getProperty("InvalidPassword");
		Assert.assertTrue(actualWarningMessage.contains(expectedWariningMessage),"Expected Warning Message is not displayed");   //Assert.assertEquals(actualWarningMessage, "Warning: No match for E-Mail Address and/or Password.");
		
	}*/
	
	/*@AfterMethod
	public void teardown()
	{
		driver.quit();
	}*/
}
