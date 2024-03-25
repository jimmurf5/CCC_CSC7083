package climateChangeCrisis;

public class SpecialArea extends Area {
	
	//instance vars
	private int resourceValue;

	//Constructor
	
	public SpecialArea(String areaName, int resourceValue) {
		super.setAreaName(areaName);
		super.setBelongsToField(false);
		this.setResourceValue(resourceValue);
	}
	
	//Getters & Setters

	/**
	 * @return the resourceValue
	 */
	public int getResourceValue() {
		return resourceValue;
	}

	/**
	 * 
	 * @param resourceValue
	 * @throws IllegalArgumentException if trying to set SpecialArea resource value to less than zero
	 */
	public void setResourceValue(int resourceValue) throws IllegalArgumentException {
		if (resourceValue < 0) {
			throw new IllegalArgumentException("SpecialArea resource value cannot be less than zero");
		}
		
		this.resourceValue = resourceValue;
	}
	
	
	
	
	

}
