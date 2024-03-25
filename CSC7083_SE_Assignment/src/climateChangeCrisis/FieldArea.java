package climateChangeCrisis;

import java.util.ArrayList;

public class FieldArea extends Area {

	// Constants for business rules
	
	//private static final int MIN_FIELDLENGTH = 10;
	

	// Instance vars

	private Development developmentObj;
	private Player ownedBy;

	// Constructor

	public FieldArea(String areaName, Development developmentObj) {
		super.setAreaName(areaName);
		super.setBelongsToField(true);
		this.setdevelopmentObj(developmentObj);
		this.ownedBy = null;
	}

	// Getters & Setters

	/**
	 * @return the developmentLevel
	 */
	public Development getdevelopmentObj() {
		return developmentObj;
	}

	/**
	 * 
	 * @param developmentObj
	 * @throws IllegalArgumentException if trying to set FieldArea Development to
	 *                                  null
	 */
	public void setdevelopmentObj(Development developmentObj) throws IllegalArgumentException {
		if (developmentObj == null) {
			throw new IllegalArgumentException("Development cannot be null");
		}
		
		if (developmentObj.getLevel <= this.developmentObj.getLevel) {
			throw new IllegalArgumentException("Development level needs to increase");
		}
		
		this.developmentObj = developmentObj;
	}

	/**
	 * @return the ownedBy
	 */
	public Player getOwnedBy() {
		return ownedBy;
	}

	/**
	 * 
	 * @param ownedBy
	 * @throws IllegalArgumentException if trying to set ownership of FieldArea to
	 *                                  null
	 */
	public void setOwnedBy(Player ownedBy) throws IllegalArgumentException {

		if (ownedBy == null) {
			throw new IllegalArgumentException("Cannot set FieldArea ownership to null");
		}

		this.setdevelopmentObj(NAME OF);
		this.ownedBy = ownedBy;
	}
	
	
}
