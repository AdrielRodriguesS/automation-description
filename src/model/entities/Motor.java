package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Motor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String tag;
	private String motorType;
	private String motorId;
	private String description;
	private String operationRestriction;
	private String hmiDescription;
	private String movementDescription;
	private Integer projectId;	
	
	public Motor() {
	}

	public Motor(Integer id, String tag, String motorType, String motorId, String description,
			String operationRestriction, String hmiDescription, String movementDescription, Integer projectId) {
		super();
		this.id = id;
		this.tag = tag;
		this.motorType = motorType;
		this.motorId = motorId;
		this.description = description;
		this.operationRestriction = operationRestriction;
		this.hmiDescription = hmiDescription;
		this.movementDescription = movementDescription;
		this.projectId = projectId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMotorType() {
		return motorType;
	}

	public void setMotorType(String motorType) {
		this.motorType = motorType;
	}

	public String getMotorId() {
		return motorId;
	}

	public void setMotorId(String motorId) {
		this.motorId = motorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperationRestriction() {
		return operationRestriction;
	}

	public void setOperationRestriction(String operationRestriction) {
		this.operationRestriction = operationRestriction;
	}

	public String getHmiDescription() {
		return hmiDescription;
	}

	public void setHmiDescription(String hmiDescription) {
		this.hmiDescription = hmiDescription;
	}

	public String getMovementDescription() {
		return movementDescription;
	}

	public void setMovementDescription(String movementDescription) {
		this.movementDescription = movementDescription;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
		Motor other = (Motor) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Motor [id=" + id + ", tag=" + tag + ", motorType=" + motorType + ", motorId=" + motorId
				+ ", description=" + description + ", operationRestriction=" + operationRestriction
				+ ", hmiDescription=" + hmiDescription + ", movementDescription=" + movementDescription + ", projectId="
				+ projectId + "]";
	}
	
	


	
}
