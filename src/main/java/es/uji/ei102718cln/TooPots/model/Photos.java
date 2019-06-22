package es.uji.ei102718cln.TooPots.model;

public class Photos {
	String photo;
	int activityId;
	
	public Photos() {
		super();
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	@Override
	public String toString() {
		return "Photos [photo=" + photo + ", activityId=" + activityId + "]";
	}
	

}
