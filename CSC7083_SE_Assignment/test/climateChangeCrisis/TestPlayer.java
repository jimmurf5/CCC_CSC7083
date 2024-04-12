package climateChangeCrisis;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestPlayer {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int INITIAL_RESOURCES = 100;

    String validNameHigh, validNameMid, validNameLow;
    String invalidNameNull, invalidNameLow, invalidNameHigh, invalidNameSpecialCharacters;

    Player p1;
    Area testArea;
    FieldArea testfieldArea;
    Development testDevelopment;

    @BeforeEach
    void setUp() throws Exception {
        validNameHigh = "a".repeat(20);
        validNameMid = "a".repeat(10);
        validNameLow = "aa";
        invalidNameNull = null;
        invalidNameLow = "a";
        invalidNameHigh = "a".repeat(21);
        invalidNameSpecialCharacters = "Player?!";

        // Create the development object
        testDevelopment = new Development(1, "Basic Development", "This is just a test.", 2.00);

        // Create a valid test area
        testArea = new TestArea("Test Area");

        // Create a dummy player for testing with the valid area
        p1 = new Player(validNameHigh, testArea);
    }

    private class TestArea extends Area {
        public TestArea(String name) {
            super();
        }
    }

    @Test
    void testPlayerConstructorWithValidData() {
        assertEquals(validNameHigh, p1.getPlayerName());
        assertEquals(INITIAL_RESOURCES, p1.getResources());
        assertEquals(testArea, p1.getCurrentArea());
        assertFalse(p1.isQuitGame());
    }

    @Test
    void testPlayerConstructorWithInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Player(invalidNameNull, testArea);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Player(validNameMid, null);
        });
    }

    @Test
    void testSetPlayerNameWithValidData() {
        p1.setPlayerName(validNameHigh);
        assertEquals(validNameHigh, p1.getPlayerName());

        p1.setPlayerName(validNameMid);
        assertEquals(validNameMid, p1.getPlayerName());

        p1.setPlayerName(validNameLow);
        assertEquals(validNameLow, p1.getPlayerName());
    }

    @Test
    void testSetPlayerNameWithInvalidData() {
        Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
            p1.setPlayerName(invalidNameNull);
        });
        assertEquals("Player name cannot be empty", exNull.getMessage());

        Exception exTooShort = assertThrows(IllegalArgumentException.class, () -> {
            p1.setPlayerName(invalidNameLow); 
        });
        assertEquals("Player name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long",
                exTooShort.getMessage());

        Exception exTooLong = assertThrows(IllegalArgumentException.class, () -> {
            p1.setPlayerName(invalidNameHigh);
        });
        assertEquals("Player name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long",
                exTooLong.getMessage());
    
        Exception exInvalidChars = assertThrows(IllegalArgumentException.class, () -> {
            p1.setPlayerName(invalidNameSpecialCharacters);
        });
        assertEquals("Player name contains invalid characters", exInvalidChars.getMessage());
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

    @Test
    void testGetSetCurrentAreaInvalidData() {
		// test for invalid Null player name
		Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
			p1.setCurrentArea(null);
		});
		
		// test for correct exception message
		assertEquals("currentArea cannot be null", exNull.getMessage());
 

 


}

}

