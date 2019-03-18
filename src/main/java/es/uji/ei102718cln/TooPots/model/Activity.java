package es.uji.ei102718cln.TooPots.model;

import java.sql.Date;
import java.sql.Time;

public class Activity {
	String id;
	String place;
	String name;
	Date schedule;
	Time duration;
	String description;
	float priceByPerson;
	int numberOfPeople;
	String instructorId;
	String activityTypeId;
	
	public Activity() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date date) {
		this.schedule = date;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time time) {
		this.duration = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPriceByPerson() {
		return priceByPerson;
	}

	public void setPriceByPerson(float priceByPerson) {
		this.priceByPerson = priceByPerson;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getActivityTypeId() {
		return activityTypeId;
	}

	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", schedule=" + schedule + ", duration=" + duration
				+ ", description=" + description + ", priceByPerson=" + priceByPerson + ", numberOfPeople="
				+ numberOfPeople + ", instructorId=" + instructorId + ", activityTypeId=" + activityTypeId + "]";
	}
	
	
}
