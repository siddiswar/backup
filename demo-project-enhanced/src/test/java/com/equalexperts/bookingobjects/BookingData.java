package com.equalexperts.bookingobjects;
public class BookingData {
	private String firstname;
	private String lastname;
	private Double totalprice;
	private String depositpaid;
	private Bookingdates bookingdates;
	private String bookingCreationStatus;

	public String getFirstname() {
	return firstname;
	}
	public void setFirstname(String firstname) {
	this.firstname = firstname;
	}
	public String getLastname() {
	return lastname;
	}
	
	public void setLastname(String lastname) {
	this.lastname = lastname;
	}
	public Double getTotalprice() {
	return totalprice;
	}
	public void setTotalprice(Double d) {
	this.totalprice = d;
	}
	public String getDepositpaid() {
	return depositpaid;
	}
	public void setDepositpaid(String depositpaid) {
	this.depositpaid = depositpaid;
	}
	public Bookingdates getBookingdates() {
	return bookingdates;
	}
	public void setBookingdates(Bookingdates bookingdates) {
	this.bookingdates = bookingdates;
	}
	
	public String getBookingCreationStatus() {
		return bookingCreationStatus;
	}
	
	public void setBookingCreationStatus(String bookingCreationStatus) {
		this.bookingCreationStatus = bookingCreationStatus;
	}
}
