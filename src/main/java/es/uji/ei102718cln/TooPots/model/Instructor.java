package es.uji.ei102718cln.TooPots.model;


//import java.util.ArrayList;

public class Instructor {
	String name;
	String nif;
	String email;
	String address;
	String bankAccount;
	String photo;
	String password;
	String state;
	//ArrayList<String> certificates;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
//	public ArrayList<String> getCertificates() {
//		return certificates;
//	}
//	public int getSizeCertificates() {
//		return certificates.size();
//	}
//	public void setCertificates(ArrayList<String> certificates) {
//		this.certificates = certificates;
//	}
	@Override
	public String toString() {
		return "Instructor [name=" + name + ", nif=" + nif + ", email=" + email + ", address=" + address
				+ ", bankAccount=" + bankAccount + ", photo=" + photo + "]";
	}
}
