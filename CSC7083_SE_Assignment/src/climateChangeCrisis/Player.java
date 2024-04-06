/**
 * 
 */
package climateChangeCrisis;
/**
*@author FERGUSON_ROBERT_40040521 
*/

import java.util.Objects;

/**
 * @author alex
 */
public class Player {
	
	//declare constants for business rules 
	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 20;
	

	
	//setting constant for initial resources given to player 
	private static final int INITIAL_RESOURCES = 200;
	
	
	//declare private instance vars
	
	private String playerName;
    private int resources;
    private Area currentArea; 
    private boolean quitGame;
    //removed isBankrupt & Gs&Ss
    
    
    //removed default constructor


    //constructor with args 
    
	/**
	 * @param playerName
	 * @param resources
	 * @param currentArea
	 * @param quitGame
	 * @param bankrupt
	 */
	public Player(String playerName, Area currentArea) {
		this.setPlayerName(playerName);
		this.setResources(INITIAL_RESOURCES);//changed to link to setter
		this.setCurrentArea(currentArea);
		this.quitGame = false; // Player starts with the intention to play the game
		
	}
	
	  
	/**
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}


	/**
	 * @param playerName the playerName to set
	 */
	public void setPlayerName(String playerName)  throws IllegalArgumentException {
		
		if(playerName ==null || playerName.trim().isEmpty()) {
			throw new IllegalArgumentException("Player name cannot be empty");
		} else if (playerName.length() < MIN_NAME_LENGTH || playerName.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Player name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long");
		} else if (!playerName.matches("^[a-zA-Z0-9 ]+$")) {
	        throw new IllegalArgumentException("Player name contains invalid characters");
		
		} else {
			this.playerName = playerName;
			
		}
	}

	/**
	 * @return the resources
	 */
	public int getResources() {
		return resources;
	}


	/**
	 * @param resources the resources to set
	 */
	public void setResources(int resources){
		this.resources = resources; 
	}


	/**
	 * @return the currentArea
	 */
	public Area getCurrentArea() {
		return currentArea;
	}


	/**
	 * @param currentArea the currentArea to set
	 */
	public void setCurrentArea(Area currentArea)throws IllegalArgumentException {
		
		if(currentArea == null) {
			throw new IllegalArgumentException("currentArea cannot be null");
		} else {
			this.currentArea = currentArea;			
		}
	}


	/**
	 * @return the quitGame
	 */
	public boolean isQuitGame() {
		return quitGame;
	}


	/**
	 * @param quitGame the quitGame to set
	 */
	public void setQuitGame(boolean quitGame) {
	    // If the game has a specific state or actions to perform when quitting, handle them here
	    this.quitGame = quitGame;
	}


	@Override
	public int hashCode() {
		return Objects.hash(playerName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(playerName, other.playerName);
	}


	
	
	
	
	 // Method to choose an action based on the square - Will need further implementation 
/*
    
	public void chooseAction(Area areaObj) {
    	 // pseudo-code for choosing an action based on the area they are currently in
    	//checking if areaObj is an instance of FieldArea
    	        if (areaObj instanceof FieldArea) {
    	            FieldArea fieldArea = (FieldArea) areaObj;
    	            
    	            // Check if the fieldArea is not owned by any player, offer to buy
    	            if (fieldArea.getOwnership() == null) {
    	                buyArea(fieldArea);
    	            } else  if (fieldArea.getOwnership().equals(this)){
    	                // The player already owns the area, so they can develop it based on the current level
    	                int currentLevel = fieldArea.getDevelopmentLevel().getLevel();
    	               
    	                if (currentLevel < 3) {
    	                    // Offer the choice to develop the area to level 3 (1st Minor Development)
    	                    developArea(fieldArea, 3);
    	                } else if (currentLevel == 3) {
    	                    // Offer the choice to upgrade to level 4  (2nd Minor development)
    	                    developArea(fieldArea, 4);
    	                } else if (currentLevel == 4) {
    	                    // Offer the choice to upgrade to level 5 (3rd minor development)
    	                    developArea(fieldArea, 5);
    	                } else if (currentLevel == 5) {
    	                    // Offer the choice to upgrade to the final level 6  (Major Development)
    	                    developArea(fieldArea, 6);
    	                }
    	            }
    	        }
    	        // Handle other types of areas or default action...
    	    }

    	    private void buyArea(FieldArea fieldArea) {
    	        // potentially overlapping here with Game.handleActions()
    	    }

    	    private void developArea(FieldArea fieldArea, int targetLevel) {
    	    	 // potentially overlapping here with Game.handleActions()
    	    }
    	*/
    	
    }

    
    
	


