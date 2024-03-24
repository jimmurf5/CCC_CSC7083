package climateChangeCrisis;

import java.util.ArrayList;

public class Field {
	
	//Instance vars
	
	private String fieldName;
	private ArrayList<Area> areas;
	private int areaCost;
	private Player ownedBy;
	private boolean isOwnedByPlayer;
	
	//Constructor 
	
	/**
	 * Constructor for Field- sets ownedBy and isOwnedByPlayer to null and false respectively
	 * @param fieldName
	 * @param areas
	 * @param areaCost
	 */
	public Field(String fieldName, ArrayList<Area> areas, int areaCost) {
		this.setFieldName(fieldName);
		this.setAreas(areas);
		this.setAreaCost(areaCost);
		this.setownedBy(null);
		this.setisOwnedByPlayer(false);
	}
	
	//Getters & Setters


	public String getFieldName() {
		return fieldName;
	}

	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
	public ArrayList<Area> getAreas() {
		return areas;
	}

	
	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}

	
	public int getAreaCost() {
		return areaCost;
	}

	
	public void setAreaCost(int areaCost) {
		this.areaCost = areaCost;
	}

	
	public Player getOwnership() {
		return ownedBy;
	}

	/**
	 * 
	 * @param ownedBy
	 * @throws IllegalArgumentException if a Player already owns the Field
	 */
	public void setownedBy(Player ownedBy) throws IllegalArgumentException {
		if (this.ownedBy != null) {
			throw new IllegalArgumentException("Field is already owned by another Player");
		}
		
		this.ownedBy = ownedBy;
		this.setisOwnedByPlayer(true);
	}

	
	public boolean isOwnedByPlayer() {
		return isOwnedByPlayer;
	}

	/**
	 * 
	 * @param isOwnedByPlayer
	 * @throws IllegalArgumentException if a Player already owns the Field
	 */
	public void setisOwnedByPlayer(boolean isOwnedByPlayer) throws IllegalArgumentException {
		
		if (this.isOwnedByPlayer) {
			throw new IllegalArgumentException("Field is already owned by another Player");
		}
		
		this.isOwnedByPlayer = isOwnedByPlayer;
	}
	
	
	

	
}
