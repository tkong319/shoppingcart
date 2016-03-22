
/*
 * TCSS 305 - Autumn 2015
 * Assignment 2 - ShoppingCart
 */

package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.math.BigDecimal;
import model.Item;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests for the Item class.
 * @author Tung Kong
 * @version 1 10/13/15
 */
public class ItemTest {
    
    /** Item name used for testing. */
    private static final String ITEM_NAME = "Silly Putty";
    
    /** Price per item used for testing. */
    private static final BigDecimal PRICE = new BigDecimal("4.41");
    
    /** Bulk quantity used for testing. */
    private static final int BULK_QUANTITY = 6;
    
    /** Bulk price used for testing. */
    private static final BigDecimal BULK_PRICE = new BigDecimal("10.04");
    
    /** Negative price used for testing. */
    private static final BigDecimal NEGATIVE_PRICE = new BigDecimal("-1.00");

    
    /** Item object used for testing. */
    private Item myItem;
    
    /** Initializes a non bulk Item object for testing. */
    @Before
    public void setUp() {
        myItem = new Item(ITEM_NAME, PRICE);
    }
    
    /** Test method for {@link Item#Item(String, BigDecimal, int, BigDecimal)}. */
    @Test
    public void testItemStringBigDecimalIntBigDecimal() {
        final Item bulkItem = new Item(ITEM_NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        assertEquals("Silly Putty, $4.41 (6 for $10.04)", bulkItem.toString());
    }
    
    /** Test method for IllegalArgument 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}.*/
    @Test(expected = IllegalArgumentException.class)
    public void testItemStringBigDecimalIntBigDecimalIllegalArgumentName() {
        new Item("", PRICE, BULK_QUANTITY, BULK_PRICE);
    }
    
    /** Test method for Illegal argument 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemStringBigDecimalIntBigDecimalIllegalArgumentPrice() {
        new Item(ITEM_NAME, NEGATIVE_PRICE, BULK_QUANTITY, BULK_PRICE);
    }
    
    /** Test method for Illegal argument 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemStringBigDecimalIntBigDecimalIllegalArgumentQuantity() {
        new Item(ITEM_NAME, PRICE, -1, BULK_PRICE);
    }

    /** Test method for Illegal argument 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemStringBigDecimalIntBigDecimalIllegalArgumentBulkPrice() {
        new Item(ITEM_NAME, PRICE, BULK_QUANTITY, NEGATIVE_PRICE);
    }
    
    /** Test method for NullPointer {@link Item#Item(String, BigDecimal, int, BigDecimal)}.*/
    @Test(expected = NullPointerException.class) 
    public void testItemStringBigDecimalIntBigDecimalNullName() {
        new Item(null, PRICE, BULK_QUANTITY, BULK_PRICE);
    }
    
    /** Test method for NullPointer {@link Item#Item(String, BigDecimal, int, BigDecimal)}.*/
    @Test(expected = NullPointerException.class) 
    public void testItemStringBigDecimalIntBigDecimalNullPrice() {
        new Item(ITEM_NAME, null, BULK_QUANTITY, BULK_PRICE);
    }
    
    /** Test method for NullPointer {@link Item#Item(String, BigDecimal, int, BigDecimal)}.*/
    @Test(expected = NullPointerException.class) 
    public void testItemStringBigDecimalIntBigDecimalNullBulkPrice() {
        new Item(ITEM_NAME, PRICE, BULK_QUANTITY, null);
    }
    
    /** Test method for IllegalArgument {@link Item#calculateItemTotal(int)}. */
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateItemTotalIllegalArgument() {
        myItem.calculateItemTotal(-1);
    }
    
    /** Test method for {@link Item#calculateItemTotal(int)}. */
    @Test
    public void testCalculateItemTotal() {
        final Item testBulkTotal = new Item(ITEM_NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        assertEquals(new BigDecimal("44.10"), myItem.calculateItemTotal(10));
        assertEquals(new BigDecimal("27.68"), testBulkTotal.calculateItemTotal(10));
        assertEquals(new BigDecimal("8.82"), testBulkTotal.calculateItemTotal(2));
    }
    
    /** Test method for {@link Item#isBulk()}. */
    @Test
    public void testIsBulk() {
        final Item testBulkItem = new Item(ITEM_NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        assertEquals(false, myItem.isBulk());
        assertEquals(true, testBulkItem.isBulk());
    }
    
    /** Test method for {@link Item#toString()}. */
    @Test
    public void testToString() {
        assertEquals("Silly Putty, $4.41", myItem.toString());
    }
    
    /** Test method for {@link Item#equals(Object)}. */
    @Test
    public void testEquals() {
        final Point testPoint = new Point();
        final Item testItem = new Item("Tape", new BigDecimal("2.00"));
        final Item testBulkItem = new Item(ITEM_NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        final Item testOtherBulkItem = new Item(ITEM_NAME, PRICE, BULK_QUANTITY, BULK_PRICE);
        assertEquals(true, myItem.equals(myItem));
        assertEquals(false, myItem.equals(testPoint));
        assertEquals(false, myItem.equals(testItem));
        assertEquals(false, myItem.equals(testBulkItem));
        assertEquals(true, testBulkItem.equals(testOtherBulkItem));
    }
    
    /** Test method for {@link Item#hashCode()}.*/
    @Test
    public void testHashCode() {
        final Item testHash = new Item(ITEM_NAME, PRICE);
        assertEquals(testHash.hashCode(), myItem.hashCode());
    }

}
    
    