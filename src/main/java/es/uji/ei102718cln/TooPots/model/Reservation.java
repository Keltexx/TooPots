package es.uji.ei102718cln.TooPots.model;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reservation {
	int reservationID;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE) 
	LocalDate bookingDate;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE) 
	LocalDate activityDate;
	float priceByPerson;
	float totalPrice;
	int numberOfPersons;
	int activityID;
	String customerID;
	String state;
	String msg;
	
	public Reservation() {
		
	}
	
	public Reservation(LocalDate activityDate, float priceByPerson,  int activityId) {
		this.activityDate = activityDate;
		this.priceByPerson = priceByPerson;
		this.activityID = activityId;
	}
	
	public void setReservationID(int id) {
		this.reservationID=id;
	}
	public int getReservationID() {
		return reservationID;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate=bookingDate;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setActivityDate(LocalDate activityDate) {
		this.activityDate=activityDate;
	}
	public LocalDate getActivityDate() {
		return activityDate;
	}
	public void setPriceByPerson(float price) {
		this.priceByPerson=price;
	}
	public float getPriceByPerson() {
		return priceByPerson;
	}
	public void setTotalPrice(float price) {
		this.totalPrice=price;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setNumberOfPersons(int num) {
		this.numberOfPersons=num;
	}
	public int getNumberOfPersons() {
		return numberOfPersons;
	}
	public void setActivityID(int id) {
		this.activityID=id;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setCustomerID(String id) {
		this.customerID=id;
	}
	public String getCustomerID() {
		return customerID;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", bookingDate=" + bookingDate + ", activityDate="
				+ activityDate + ", priceByPerson=" + priceByPerson + ", totalPrice=" + totalPrice
				+ ", numberOfPersons=" + numberOfPersons + ", activityID=" + activityID + ", customerID=" + customerID
				+ ", state=" + state + ", msg=" + msg + "]";
	}


}
