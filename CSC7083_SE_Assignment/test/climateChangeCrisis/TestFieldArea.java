package climateChangeCrisis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestFieldArea {

	// test data

	Development emptySquare;
	Player player1, player2;
	FieldArea area1, area2;

	String validAreanameLow, validAreanameMed, validAreanameHigh, invalidAreanameLow, invalidAreanameHigh,
			emptyAreaname;

	@BeforeEach
	void setUp() throws Exception {

		validAreanameLow = "X".repeat(10);
		validAreanameMed = "X".repeat(20);
		validAreanameHigh = "X".repeat(30);
		invalidAreanameLow = "X".repeat(9);
		invalidAreanameHigh = "X".repeat(31);
		emptyAreaname = "";

		area1 = new FieldArea("Hurricane Hit", emptySquare);
		area2 = new FieldArea("Wicked Wildfire", emptySquare);
		emptySquare = new Development(1, "Empty Square", "Bla bla bla description", 1.00);
		player1 = new Player("Matthew", 100, area1, false, false);
		player2 = new Player("Leonard", 100, area2, false, false);
	}

	@Test
	void testFieldAreaConstructorValid() {
		FieldArea test = new FieldArea("Laughing larry", emptySquare);

		assertEquals("Laughing larry", test.getAreaName());
		assertEquals(emptySquare, test.getdevelopmentObj());
		assertEquals(null, test.getOwnedBy());
		assertEquals(true, test.isBelongsToField());

		FieldArea test2 = new FieldArea(validAreanameLow, emptySquare);

		assertEquals(validAreanameLow, test2.getAreaName());
		assertEquals(emptySquare, test2.getdevelopmentObj());
		assertEquals(null, test2.getOwnedBy());
		assertEquals(true, test2.isBelongsToField());

		FieldArea test3 = new FieldArea(validAreanameMed, emptySquare);

		assertEquals(validAreanameMed, test3.getAreaName());
		assertEquals(emptySquare, test3.getdevelopmentObj());
		assertEquals(null, test3.getOwnedBy());
		assertEquals(true, test3.isBelongsToField());

		FieldArea test4 = new FieldArea(validAreanameHigh, emptySquare);

		assertEquals(validAreanameHigh, test4.getAreaName());
		assertEquals(emptySquare, test4.getdevelopmentObj());
		assertEquals(null, test4.getOwnedBy());
		assertEquals(true, test4.isBelongsToField());

	}

	@Test
	void testFieldAreaConstructorInvalid() {
		assertThrows(IllegalArgumentException.class, () -> {
			FieldArea test5 = new FieldArea(null, emptySquare);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			FieldArea test5 = new FieldArea(invalidAreanameLow, emptySquare);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			FieldArea test5 = new FieldArea(invalidAreanameHigh, emptySquare);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			FieldArea test5 = new FieldArea(emptyAreaname, emptySquare);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			FieldArea test5 = new FieldArea("Hurricane Hit", null);
		});

	}

	@Test
	void testGetSetdevelopmentObjValid() {

		Development ownedSquare = new Development(2, "Owned Square", "Bla bla bla description", 1.10);
		area1.setdevelopmentObj(ownedSquare);
		assertEquals(ownedSquare, area1.getdevelopmentObj());
	}

	@Test
	void testGetSetdevelopmentObjInvalid() {
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setdevelopmentObj(null);
		});

		assertEquals("Development cannot be null", e.getMessage());

		Development ownedSquare = new Development(2, "Owned Square", "Bla bla bla description", 1.10);
		FieldArea area3 = new FieldArea("Seed sorrow", ownedSquare);

		e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setdevelopmentObj(emptySquare);
		});

		assertEquals("Development level needs to increase", e.getMessage());
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
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setOwnedBy(null);
		});

		assertEquals("Cannot set FieldArea ownership to null", e.getMessage());

		area1.setOwnedBy(player2);

		e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setOwnedBy(player1);
		});

		assertEquals("Another Player already owns this FieldArea", e.getMessage());
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
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setAreaName(null);
		});

		assertEquals("Area name cannot be null", e.getMessage());

		e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setAreaName(invalidAreanameLow);
		});

		assertEquals("Area name is too long or too short", e.getMessage());

		e = assertThrows(IllegalArgumentException.class, () -> {
			area1.setAreaName(invalidAreanameHigh);
		});

		assertEquals("Area name is too long or too short", e.getMessage());
	}

}
