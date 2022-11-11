package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Project implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String projectName;
	private String client;
	private String serialNumber;
	private String supply;
	private String power;
	private String mainDescription;
	
	public Project() {
	}

	public Project(Integer id, String projectName, String client, String serialNumber, String supply, String power,
			String mainDescription) {
		this.id = id;
		this.projectName = projectName;
		this.client = client;
		this.serialNumber = serialNumber;
		this.supply = supply;
		this.power = power;
		this.mainDescription = mainDescription;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getMainDescription() {
		return mainDescription;
	}

	public void setMainDescription(String mainDescription) {
		this.mainDescription = mainDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", client=" + client + ", serialNumber="
				+ serialNumber + ", supply=" + supply + ", power=" + power + ", mainDescription=" + mainDescription
				+ "]";
	}
	
	public String formatSerialNumber() {
		String newSerial = this.serialNumber.replaceAll("-", "");
		return newSerial;
	}
	

}
