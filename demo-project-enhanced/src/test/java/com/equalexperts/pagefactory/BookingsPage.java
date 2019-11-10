package com.equalexperts.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingsPage {
	WebDriverWait wait;

	public BookingsPage(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
		wait = new WebDriverWait(webDriver, 6);
	}

	@FindBy(css = "#firstname")
	private WebElement firstnameElement;

	@FindBy(css = "#lastname")
	private WebElement surnameElement;

	@FindBy(css = "#totalprice")
	private WebElement priceElement;

	@FindBy(css = "#depositpaid")
	private WebElement depositpaidElement;

	@FindBy(css = "#checkin")
	private WebElement fromdateElement;

	@FindBy(css = "#checkout")
	private WebElement todateElement;

	@FindBy(xpath = "html[1]/body[1]/div[1]/div[3]/div[1]/div[7]/input[1]")
	private WebElement saveButtonElement;

	@FindBy(css = "div[class='row'][id]")
	private List<WebElement> allBookingElements;

	//@FindBy(css = "div[class='row'][id]")
	//private List<WebElement> allDeletebuttonElements;
	

	public void fillFirstName(String fname) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(firstnameElement));
			firstnameElement.sendKeys(fname);

		} catch (InvalidSelectorException e) {
			System.out.println("firstnameElement is not found");
		}
	}

	public void fillSurName(String sname) {
		wait.until(ExpectedConditions.elementToBeClickable(surnameElement));
		surnameElement.sendKeys(sname);
	}

	public void fillPrice(String price) {
		wait.until(ExpectedConditions.elementToBeClickable(priceElement));
		priceElement.sendKeys(price);
	}

	public void fillDepositPaid(String depositPaid) {
		wait.until(ExpectedConditions.elementToBeClickable(depositpaidElement));
		Select depositSelect = new Select(depositpaidElement);
		depositSelect.selectByVisibleText(depositPaid);
	}

	public void fillCheckinDate(String fromDate) {
		wait.until(ExpectedConditions.elementToBeClickable(fromdateElement));
		fromdateElement.sendKeys(fromDate);
	}

	public void fillCheckoutDate(String toDate) {
		wait.until(ExpectedConditions.elementToBeClickable(todateElement));
		todateElement.sendKeys(toDate);
	}

	public void clickSaveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(saveButtonElement));
		saveButtonElement.click();
	}

	public void deleteFirstBooking() {
		// Here we are picking the first booking. using that element we are
		// locating its corresponding delete button and then clicking it
		WebElement firstBookingElement = allBookingElements.get(0);
		firstBookingElement.findElement(By.cssSelector("input[value='Delete']")).click();
		
		// Here we are directly locating first delete button and then clicking
		// it
		// WebElement firstDeleteButtonElement = allDeletebuttonElements.get(0);
		// firstDeleteButtonElement.click();
	}

	public int getBookingsCount() {
		return allBookingElements.size();
	}

}
