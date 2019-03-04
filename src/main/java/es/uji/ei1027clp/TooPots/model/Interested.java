package es.uji.ei1027clp.TooPots.model;

public class Interested {
	String interestedID;
	String customerID;
	String activityTypeID;
	
	public Interested() {
	}
	
	public void setInterestedID(String id) {
		this.interestedID=id;
	}
	public String getInterestedID() {
		return interestedID;
	}
	public void setCustomerID(String id) {
		this.customerID=id;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setActivityTypeID(String id) {
		this.activityTypeID=id;
	}
	public String getActivityTypeID() {
		return activityTypeID;
	}
	
}
