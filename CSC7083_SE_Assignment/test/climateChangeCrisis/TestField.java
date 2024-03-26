/**
 * 
 */
package climateChangeCrisis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestField {
	
	//test data
	Field fieldObj;
	Player player1, player2;
	FieldArea area1, area2, area3;
	Development emptySquare;
	
	int areaBuyCostInvalid, areaBuyCostLow, areaBuyCostMed, areaBuyCostHigh, areaDonationCostInvalid,
	areaDonationCostLow, areaDonationCostMed, areaDonationCostHigh;
	
	String validFieldnameLow, validFieldnameMed, validFieldnameHigh, invalidFieldnameLow, 
	invalidFieldnameHigh, emptyFieldname;

	ArrayList<Area> areas, areas2, areasEmpty;
	
	@BeforeEach
	void setUp() throws Exception {
		
		validFieldnameLow = "X".repeat(10);
		validFieldnameMed = "X".repeat(20);
		validFieldnameLow = "X".repeat(30);
		invalidFieldnameLow = "X".repeat(9);
		invalidFieldnameHigh = "X".repeat(31);
		emptyFieldname = "";
		
		areaBuyCostInvalid = -1;
		areaBuyCostLow = 0;
		areaBuyCostMed = 10;
		areaBuyCostHigh = 100;
		areaDonationCostInvalid = -1;
		areaDonationCostLow = 0;
		areaDonationCostMed = 10;
		areaDonationCostHigh = 100;
		
		emptySquare = new Development(1, "Empty Square", "Bla bla bla description", 1.00);
		area1 = new FieldArea("Hurricane Hit", emptySquare);
		area2 = new FieldArea("Wicked Wildfire", emptySquare);
		area3 = new FieldArea("Seashore Sorrow", emptySquare);
		
		areas = new ArrayList<Area>();
		areas2 = new ArrayList<Area>();
		areasEmpty = new ArrayList<Area>();
		
		areas.add(area1);
		areas.add(area2);
		areas.add(area3);
		
		areas2.add(area1);
		areas2.add(area3);
		
		fieldObj = new Field("Water and Food Shortage", areas, areaBuyCostMed, areaDonationCostMed);
		player1 = new Player("Matthew", 100, area1, false, false);
		player2 = new Player("Leonard", 100, area2, false, false);
	}

	
	@Test
	void testFieldConstructorValid() {
		Field test = new Field("Save our Seas", areas, areaBuyCostMed, areaDonationCostMed);
		
		assertEquals("Save our Seas", test.getFieldName());
		assertEquals(areas, test.getAreas());
		assertEquals(10, test.getareaBuyCost());
		assertEquals(10, test.getareaDonationCost());

	}
	
	@Test
	void testFieldConstructorInvalid() {
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field(null, areas, areaBuyCostMed, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field(emptyFieldname, areas, areaBuyCostMed, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field(invalidFieldnameLow, areas, areaBuyCostMed, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field(invalidFieldnameHigh, areas, areaBuyCostMed, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field("Save our Plants", areasEmpty, areaBuyCostMed, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field("Save our Plants", null, areaBuyCostMed, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field("Save our Seas", areas, areaBuyCostInvalid, areaDonationCostMed);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			Field test2 = new Field("Save our Seas", areas, areaBuyCostMed, areaDonationCostInvalid);
		});
		
	}


	@Test
	void testGetSetFieldNameValid() {
		
		fieldObj.setFieldName(validFieldnameLow);
		assertEquals(validFieldnameLow, fieldObj.getFieldName());
		
		fieldObj.setFieldName(validFieldnameMed);
		assertEquals(validFieldnameMed, fieldObj.getFieldName());
		
		fieldObj.setFieldName(validFieldnameHigh);
		assertEquals(validFieldnameHigh, fieldObj.getFieldName());
		
	}
	
	@Test
	void testGetSetFieldNameInvalid() {
		
		Exception e = assertThrows(IllegalArgumentException.class, ()->{
			fieldObj.setFieldName(emptyFieldname);
		});
		
		assertEquals("Field name is too long or too short", e.getMessage());
		
		e = assertThrows(IllegalArgumentException.class, ()->{
			fieldObj.setFieldName(invalidFieldnameLow);
		});
		
		assertEquals("Field name is too long or too short", e.getMessage());
		
		e = assertThrows(IllegalArgumentException.class, ()->{
			fieldObj.setFieldName(invalidFieldnameHigh);
		});
		
		assertEquals("Field name is too long or too short", e.getMessage());
		
		e = assertThrows(IllegalArgumentException.class, ()->{
			fieldObj.setFieldName(null);
		});
		
		assertEquals("Field name cannot be null", e.getMessage());
		
	}


	
	@Test
	void testGetSetAreasValid() {
		fieldObj.setAreas(areas2);
		
		assertTrue(fieldObj.getAreas().contains(area1));
		assertTrue(fieldObj.getAreas().contains(area3));
		assertTrue(fieldObj.getAreas().size() == 2);
		
		fieldObj.setAreas(areas);
		
		assertTrue(fieldObj.getAreas().contains(area1));
		assertTrue(fieldObj.getAreas().contains(area2));
		assertTrue(fieldObj.getAreas().contains(area3));
		assertTrue(fieldObj.getAreas().size() == 3);
	}

	
	@Test
	void testGetSetareaBuyCostValid() {
		fieldObj.setareaBuyCost(areaBuyCostLow);
		assertEquals(areaBuyCostLow, fieldObj.getareaBuyCost());
		
		fieldObj.setareaBuyCost(areaBuyCostMed);
		assertEquals(areaBuyCostMed, fieldObj.getareaBuyCost());
		
		fieldObj.setareaBuyCost(areaBuyCostHigh);
		assertEquals(areaBuyCostHigh, fieldObj.getareaBuyCost());
	}

	@Test
	void testGetSetareaBuyCostInvalid() {
		Exception e = assertThrows(IllegalArgumentException.class, ()->{
			fieldObj.setareaBuyCost(areaBuyCostInvalid);
		});
		
		assertEquals("Area buy cost cannot be less than zero", e.getMessage());
	}
	
	@Test
	void testGetSetareaDonationCostValid() {
		fieldObj.setareaBuyCost(areaDonationCostLow);
		assertEquals(areaDonationCostLow, fieldObj.getareaBuyCost());
		
		fieldObj.setareaBuyCost(areaDonationCostMed);
		assertEquals(areaDonationCostMed, fieldObj.getareaBuyCost());
		
		fieldObj.setareaBuyCost(areaDonationCostHigh);
		assertEquals(areaDonationCostHigh, fieldObj.getareaBuyCost());
	}
	
	@Test
	void testGetSetareaDonationCostInvalid() {
		Exception e = assertThrows(IllegalArgumentException.class, ()->{
			fieldObj.setareaDonationCost(areaDonationCostInvalid);
		});
		
		assertEquals("Area donation cost cannot be less than zero", e.getMessage());
	}


	@Test
	void testGetSetownedByValid() {
		fieldObj.setownedBy(player2);
		assertEquals(player2, fieldObj.getownedBy());
	}
	
	@Test
	void testGetSetownedByInvalid() {
		
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			fieldObj.setownedBy(null);
		});

		assertEquals("Cannot change Field ownership to null", e.getMessage());

		fieldObj.setownedBy(player2);

		e = assertThrows(IllegalArgumentException.class, () -> {
			fieldObj.setownedBy(player1);
		});

		assertEquals("Field is already owned by another Player", e.getMessage());
		
	}

}
