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
	Field field;
	SpecialArea goSquare;

	@BeforeEach
	void setUp() throws Exception {
		validFieldnameLow = "X".repeat(10);
		validFieldnameMed = "X".repeat(20);
		validFieldnameHigh = "X".repeat(30);
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
		goSquare = new SpecialArea("Go Sq", 100);
		player1 = new Player("Matthew", goSquare);
		player2 = new Player("Leonard", goSquare);

	    emptySquare = new Development(1, "Empty Square", "Bla bla bla description", 1.00);
	        area1 = new FieldArea("Hurricane Hit", emptySquare, emptyFieldname, player1);
	        area2 = new FieldArea("Wicked Wildfire", emptySquare, emptyFieldname, player1);
	        area3 = new FieldArea("Seashore Sorrow", emptySquare, emptyFieldname, player1);

		areas = new ArrayList<Area>();
		areas2 = new ArrayList<Area>();
		areasEmpty = new ArrayList<Area>();
		areas.add(area1);
		areas.add(area2);
		areas.add(area3);
		areas2.add(area1);
		areas2.add(area3);
		fieldObj = new Field("Water and Food Shortage", areas, areaBuyCostMed, areaDonationCostMed, null);
        field = new Field("Rising Seas", areas, areaBuyCostMed, areaDonationCostMed, player1);
	}
 
	
	@Test
	void testFieldConstructorValid() {

		assertEquals("Rising Seas", field.getFieldName());
		assertEquals(areas, field.getAreas());
		assertEquals(areaBuyCostMed, field.getareaBuyCost());
		assertEquals(areaDonationCostMed, field.getareaDonationCost());
		assertEquals(player1,field.getownedBy());
	}

   
    
    @Test
    void testFieldConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field(null, areas, areaBuyCostMed, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field(emptyFieldname, areas, areaBuyCostMed, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field(invalidFieldnameLow, areas, areaBuyCostMed, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field(invalidFieldnameHigh, areas, areaBuyCostMed, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field("Save our Plants", areasEmpty, areaBuyCostMed, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field("Save our Plants", null, areaBuyCostMed, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field("Save our Seas", areas, areaBuyCostInvalid, areaDonationCostMed, null);
        });
        
        assertThrows(IllegalArgumentException.class, ()->{
            Field test2 = new Field("Save our Seas", areas, areaBuyCostMed, areaDonationCostInvalid, null);
        });
        
    }
    
    @Test
    void testGetSetFieldNameValid() {
        
        field.setFieldName(validFieldnameLow);
        assertEquals(validFieldnameLow, field.getFieldName());
        
        field.setFieldName(validFieldnameMed);
        assertEquals(validFieldnameMed, field.getFieldName());
        
        field.setFieldName(validFieldnameHigh);
        assertEquals(validFieldnameHigh, field.getFieldName());
        
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
        assertEquals(2, fieldObj.getAreas().size());
        
        fieldObj.setAreas(areas);
        
        assertTrue(fieldObj.getAreas().contains(area1));
        assertTrue(fieldObj.getAreas().contains(area2));
        assertTrue(fieldObj.getAreas().contains(area3));
        assertEquals(3, fieldObj.getAreas().size());
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
        fieldObj.setareaDonationCost(areaDonationCostLow);
        assertEquals(areaDonationCostLow, fieldObj.getareaDonationCost());
        
        fieldObj.setareaDonationCost(areaDonationCostMed);
        assertEquals(areaDonationCostMed, fieldObj.getareaDonationCost());
        
        fieldObj.setareaDonationCost(areaDonationCostHigh);
        assertEquals(areaDonationCostHigh, fieldObj.getareaDonationCost());
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
    
    
}
