/**
 * 
 */
package climateChangeCrisis;





import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author alex_
 *
 */
class TestDevelopment {

	// test data
	
	private static final int MIN_NAME_LENGTH = 2;
	private static final int MAX_NAME_LENGTH = 20;
	

	String validNameHigh, validNameMid, validNameLow;
	String invalidNameNull, invalidNameLow, invalidNameHigh;

	int level1;
	int level2;
	int level3;
	int level4;
	int level5;
	int level6;

	int invalidLevelLow = 0;
	int invalidLevelHigh = 7;

	float costMultiplierLow = 1.0f;
	float costMultiplierMiddle = 2.5f;
	float costMultiplierHigh = 4.0f;

	Development d1;

	@BeforeEach
	void setUp() throws Exception {

		validNameHigh = "a".repeat(20);
		validNameMid = "a".repeat(10);
		validNameLow = "aa";
		invalidNameNull = null;
		invalidNameLow = "a";
		invalidNameHigh = "a".repeat(21);
		
		

		invalidLevelLow = 0;
		invalidLevelHigh = 7;
		level1 = 1;
		level2 = 2;
		level3 = 3;
		level4 = 4;
		level5 = 5;
		level6 = 6;
		
		d1 = new Development(level1, validNameHigh, "development description", costMultiplierHigh);
	}

	@Test
	void testDevelopmentDefaultConstructor() {
		Development d2 = new Development();
		assertNotNull(d2);
	}

	@Test
	void testDevelopmentConstructorArgsValidData() {

		assertEquals(level1, d1.getLevel());
		assertEquals(validNameHigh, d1.getName());
		assertEquals("development description", d1.getDescription());
		assertEquals(costMultiplierHigh, d1.getCostMultiplier());

	}

	@Test
	void testDevelopmentConstructorArgsInvalidData() {

		// test for invalid level
		assertThrows(IllegalArgumentException.class, () -> {
			new Development(invalidLevelLow, validNameHigh, "description", costMultiplierHigh);
		});

		// test for invalid name
		assertThrows(IllegalArgumentException.class, () -> {
			new Development(level1, invalidNameHigh, "description", costMultiplierHigh);
		});

		// test for invalid description
		assertThrows(IllegalArgumentException.class, () -> {
			new Development(level1, invalidNameHigh, null, costMultiplierHigh);
		});
	
	}

	
	@Test
	void testGetSetDevelopmentNameValidData() {

		d1.setName(validNameHigh);
		assertEquals(validNameHigh, d1.getName());

		d1.setName(validNameMid);
		assertEquals(validNameMid, d1.getName());

		d1.setName(validNameLow);
		assertEquals(validNameLow, d1.getName());

	}
	
	

	void testGetSetDevelopmentNameInvalidData() {
		// test for invalid Null player name
		Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
			d1.setName(invalidNameNull);
		});
		
		// test for correct exception message
		assertEquals("development name cannot be empty", exNull.getMessage());

		// test for invalid name - less than 2 characters
		Exception exTooShort = assertThrows(IllegalArgumentException.class, () -> {
			d1.setName(invalidNameLow); 
		});
		assertEquals("development name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long",
				exTooShort.getMessage());

		// test for invalid name - more than 20 characters
		Exception exTooLong = assertThrows(IllegalArgumentException.class, () -> {
			d1.setName(invalidNameHigh);
		});
		assertEquals("development name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters long",
				exTooLong.getMessage());
		

	}
	
	

	void testGetSetDevelopmentDescriptionInvalidData() {
		// test for invalid Null player name
		Exception exNull = assertThrows(IllegalArgumentException.class, () -> {
			d1.setDescription(null);
		});
		;
		// test for correct exception message
		assertEquals("development description cannot be empty", exNull.getMessage());
		
	}
	
	
	@Test
	void testGetSetDevelopmentLevelValidData() {

		d1.setLevel(level1);
		assertEquals(level1, d1.getLevel());

		d1.setLevel(level2);
		assertEquals(level2, d1.getLevel());
		
		d1.setLevel(level3);
		assertEquals(level3, d1.getLevel());
		
		d1.setLevel(level4);
		assertEquals(level4, d1.getLevel());
		
		d1.setLevel(level5);
		assertEquals(level5, d1.getLevel());
		
		d1.setLevel(level6);
		assertEquals(level6, d1.getLevel());
		
	}

	
	@Test
	void testGetSetDevelopmntLevelInvalidData() {
		
	Exception ex =	assertThrows(IllegalArgumentException.class, ()->{
			d1.setLevel(invalidLevelHigh);
		});
	
	assertEquals("development level out of range", ex.getMessage());
	
	ex = assertThrows(IllegalArgumentException.class, ()->{
		d1.setLevel(invalidLevelLow);
	});
	
	assertEquals("development level out of range", ex.getMessage());
		
	}
	
	@Test
	void testGetSetcostMultiplierValidData() {

		d1.setCostMultiplier(costMultiplierHigh);
		assertEquals(costMultiplierHigh, d1.getCostMultiplier());
		
		d1.setCostMultiplier(costMultiplierMiddle);
		assertEquals(costMultiplierMiddle, d1.getCostMultiplier());
		
		d1.setCostMultiplier(costMultiplierLow);
		assertEquals(costMultiplierLow, d1.getCostMultiplier());
	
	}
	


}
