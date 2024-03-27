package climateChangeCrisis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {

		//Imported tools
	
		Scanner sc = new Scanner(System.in);
		Random rand = new Random();
		
		//Constants for business rules
		
		private static final int MIN_PLAYER_NUMBER = 1;
		private static final int MAX_PLAYER_NUMBER = 4;
	
		//Instance vars
	
		private ArrayList<Player> players;
		private ArrayList<Player> playerOrder;
		private int maxNoPlayers;
		private int minNoPlayers;
		private ArrayList<Area> squareOrder;
		private ArrayList<Field> fieldOrder;
		
		
		//Constructor
		
		/**
		 * Constructor for Game
		 * @param players
		 * @param playerOrder
		 * @param maxNoPlayers
		 * @param minNoPlayers
		 * @param squareOrder
		 * @param fieldOrder
		 */
		public Game(ArrayList<Player> players, ArrayList<Player> playerOrder, int maxNoPlayers, int minNoPlayers, ArrayList<Area> squareOrder, ArrayList<Field> fieldOrder) {
			this.setPlayers(players);
			this.setPlayerOrder(playerOrder);
			this.setMaxNoPlayers(maxNoPlayers);
			this.setMinNoPlayers(minNoPlayers);
			this.setSquareOrder(squareOrder);
			this.setFieldOrder(fieldOrder);
		}
		
		//Driver
		
		public static void main(String[] args) {
			
			Game game = new Game(null, null, MIN_PLAYER_NUMBER, MAX_PLAYER_NUMBER, null, null);
			
		}
		
		//Getters & Setters

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

		public ArrayList<Player> getPlayerOrder() {
			return playerOrder;
		}

		/**
		 * 
		 * @param players
		 * @throws IllegalArgumentException if a empty array is passed in
		 */
		public void setPlayerOrder(ArrayList<Player> players) {
			
			if (players == null) {
				throw new IllegalArgumentException("Players array cannot be empty");
			} else {
				Collections.shuffle(players);
				this.playerOrder = players;//expand on this?
			}
			
		}

		public int getMaxNoPlayers() {
			return maxNoPlayers;
		}

		/**
		 * 
		 * @param maxNoPlayers
		 * @throws IllegalArgumentException if an invalid number of players is entered
		 */
		public void setMaxNoPlayers(int maxNoPlayers) {
			
			if(maxNoPlayers > MAX_PLAYER_NUMBER || maxNoPlayers < MIN_PLAYER_NUMBER) {
				throw new IllegalArgumentException("Player count must be between 1-4");
			}   else {
				this.maxNoPlayers = maxNoPlayers;
			}
			
			
			
		}

		public int getMinNoPlayers() {
			return minNoPlayers;
		}

		/**
		 * 
		 * @param minNoPlayers
		 * @throws IllegalArgumentException if an invalid number of players is entered
		 */
		public void setMinNoPlayers(int minNoPlayers) {
			
			if(minNoPlayers > MAX_PLAYER_NUMBER || minNoPlayers < MIN_PLAYER_NUMBER) {
				throw new IllegalArgumentException("Player count must be between 1-4");
			}   else {
				this.minNoPlayers = minNoPlayers;
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

		public ArrayList<Field> getFieldOrder() {
			return fieldOrder;
		}

		/**
		 * 
		 * @param fieldOrder
		 * @throws IllegalArgumentException if a empty array is passed in
		 */
		public void setFieldOrder(ArrayList<Field> fieldOrder) {
			if (fieldOrder == null) {
				throw new IllegalArgumentException("Field Order array cannot be empty");
			} else {
				this.fieldOrder = fieldOrder;
			}
		}
		
		//Methods
		
		/**
		 * Handles a player's turn when taken
		 * @param player
		 */
		public void handleTurn(Player player) {
			
			System.out.println("It's " +player.getPlayerName()+"'s turn!");
			System.out.println(showOptions());
			String input = sc.next();
			handleUserInput(input);
			//handleActions(player, area);
			int nextIndex = (getPlayerOrder().indexOf(player) + 1) % getPlayerOrder().size(); //cycles round to start of playerOrder if end is reached
		    Player nextPlayer = getPlayerOrder().get(nextIndex);
		    handleTurn(nextPlayer);
		}
		
		/**
		 * Rolls two dice to determine where a player moves on the board
		 * @return result
		 */
		public int rollDice() {
			
			int die1, die2, result;
			die1 = rand.nextInt(6)+1;
			die2 = rand.nextInt(6)+1;
			result = die1 + die2;
			System.out.println("First die shows a " +die1);
			System.out.println("Second die shows a " +die2);
			System.out.println("You move " +result +" spaces!");
			return result;
		}
		
		/**
		 * Handles a player's actions after landing on an area
		 * @param player
		 * @param area
		 */
		public void handleActions(Player player, Area area) {
			//Player shown info on area landed on 
			
			//If area.ownedByPlayer = true, show obligation info (Player resource change)
			//	Else prompt player for decision to buy or offer unwanted square
			
			//If player buys square, show buy info, set ownership to player (Player resource change)
			//	Else getPlayerOrder and options are shown to next player if hasSufficientResources = true
			//If this player buys square, show buy info, set ownership to this player (Player resource change)
			//	Else getPlayerOrder again and repeat until playerOrder is exhausted or area is bought
		}
		
		/**
		 * Handles a player's input when entered during their turn (at the start)
		 * @param input
		 */
		public void handleUserInput(String input) {
			
			 switch (input) {
		        case "1":
		            System.out.println("Rolling dice...");
		            //setCurrentArea(rollDice(), squareOrder)
		            break;
		        case "2":
		            System.out.println("Buying development...");
		            //makeDevelopment();
		            break;
		        case "3":
		            System.out.println("Quitting game...");
		            //endGame();
		            break;
		        default:
		            System.out.println("Please enter a valid option");
		            break;
		    }
		}
		
		/**
		 * Displays the options a player has available to them (at the start of turn)
		 * @return options
		 */
		public String showOptions() {
			 
			String options = "Choose an option:"
			 		+ "\n 1) Roll dice"
			 		+ "\n 2) Buy Development"
			 		+ "\n 3) Quit Game";
			return options;
		}
		
		/**
		 * Registers a new player and adds them to the ArrayList players
		 * @param players
		 * @return players
		 * @throws IllegalArgumentException if a duplicate playerName exists in players
		 * @throws IllegalArgumentException if the maximum number of players has already been reached
		 */
		public ArrayList<Player> registerNewPlayer(ArrayList<Player> players){
			
			String playerName;
			playerName = sc.next();
			
			 for (Player player : players) {
			        if (player.getPlayerName().equals(playerName)) {
			            throw new IllegalArgumentException("Player name already exists, please choose a different name.");
			        }
			    }
			
			Player newPlayer = new Player(playerName, /*INITIAL_RESOURCES, currentArea*/); //not visible
			if(players.size() < maxNoPlayers) {
				players.add(newPlayer);
				System.out.println("Welcome, " +newPlayer.getPlayerName());
			} else {
				throw new IllegalArgumentException("Max player count has been reached");
			}	
			return players;
		}
					
}
