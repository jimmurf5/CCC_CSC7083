package climateChangeCrisis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSpecialArea {
	

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSpecialAreaConstructorValid() {
		SpecialArea test = new SpecialArea("Blank Square", 10);
		
		assertEquals("Blank Square", test.getAreaName());
		assertEquals(10, test.getResourceValue());
		assertEquals(false, test.isBelongsToField());
	}
	
	@Test
	void testSpecialAreaConstructorInvalid() {
		
		assertThrows(IllegalArgumentException.class, ()->{
			SpecialArea test2 = new SpecialArea(null, 10);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			SpecialArea test2 = new SpecialArea("Blank Square", -1);
		});
		
	}

	@Test
	void testGetSetResourceValueValid() {
		SpecialArea test3 = new SpecialArea("Blank Square", 10);
		
		test3.setResourceValue(50);
		assertEquals(50, test3.getResourceValue());
		
		test3.setResourceValue(30);
		assertEquals(30, test3.getResourceValue());
	}
	
	@Test
	void testGetSetResourceValueInvalid() {
		
		SpecialArea test4 = new SpecialArea("Blank Square", 10);
		
		assertThrows(IllegalArgumentException.class, ()->{
			test4.setResourceValue(-0.01);
		});
		
		assertThrows(IllegalArgumentException.class, ()->{
			test4.setResourceValue(-1);
		});
	}

}
