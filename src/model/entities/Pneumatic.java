package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Pneumatic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String tag;
	private String pneumaticType;
	private String pneumaticId;
	private String description;
	private String associatedSignal;
	private String failEvent;
	private String operationRestriction;
	private String hmiDescription;
	private String movementDescription;
	private Integer projectId;
	
	
	public Pneumatic() {

	}

	public Pneumatic(Integer id, String tag,String pneumaticType, String pneumaticId, String description, String associatedSignal, String failEvent,
			String operationRestriction, String hmiDescription, String movementDescription, Integer projectId) {
		
		this.id = id;
		this.tag = tag;
		this.pneumaticType = pneumaticType;
		this.pneumaticId = pneumaticId;
		this.description = description;
		this.associatedSignal = associatedSignal;
		this.failEvent = failEvent;
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

	public String getPneumaticType() {
		return pneumaticType;
	}

	public void setPneumaticType(String pneumaticType) {
		this.pneumaticType = pneumaticType;
	}

	public String getPneumaticId() {
		return pneumaticId;
	}

	public void setPneumaticId(String pneumaticId) {
		this.pneumaticId = pneumaticId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssociatedSignal() {
		return associatedSignal;
	}

	public void setAssociatedSignal(String associatedSignal) {
		this.associatedSignal = associatedSignal;
	}

	public String getFailEvent() {
		return failEvent;
	}

	public void setFailEvent(String failEvent) {
		this.failEvent = failEvent;
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
		Pneumatic other = (Pneumatic) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Pneumatic [id=" + id + ", tag=" + tag + ", pneumaticType=" + pneumaticType + ", pneumaticId="
				+ pneumaticId + ", description=" + description + ", associatedSignal=" + associatedSignal
				+ ", failEvent=" + failEvent + ", operationRestriction=" + operationRestriction + ", hmiDescription="
				+ hmiDescription + ", movementDescription=" + movementDescription + ", projectId=" + projectId + "]";
	}
	
	


	
	
	
	
	
	

}
