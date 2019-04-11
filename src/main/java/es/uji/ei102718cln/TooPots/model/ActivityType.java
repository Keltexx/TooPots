package es.uji.ei102718cln.TooPots.model;

public class ActivityType {
	int id;
	String nameType;
	String riskLevel;
	String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ActivityType [id=" + id + ", nameType=" + nameType + ", riskLevel=" + riskLevel + ", description="
				+ description + "]";
	}
}
