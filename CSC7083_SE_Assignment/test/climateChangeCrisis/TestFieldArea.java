package climateChangeCrisis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFieldArea {


	 // test data

    Development emptySquare;
    Player player1, player2;
    FieldArea area1, area2;
    
    SpecialArea goSquare;

    String validAreanameLow, validAreanameMed, validAreanameHigh, invalidAreanameLow, invalidAreanameHigh,
            emptyAreaname;

    @BeforeEach
    void setUp() throws Exception {
    	
    	 emptySquare = new Development(1, "Empty Square", "Bla bla bla description", 1.00);
    	 goSquare = new SpecialArea("goSquare", 100);
    	 
    	// Initialize players
         player1 = new Player("Matthew", goSquare);
         player2 = new Player("Leonard", goSquare);
    	
     
        // Create FieldArea instances with valid Player objects
        area1 = new FieldArea("Hurricane Hit", emptySquare, "Message", player1);
        area2 = new FieldArea("Wicked Wildfire", emptySquare, "Message2", player2);
        
        
        validAreanameLow = "X".repeat(5);
        validAreanameMed = "X".repeat(25);
        validAreanameHigh = "X".repeat(50);
        invalidAreanameLow = "X".repeat(4);
        invalidAreanameHigh = "X".repeat(51);
        emptyAreaname = "";

        
        
      
    }



    @Test
    void testFieldAreaConstructorValid() {
      
    	assertEquals("Hurricane Hit", area1.getAreaName());
        assertEquals(emptySquare, area1.getdevelopmentObj());
        assertEquals("Message", area1.getInitialSquareMessage());
        assertEquals(player1, area1.getOwnedBy());
   
    }

    @Test
    void testFieldAreaConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new FieldArea(null, emptySquare, emptyAreaname, player1));
        assertThrows(IllegalArgumentException.class, () -> new FieldArea(invalidAreanameLow, emptySquare, emptyAreaname, player1));
        assertThrows(IllegalArgumentException.class, () -> new FieldArea(invalidAreanameHigh, emptySquare, emptyAreaname, player1));
        assertThrows(IllegalArgumentException.class, () -> new FieldArea(emptyAreaname, emptySquare, emptyAreaname, player1));
        assertThrows(IllegalArgumentException.class, () -> new FieldArea("Hurricane Hit", null, emptyAreaname, player1));
    }

    @Test
    void testGetSetDevelopmentObjValid() {
        Development ownedSquare = new Development(2, "Owned Square", "Bla bla bla description", 1.10);
        area1.setdevelopmentObj(ownedSquare);
        assertEquals(ownedSquare, area1.getdevelopmentObj());
    }

    @Test
    void testGetSetDevelopmentObjInvalid() {
       
 
        Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
			area1.setdevelopmentObj(null);
		});
		// test for correct exception message
		assertEquals("Development cannot be null", exNull.getMessage());

       
    }

    @Test 
    void testGetSetOwnedByValid() {
        area1.setOwnedBy(player2);
        assertEquals(player2, area1.getOwnedBy());

        area2.setOwnedBy(player1);
        assertEquals(player1, area2.getOwnedBy());
    }

    @Test
    void testGetSetOwnedByInvalid() {

        Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
			area1.setOwnedBy(null);
		});
		// test for correct exception message
		assertEquals("Cannot set owned by to null", exNull.getMessage());
    }

    @Test
    void testGetSetAreaNameValid() {
        area1.setAreaName(validAreanameLow);
        assertEquals(validAreanameLow, area1.getAreaName());

        area1.setAreaName(validAreanameMed);
        assertEquals(validAreanameMed, area1.getAreaName());

        area1.setAreaName(validAreanameHigh);
        assertEquals(validAreanameHigh, area1.getAreaName());
    }

    @Test
    void testGetSetAreaNameInvalid() {
        assertThrows(IllegalArgumentException.class, () -> area1.setAreaName(null));
        assertThrows(IllegalArgumentException.class, () -> area1.setAreaName(invalidAreanameLow));
        assertThrows(IllegalArgumentException.class, () -> area1.setAreaName(invalidAreanameHigh));
    }

}