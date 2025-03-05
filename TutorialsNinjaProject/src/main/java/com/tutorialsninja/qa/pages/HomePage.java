package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	//Objects
	//ul[@class='list-inline']/li[2]/a/span[1]
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropdownMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOptions;
	
	@FindBy(linkText="Register")
	private WebElement registerOptions;
	
	@FindBy(name="search")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	/*public void enterProductIntoSearchBoxField(String productText)
	{
		searchBoxField.sendKeys(productText);
	}*/

	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
	
	/*public void clickOnMyAccount()
	{
		myAccountDropdownMenu.click();
	}*/
	
	/*public LoginPage selectLoginOption()
	{
		loginOptions.click();
		return new LoginPage(driver);
	}*/
	
	public LoginPage navigateToLoginPage()
	{
		myAccountDropdownMenu.click();
		loginOptions.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropdownMenu.click();
		registerOptions.click();
		return new RegisterPage(driver);
	}
	
	public SearchPage searchForAProduct(String productText)
	{
		searchBoxField.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}
}
