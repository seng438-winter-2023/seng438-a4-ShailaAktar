package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.jmock.api.ExpectationError;
import org.junit.*;
import java.lang.*;
import java.util.*;

/**
 * Some tests for the {@link DataUtilities} class.
 */
public class DataUtilitiesTest {
	private double[] data;
	private double[][] data2D;
	private Mockery mockingContext;
	private DefaultKeyedValues2D values1;
	private Values2D values;
	private DefaultKeyedValues keyVal;
	private static final double EPSILON = 0.000000001;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    }
	
	@Before
    public void setUp() throws Exception { 
		data = new double[3];
		data[0] = 1.0; data[1] = 2.0; data[2] = 3.0;
		
		data2D = new double[3][2];
		data2D[0][0] = 1.0;data2D[0][1] = 2.0;data2D[1][0] = 3.0;data2D[1][1] = 4.0;data2D[2][0] = 5.0;data2D[2][1] = 6.0;
		
		mockingContext = new Mockery();
    }
	
	// testing method: createNumberArray()
	@Test
    public void testCreateNumberArrayValid() {
        Number[] result = DataUtilities.createNumberArray(data);
        Number[] expected = {1.0, 2.0, 3.0};       
        assertArrayEquals(result, expected);
    }
	
	@Test
	public void testCreateNumberArrayLenngth() {
        Number[] result = DataUtilities.createNumberArray(data);
        Number[] expected = {1.0, 2.0, 3.0};       
        assertEquals(result.length, expected.length);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArrayExceptionThrown() {
		DataUtilities.createNumberArray(null);
	}

	
	// testing method: createNumberArray2D()
	@Test
    public void testCreateNumberArray2DValid() {
        Number[][] result = DataUtilities.createNumberArray2D(data2D);
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};       
        assertArrayEquals(result, expected);
    }
	
	@Test
    public void testCreateNumberArray2DLength() {
        Number[][] result = DataUtilities.createNumberArray2D(data2D);
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};  
        assertEquals(result.length, expected.length);
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArray2DExceptionThrown() {
		DataUtilities.createNumberArray2D(null);
	}
    


	// testing method: calculateColumnTotal()
	@Test
	public void testcalculateColumnTotalForTwoValues() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(7.5, 0, 0);
		values1.addValue(2.5, 1, 0);
	    double result = DataUtilities.calculateColumnTotal(values1, 0);
	    assertEquals(result, 10.0, .000000001d);
	}
	
	@Test
	public void testcalculateColumnTotalForSecondCol() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(7.5, 0, 0);
		values1.addValue(2.5, 1, 0);
		values1.addValue(8.5, 0, 1);
		values1.addValue(3.5, 1, 1);
	    double result = DataUtilities.calculateColumnTotal(values1, 1);
	    assertEquals(result, 12.0, .000000001d);
	}
	
	@Test
	public void testCalculateColumnTotalForEmptyTable() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(null, 0, 0);
	    double result = DataUtilities.calculateColumnTotal(values1, 0);
	    assertEquals(result, 0.0, .000000001d);
	}
	
	@Test
	public void testCalculateColumnTotalForSingleValue() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(10.0, 0, 0);
	    double result = DataUtilities.calculateColumnTotal(values1, 0);
	    assertEquals(result, 10.0, .000000001d);
	}

	@Test
	public void testCalculateColumnTotalForNegativeValues() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(-2.0, 0, 0);
		values1.addValue(-3.0, 1, 0);
		values1.addValue(-4.0, 2, 0);
		values1.addValue(-5.0, 3, 0);
	    double result = DataUtilities.calculateColumnTotal(values1, 0);
	    assertEquals(result, -14.0, .000000001d);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateColumnTotalForInvalidColumn() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(-2.0, 0, 0);
		// this will throw an exception because the column index is out of bounds
	    DataUtilities.calculateColumnTotal(values1, 2);
	}
	
	@Test
    public void testCalculateColumnTotal2() {
        values1 = new DefaultKeyedValues2D();
        values1.addValue(new Double(1.0), "R0", "C0");
        values1.addValue(new Double(2.0), "R0", "C1");
        values1.addValue(new Double(3.0), "R1", "C0");
        values1.addValue(new Double(4.0), "R1", "C1");
        assertEquals(4.0, DataUtilities.calculateColumnTotal(values1, 0,
                new int[] {0, 1}), EPSILON);
        assertEquals(1.0, DataUtilities.calculateColumnTotal(values1, 0,
                new int[] {0}), EPSILON);
        assertEquals(3.0, DataUtilities.calculateColumnTotal(values1, 0,
                new int[] {1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values1, 0,
                new int[] {}), EPSILON);

        assertEquals(6.0, DataUtilities.calculateColumnTotal(values1, 1,
                new int[] {0, 1}), EPSILON);
        assertEquals(2.0, DataUtilities.calculateColumnTotal(values1, 1,
                new int[] {0}), EPSILON);
        assertEquals(4.0, DataUtilities.calculateColumnTotal(values1, 1,
                new int[] {1}), EPSILON);

        values1.setValue(null, "R1", "C1");
        assertEquals(2.0, DataUtilities.calculateColumnTotal(values1, 1,
                new int[] {0, 1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values1, 1,
                new int[] {1}), EPSILON);
    }

    /**
     * Some checks for the calculateRowTotal() method.
     */
    @Test
    public void testCalculateRowTotal() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(new Double(1.0), "R0", "C0");
        table.addValue(new Double(2.0), "R0", "C1");
        table.addValue(new Double(3.0), "R1", "C0");
        table.addValue(new Double(4.0), "R1", "C1");
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 0), EPSILON);
        assertEquals(7.0, DataUtilities.calculateRowTotal(table, 1), EPSILON);
        table.setValue(null, "R1", "C1");
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 1), EPSILON);
    }

    
    // testing method: calculateRowTotal()
	@Test
	public void testcalculateRowTotalForTwoValues() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(7.5, 0, 0);
		values1.addValue(2.5, 0, 1);
	     double result = DataUtilities.calculateRowTotal(values1, 0);
	     assertEquals(result, 10.0, .000000001d);
	}
	
	@Test
	public void testcalculateRowTotalForSecondRow() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(7.5, 0, 0);
		values1.addValue(2.5, 0, 1);
		values1.addValue(8.5, 1, 0);
		values1.addValue(3.5, 1, 1);
	     double result = DataUtilities.calculateRowTotal(values1, 1);
	     assertEquals(result, 12.0, .000000001d);
	}
	
	@Test
	public void testCalculateRowTotalForEmptyTable() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(null, 0, 0);
	    double result = DataUtilities.calculateRowTotal(values1, 0);
	    assertEquals(result, 0.0, .000000001d);
	}
	
	@Test
	public void testCalculateRowTotalForSingleValue() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(10.0, 0, 0);
	    double result = DataUtilities.calculateRowTotal(values1, 0);
	    assertEquals(result, 10.0, .000000001d);
	}
	
	@Test
	public void testCalculateRowTotalForNegativeValues() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(-2.0, 0, 0);
		values1.addValue(-3.0, 0, 1);
		values1.addValue(-4.0, 0, 2);
		values1.addValue(-5.0, 0, 3);
	    double result = DataUtilities.calculateRowTotal(values1, 0);
	    assertEquals(result, -14.0, .000000001d);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateRowTotalForInvalidRow() {
		values1 = new DefaultKeyedValues2D(false);
		values1.addValue(-2.0, 0, 0);
	    // this will throw an exception because the row index is out of bounds
	    DataUtilities.calculateRowTotal(values1, 2);
	}

	
    // testing method: equal()
	@Test
	public void testEqualNull() {
		assertTrue(DataUtilities.equal(null, null));
	}
	
	@Test
	public void testEqualReturnsTrue() {
		double[][] a = new double[5][];
        double[][] b = new double[5][];
        assertTrue(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testEqualReturnsFalse() {
		double[][] a = new double[4][];
        double[][] b = new double[5][];
        assertFalse(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testEqualReturnsFalse2() {
		double[][] a = new double[4][];
        double[][] b = new double[4][];
        b[0] = new double[6];
        assertFalse(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testEqualReturnsFalse3() {
		double[][] a = new double[4][6];
        double[][] b = new double[4][6];
        a[0][0] = 1.0;
        b[0][0] = 1.0;
        a[0][1] = Double.NaN;
        assertFalse(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testEqualReturnsTrue2() {
		double[][] a = new double[4][6];
        double[][] b = new double[4][6];
        a[0][0] = 1.0;
        b[0][0] = 1.0;
        a[0][1] = Double.NaN;
        b[0][1] = Double.NaN;
        assertTrue(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testEqualReturnsTrueInfinity() {
		double[][] a = new double[4][6];
        double[][] b = new double[4][6];
        a[0][0] = 1.0;
        b[0][0] = 1.0;
        a[0][1] = Double.NaN;
        b[0][1] = Double.NaN;
        a[0][2] = Double.NEGATIVE_INFINITY;
        b[0][2] = Double.NEGATIVE_INFINITY;
        assertTrue(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testEqualReturnsFalseInfinity() {
		double[][] a = new double[4][6];
        double[][] b = new double[4][6];
        a[0][0] = 1.0;
        b[0][0] = 1.0;
        a[0][1] = Double.NaN;
        b[0][1] = Double.NaN;
        a[0][2] = Double.NEGATIVE_INFINITY;
        b[0][2] = Double.NEGATIVE_INFINITY;
        a[0][3] = Double.POSITIVE_INFINITY;
        b[0][3] = Double.NEGATIVE_INFINITY;
        assertFalse(DataUtilities.equal(a, b));
	}
	

    // testing method: clone()
	@Test
	public void testCloneReturnsTrue() {
		double[][] a = new double[1][];
        double[][] b = DataUtilities.clone(a);
        assertTrue(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testCloneReturnsFalse() {
		double[][] a = new double[1][];
        double[][] b = DataUtilities.clone(a);
        a[0] = new double[] { 3.0, 4.0 };
        assertFalse(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testCloneReturnsTrueNaN() {
		double[][] a = new double[2][3];
        a[0][0] = 1.23;
        a[1][1] = Double.NaN;
        double[][] b = DataUtilities.clone(a);
        assertTrue(DataUtilities.equal(a, b));
	}
	
	@Test
	public void testCloneReturnsFalse2() {
		double[][] a = new double[2][3];
        double[][] b = DataUtilities.clone(a);
        a[0][0] = 99.9;
        assertFalse(DataUtilities.equal(a, b));
	}
	
	// testing method: getCumulativePercentages()
	@Test
	public void testGetCumulativePercentages() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue("0", 9);
		data.addValue("1", 2);
		data.addValue("2", 14);
		
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		
		List<String> keys = result.getKeys();
		
		assertEquals(3, keys.size());
		assertEquals(9/25.0, result.getValue("0"));
		assertEquals(11/25.0, result.getValue("1"));
		assertEquals(25/25.0, result.getValue("2"));
	}
	
	@Test
	public void testGetCumulativePercentagesSingle() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue("0", 9);
		
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		
		List<String> keys = result.getKeys();
		
		assertEquals(1, keys.size());
		assertEquals(9/9.0, result.getValue("0"));
	
	}
	
	@Test
	public void testGetCumulativePercentagesEmpty() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		
		List<String> keys = result.getKeys();
		
		assertEquals(0, keys.size());
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetCumulativePercentagesNullData() {
		DataUtilities.getCumulativePercentages(null);
	}
	
	
   

}
