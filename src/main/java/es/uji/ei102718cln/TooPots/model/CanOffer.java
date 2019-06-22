package es.uji.ei102718cln.TooPots.model;

public class CanOffer {
	int canOfferId;
	String activityTypeId;
	String instructorId;
	
	public CanOffer() {
		super();
	}
	
	public CanOffer(String instructorid) {
		this.instructorId=instructorid;
	}
	public int getCanOfferId() {
		return canOfferId;
	}

	public void setCanOfferId(int canOfferId) {
		this.canOfferId = canOfferId;
	}

	public String getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	@Override
	public String toString() {
		return "CanOffer [canOfferId=" + canOfferId + ", activityTypeId=" + activityTypeId + ", instructorId="
				+ instructorId + "]";
	}

}
