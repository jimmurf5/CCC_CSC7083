package climateChangeCrisis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {

	// Imported tools

	Scanner sc = new Scanner(System.in);
	Random rand = new Random();

	// Constants for business rules

	private static final int MIN_PLAYER_NUMBER = 1;
	private static final int MAX_PLAYER_NUMBER = 4;

	// Instance vars

	private ArrayList<Player> players;
	private ArrayList<Area> squareOrder;
	private ArrayList<Field> fields;
	// removed fieldOrder,playerOrder, min & max no of Players- none of these
	// instance vars and Gs & Ss are required
	// changes the constructor also

	// Constructor

	public Game(ArrayList<Player> players, ArrayList<Area> squareOrder, ArrayList<Field> fields) {
		this.setPlayers(players);
		this.setSquareOrder(squareOrder);
		this.setFields(fields);
	}

	// Driver

	public static void main(String[] args) {
		// Setting up all Game objects
		// Developments x 6
		ArrayList<Development> developmentsArrayL = createDevelopments();

		// areas arrayLists per Field (2 x 2 and 2 x 3 areas)
		ArrayList<Area> waterFoodAreaArrayL = createWFFieldAreas(developmentsArrayL.get(0));
		ArrayList<Area> bioLossAreaArrayL = createBLFieldAreas(developmentsArrayL.get(0));
		ArrayList<Area> risingSeasAreaArrayL = createRSFieldAreas(developmentsArrayL.get(0));
		ArrayList<Area> ExtremeWeatherAreaArrayL = createEWFieldAreas(developmentsArrayL.get(0));

		// 4 Fields (passing in the areas arrayLists)
		Field waterFoodShortage = new Field("Water and Food Shortage", waterFoodAreaArrayL, 5, 7);
		Field biodiversityLoss = new Field("Biodiversity Loss", bioLossAreaArrayL, 5, 7);
		Field risingSeas = new Field("Rising Seas", risingSeasAreaArrayL, 2, 4);
		Field extremeWeather = new Field("Extreme Weather", ExtremeWeatherAreaArrayL, 8, 10);

		ArrayList<Field> fields = new ArrayList<>();
		fields.add(waterFoodShortage);
		fields.add(biodiversityLoss);
		fields.add(risingSeas);
		fields.add(extremeWeather);

		// Special areas (blank and GO)
		SpecialArea goSquare = new SpecialArea("GO Square", 5);
		SpecialArea blankSquare = new SpecialArea("BLANK Square", 0);

		// create squareOrder exactly as per the last drawing order
		ArrayList<Area> squaresOrdered = createSquareOrder(goSquare, waterFoodAreaArrayL, bioLossAreaArrayL,
				blankSquare, risingSeasAreaArrayL, ExtremeWeatherAreaArrayL);

		// registration method before game constructor to create Players list (already
		// randomised & presented back)
		ArrayList<Player> playersOrdered = registerPlayers(goSquare);

		// pass them into Game constructor
		Game game = new Game(playersOrdered, squaresOrdered, fields);

		/** CHRIS AND ALEX COMMENTS **/

		// Need a check/loop construct for checking if at least 1 field is owned by a
		// player before showing MAKE DEVELOPMENT OPTION
		// option to make a Development should only be displayed to user when they own
		// at least 1 FIELD.
		// This is the first check that needs made on next user's turn
		// options for Take Turn need broken down.
		// will need to handle user input methods for makeDevelopment- sequence in
		// itself, check the diagrams

		// need to have an endGame() method which gets called/triggered when player
		// Quits or resources drop to <0 (triggered in setResources in Player class)

		// Need a check to see whether player has passed/landed on GO SQUARE.
		// Get index of currentArea in squareOrder arrayList and add the diceRoll to
		// it-> if >12 trigger the passGo method and makes
		// changes to player balances, informing them

		// Method for update player currentSquare
		// Method for checking currentSquare attributes (development level etc-> cost
		// multipliers x costDonation OR costBuy options)

		// Method for updating player balances and communicating to players

		// COMMS MESSAGES
		// Messages for specialArea will be simple and always the same and set in the
		// specialArea class
		// messages for FieldAreas will be individually customised and in FieldArea
		// class for each, when dev level is 1
		// messages for FieldAreas will be generic for FieldAreas depending on their dev
		// level- Development class needs fleshed out
		// conditional logic based on currentArea attributes

		// offer unwantedSquare method will also need implemented
		// ***SEQUENCE DIAGRAMS WILL HELP WITH ALL OF THE ABOVE***
	}

	// Getters & Setters

	public ArrayList<Player> getPlayers() {
		return players;
	}

	// ADD VALIDATION FOR MIN AND MAX NO OF PLAYERS USING CONSTANTS IN REGISTRATION
	/**
	 * 
	 * @param players
	 * @throws IllegalArgumentException if a empty array is passed in
	 */
	public void setPlayers(ArrayList<Player> players) {

		if (players == null) {
			throw new IllegalArgumentException("Players array cannot be null");
		} else {
			this.players = players;
		}

	}

	public ArrayList<Area> getSquareOrder() {
		return squareOrder;
	}

	/**
	 * 
	 * @param squareOrder
	 * @throws IllegalArgumentException if a empty array is passed in
	 */
	public void setSquareOrder(ArrayList<Area> squareOrder) {

		if (squareOrder == null) {
			throw new IllegalArgumentException("Square Order array cannot be empty");
		} else {
			this.squareOrder = squareOrder;
		}
	}

	public ArrayList<Field> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}

	// Methods

	/**
	 * Handles a player's turn when taken
	 * 
	 * @param player
	 */
	public void handleTurn(Player player) {

		System.out.println("It's " + player.getPlayerName() + "'s turn!");
		System.out.println(showOptions());
		String input = sc.next();
		handleUserInput(input);
		// handleActions(player, area);
		// int nextIndex = (getPlayerOrder().indexOf(player) + 1) %
		// getPlayerOrder().size(); //cycles round to start of playerOrder if end is
		// reached
		// Player nextPlayer = getPlayerOrder().get(nextIndex);
		// handleTurn(nextPlayer);
	}

	/**
	 * Rolls two dice to determine where a player moves on the board
	 * 
	 * @return result
	 */
	public int rollDice() {

		int die1, die2, result;
		die1 = rand.nextInt(6) + 1;
		die2 = rand.nextInt(6) + 1;
		result = die1 + die2;
		System.out.println("First die shows a " + die1);
		System.out.println("Second die shows a " + die2);
		System.out.println("You move " + result + " spaces!");
		return result;
	}

	/**
	 * Handles a player's actions after landing on an area
	 * 
	 * @param player
	 * @param area
	 */
	public void handleActions(Player player, Area area) {
		// Player shown info on area landed on

		// If area.ownedByPlayer = true, show obligation info (Player resource exchange)
		// Else prompt player for decision to buy or offer unwanted square

		// If player buys square, show buy info, set ownership to player (Player
		// resource change)
		// Else getPlayerOrder and options are shown to next player if
		// hasSufficientResources = true
		// If this player buys square, show buy info, set ownership to this player
		// (Player resource change)
		// Else getPlayerOrder again and repeat until playerOrder is exhausted or area
		// is bought
	}

	/**
	 * this method gives a player the opportunity to decide weather or not to make a development decision
	 * after first checking if the player is eligible to make a development decision
	 * 
	 * @param player, an object of type player
	 * @param fields, an arraylist of objects of type Field
	 * @throws IllegalArgumentException if the player is null, or if the arraylist is null or empty
	 */
	public void offerDevOpportunyIfAvailable(Player player, ArrayList<Field> fields) throws IllegalArgumentException {
		// some validation first
		if (fields == null) {
			throw new IllegalArgumentException("The list was null, that is invalid.");
		} else if (fields.isEmpty()) {
			throw new IllegalArgumentException("The list was empty, that is not valid.");
		} else if (player == null) {
			throw new IllegalArgumentException("The player was null, that is not valid.");
		}
		// make an empty list to handle any matches, i.e. any fields owned
		// by player
		ArrayList<Field> results = new ArrayList<>(); 
		// iterate through fields arraylist to check if any fields are owned by the
		// player
		// if so add to results
		for (Field aField : fields) {
			if (aField.getownedBy() == (player)) {
				results.add(aField);
			}

			if (results.isEmpty()) {
				// the player is not in ownership of any field and hence cannot develop
				// don't necessarily need to display this message, TEAM TO DISCUSS
				System.out.println("You are not in ownership of any field and hence you cannot make a development.");
			} else {
				// the player is in ownership of one or more fields and has a development
				// decision to make
				System.out.println("You are in ownership of one or more fields.");
				// results.get(0).getFieldName());
				String playerResponse = sc.nextLine();

				do {
					System.out.println("Would you like to make a development?");
					System.out.println("Select Y for yes or No for no, all other inputs are invalid");
					if (playerResponse.toLowerCase().equals("y")) {
						// the player wants to make a development
						makeDevelopment(player, results);
					} else if (playerResponse.toLowerCase().equals("n")) {
						System.out.println("You have choosen not to make a development.");
					}
				} while (!playerResponse.toLowerCase().equals("y") || !playerResponse.toLowerCase().equals("n"));
			}
		}
	}
	
	/**
	 * this method determines which field a player wants to develop
	 * 
	 * @param player, an object of type player
	 * @param fields, an arraylist of objects of type Field
	 * @throws IllegalArgumentException if the player is null, or if the arraylist
	 *                                  is null or empty
	 */
	public void makeDevelopment(Player player, ArrayList<Field> fields) throws IllegalArgumentException {
		// some validation first
		if (fields == null) {
			throw new IllegalArgumentException("The list was null, that is invalid.");
		} else if (fields.isEmpty()) {
			throw new IllegalArgumentException("The list was empty, that is not valid.");
		} else if (player == null) {
			throw new IllegalArgumentException("The player was null, that is not valid.");
		}
		// first lets check if the player is in ownership of only one field
		if (fields.size() == 1) {
			// lets make sure the player can afford to develop an area in that field
			if (fields.get(0).getareaBuyCost() > player.getResources()) {
				System.out.println("You cannot afford to develop at this time.");
			} else {
				// the player owns only one field, lets store it in a var
				selectWhichArea(fields.get(0), player);
			}

		} else {
			System.out.println(
					"You are the owner of more than one field. You must choose which one you wish to develop.");
			// iterate through the fields within fields arraylist for the player
			// so player can select a field to develop that they own
			// using the index of the field object in the field arraylist plus one! as the
			// number in the menu system
			for (Field field : fields) {
				System.out.printf("%d. %s%n", fields.indexOf(field) + 1,
						field.getFieldName());
			}
			// establish size of list for validation
			int highNumb = fields.size();
			// boolean to check if input is valid
			boolean isValidInput = false;
			int playerResponse = 0; // Declare playerResponse outside the loop

			do {
				System.out.println(
						"Please select which field you would like to develop by choosing a number from the menu above, all other responses are invalid.");
				if (sc.hasNextInt()) {
					playerResponse = sc.nextInt();
					if (0 < playerResponse && playerResponse <= highNumb) {
						isValidInput = true;
					} else {
						System.out.printf("Invalid input. Please enter a number within the valid range, 1- %d%n.",
								highNumb);
					}
				} else {
					// Consume the non-integer token to clear the input buffer
					sc.next();
					System.out.println("Invalid input. Please enter a number.");
				}
			} while (!isValidInput);
			// field px selected, store it in a var, dont forget to subtract one
			Field fieldSelected = fields.get(playerResponse - 1);

			// lets make sure the player can afford to develop an area in that field
			if (fieldSelected.getareaBuyCost() > player.getResources()) {
				System.out.println("You cannot afford to develop at this time.");
			} else {
				// the player owns only one field, lets store it in a var
				selectWhichArea(fieldSelected, player);
			}
		}

	}

	/**
	 * 
	 * @param field,  an object of type field
	 * @param player, an object of type player
	 * @throws IllegalArgumentException if the either field or player is null
	 */
	public void selectWhichArea(Field field, Player player) throws IllegalArgumentException {
		// some validation first
		if (field == null || player == null) {
			throw new IllegalArgumentException("Neither field not player can be null");
		}
		System.out.printf("Congratulations you are in ownership of %s%n", field.getFieldName());
		System.out.println("It contains the following areas:");
		// iterate through the areas within the one field that the player owns and list
		// them for the player
		// using the index of the area object in the area arraylist plus one! as the
		// number in the menu system

		for (Area area : field.getAreas()) {
			System.out.printf("%d. %s%n", field.getAreas().indexOf(area) + 1, area.getAreaName());
		}
		// establish size of list for validation
		int highNumb = field.getAreas().size();
		// boolean to check if input is valid
		boolean isValidInput = false;
		int playerResponse = 0; // Declare playerResponse outside the loop

		do {
			System.out.println(
					"Please select which area you would like to develop by choosing a number from the menu above, all other responses are invalid.");
			if (sc.hasNextInt()) {
				playerResponse = sc.nextInt();
				if (0 < playerResponse && playerResponse <= highNumb) {
					isValidInput = true;
				} else {
					System.out.printf("Invalid input. Please enter a number within the valid range, 1- %d%n.",
							highNumb);
				}
			} else {
				// Consume the non-integer token to clear the input buffer
				sc.next();
				System.out.println("Invalid input. Please enter a number.");
			}
		} while (!isValidInput);
		// area px selected, store it in a var, dont forget to subtract one and call
		// changeDevLevel method
		Area areaSelected = field.getAreas().get(playerResponse - 1);

		// Check if the areaSelected is an instance of FieldArea before casting
		if (areaSelected instanceof FieldArea) {
			// caste area to fieldArea in order to access its methods
			FieldArea fieldAreaSelected = ((FieldArea) areaSelected);
			// calculate the cost of development
			float costToDev = (float) (fieldAreaSelected.getdevelopmentObj().getCostMultiplier() * field.getareaBuyCost());
			// lets make sure the player can afford to develop an area in that field
			if ((costToDev > player.getResources())) {
				System.out.println("You cannot afford to develop at this time.");
			} else {
				changeDevLevel(fieldAreaSelected, null);//this needs to be changed, probably better to have list as an instance avr of the game class
			}
		} else {
			// Handle the case where the areaSelected is not a FieldArea
			throw new IllegalArgumentException("Selected area is not a FieldArea.");
		}
	}
	
	//note to discuss, I don't have access to developmentArryaL in order to use it to change the dev level. Should we declare it as an instance var in the game class?
	//that might be the easiest thing to do, thats if I am going about changing the dev level in the correct way
	//I have passed it as a parameter here but its not really working good
	
	/**
	 * 
	 * @param area, an object of type area
	 * @param developmentsArrayL, an arraylist of development objects
	 * @throws IllegalArgumentException if the list is not valid or the objects are null
	 */
	private void changeDevLevel(Area area, ArrayList<Development> developmentsArrayL) throws IllegalArgumentException{
		// some validation first
		if (developmentsArrayL == null) {
			throw new IllegalArgumentException("The list was null, that is invalid.");
		} else if (developmentsArrayL.isEmpty()) {
			throw new IllegalArgumentException("The list was empty, that is not valid.");
		} else if (area == null) {
			throw new IllegalArgumentException("The area was null, that is not valid.");
		}else if(!(area instanceof FieldArea)) {
			throw new IllegalArgumentException("The area object is not of type fieldArea, you cannot develop.");
		}
		// caste area to fieldArea in order to access its methods
		FieldArea fieldArea = ((FieldArea) area);
		// get the field areas development level
		int fieldAreaDevLevel = fieldArea.getdevelopmentObj().getLevel();
		// increase the player development level by one
		fieldArea.setdevelopmentObj(developmentsArrayL.get(fieldAreaDevLevel + 1));

		// now the development level is updated lets amend the player balance
		updatePlayerBalance(player, resources);
	} 


		

	// Method to check if a player owns the entire field
	/**
     * Checks if a player owns the entire field.
     * 
     * @param player the player to check ownership for
     * @return true if the player owns all areas within any field, false otherwise
     */
	/**
	 * Checks if a player owns the entire field.
	 * 
	 * @param player the player to check ownership for
	 * @return true if the player owns all areas within any field, false otherwise
	 */
	public boolean playerOwnsEntireField(Player player) {
	    for (Field field : fields) {
	        boolean ownsField = field.getownedBy() == player; // Check if the field is owned by the player
	        if (ownsField) {
	            return true;
	        }
	    }
	    return false;
	}




    
 // Method to update player's current square
    //This method calculates the new position of the player after rolling the dice and updates their current square accordingly.
    //It also checks if the player has passed or landed on the "GO Square" and triggers the passGo method if necessary.
	/**
     * Updates the player's current square after rolling the dice.
     * 
     * @param player    the player whose square is being updated
     * @param diceRoll  the total number rolled on the dice
     */
    public void updatePlayerCurrentSquare(Player player, int diceRoll) {
        int currentPlayerIndex = squareOrder.indexOf(player.getCurrentArea());
        int newIndex = (currentPlayerIndex + diceRoll) % squareOrder.size();
        if (newIndex < 0) {
            newIndex += squareOrder.size();
        }
        player.setCurrentArea(squareOrder.get(newIndex));

        if (diceRoll > 12) {
            player.passGo();
            System.out.println(player.getPlayerName() + " passed GO SQUARE and received additional funds!");
        } else if (squareOrder.get(newIndex) instanceof SpecialArea
                && ((SpecialArea) squareOrder.get(newIndex)).getAreaName().equalsIgnoreCase("GO Square")) {
            player.landOnGo();
            System.out.println(player.getPlayerName() + " landed on GO SQUARE and received additional funds!");
        }
    }


    
    // Method to handle quitting the game
    public void handleGameQuit(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to quit the game? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (confirmation.equals("Y")) {
            players.remove(player);
            System.out.println(player.getPlayerName() + " has quit the game.");

            if (players.isEmpty()) {
                System.out.println("No more players left in the game. Exiting...");
                System.exit(0); // Exit the program if no players are left
            }
        } else {
            System.out.println("Returning to the game.");
        }
        scanner.close();
    }
    

	// Method to start the game
    public void startGame() {
        // Loop through players and handle their turns until the game ends
        for (Player player : players) {
            handleTurn(player);
            if (isGameOver()) {
                break;
            }
        }
    }



	private boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Handles a player's input when entered during their turn (at the start)
	 * 
	 * @param input
	 */
 // Method to handle user input during turn
    public void handleUserInput(Player player, String input) {
        switch (input) {
            case "1":
                System.out.println("Rolling dice...");
                int diceRoll = rollDice();
                updatePlayerCurrentSquare(player, diceRoll);
                break;
            case "2":
                System.out.println("Quitting game...");
                handleGameQuit(player);
                
                break;
            default:
                System.out.println("Please enter a valid option");
                break;
        }
    }
	/**
	 * Displays the options a player has available to them (at the start of turn)
	 * 
	 * @return options
	 */
	public String showOptions() {

		String options = "Choose an option:" + "\n 1) Roll dice" + "\n 2) Buy Development" + "\n 3) Quit Game";
		return options;
	}

	/**
	 * Registers a new player and adds them to the ArrayList players
	 * 
	 * @param players
	 * @return players
	 * @throws IllegalArgumentException if a duplicate playerName exists in players
	 * @throws IllegalArgumentException if the maximum number of players has already
	 *                                  been reached
	 */
	public static ArrayList<Player> registerPlayers(Area goSquare) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Player> players = new ArrayList<>();

		String playerName;
		int noPlayers;

		System.out.println("Please enter number of players");
		noPlayers = sc.nextInt();

		while ((noPlayers < MIN_PLAYER_NUMBER) || (noPlayers > MAX_PLAYER_NUMBER)) {
			System.out.printf("Number of players needs to be between %d and %d%n", MIN_PLAYER_NUMBER,
					MAX_PLAYER_NUMBER);
			System.out.println("Please enter number of players");
			noPlayers = sc.nextInt();
			sc.nextInt();
		}

		while (players.size() < noPlayers) {
			if (players.size() == 0) {
				System.out.println("Please enter first player name:");
			} else {
				System.out.println("Please enter next player name:");
			}

			playerName = sc.nextLine().trim();
			for (Player player : players) {
				if (player.getPlayerName().equals(playerName)) {
					System.out.println("Player name already exists, please choose a different name.");
					sc.nextLine();
					continue;
				} else {
					Player newPlayer = new Player(playerName, goSquare);
					players.add(newPlayer);
					System.out.printf("%s has been successfully registered as a player!%n", newPlayer.getPlayerName());
					sc.nextLine();
				}

				System.out.printf(
						"All %d players have been successfully registered!  Welcome to Climate Change Crisis...%n",
						players.size());
			}

		}
		Collections.shuffle(players);
		System.out.println("Player order for this game:");
		for (Player player : players) {
			System.out.printf("Player %d: %s%n", players.indexOf(player), player.getPlayerName());
		}

		return players;

	}

	private static ArrayList<Development> createDevelopments() {
		ArrayList<Development> developments = new ArrayList<>();
		developments.add(new Development(1, "Unowned Square", "Square is currently available!", 1.00));
		developments.add(new Development(2, "Owned Square", "Square already owned by player", 1.20));
		developments
				.add(new Development(3, "Minor Development 1", "Create a Sustainability Educational Programme", 1.40));
		developments.add(new Development(4, "Minor Development 2", "Build an Eco Learning Centre", 1.60));
		developments
				.add(new Development(5, "Minor Development 3", "Establish a Green Community Action Initiative", 1.80));
		developments.add(new Development(6, "Major Development", "Form a Global Climate Accord", 3.00));

		return developments;

	}

	private static ArrayList<Area> createWFFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Wellspring Woe", devObj));
		fieldAreas.add(new FieldArea("Parched Pastures", devObj));
		fieldAreas.add(new FieldArea("Harvest Havoc", devObj));

		return fieldAreas;

	}

	private static ArrayList<Area> createBLFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Intruder Infestation", devObj));
		fieldAreas.add(new FieldArea("Deforestation Disaster", devObj));
		fieldAreas.add(new FieldArea("Silent Species", devObj));

		return fieldAreas;

	}

	private static ArrayList<Area> createRSFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Seashore Sorrow", devObj));
		fieldAreas.add(new FieldArea("Coastal Catastrophe", devObj));

		return fieldAreas;

	}

	private static ArrayList<Area> createEWFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Hurricane Hit", devObj));
		fieldAreas.add(new FieldArea("Wicked Wildfire", devObj));

		return fieldAreas;

	}

	private static ArrayList<Area> createSquareOrder(SpecialArea goSquare, ArrayList<Area> waterFoodAreaArrayL,
			ArrayList<Area> bioLossAreaArrayL, SpecialArea blankSquare, ArrayList<Area> risingSeasAreaArrayL,
			ArrayList<Area> extremeWeatherAreaArrayL) {

		ArrayList<Area> squareOrder = new ArrayList<>();

		squareOrder.add(goSquare);
		squareOrder.add(waterFoodAreaArrayL.get(0));
		squareOrder.add(waterFoodAreaArrayL.get(1));
		squareOrder.add(waterFoodAreaArrayL.get(2));
		squareOrder.add(bioLossAreaArrayL.get(0));
		squareOrder.add(bioLossAreaArrayL.get(1));
		squareOrder.add(bioLossAreaArrayL.get(2));
		squareOrder.add(blankSquare);
		squareOrder.add(risingSeasAreaArrayL.get(0));
		squareOrder.add(risingSeasAreaArrayL.get(1));
		squareOrder.add(extremeWeatherAreaArrayL.get(0));
		squareOrder.add(extremeWeatherAreaArrayL.get(1));

		return squareOrder;
	}

}
