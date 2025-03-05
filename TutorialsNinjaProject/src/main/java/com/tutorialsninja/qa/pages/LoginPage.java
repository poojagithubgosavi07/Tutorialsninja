package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	 WebDriver driver;
	
	//Objects
	@FindBy(id="input-email")
	private WebElement emailAddressField;
	
	@FindBy(css="input[name='password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNotMatchingWarning;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	//Here, AccountPage is HomePage
	public AccountPage login(String emailText, String passwordText)
	{
		emailAddressField.sendKeys(emailText);
		passwordField.sendKeys(passwordText);
		loginButton.click();
		return new AccountPage(driver);
	} //HomePage
	
	public String retrieveEmailPasswordNotMatchingWarningMessageText()
	{
		String warningText = emailPasswordNotMatchingWarning.getText();
		return warningText;
	}
	
	/*public AccountPage clickOnLoginButton()
	{
		loginButton.click();
		return new AccountPage(driver);
	}*/
}	

	
