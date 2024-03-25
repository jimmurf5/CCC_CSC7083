package climateChangeCrisis;

public abstract class Area {

	// Constants for business rules

	private static final int MIN_AREALENGTH = 10;
	private static final int MAX_AREALENGTH = 30;

	// instance vars

	private String areaName;
	private boolean belongsToField;

	// Getters & Setters

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		
		if (areaName == null) {
			throw new IllegalArgumentException("Area name cannot be null");
		}

		if ((areaName.length() < MIN_AREALENGTH) || (areaName.length() > MAX_AREALENGTH)) {
			throw new IllegalArgumentException("Area name is too long or too short");
		}
		this.areaName = areaName;
	}

	/**
	 * @return the belongsToField
	 */
	public boolean isBelongsToField() {
		return belongsToField;
	}

	/**
	 * @param belongsToField the belongsToField to set
	 */
	public void setBelongsToField(boolean belongsToField) {
		this.belongsToField = belongsToField;
	}

}
