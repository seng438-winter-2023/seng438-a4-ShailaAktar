package org.jfree.data;

import org.junit.*;
import static org.junit.Assert.*;
import org.jfree.data.Range;

public class RangeTest {
	
	private Range exampleRange;
	private Range positiveBounds;
	private Range negativeBounds;
	private Range equalBounds;
	private Range exampleRange2;
	private Range exampleRange3;
	private Range nullRange;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    }
	
	@Before
    public void setUp() throws Exception { 
		exampleRange = new Range(-1, 1);
		exampleRange2 = new Range(-1, 1);
		positiveBounds = new Range(2, 5);
		negativeBounds = new Range(-4, -2);
		equalBounds = new Range(2, 2);
    }
	
	@Test
	public void testConstructor() {
		Range r1 = new Range(0.1, 1000.0);
		assertEquals(r1.getLowerBound(), 0.1, 0.0d);
        assertEquals(r1.getUpperBound(), 1000.0, 0.0d);

        try {
            /*Range r2 =*/ new Range(10.0, 0.0);
            fail("Lower bound cannot be greater than the upper");
        }
        catch (Exception e) {
            // expected
        }
    }
	
	// testing method: getCentralValue()
	@Test
	public void testGetCEntralValue() {
		assertEquals("Central Value should be 0", 0, exampleRange.getCentralValue(), .000000001d);
	}
	
	@Test
	public void testGetCEntralValueSameRange() {
		assertEquals("Central Value should be 2", 2, equalBounds.getCentralValue(), .000000001d);
	}
	
	
	// testing method: contains()
	@Test
	public void testContainsPositiveReturnsTrue() {
		assertTrue(exampleRange.contains(0.5));
	}
	
	@Test
	public void testContainsPositiveReturnsFalse() {
		assertFalse(exampleRange.contains(1.5));
	}
	
	@Test
	public void testContainsNegitiveReturnsTrue() {
		assertTrue(exampleRange.contains(-0.5));
	}
	
	@Test
	public void testContainsNegitiveReturnsFalse() {
		assertFalse(exampleRange.contains(-1.5));
	}
	
	@Test
	public void testContainsLowerBoundReturnsTrue() {
		assertTrue(exampleRange.contains(-1));
	}
	
	@Test
	public void testContainsUpperBoundReturnsTrue() {
		assertTrue(exampleRange.contains(1));
	}
	
	// testing method: intersects()
	@Test
	public void testIntersectsReturnsTrue() {
		assertTrue(positiveBounds.intersects(0, 4));
	}
	
	// new ones added
	@Test
	public void testIntersectsReturnsTrueWithinRange() {
		assertTrue(positiveBounds.intersects(3, 4));
	}
	
	@Test
	public void testIntersectsReturnsTrueAboveRange() {
		assertTrue(positiveBounds.intersects(4, 9));
	}
	
	@Test
	public void testIntersectsReturnsTrueBiggerThanRange() {
		assertTrue(positiveBounds.intersects(1, 9));
	}
	
	@Test
	public void testIntersectsReturnsFalseAbove() {
		assertFalse(positiveBounds.intersects(6, 8));
	}
	
	@Test
	public void testIntersectsReturnsFalseBelow() {
		assertFalse(positiveBounds.intersects(-2, 1));
	}
	
	// end
	
	@Test
	public void testIntersectsOneArgReturnsTrue() {
		assertTrue(positiveBounds.intersects(new Range(2, 4)));
	}
	
	@Test
	public void testIntersectsOneArgReturnsFalse() {
		assertFalse(positiveBounds.intersects(new Range(0, 1)));
	}
	
	// testing method: constrain()
	@Test
	public void testConstrainWithinRange() {
		assertEquals("Should return 3", 3, positiveBounds.constrain(3), .000000001d);
	}
	
	@Test
	public void testConstrainBelowRange() {
		assertEquals("Should return 2", 2, positiveBounds.constrain(0), .000000001d);
	}
	
	@Test
	public void testConstrainAboveRange() {
		assertEquals("Should return 5", 5, positiveBounds.constrain(7), .000000001d);
	}
	
	// testing method: combine()
	@Test
	public void testCombineWithNull() {
		Range r1 = null;
		Range r2 = null;
		assertNull("should return null", Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWithNull1() {
		Range r1 = null;
		Range r2 = new Range(1, 2);
		assertEquals("should return r2", r2, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineWithNull2() {
		Range r1 = new Range(-1, 2);
		Range r2 = null;
		assertEquals("should return r1", r1, Range.combine(r1, r2));
	}
	
	@Test
	public void testCombineNormal() {
		assertEquals("should return ()", new Range(-1, 5), Range.combine(exampleRange, positiveBounds));
	}
	
	@Test
	public void testCombineNaN() {
		Range r1 = new Range(1.0, 2.0);
		Range r3 = new Range(Double.NaN, 1.3);
		Range rr = Range.combine(r1, r3);
		assertTrue(Double.isNaN(rr.getLowerBound()));
		assertEquals(2.0, rr.getUpperBound(), .000000001d);
		
		Range r4 = new Range(1.7, Double.NaN);
		rr = Range.combine(r4, r1);
		assertEquals(1.0, rr.getLowerBound(), .000000001d);
		assertTrue(Double.isNaN(rr.getUpperBound()));
	}
	
	// testing method: combineIgnoringNaN()
	@Test
	public void testCombineIgnoringNaNWithNull() {
		assertNull("should return null", Range.combineIgnoringNaN(null, null));
	}
	
	@Test
	public void testCombineIgnoringNaNWithNull1() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(1.5, 2.5);
		assertEquals("should return r2", r2, Range.combineIgnoringNaN(null, r2));
	}
	
	@Test
	public void testCombineIgnoringNaNWithNull2() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(1.5, 2.5);
		assertEquals("should return r1", r1, Range.combineIgnoringNaN(r1, null));
	}
	
	@Test
	public void testCombineIgnoringNaN() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(1.5, 2.5);
		assertEquals(new Range(1.0, 2.5), Range.combineIgnoringNaN(r1, r2));
	}
	
	@Test 
	public void testCombineIgnoringNaN_NaN_LB() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(1.5, 2.5);
		Range r3 = new Range(Double.NaN, 1.3);
		Range r4 = new Range(1.7, Double.NaN);
		Range rr = Range.combineIgnoringNaN(r1, r3);
		assertEquals(1.0, rr.getLowerBound(), .000000001d);
		assertEquals(2.0, rr.getUpperBound(), .000000001d);
	}
	
	@Test 
	public void testCombineIgnoringNaN_NaN_UB() {
		Range r1 = new Range(1.0, 2.0);
		Range r2 = new Range(1.5, 2.5);
		Range r3 = new Range(Double.NaN, 1.3);
		Range r4 = new Range(1.7, Double.NaN);
		Range rr = Range.combineIgnoringNaN(r4, r1);
		assertEquals(1.0, rr.getLowerBound(), .000000001d);
		assertEquals(2.0, rr.getUpperBound(), .000000001d);
	}
	
	// testing method: expandToInclude()
	@Test
	public void testExpandToIncludeNullRange() {
		assertEquals("should return (2, 2)", new Range(2, 2), Range.expandToInclude(null, 2));
	}
	
	@Test
	public void testExpandToIncludeValueLessThanLB() {
		Range r1 = new Range(1, 2);
		assertEquals("should return (-1, 2)", new Range(-1, 2), Range.expandToInclude(r1, -1));
	}
	
	@Test
	public void testExpandToIncludeValueMoreThanUB() {
		Range r1 = new Range(1, 2);
		assertEquals("should return (1, 4)", new Range(1, 4), Range.expandToInclude(r1, 4));
	}
	
	@Test
	public void testExpandToIncludeValueWithinBounds() {
		Range r1 = new Range(1, 4);
		assertEquals("should return (1, 4)", new Range(1, 4), Range.expandToInclude(r1, 2));
	}
	
	// testing method: expand()
	@Test
	public void testExpandBothBounds() {
		Range r1 = new Range(0.0, 100.0);
		Range r2 = Range.expand(r1, 0.10, 0.10);
		assertEquals(-10.0, r2.getLowerBound(), 0.001);
		assertEquals(110.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testExpandZeroMargin() {
		Range r1 = new Range(0.0, 100.0);
		Range r2 = Range.expand(r1, 0.0, 0.0);
		assertEquals(r1, r2);
	}
	
	@Test
	public void testExpandNullRange() {
		try {
			Range.expand(null, 0.1, 0.1);
			fail("Null value is accepted");
		}
		catch (Exception e) {
		}
	}
	
	// delete
	@Test
	public void testExpandInvalidMargins() {
		Range r1 = new Range(0.0, 100.0);
		Range r2 = Range.expand(r1, -0.8, -0.5);
		assertEquals(65.0, r2.getLowerBound(), 0.001);
		assertEquals(65.0, r2.getUpperBound(), 0.001);
	}
	
	// testing method: shift()
	@Test
	public void testShift() {
		Range r1 = new Range(10.0, 20.0);
		Range r2 = Range.shift(r1, 20.0);
		assertEquals(30.0, r2.getLowerBound(), 0.001);
		assertEquals(40.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftAllowCrossing() {
		Range r1 = new Range(0.0, 100.0);
		Range r2 = Range.shift(r1, -50.0, true);
		assertEquals(-50.0, r2.getLowerBound(), 0.001);
		assertEquals(50.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftAllowCrossingPosDelta() {
		Range  r1 = new Range(-10.0, 20.0);
		Range r2 = Range.shift(r1, 20.0, true);
		assertEquals(10.0, r2.getLowerBound(), 0.001);
      	assertEquals(40.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftNoCrossing() {
		Range r1 = new Range(-10.0, 20.0);
		Range r2 = Range.shift(r1, -30.0, false);
		assertEquals(-40.0, r2.getLowerBound(), 0.001);
		assertEquals(0.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftNoCrossingPosDelta() {
		Range r1 = new Range(-10.0, 20.0);
		Range r2 = Range.shift(r1, 20.0, false);
		assertEquals(0.0, r2.getLowerBound(), 0.001);
		assertEquals(40.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testShiftZeroDelta() {
		Range r1 = new Range(-10.0, 20.0);
		Range r2 = Range.shift(r1, 0.0);
		assertEquals(r1, r2);
	}
	
	@Test
	public void testShiftNullRange() {
		try {
			Range.shift(null, 0.1);
			fail("Null value is accepted");
		}
		catch (Exception e) {
		}
	}
	
	
	// testing method: scale()
	@Test
	public void testScale() {
		Range r1 = new Range(0.0, 100.0);
		Range r2 = Range.scale(r1, 0.10);
		assertEquals(0.0, r2.getLowerBound(), 0.001);
		assertEquals(10.0, r2.getUpperBound(), 0.001);
	}
	
	@Test
	public void testScaleFactorOne() {
		Range r1 = new Range(0.0, 100.0);
		Range r2 = Range.scale(r1, 1.0);
		assertEquals(r1, r2);
	}
	
	@Test
	public void testScaleNullRange() {
		 try {
			 Range.scale(null, 0.1);
	         fail("Null value is accepted");
	     }
	     catch (Exception e) {
	     }
	}
	
	@Test
	public void testScaleNegativeFactor() {
		Range r1 = new Range(0.0, 100.0);
		try {
			Range.scale(r1, -0.5);
			fail("Negative factor accepted");
		}
		catch (Exception e) {
		}
	}
	
	// testing method: isNaNRange()
	@Test
	public void testIsNaNRangeNaNBBounds() {
		assertTrue(new Range(Double.NaN, Double.NaN).isNaNRange());	
	}
	
	@Test
	public void testIsNaNRange() {
		assertFalse(new Range(1.0, 2.0).isNaNRange());
	}
	
	@Test
	public void testIsNaNRangeNaNLB() {
		assertFalse(new Range(Double.NaN, 2.0).isNaNRange());
	}
	
	@Test
	public void testIsNaNRangeNaNUB() {
		assertFalse(new Range(1.0, Double.NaN).isNaNRange());
	}
	
	
	// testing method: getLength()
	@Test
	public void testGetLength() {
		assertEquals("Length from -1 to 1 should be 2", 
				2, exampleRange.getLength(), .000000001d);
	}
	
	
	// testing method: hashCode()
	@Test
	public void testHashCode() {
		Range r1 = new Range(1.0, 100.0);
		Range r2 = new Range(1.0, 100.0);
		assertEquals(r1.hashCode(), r2.hashCode());
	}
	
	@Test
    public void testToString() {
        assertEquals("should return 'Range[-1.0,1.0]'",
        "Range[-1.0,1.0]" , exampleRange.toString());
    }
	
	@Test
	public void testGetLengthPositiveBounds() {
		assertEquals("Length from 2 to 5 should be 3", 
				3, positiveBounds.getLength(), .000000001d);
	}
	
	@Test
	public void testGetLengthNegitiveBounds() {
		assertEquals("Length from -4 to -2 should be 2", 
				2, negativeBounds.getLength(), .000000001d);
	}
	
	
	@Test
	public void testGetLengthEqualBounds() {
		assertEquals("Length from 2 to 2 should be 0", 
				0, equalBounds.getLength(), .000000001d);
	}
	
	
	
	// testing method: getLowerBound()
	@Test
	public void testGetLowerBound() {
		assertEquals("Lower bound of -1 to 1 is -1",
				-1, exampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundEqualBounds() {
		assertEquals("Lower bound of 2 to 2 is 2",
				2, equalBounds.getLowerBound(), .000000001d);
	}
	
	
	
	// testing method: getUpperBound()
	@Test
	public void testGetUpperBoundEqualBounds() {
		assertEquals("Upper bound of 2 to 2 is 2",
				2, equalBounds.getUpperBound(), .000000001d);
	}
	
	@Test
	public void testGetUpperBound() {
		assertEquals("Upper bound of -1 to 1 is 1",
				1, exampleRange.getUpperBound(), .000000001d);
	}
	
	
	
	// testing method: equals()
	@Test
	public void testEqualsSameRangeReturnsTrue() {
		assertTrue(exampleRange.equals(exampleRange2));
	}
	
	@Test
	public void testEqualsDifferentRangeReturnsFalse() {
		assertFalse(exampleRange.equals(positiveBounds));
	}
	
	@Test
	public void testEqualsNullObjectReturnsFalse() {
		assertFalse(exampleRange.equals(nullRange));
	}
	
	@Test
	public void testEqualsSameRangeDifferentBoundsReturnsFalse() {
		assertFalse(exampleRange.equals(negativeBounds));
	}
	
	// new methods
	@Test (expected = IllegalArgumentException.class)
	public void testInvalidRange() {
		exampleRange3 = new Range(1, -1);
	}
	
	

	
	
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
