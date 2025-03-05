package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public WebDriver driver;
	HomePage hp;
	RegisterPage rp;
	AccountSuccessPage asp;
	
	@BeforeMethod
	public void setup()
	{
		loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		hp = new HomePage(driver);
		//rp = new RegisterPage(driver);
		rp = hp.navigateToRegisterPage();
		
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() throws InterruptedException
	{
		asp = rp.registerWithMandatoryFields(dataProp.getProperty("firstname"),dataProp.getProperty("lastname"),Utilities.genearteEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
		
		//asp = new AccountSuccessPage(driver);
		String actualSuccessHeading = asp.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");
		
	}
	
	@Test(priority=2)
	public void verifyRegisteringAnAccountByProvidingAllFields() 
	{	
		//asp = new AccountSuccessPage(driver);
		asp = rp.registerWithAllFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), Utilities.genearteEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		String actualSuccessHeading = asp.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");
		
		//String actualErrorMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		//Assert.assertTrue(actualErrorMessage.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate email address is not displayed");	
		
	}
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {
		
		asp = rp.registerWithAllFields(dataProp.getProperty("firstname"), dataProp.getProperty("lastname"), prop.getProperty("validEmail"), dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
				
		/*rp = new RegisterPage(driver);
		rp.enterFirstName(dataProp.getProperty("firstname"));  //driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstname"));
		rp.enterLastName(dataProp.getProperty("lastname"));  //driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastname"));
		rp.enterEmailAddress(prop.getProperty("validEmail"));  //driver.findElement(By.cssSelector("input[name='email']")).sendKeys(prop.getProperty("validEmail"));
		rp.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));  //driver.findElement(By.xpath("//input[@name='telephone']")).sendKeys(dataProp.getProperty("telephoneNumber"));
		rp.enterPassword(prop.getProperty("validPassword"));  //driver.findElement(By.xpath("//input[@name='password']")).sendKeys(prop.getProperty("validPassword"));
		rp.enterConfirmPassword(prop.getProperty("validPassword"));  //driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		
		rp.selectYesNewsLetterOption(); //driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		
		rp.selectPrivacyPolicy();   //driver.findElement(By.name("agree")).click();
		rp.clickOnContinueButton();  //driver.findElement(By.xpath("//input[@value='Continue']")).click();*/
		
		String actualErrorMessage = rp.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualErrorMessage.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate email address is not displayed");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() throws InterruptedException {
		
		Assert.assertFalse(rp.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")),"Warning messages are not displayed");
		
		
		
		/*String actualPrivacyPolicyWarning = rp.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy policy warning message is not displayed");
		
		String actualFirstNameWarning = rp.retrieveFirstNameWarning();
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("firstNameWarning")),"First name warning message is not displayed");
		
		String actualLastNameWarning = rp.retrieveLastNameWarning();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameWarning")),"Last name warning message is not displayed");
		
		String actualEmailWarning = rp.retrieveEmailWarning();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("emailWarning")),"Email warning message is not displayed");
		
		String actualTelephoneWarning = rp.retrieveTelephoneWarning();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("telephoneWarning")),"Telephone warning message is not displayed");
		
		String actualPasswordWarning = rp.retrievePasswordWarning();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")),"Password warning message is not displayed");	*/
		
	}
	
	
}
