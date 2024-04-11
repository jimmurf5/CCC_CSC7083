package climateChangeCrisis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author FERGUSON_ROBERT_40040521
 */

class TestGame {

	// test data

	private final int GO_VALUE = 5;
	
	
	Player player1, player2, player3, player4, defaultPlayer;
	
	ArrayList<Player> players;

	ArrayList<Area> squareOrder;
	ArrayList<Field> fields;
	ArrayList<Development> devObjects;
	boolean isGameOver;

	SpecialArea goSquare;
	SpecialArea blankSquare;
	
	ArrayList<Area> waterFoodShortageFA;
	ArrayList<Area> bioLossFA;
	ArrayList<Area> risingSeasFA;
	ArrayList<Area> extremeWeatherFA;

	ArrayList<Development> developments;

	Development dummyDevelopment;

	ArrayList<Area> waterFoodShortageArrayL;
	ArrayList<Area> risingSeasArrayL;
	ArrayList<Area> bioLossArrayL;
	ArrayList<Area> extremeWeatherArrayL;

	Field waterFoodShortageField;
	Field biodiversityLossField;
	Field risingSeasField;
	Field extremeWeatherField;

	Game game;
	

	@BeforeEach
	void setUp() throws Exception {
		// creating 2 special Areas
		goSquare = new SpecialArea("GO Square", GO_VALUE);
		blankSquare = new SpecialArea("BLANK Square", 0);

		player1 = new Player("Kate", goSquare);
		player2 = new Player("Bob", goSquare);
		player3 = new Player("Adam", goSquare);
		player4 = new Player("John", goSquare);
		defaultPlayer = new Player("defaultPlayer", goSquare);

		players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		players.add(player4);

		dummyDevelopment = new Development(1, "Dummy Development", "Dummy Description", 1.00);

		developments = createDummyDevelopments();

		waterFoodShortageFA = createDummyWFFieldAreas(dummyDevelopment, defaultPlayer);
		bioLossFA = createDummyBLFieldArea(dummyDevelopment, defaultPlayer);
		risingSeasFA = createDummyRSFieldArea(dummyDevelopment, defaultPlayer);
		extremeWeatherFA = createDummyEWFieldArea(dummyDevelopment, defaultPlayer);

		// creating 10 fieldAreas
		waterFoodShortageArrayL = new ArrayList<>();
		waterFoodShortageArrayL.add(new FieldArea("Wellspring Woe", developments.get(0), "Initial square Area message", defaultPlayer));
		waterFoodShortageArrayL.add(new FieldArea("Parched Pastures", developments.get(0), "Initial square Area message", defaultPlayer));
		waterFoodShortageArrayL.add(new FieldArea("Harvest Havoc", developments.get(0), "Initial square Area message", defaultPlayer));

		extremeWeatherArrayL = new ArrayList<>();
		extremeWeatherArrayL.add(new FieldArea("Hurricane Hit", developments.get(0),"Initial square Area message", defaultPlayer));
		extremeWeatherArrayL.add(new FieldArea("Wicked Wildfire", developments.get(0),"Initial square Area message", defaultPlayer));

		risingSeasArrayL = new ArrayList<>();
		risingSeasArrayL.add(new FieldArea("Seashore Sorrow", developments.get(0), "Initial square Area message", defaultPlayer));
		risingSeasArrayL.add(new FieldArea("Coastal Catastrophe", developments.get(0), "Initial square Area message", defaultPlayer));

		bioLossArrayL = new ArrayList<>();
		bioLossArrayL.add(new FieldArea("Intruder Infestation", developments.get(0), "Initial square Area message", defaultPlayer));
		bioLossArrayL.add(new FieldArea("Deforestation Disaster", developments.get(0), "Initial square Area message", defaultPlayer));
		bioLossArrayL.add(new FieldArea("Silent Species", developments.get(0), "Initial square Area message", defaultPlayer));

		squareOrder = new ArrayList<>();
		squareOrder.add(goSquare);
		squareOrder.add(waterFoodShortageArrayL.get(0));
		squareOrder.add(waterFoodShortageArrayL.get(1));
		squareOrder.add(waterFoodShortageArrayL.get(2));
		squareOrder.add(bioLossArrayL.get(0));
		squareOrder.add(bioLossArrayL.get(1));
		squareOrder.add(bioLossArrayL.get(2));
		squareOrder.add(blankSquare);
		squareOrder.add(risingSeasArrayL.get(0));
		squareOrder.add(risingSeasArrayL.get(1));
		squareOrder.add(extremeWeatherArrayL.get(0));
		squareOrder.add(extremeWeatherArrayL.get(1));

		// 4 fields test data
		waterFoodShortageField = new Field("Water and Food Shortage", waterFoodShortageArrayL, 5, 7, defaultPlayer);
		biodiversityLossField = new Field("Biodiversity Loss", bioLossArrayL, 5, 7, defaultPlayer);
		risingSeasField = new Field("Rising Seas", risingSeasArrayL, 2, 4, defaultPlayer);
		extremeWeatherField = new Field("Extreme Weather", extremeWeatherArrayL, 8, 10, defaultPlayer);

		fields = new ArrayList<>();
		fields.add(waterFoodShortageField);
		fields.add(biodiversityLossField);
		fields.add(risingSeasField);
		fields.add(extremeWeatherField);

		game = new Game(squareOrder, fields);
		
	}
	

	// helper methods to create dummy development object
	private ArrayList<Development> createDummyDevelopments() {
		ArrayList<Development> developments = new ArrayList<>();

		developments.add(new Development(1, "an unowned Square", "description!", 1.00));
		developments.add(new Development(2, "an owned Square", "description", 2.00));
		developments.add(new Development(3, "a Sustainability Educational Programme", "description", 3.00));
		developments.add(new Development(4, "an Eco Learning Centre", "description", 4.00));
		developments.add(new Development(5, "a Green Community Action Initiative", "description", 5.00));
		developments.add(new Development(6, "a Global Climate Accord", "description", 8.00));
		
		return developments;
	}

	// helper methods to create dummy WFFieldAreas object
	private ArrayList<Area> createDummyWFFieldAreas(Development devObj, Player player) {

		ArrayList<Area> fieldAreas = new ArrayList<>();

		fieldAreas.add(new FieldArea("waterFoodArea1", developments.get(0), "Initial square Area message", defaultPlayer));
		fieldAreas.add(new FieldArea("waterFoodArea2", developments.get(0), "Initial square Area message", defaultPlayer));
		fieldAreas.add(new FieldArea("waterFoodArea3", developments.get(0), "Initial square Area message", defaultPlayer));

		return fieldAreas;
	}

	// helper methods to create dummy WFFieldAreas object
	private ArrayList<Area> createDummyBLFieldArea(Development devObj, Player player) {

		ArrayList<Area> fieldAreas = new ArrayList<>();

		fieldAreas.add(new FieldArea("bioLossArea1", developments.get(0), "Initial square Area message", defaultPlayer));
		fieldAreas.add(new FieldArea("bioLossArea2", developments.get(0), "Initial square Area message", defaultPlayer));
		fieldAreas.add(new FieldArea("bioLossArea3", developments.get(0), "Initial square Area message", defaultPlayer));

		return fieldAreas;
	}

	// helper methods to create dummy RSFieldArea objects
	private ArrayList<Area> createDummyRSFieldArea(Development devObj, Player player) {

		ArrayList<Area> fieldAreas = new ArrayList<>();

		fieldAreas.add(new FieldArea("risingSeaArea1", developments.get(0), "Initial square Area message", defaultPlayer));
		fieldAreas.add(new FieldArea("risingSeaArea2", developments.get(0), "Initial square Area message", defaultPlayer));

		return fieldAreas;
	}
	
	
	// helper methods to create dummy RSFieldArea objects
		private ArrayList<Area> createDummyEWFieldArea(Development devObj, Player player) {

			ArrayList<Area> fieldAreas = new ArrayList<>();

			fieldAreas.add(new FieldArea("extremeWeatherArea1", developments.get(0), "Initial square Area message", defaultPlayer));
			fieldAreas.add(new FieldArea("extremWeatherArea2", developments.get(0), "Initial square Area message", defaultPlayer));

			return fieldAreas;
		}
	

	@Test
	void testGameConstructorValidData() {
		assertEquals(squareOrder, game.getSquareOrder());
		assertEquals(fields, game.getFields());

	}

	@Test
	void testGameConstructorInvalidData() {
		// null square order
		assertThrows(IllegalArgumentException.class, () -> {
			new Game(null, game.getFields());
		});

	}

	@Test
	void testGetSetPlayersValidData() {

		game.setPlayers(players);
		assertEquals(players, game.getPlayers());

	}

	@Test
	void testGetSetPlayersInvalidData() {

		// call setPlayers with null data
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			game.setPlayers(null);
		});

		// test for correct exception message
		assertEquals("Players array cannot be null", ex.getMessage());

		// verify that the players field remains unchanged
		assertNull(game.getPlayers());

	}

	@Test
	void testGetSetSquareOrderValidData() {
		game.setSquareOrder(squareOrder);
		assertEquals(squareOrder, game.getSquareOrder());
	}

	@Test
	void testGetSetSquareOrderInvalidData() {

		// Get the initial value of squareOrder
		ArrayList<Area> initialSquareOrder = game.getSquareOrder();

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			game.setSquareOrder(null);
		});

		// check for correct exception message
		assertEquals("Square Order array cannot be empty", ex.getMessage());

		assertEquals(initialSquareOrder, game.getSquareOrder());
	}

	@Test
	void testGetSetFieldsValidData() {
		game.setFields(fields);
		assertEquals(fields, game.getFields());
	}


	@Test
	void testGetDevObjectsValidata() {
		game.setDevObjects(developments);

		assertEquals(developments, game.getDevObjects());
	}

	

	@Test
	void testIsGameOver() {

		game.setGameOver(true);
		assertEquals(true, game.isGameOver());

		game.setGameOver(false);
		assertEquals(false, game.isGameOver());
	}

	@Test
	void testCreateDevelopments() {

		assertNotNull(developments, "The list of developments should not be null");

		// checking for 6 dev objects
		assertTrue(developments.size() == 6);

	}

	@Test
	void testCreateWFFieldArea() {

		assertNotNull(waterFoodShortageFA, "The list of Field Areas should not be null");

		// checking for 3 FieldArea objects
		assertTrue(waterFoodShortageFA.size() == 3);

	}

	@Test
	void testCreateBLFieldArea() {

		assertNotNull(bioLossFA, "The list of Field Areas should not be null");

		// checking for 3 FieldArea objects
		assertTrue(bioLossFA.size() == 3);
	}

	@Test
	void testCreateRSFieldArea() {

		assertNotNull(risingSeasFA, "The list of Field Areas should not be null");

		// checking for 2 FieldArea objects
		assertTrue(risingSeasFA.size() == 2);
	}
	
	@Test
	void testCreateEWFieldArea() {

		assertNotNull(extremeWeatherFA, "The list of Field Areas should not be null");

		// checking for 2 FieldArea objects
		assertTrue(extremeWeatherFA.size() == 2);
	}
	

	@Test
	void testRollDiceValidRange() {

		// Test the rollDice method with multiple calls ensuring result is always
		// between 1 and 12
		for (int i = 0; i < 100; i++) { // Perform 100 iterations
			int result = game.rollDice();
			assertTrue(result >= 2 && result <= 12, "Result should be between 2 and 12");
		}

		// Test with a known incorrect result
		int incorrectResult = 20;
		assertFalse(incorrectResult >= 2 && incorrectResult <= 12, "Incorrect result should fail the test");

	}
	
	@Test
	void testRegisterPlayersValidNumberOfPlayersTwo() {
	   
		// create a ByteArrayInputStream containing the input we want to simulate for the test
		//simulating the user entering '2' followed by two player names 
	    ByteArrayInputStream in = new ByteArrayInputStream("2\nBob\nHelen\n".getBytes());
	    System.setIn(in);

	    // we create a ByteArrayOutputStream to capture the output generated by the registerPlayers method
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    // Call the method
	    game.registerPlayers(goSquare);

	    // Verify the output
	    String expectedOutput = "All 2 players have been successfully registered!  Now starting game...";
	    assertTrue(out.toString().contains(expectedOutput));
	    
	    // Check that each player is registered with the correct name
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Bob")));
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Helen")));
	    
	}
	
	@Test
	void testRegisterPlayersValidNumberOfPlayersThree() {
	   
		// create a ByteArrayInputStream containing the input we want to simulate for the test
		//simulating the user entering '3' followed by three player names 
	    ByteArrayInputStream in = new ByteArrayInputStream("3\nBob\nHelen\nSarah\n".getBytes());
	    System.setIn(in);

	    // we create a ByteArrayOutputStream to capture the output generated by the registerPlayers method
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    // Call the method
	    game.registerPlayers(goSquare);

	    // Verify the output
	    String expectedOutput = "All 3 players have been successfully registered!  Now starting game...";
	    assertTrue(out.toString().contains(expectedOutput));
	    
	 // Check that each player is registered with the correct name
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Bob")));
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Helen")));
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Sarah")));
	}
	
	
	@Test
	void testRegisterPlayersValidNumberOfPlayersFour() {
	   
		// create a ByteArrayInputStream containing the input we want to simulate for the test
		//simulating the user entering '4' followed by four player names 
	    ByteArrayInputStream in = new ByteArrayInputStream("4\nBob\nHelen\nSarah\nAdam\n".getBytes());
	    System.setIn(in);

	    // we create a ByteArrayOutputStream to capture the output generated by the registerPlayers method
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    // Call the method
	    game.registerPlayers(goSquare);

	    // Verify the output
	    String expectedOutput = "All 4 players have been successfully registered!  Now starting game...";
	    assertTrue(out.toString().contains(expectedOutput));
	    
	 // Check that each player is registered with the correct name
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Bob")));
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Helen")));
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Sarah")));
	    assertTrue(game.getPlayers().stream().anyMatch(player -> player.getPlayerName().equals("Adam")));
	}
	
	
	
	
	
	@Test 
	void testRegisterPlayersInvalidNumberOfPlayers() {
	    
	
		// Mock input user entering only 1 player
	    ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
	    System.setIn(in);

	    // we create a ByteArrayOutputStream to capture the output generated by the registerPlayers method
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    // Call the method
	    game.registerPlayers(goSquare);

	    // Verify the output
	    String expectedOutput = "Number of players needs to be between 2 and 4\n";
	    assertTrue(out.toString().contains(expectedOutput)); 
	}
	

    @Test
    void testUpdatePlayerBalanceIncrease() {
      
        player1.setResources(100); // Starting resources
        int lowChangeAmount = 20;
        int highChangeAmount = 99;
        
        int expectedNewBalance = player1.getResources() + lowChangeAmount;

        // Execute the method
        game.updatePlayerBalance(player1, lowChangeAmount);

        // Assert that the player's balance is updated correctly
        assertEquals(expectedNewBalance, player1.getResources());
        
        //ensure endGame method is not called 
        assertFalse(game.isGameOver());
        
        
        //testing boundary value - Player2
        player2.setResources(100);
        
        expectedNewBalance = player2.getResources() + highChangeAmount;

        // Execute the method
        game.updatePlayerBalance(player2, highChangeAmount);

        // Assert that the player's balance is updated correctly
        assertEquals(expectedNewBalance, player2.getResources());
        
        //ensure endGame method is not called 
        assertFalse(game.isGameOver());  
    }
    


    @Test
    void testUpdatePlayerBalanceDecrease() {
        
        player1.setResources(100); // Starting resources
        int changeAmount = -100; // Amount to decrease
        int expectedNewBalance = player1.getResources() + changeAmount; // Expected new balance

        // Execute the method
        game.updatePlayerBalance(player1, changeAmount);

        // Assert that the player's balance is updated correctly
        assertEquals(expectedNewBalance, player1.getResources());
        
        //ensure endGame method is not called 
        assertFalse(game.isGameOver());
    }
    
    
    @Test   
    void testUpdatePlayerBalanceTriggeringGameOver() {
        

       player1.setResources(100); // Set starting resources
        
        int changeAmount = 100; // this will take balance to 200 - endGameValue
        int expectedNewBalance = player1.getResources() + changeAmount; // Expected new balance

        // Execute the method
       game.updatePlayerBalance(player1, changeAmount);

        // Assert that the player's balance is updated correctly
        assertEquals(expectedNewBalance, player1.getResources());
        assertTrue(game.isGameOver()); // Verify that the end game condition is triggered
    }
    
    
    
    @Test
    void testUpdatePlayerSquareWithoutPassingGo() {
    	
    	int initialResources = 100;
    	player1.setResources(initialResources);
    	
    	//setting player to be positioned on Square index 1 - 'Wellspring Woe'
        player1.setCurrentArea(squareOrder.get(1));
      
        //dice roll not enough to go past goSquare
        int diceRoll = 4; 

         
        // Execute the method - player moves 4 places 
        game.updatePlayerSquare(player1, diceRoll);
        

        //player moved 4 places to land on 'Deforestation Disaster'
        assertEquals("Deforestation Disaster", player1.getCurrentArea().getAreaName());
        
        
        //check playersResurces haven't changed as they haven't passed Go Square
        assertEquals(initialResources, player1.getResources());
    }
    
    
    @Test
    void testUpdatePlayerSquarePassingGo() {
    	
    	int initialResources = 100;
    	player1.setResources(initialResources);
    	
    	//setting player to be positioned on Square index 6 - 'Silent Species '
        player1.setCurrentArea(squareOrder.get(6));
      
        //dice roll enough to go past goSquare
        int diceRoll = 8; 

         
        // Execute the method - player moves 4 places 
        game.updatePlayerSquare(player1, diceRoll);
        

        //player moved 4 places to land on 'Deforestation Disaster'
        assertEquals("Parched Pastures", player1.getCurrentArea().getAreaName());
        
        //player has passed go therefore resources should increase 
        int updatedResources = initialResources + GO_VALUE;
        
        
        //check playersResurces haven't changed as they haven't passed Go Square
        assertEquals(updatedResources, player1.getResources());
    }
    
   @Test
    void testOfferSquarePurchase() {
        // Set up initial conditions
    	
    	FieldArea fieldArea = new FieldArea("Harvest Havoc", developments.get(0), "Initial square Area message", defaultPlayer);
       
    	// Assuming it's the first offer
        int offerCounter = 0; 

        // Mock user input to simulate player choosing to purchase the square
        ByteArrayInputStream in = new ByteArrayInputStream("y\n".getBytes());
        System.setIn(in);

        // Execute the method
        game.offerSquare(player1, offerCounter, fieldArea);

        // Assert that the square is purchased by the player
        assertEquals(player1, fieldArea.getOwnedBy());
        
    }
    
    @Test
    void testQuitGame() {
    	
    	// Mock user input to simulate player choosing to quit the game
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(in);

        // Execute the method
        game.quitGame(player1);

        // Assert that the player's resources are set to -1
        assertEquals(-1, player1.getResources());

        // Assert that the game over state is set to true
        assertTrue(game.isGameOver());
    
    }

    
    @Test
    void testHandleActionsWithNullPlayer() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.handleActions(null);
        });
    }

	
}
