package es.uji.ei1027clp.TooPots.model;
import java.util.Date;

public class Customer {
	String name;
	String nif;
	String email;
	String gender;
	Date birthDate;
	
	public Customer() {
		
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setNif(String nif) {
		this.nif=nif;
	}
	public String getNif() {
		return nif;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public void setGender(String gender) {
		this.gender=gender;
	}
	public String getGender() {
		return this.gender;
	}
	public void setBirthDate(Date birth) {
		this.birthDate=birth;
	}
	public Date getBirthDate() {
		return birthDate;
	}
}
