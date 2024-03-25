package climateChangeCrisis;

import java.util.ArrayList;

public class Field {

	// Constants for business rules

	private static final int MIN_FIELDLENGTH = 10;
	private static final int MAX_FIELDLENGTH = 30;

	// Instance vars

	private String fieldName;
	private ArrayList<Area> areas;
	private int areaBuyCost;
	private int areaDonationCost;
	private Player ownedBy;

	// Constructor

	/**
	 * Constructor for Field- sets ownedBy and isOwnedByPlayer to null and false
	 * respectively
	 * 
	 * @param fieldName
	 * @param areas
	 * @param areaCost
	 */
	public Field(String fieldName, ArrayList<Area> areas, int areaBuyCost, int areaDonationCost) {
		this.setFieldName(fieldName);
		this.setAreas(areas);
		this.setareaBuyCost(areaBuyCost);
		this.setareaDonationCost(areaDonationCost);
		this.setownedBy(null);
	}

	// Getters & Setters

	public String getFieldName() {
		return fieldName;
	}

	/**
	 * 
	 * @param fieldName
	 * @throws IllegalArgumentException if field name is outside business rules or
	 *                                  trying to set null
	 */
	public void setFieldName(String fieldName) throws IllegalArgumentException {

		if (fieldName == null) {
			throw new IllegalArgumentException("Field name cannot be null");
		}

		if ((fieldName.length() < MIN_FIELDLENGTH) || (fieldName.length() > MAX_FIELDLENGTH)) {
			throw new IllegalArgumentException("Field name is too long or too short");
		}

		this.fieldName = fieldName;
	}

	public ArrayList<Area> getAreas() {
		return areas;
	}

	/**
	 * 
	 * @param areas
	 * @throws IllegalArgumentException if ArrayList passed in is null or empty
	 */
	public void setAreas(ArrayList<Area> areas) throws IllegalArgumentException {

		if (areas == null) {
			throw new IllegalArgumentException("Areas ArrayList cannot be null");
		}
		if (areas.size() < 1) {
			throw new IllegalArgumentException("Areas ArrayList cannot be empty");
		}

		this.areas = areas;
	}

	public int getareaBuyCost() {
		return areaBuyCost;
	}

	/**
	 * 
	 * @param buyAreaCost
	 * @throws IllegalArgumentException if cost passed in is less than zero
	 */
	public void setareaBuyCost(int areaBuyCost) throws IllegalArgumentException {

		if (areaBuyCost < 0) {
			throw new IllegalArgumentException("Area buy cost cannot be less than zero");
		}

		this.areaBuyCost = areaBuyCost;
	}

	public int getareaDonationCost() {
		return areaDonationCost;
	}

	/**
	 * 
	 * @param areaDonationCost
	 * @throws IllegalArgumentException if cost passed in is less than zero
	 */
	public void setareaDonationCost(int areaDonationCost) throws IllegalArgumentException {

		if (areaDonationCost < 0) {
			throw new IllegalArgumentException("Area donation cost cannot be less than zero");
		}

		this.areaDonationCost = areaDonationCost;
	}

	public Player getownedBy() {
		return ownedBy;
	}

	/**
	 * 
	 * @param ownedBy
	 * @throws IllegalArgumentException if a Player already owns the Field or the
	 *                                  argument passed in is null
	 */
	public void setownedBy(Player ownedBy) throws IllegalArgumentException {
		if (this.ownedBy != null) {
			throw new IllegalArgumentException("Field is already owned by another Player");
		}

		if (ownedBy == null) {
			throw new IllegalArgumentException("Cannot change Field ownership to null");
		}

		this.ownedBy = ownedBy;
	}

}
