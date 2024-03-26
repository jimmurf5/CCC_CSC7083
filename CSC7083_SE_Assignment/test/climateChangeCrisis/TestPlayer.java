package climateChangeCrisis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestPlayer {

	// test data

	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 20;

	String validNameHigh, validNameMid, validNameLow;
	String invalidNameNull, invalidNameLow, invalidNameHigh, invalidNameSpecialCharacters;

	int validResourcesSetTo50, validResourcesSetAbove50, validResourcesSetBelow50, validResourcesSetToZero,
			validResourcesSetBelowZero;

	Area testArea;
	Player p1;

	@BeforeEach
	void setUp() throws Exception {

		validNameHigh = "a".repeat(20);
		validNameMid = "a".repeat(10);
		validNameLow = "aa";
		invalidNameNull = null;
		invalidNameLow = "a";
		invalidNameHigh = "a".repeat(21);
		invalidNameSpecialCharacters = "Player?!";

		validResourcesSetTo50 = 50;
		validResourcesSetAbove50 = 100;
		validResourcesSetBelow50 = 25;
		validResourcesSetToZero = 0;
		validResourcesSetBelowZero = -10;

		testArea = createTestFieldArea();
		

		p1 = new Player(validNameHigh, validResourcesSetTo50, testArea);

	}
	
	private FieldArea createTestFieldArea() {
	    // dummy objects for Development and Player with test data
	    Development testDevelopment = new Development(1, "Basic Development", "This is just a test.", 0.5f);	   // Player testPlayer = new Player("TestPlayer", 100, null); // Assuming Player has a constructor that matches these parameters

	    // Now create the FieldArea using the testDevelopment
	
	    FieldArea testFieldArea = new FieldArea("Test Field", testDevelopment);

	    return testFieldArea;
	}

	

	@Test
	void testPlayerDefaultConstructor() {
		Player p2 = new Player();
		assertNotNull(p2);
	}

	@Test
	void testPlayerConstructorArgsValidData() {

		assertEquals(validNameHigh, p1.getPlayerName());
		assertEquals(validResourcesSetTo50, p1.getResources());
		assertEquals(testArea, p1.getCurrentArea());
		assertFalse(p1.isQuitGame());
		assertFalse(p1.isBankrupt());
	}

	@Test
	void testPlayerConstructorArgsInvalidData() {

		// test for invalid name
		assertThrows(IllegalArgumentException.class, () -> {
			new Player(invalidNameNull, validResourcesSetTo50, testArea);
		});

		
		
		// Test for null starting area
		assertThrows(IllegalArgumentException.class, () -> {
			new Player(validNameMid, validResourcesSetTo50, null);
		});

	}

	@Test
	void testGetSetPlayerNameValidData() {

		p1.setPlayerName(validNameHigh);
		assertEquals(validNameHigh, p1.getPlayerName());

		p1.setPlayerName(validNameMid);
		assertEquals(validNameMid, p1.getPlayerName());

		p1.setPlayerName(validNameLow);
		assertEquals(validNameLow, p1.getPlayerName());

	}

	void testGetSetPlayerNameInvalidData() {
		// test for invalid Null player name
		Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
			p1.setPlayerName(invalidNameNull);
		});
		
		// test for correct exception message
		assertEquals("Player name cannot be empty", exNull.getMessage());

		// test for invalid name - less than 2 characters
		Exception exTooShort = assertThrows(IllegalArgumentException.class, () -> {
			p1.setPlayerName(invalidNameLow); 
		});
		assertEquals("Player name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long",
				exTooShort.getMessage());

		// test for invalid name - more than 20 characters
		Exception exTooLong = assertThrows(IllegalArgumentException.class, () -> {
			p1.setPlayerName(invalidNameHigh);
		});
		assertEquals("Player name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long",
				exTooLong.getMessage());
		
	
		//test for invalid name - use of special characters 
		Exception exInvalidChars = assertThrows(IllegalArgumentException.class, () -> {
			p1.setPlayerName(invalidNameSpecialCharacters); // Assuming MAX_NAME_LENGTH is 20
		});
		assertEquals("Player name contains invalid characters",
				exInvalidChars.getMessage());
	}

	@Test
	void testGetSetResources() {

		p1.setResources(validResourcesSetTo50);
		assertEquals(validResourcesSetTo50, p1.getResources());

		p1.setResources(validResourcesSetAbove50);
		assertEquals(validResourcesSetAbove50, p1.getResources());

		p1.setResources(validResourcesSetBelow50);
		assertEquals(validResourcesSetBelow50, p1.getResources());

		p1.setResources(validResourcesSetBelowZero);
		assertEquals(validResourcesSetBelowZero, p1.getResources());
	}

	@Test
	void settingResourcesBelowZeroShouldTriggerBankruptcy() {
		// Set resources below zero and check bankruptcy status
		p1.setResources(validResourcesSetBelowZero);
		assertTrue(p1.isBankrupt(), "Player should be bankrupt when resources are below zero.");
	}

	@Test
	void settingResourcesToZeroShouldNotTriggerBankruptcy() {
		// Set resources to zero and check bankruptcy status
		p1.setResources(validResourcesSetToZero);
		assertFalse(p1.isBankrupt(), "Player should not be bankrupt when resources are exactly zero.");
	}
	
	 @Test
	    void quittingGameShouldSetQuitGameToTrue() {
	        // Initially, the player has not quit the game
	        assertFalse(p1.isQuitGame(), "Player initially want to play and not quit the game.");

	        // Simulate player quitting the game
	        p1.setQuitGame(true);

	        // Verify that the player's quitGame status is updated
	        assertTrue(p1.isQuitGame(), "Player quits the game.");
	    }
	 
	 
	 void testGetSetCurrentAreaInvalidData() {
			// test for invalid Null player name
			Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
				p1.setCurrentArea(null);
			});
			
			// test for correct exception message
			assertEquals("currentArea cannot be null", exNull.getMessage());
	 

	 }


}
