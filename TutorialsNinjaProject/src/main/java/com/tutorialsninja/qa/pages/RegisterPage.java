package com.tutorialsninja.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class RegisterPage {

	WebDriver driver;
	
	@FindBy(id="input-firstname")
	WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	WebElement lastNameField;
	
	@FindBy(id="input-email")
	WebElement emailAddressField;
	
	@FindBy(id="input-telephone")
	WebElement telephoneField;
	
	@FindBy(id="input-password")
	WebElement passwordField;
	
	@FindBy(id="input-confirm")
	WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement privacyPolicyField;
	
	//input[@name='agree' and @value='1']
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	WebElement yesNewsLetterOption;

	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement duplicateEmailAddressWarning;

	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmailAddress(String emailText)
	{
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterTelephoneNumber(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterConfirmPassword(String passwordText)
	{
		passwordConfirmField.sendKeys(passwordText);
	}
	
	public void selectPrivacyPolicy() throws InterruptedException
	{
		Thread.sleep(2000);
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void clickOnContinueButton1()
	{
		continueButton.click();
	}
	
	public void selectYesNewsLetterOption()
	{
		yesNewsLetterOption.click();
	}

	public String retrieveDuplicateEmailAddressWarning()
	{
		String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning()
	{
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning()
	{
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning()
	{
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retrieveEmailWarning()
	{
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	
	public String retrieveTelephoneWarning()
	{
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	
	public String retrievePasswordWarning()
	{
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
	
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) throws InterruptedException
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		//Thread.sleep(2000);
		privacyPolicyField.click();
		//Thread.sleep(2000);
		continueButton.click();
		//Thread.sleep(2000);
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(passwordText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	
	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning, String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning, String expectedPasswordWarning) throws InterruptedException
	{
		
		continueButton.click();
		Thread.sleep(4000);
		
		String actualPrivacyPolicyWarningText = privacyPolicyWarning.getText();
		boolean privacyPolicyWarningStatus = actualPrivacyPolicyWarningText.contains(expectedPrivacyPolicyWarning);
		
		String firstNameWarningText = firstNameWarning.getText();
		boolean firstNameWarningStatus = actualPrivacyPolicyWarningText.contains(expectedFirstNameWarning);
		
		String lastNameWarningText = lastNameWarning.getText();
		boolean lastNameWarningStatus = actualPrivacyPolicyWarningText.contains(expectedLastNameWarning);
		
		String emailWarningText = emailWarning.getText();
		boolean emailWarningStatus = actualPrivacyPolicyWarningText.contains(expectedEmailWarning);
		
		String telephoneWarningText = telephoneWarning.getText();
		boolean telephoneWarningStatus = actualPrivacyPolicyWarningText.contains(expectedTelephoneWarning);
		
		String passwordWarningText = passwordWarning.getText();
		boolean passwordWarningStatus = actualPrivacyPolicyWarningText.contains(expectedPasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
	}
	
	
	
}
