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

	private static final int MIN_PLAYER_NUMBER = 2;
	private static final int MAX_PLAYER_NUMBER = 4;
	private static final int GO_VALUE = 5;

	// Instance vars

	private ArrayList<Player> players;
	private ArrayList<Area> squareOrder;
	private ArrayList<Field> fields;
	private ArrayList<Development> devObjects;
	private boolean isGameOver;

	// Constructor

	public Game( ArrayList<Area> squareOrder, ArrayList<Field> fields) {
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
		SpecialArea goSquare = new SpecialArea("GO Square", GO_VALUE);
		SpecialArea blankSquare = new SpecialArea("BLANK Square", 0);

		// create squareOrder exactly as per the last drawing order
		ArrayList<Area> squaresOrdered = createSquareOrder(goSquare, waterFoodAreaArrayL, bioLossAreaArrayL,
				blankSquare, risingSeasAreaArrayL, ExtremeWeatherAreaArrayL);

		

		// pass them into Game constructor
		Game game = new Game(squaresOrdered, fields);

		game.setDevObjects(developmentsArrayL);
		
		game.registerPlayers(goSquare);
		game.handleTurn(game.players.get(0));

	}

	// Getters & Setters

	public ArrayList<Player> getPlayers() {
		return players;
	}

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
	
	

	// Logic methods

	// welcomeMenu- DONE
	// registerPlayers- DONE

	// (loop begins) with int to track playerOrder outside it- DONE

	// taketurn:- DONE

	// checkDevelopmentOption- DONE
	// offerDevelopment- DONE
	// makeDevelopment- DONE
	// changeDevLevel- DONE
	// updatePlayerBalance- DONE
	// informPlayer- DONE

	// rollDice- DONE
	// updateCurrentArea- DONE

	// pickupResources- DONE

	// handleActions - switch on new currentArea conditions - DONE

	// unowned- DONE
	// offerSquare- DONE
	// offerSquareOthers- DONE
	// buySquare- DONE
	// updatePlayerBalance- DONE

	// ownedbyplayer- DONE
	// inform that no action available- DONE

	// ownedbyotherplayers- DONE
	// makeDonation- DONE
	// updatePlayerBalances- DONE

	// quitGame- DONE

	/**
	 * @return the devObjects
	 */
	public ArrayList<Development> getDevObjects() {
		return devObjects;
	}

	/**
	 * @param devObjects the devObjects to set
	 */
	public void setDevObjects(ArrayList<Development> devObjects) {
		this.devObjects = devObjects;
	}
	
	

	/**
	 * @return the isGameOver
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * @param isGameOver the isGameOver to set
	 */
	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	/**
	 * Handles a player's turn when taken
	 * 
	 * @param player
	 */
	public void handleTurn(Player player) {
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println();
		System.out.println("It's " + player.getPlayerName() + "'s turn!");
		boolean canDev = checkDevelopmentOption(player);
		if (canDev) {
			calculateDevelopmentOptions(player);// makeDevelopment called within this method
		}
		takeTurn(player);
		
		if (player.getResources()<0) {
			System.out.printf("OH DEAR!  %s has run out of eco tokens.  Game is now over.  Calculating standings...%n", player.getPlayerName());
			this.setGameOver(true);
			calculateStandings();
		}
		if (!isGameOver){
			handleTurn(getNextPlayer(player));
		}
		sc.close();
	
	}

	private Player getNextPlayer(Player player) {
		int nextIndex = (getPlayers().indexOf(player) + 1) % players.size(); //cycles round to start of playerOrder if end is reached
		Player nextPlayer = getPlayers().get(nextIndex);
		return nextPlayer;
	}

	private void calculateDevelopmentOptions(Player player) {

		ArrayList<Area> areasForDev = new ArrayList<>();
		ArrayList<Integer> areaCosts = new ArrayList<>();
		ArrayList<Double> devMultiplier = new ArrayList<>();
		ArrayList<Area> fieldAreasForDev = new ArrayList<>();
		ArrayList<Double> totalCost = new ArrayList<>();
		ArrayList<Integer> devLevels = new ArrayList<>();
		ArrayList<Development> devObjs = new ArrayList<>();
		ArrayList<Development> FinalDevObjs = new ArrayList<>();
		ArrayList<FieldArea> fieldAreasAvailForDev = new ArrayList<>();
		ArrayList<Integer> totalCostsAvailForDev = new ArrayList<>();

		for (Field field : this.fields) {
			// check if field is owned by player- all areas should be level 2 and above
			if (field.getownedBy().equals(player)) {

				// add all the areas in this field to arrayList
				areasForDev.addAll(field.getAreas());

				FieldArea fieldArea;
				// go through all areas and cast to fieldarea so we can access getDevelopmentObj
				// per fieldArea
				for (Area area : areasForDev) {

					fieldArea = ((FieldArea) area);
					fieldAreasForDev.add(fieldArea);
					areaCosts.add(field.getareaBuyCost());
					devMultiplier.add(fieldArea.getdevelopmentObj().getCostMultiplier());
					devLevels.add(fieldArea.getdevelopmentObj().getLevel());
					devObjs.add(fieldArea.getdevelopmentObj());
					int idx = areaCosts.size() - 1;

					totalCost.add((areaCosts.get(idx)) * (devMultiplier.get(idx)));
				}

			}

			for (int i = 0; i < fieldAreasForDev.size(); i++) {

				// ignore fieldareas development level 6 and fieldareas not affordable by player
				if ((totalCost.get(i) > player.getResources()) || (devLevels.get(i).equals(6))) {
					continue;
				}

				// adding to final curated lists
				FieldArea fieldA = ((FieldArea) fieldAreasForDev.get(i));
				fieldAreasAvailForDev.add(fieldA);
				int cost = totalCost.get(i).intValue();
				totalCostsAvailForDev.add(cost);
				FinalDevObjs.add(devObjs.get(i));

			}

			// get finalchoice of fieldarea from user
			int index = offerDevelopment(fieldAreasAvailForDev, totalCostsAvailForDev, FinalDevObjs);

			// if change their mind return null
			if (index == -1) {
				return;
			} else {
				FieldArea finalChoice = fieldAreasAvailForDev.get(index);
				Integer costToDev = totalCostsAvailForDev.get(index);
				makeDevelopment(player, finalChoice, costToDev);
			}

		}
	}

	private int offerDevelopment(ArrayList<FieldArea> fieldAreas, ArrayList<Integer> devCosts, ArrayList<Development> devObjs) {
		System.out.println("Areas available for Development: ");
		for (int i = 0; i < fieldAreas.size(); i++) {
			System.out.printf("%s. Area: %s, Cost: %d%n", (i + 1), fieldAreas.get(i).getAreaName(), devCosts.get(i));
			System.out.printf("Development option: %2%n",devObjs.get(i+1).getDescription());
		}
		System.out.println(
				"Please choose which Area you wish to Develop: (enter corresponding number or 'N' to change your mind");
		String option = sc.nextLine().toUpperCase().trim();

		while ((Integer.valueOf(option) < 1) && (Integer.valueOf(option) > fieldAreas.size() - 1)
				&& (!option.equals("N"))) {
			System.out.println("Please select a valid option");
			sc.nextLine();
			option = sc.nextLine().toUpperCase().trim();
		}

		if (option.equals("N")) {
			System.out.println("Player has chosen not to Develop");
			return -1;
		}

		return (Integer.valueOf(option) - 1);
	}

	private void makeDevelopment(Player player, FieldArea fieldAreaToDevelop, Integer costToDev) {

		Field fieldBeingUpdated= changeDevLevel(player, fieldAreaToDevelop);
		int neg = costToDev * -1;
		updatePlayerBalance(player, neg);
		System.out.printf("Congratulations! %s has been developed to %s%n", fieldAreaToDevelop.getAreaName(),
				fieldAreaToDevelop.getdevelopmentObj().getName());
		System.out.printf("Field %s is now wholly owned by %s.  Congrats!%n", fieldBeingUpdated.getFieldName(),
				player.getPlayerName());
		System.out.printf("%s now has %d eco tokens.%n", player.getPlayerName(), player.getResources());
		System.out.println("Development complete!");
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
	 * Handles a players actions after lands on an area
	 * 
	 * @param player, object of type player
	 * @param area,   object of type
	 * @throws IllegalArgumentException if player or area are null
	 */
	public void handleActions(Player player) throws IllegalArgumentException {

		if (player == null) {
			throw new IllegalArgumentException("Player cannot be null.");
		}
		
		System.out.printf("You have landed on %s%n", player.getCurrentArea());
		
		//SWITCH ON SQUARE OWNERSHIP
	
		//check if square player landed on is FieldArea
		if (player.getCurrentArea().isBelongsToField()) {
			FieldArea fieldArea = null;
			
			//cast to gain access to methods
			fieldArea = ((FieldArea) player.getCurrentArea());
			
			//owned by player
			if (fieldArea.getOwnedBy().equals(player)){
				System.out.println("This is your square, you incur no cost by landing on it.");
				
				//unowned square
			} else if (fieldArea.getOwnedBy()==null){
				int offerCounter=0;
				offerSquare(player, offerCounter);
				
				//another player owns it
			}else {
				payDonation(player, fieldArea);
			}
		
			//If not, square is SpecialArea
		}else {
			if (player.getCurrentArea().getAreaName().equals("GO Square")) {
				System.out.println("You have landed on the GO Square.  No further actions available.");
			} else if (player.getCurrentArea().getAreaName().equals("BLANK Square")) {
				System.out.println("You have landed on the BLANK Square.  Sit this one out.");
			}
		}
		
		}
	
	public void offerSquare(Player player, int offerCounter) {
		FieldArea originalSquare= null;
		if (offerCounter == 0) {
			originalSquare = ((FieldArea) player.getCurrentArea());
			System.out.println(originalSquare.getInitialSquareMessage());
		}
		
		if (offerCounter == players.size()-1) {
			System.out.println("No player wishes to purchase square.");
			return;
		}
		

		// a switch to determine what the players landing obligation is if any
		// and also set the cost to buy if there is one for the square
		int costToBuy = 0;
		int index = squareOrder.indexOf(originalSquare);
		switch (index) {
		case 0:
			return;
		case 1:
		case 2:
		case 3:
			costToBuy = fields.get(index).getareaBuyCost();
			break;
		case 4:
		case 5:
		case 6:
			costToBuy = fields.get(index).getareaBuyCost();
			break;
		case 7:
			return;
		case 8:
		case 9:
			costToBuy = fields.get(index).getareaBuyCost();
			break;
		case 10:
		case 11:
			costToBuy = fields.get(index).getareaBuyCost();
			break;
		default:
			break;
		}
		
			// make sure the player can afford it
			if (player.getResources() < costToBuy) {
				System.out.println("You have insufficient resources at this time and cannot purchase this square.");
				System.out.println("The square will be offered to the other players.");
				
				offerCounter++;
					
				offerSquare(getNextPlayer(player), offerCounter);
			}

			System.out.printf("Would you like to purchase it? The cost is %d. Type Y/ N.%n", costToBuy);
			System.out.println("Y for yes, N will offer the opportuny to purchase to the other players.");

			String playerResponse;

			do {
				System.out.print("Enter your choice, only Y and N are valid inputs: ");
				playerResponse = sc.nextLine().trim().toLowerCase();

				switch (playerResponse) {
				case "y":
					
					// change the squares dev level, change the squares ownership
					originalSquare.setOwnedBy(player);
					changeDevLevel(player, originalSquare);
					int neg= costToBuy * -1;
					updatePlayerBalance(player, neg);
					System.out.printf("%s has purchased %s%n",player.getPlayerName(),originalSquare.getAreaName());
					System.out.printf("%s now has %d eco tokens.%n", player.getPlayerName(), player.getResources());
					System.out.println("Turn complete!");
					break;
				case "n":
					offerCounter++;
					offerSquare(getNextPlayer(player), offerCounter);
					break;
				default:
					System.out.println("Invalid input. Please enter 'Y' or 'N'.");
					break;
				}
			} while (!playerResponse.equals("y") && !playerResponse.equals("n"));
		
	}


	public boolean checkDevelopmentOption(Player player) throws IllegalArgumentException {

		boolean canDev = false;

		if (player == null) {
			throw new IllegalArgumentException("The player was null, that is not valid.");
		}

		for (Field field : this.fields) {
			if (field.getownedBy().equals(player)) {
				canDev = true;
				break;
			} else {
				canDev = false;
			}

		}
		return canDev;
	}

	private Field changeDevLevel(Player player, FieldArea fieldAreaToDev) {
		int devLevel = fieldAreaToDev.getdevelopmentObj().getLevel();
		Development devObj = this.getDevObjects().get(devLevel);

		fieldAreaToDev.setdevelopmentObj(devObj);

		ArrayList<FieldArea> fieldAreas = new ArrayList<>();
		FieldArea fieldArea;
		Field fieldBeingUpdated = null;

		for (Field field : fields) {
			if (field.getAreas().contains(fieldAreaToDev)) {
				for (Area area : field.getAreas()) {
					fieldBeingUpdated = field;
					fieldArea = (FieldArea) area;
					fieldAreas.add(fieldArea);
				}

			}
		}

		boolean doesOwnershipNeedChanged = false;
		for (FieldArea area : fieldAreas) {
			if (!area.equals(fieldAreaToDev)) {
				if (!area.getOwnedBy().equals(player)) {
					doesOwnershipNeedChanged = false;
					break;
				} else if (area.getOwnedBy().equals(player)) {
					doesOwnershipNeedChanged = true;
				}

			}
		}

		if (doesOwnershipNeedChanged) {
			fieldAreaToDev.setOwnedBy(player);

		}
		return fieldBeingUpdated;

	}

	public void updatePlayerBalance(Player player, int changeAmount) {
		int newBalance = player.getResources() + changeAmount;
		player.setResources(newBalance);
	}

	public void payDonation(Player player, FieldArea fieldArea) {
		
		Double costMultiplier = fieldArea.getdevelopmentObj().getCostMultiplier();
		Integer donationCost = 0;
		
		for (Field field: this.fields) {
			if (field.getAreas().contains(fieldArea)) {
				
				donationCost= field.getareaDonationCost();
				
			}
		}
		
		Double multipliedCost = ((Integer) donationCost* costMultiplier);
		int finalDonationCost;
		finalDonationCost = multipliedCost.intValue();

		//Get squareOwner
		Player owner = fieldArea.getOwnedBy();
		System.out.printf("Unfortunately for you, %s currently owns %s.",owner.getPlayerName(),fieldArea.getAreaName());
		
		int devLevel = fieldArea.getdevelopmentObj().getLevel();
		if (devLevel>2) {
			
			switch(devLevel) {
			case 3: System.out.println("Help them create a Sustainability Educational Programme that aims to educate individuals of all ages.");
				break;
			case 4: System.out.println("Help them build an Eco Learning Centre which will promote environmental knowledge and skills.");
			break;
			case 5: System.out.println("Help them establish a Green Community Initiative to help empower communities and inspire positive change.");
			break;
			case 6: System.out.println("Help them form a Global Climate Accord to address urgent challenges posed by climate change.");
			break;
			default: System.out.println("Unrecognised Development Level");
			break;
		}
		
		int donationNeg= finalDonationCost * -1;
		updatePlayerBalance(player, donationNeg);
		updatePlayerBalance(owner, finalDonationCost);
		System.out.printf("Paying %d eco tokens...",finalDonationCost);
		System.out.printf("Updated balance for %s: %d eco tokens.%n",player.getPlayerName(),player.getResources());
		System.out.printf("Updated balance for %s: %d eco tokens.%n",owner.getPlayerName(),owner.getResources());
		
		}
	}


	// Method to update player's current square
	// This method calculates the new position of the player after rolling the dice
	// and updates their current square accordingly.
	// It also checks if the player has passed or landed on the "GO Square" and
	// triggers the passGo method if necessary.
	/**
	 * Updates the player's current square after rolling the dice.
	 * 
	 * @param player   the player whose square is being updated
	 * @param diceRoll the total number rolled on the dice
	 */
	public void updatePlayerSquare(Player player, int diceRoll) {
		int currentPlayerIndex = squareOrder.indexOf(player.getCurrentArea());
		int newIndex = (currentPlayerIndex + diceRoll);

		if (newIndex > squareOrder.size()) {
			int newSquareIdx = (newIndex - squareOrder.size());
			player.setCurrentArea(squareOrder.get(newSquareIdx));
			System.out.printf("%s has passed GO SQUARE and received %d additional funds! Updated amount: %d eco tokens%n",player.getPlayerName(), GO_VALUE, player.getResources() );
			updatePlayerBalance(player, GO_VALUE);
		} else if (newIndex < squareOrder.size()) {
			player.setCurrentArea(squareOrder.get(newIndex));
		}
	}

	// Method to handle quitting the game
	public void endGame(Player player) {

		System.out.print("Are you sure you want to quit the game? (Y/N): ");
		String confirmation = sc.nextLine().trim().toUpperCase();

		if (confirmation.equals("Y")) {
			System.out.println(player.getPlayerName() + " has quit the game.");
			calculateStandings();
			this.setGameOver(true);
		} else {
			System.out.println("Returning to the game.");
		}
	}

	private void calculateStandings() {
		Collections.sort(players, new CompareByResources());
		
		for (Player player: players) {
			if (players.get(0).equals(player)) {
				System.out.printf("WINNER: %n"
						+ "%d. %s: %d eco tokens",players.indexOf(player),player.getPlayerName(), player.getResources());
			} else {
				
				System.out.printf("%d. %s: %d eco tokens",players.indexOf(player),player.getPlayerName(), player.getResources());
			}
			
		}
		
		System.out.println("***** ~ WE HOPE YOU HAD FUN! THANKS FOR PLAYING! ~ *****");
		
	}

	/**
	 * This method allow the player to take their turn By displaying the options
	 * menu Then taking the players input and either quitting the game or rolling
	 * the dice and updating player position
	 * 
	 * @param player, an object of type player
	 * @throws IllegalArgumentException if the player is null
	 */
	public void takeTurn(Player player) throws IllegalArgumentException {
		if (player == null) {
			throw new IllegalArgumentException("Player is null, that is invalid.");
		}
		// Display options
		System.out.printf("%s, please choose an option:%n", player.getPlayerName());
		System.out.println(" 1. Roll dice");
		System.out.println(" 2. Quit Game");

		// Take user input
		String input;
		do {
			System.out.print("Enter your choice (1 or 2): ");
			input = sc.nextLine();
			if ((!input.equals("1")) && (!input.equals("2"))) {
				System.out.println("Invalid input.");
				sc.nextLine();
			}
		} while ((!input.equals("1")) && (!input.equals("2")));

		// Process user input
		switch (input) {
		case "1":
			System.out.println("Rolling dice...");
			int diceRoll = rollDice();
			updatePlayerSquare(player, diceRoll);
			handleActions(player);
			break;
		case "2":
			System.out.println("You have selected to quit game...%n");
			endGame(player);
			break;
		default:
			System.out.println("Please enter a valid option");
			break;
		}
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
	public void registerPlayers(Area goSquare) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Player> players = new ArrayList<>();

		String playerName;
		int noPlayers;
		
		System.out.println("***** WELCOME TO CLIMATE CHANGE CRISIS *****");
		System.out.println("Please enter number of players:");
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
						"All %d players have been successfully registered!  Now starting game...%n",
						players.size());
				System.out.println();
			}

		}
		Collections.shuffle(players);
		System.out.println("Player order for this game:");
		for (Player player : players) {
			System.out.printf("Player %d: %s%n", players.indexOf(player), player.getPlayerName());
		}

		this.setPlayers(players);

	}

	// OBJECT CREATION METHODS

	private static ArrayList<Development> createDevelopments() {
		ArrayList<Development> developments = new ArrayList<>();
		developments.add(new Development(1, "an unowned Square", "Square is currently available!", 1.00));
		developments.add(new Development(2, "an owned Square", "Square already owned by player", 1.20));
		developments
				.add(new Development(3, "a Sustainability Educational Programme", "Create a Sustainability Educational Programme!", 1.40));
		developments.add(new Development(4, "an Eco Learning Centre", "Build an Eco Learning Centre!", 1.60));
		developments
				.add(new Development(5, "a Green Community Action Initiative", "Establish a Green Community Action Initiative!", 1.80));
		developments.add(new Development(6, "a Global Climate Accord", "Form a Global Climate Accord!", 3.00));

		return developments;

	}

	private static ArrayList<Area> createWFFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Wellspring Woe", devObj, "The once gushing wells and springs have dried up; please support locals by investing in clean water technologies!"));
		fieldAreas.add(new FieldArea("Parched Pastures", devObj, "Dry earth stretches as far as the eye can see; invest in irrigation systems to revive the local landscape!"));
		fieldAreas.add(new FieldArea("Harvest Havoc", devObj, "A devastating drought has withered crops; help farmers invest in drought-resistant farming techniques!"));

		return fieldAreas;

	}

	private static ArrayList<Area> createBLFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Intruder Infestation", devObj, "Pesky fire ants are causing chaos to the habitat. Invest in pest control plans to restore the ecosystem!"));
		fieldAreas.add(new FieldArea("Deforestation Disaster", devObj, "The once towering trees are now reduced to barren, exposed stumps. Revive reforestation efforts by planting trees!"));
		fieldAreas.add(new FieldArea("Silent Species", devObj, "You find yourself in a once vibrant ecosystem that has fallen silent.  Establish a new state-of-the-art breeding centre!"));

		return fieldAreas;

	}

	private static ArrayList<Area> createRSFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Seashore Sorrow", devObj, "Encroaching waves reveal the toll of rising sea levels. Protect the coast by building sea walls and restoring sand dunes!"));
		fieldAreas.add(new FieldArea("Coastal Catastrophe", devObj, "An oil tanker rupture has unleashed a deluge of crude oil. Invest in oil booms to help contain the spread of the oil!"));

		return fieldAreas;

	}

	private static ArrayList<Area> createEWFieldAreas(Development devObj) {
		ArrayList<Area> fieldAreas = new ArrayList<>();
		fieldAreas.add(new FieldArea("Hurricane Hit", devObj, "Brace yourself as a powerful hurricane is on its way! Help build a sturdy shelter!"));
		fieldAreas.add(new FieldArea("Wicked Wildfire", devObj, "A blazing fire has swept its way through the forest once more. Fund emergency helicopters to help put out the fire!"));

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
