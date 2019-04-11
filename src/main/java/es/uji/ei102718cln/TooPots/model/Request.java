package es.uji.ei102718cln.TooPots.model;

public class Request {
	int requestID;
	String state;
	String certificateAttached;
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
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
				+ "]";
	}
	
}
