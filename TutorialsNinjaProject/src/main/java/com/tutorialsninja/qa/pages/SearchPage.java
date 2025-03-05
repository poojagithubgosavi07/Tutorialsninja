package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	WebElement validHPProduct;
	
	@FindBy(xpath="//input[@id='button-search']/following-sibling::p")
	WebElement noProductMessage;
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfHPValidProduct()
	{
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retrieveNoProductMessageText()  //direct click on search button - without searching any product
	{
		String noProductMessageText = noProductMessage.getText();
		return noProductMessageText;
	}
}
