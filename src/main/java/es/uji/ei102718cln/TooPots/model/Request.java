package es.uji.ei102718cln.TooPots.model;

public class Request {
	int requestID;
	String state;
	String certificateAttached;
	String instructorID;
	String activityTypeId;
	
	public String getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	
	public String getInstructorID() {
		return instructorID;
	}
	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCertificateAttached() {
		return certificateAttached;
	}
	public void setCertificateAttached(String certificateAttached) {
		this.certificateAttached = certificateAttached;
	}
	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", state=" + state + ", certificateAttached=" + certificateAttached
				+ ", instructorID=" + instructorID + ", activityTypeId=" + activityTypeId + "]";
	}

	
}
