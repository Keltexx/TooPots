package es.uji.ei102718cln.TooPots.model;
import java.util.Date;

public class Reservation {
	int reservationID;
	Date bookingDate;
	Date activityDate;
	float priceByPerson;
	float totalPrice;
	int numberOfPersons;
	int activityID;
	String customerID;
	
	public Reservation() {
		
	}
	
	public void setReservationID(int id) {
		this.reservationID=id;
	}
	public int getReservationID() {
		return reservationID;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate=bookingDate;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate=activityDate;
	}
	public Date getActivityDate() {
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

	@Override
	public String toString() {
		return "Reservation [reservationID=" + reservationID + ", bookingDate=" + bookingDate + ", activityDate="
				+ activityDate + ", priceByPerson=" + priceByPerson + ", totalPrice=" + totalPrice
				+ ", numberOfPersons=" + numberOfPersons + ", activityID=" + activityID + ", customerID=" + customerID
				+ "]";
	}
	
}
