package es.uji.ei102718cln.TooPots.model;

public class Interested {
	int interestedID;
	String customerID;
	int activityTypeID;
	
	public Interested() {
	}
	
	public void setInterestedID(int id) {
		this.interestedID=id;
	}
	public int getInterestedID() {
		return interestedID;
	}
	public void setCustomerID(String id) {
		this.customerID=id;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setActivityTypeID(int id) {
		this.activityTypeID=id;
	}
	public int getActivityTypeID() {
		return activityTypeID;
	}

	@Override
	public String toString() {
		return "Interested [interestedID=" + interestedID + ", customerID=" + customerID + ", activityTypeID="
				+ activityTypeID + "]";
	}
	
}
