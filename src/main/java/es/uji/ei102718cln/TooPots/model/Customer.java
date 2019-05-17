package es.uji.ei102718cln.TooPots.model;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer {
	String name;
	String nif;
	String email;
	String gender;
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE) 
	LocalDate birthDate;
	
	public Customer() {}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", nif=" + nif + ", email=" + email + ", gender=" + gender + ", birthDate="
				+ birthDate + "]";
	}
	

}
