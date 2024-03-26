/**
 * 
 */
package climateChangeCrisis;
/**

*/

/**
 
 *
 */
public class Development {
	
	//declare constants for business rules 
	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 20;
	
	private static final int MIN_DEV_LEVEL = 1;
	private static final int MAX_DEV_LEVEL = 6;

	// private instance vars 
	private int level;
	private String name;
	private String description;
	private float costMultiplier;

	
	//default constructor 
	public Development() {
		
	}
	
	
	// Constructor with args
	public Development(int level, String name, String description, float costMultiplier) {
		this.setLevel(level);
		this.setName(name);
		this.setDescription(description);
		this.costMultiplier = costMultiplier;
	}

	// Getters and setters for each field
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) throws IllegalArgumentException {
		
		if ( level < MIN_DEV_LEVEL || level > MAX_DEV_LEVEL) {
			throw new IllegalArgumentException("development level out of range");
		} else {
			this.level = level;
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name)throws IllegalArgumentException {

		if(name ==null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("development name cannot be empty");
		} else if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("development name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long");
		} else {
			this.name = name;
			
		}
	}

	public String getDescription() {
		return description;
	}

	// validation on null entry and 
	public void setDescription(String description)throws IllegalArgumentException{
		if(description ==null || description.trim().isEmpty()) {
			throw new IllegalArgumentException("development description cannot be empty");
		}  else {
	        this.description = description;
	    }
	}

	public float getCostMultiplier() {
		return costMultiplier;
	}

	public void setCostMultiplier(float costMultiplier) {
		this.costMultiplier = costMultiplier;
	}

	

}
