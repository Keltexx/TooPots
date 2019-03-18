package es.uji.ei102718cln.TooPots.model;

public class Request {
	String state;
	String certificateAttached;
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
		return "Request [state=" + state + ", certificateAttached=" + certificateAttached + "]";
	}
	
}
