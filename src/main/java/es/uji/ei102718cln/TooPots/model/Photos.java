package es.uji.ei102718cln.TooPots.model;

public class Photos {
	String photosId;
	int activityId;
	
	public Photos() {
		super();
	}
	
	public String getPhotosId() {
		return photosId;
	}

	public void setPhotoId(String photosId) {
		this.photosId = photosId;
	}
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	@Override
	public String toString() {
		return "Photos [photosId=" + photosId + ", activityId=" + activityId + "]";
	}
	

}
