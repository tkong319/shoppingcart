/*
 * TCSS 305 - Autumn 2015
 * Assignment 2 - ShoppingCart
 */

package tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.Before;

import org.junit.Test;

/**
 * Tests for the ItemOrder class.
 * @author Tung Kong
 * @version 1 10/13/15
 */
public class ItemOrderTest {
    /** Item object used to pass through the ItemOrder object parameter.*/
    private final Item myTestItem = new Item("Tape", new BigDecimal("2.00"));
    
    /** ItemOrder object used for testing.*/
    private ItemOrder myItemOrder;
    
    /** Constructs ItemOrder object.*/
    @Before
    public void setUp() {
        myItemOrder = new ItemOrder(myTestItem, 20);
    }
    
    /** Test method for IllegalArgument{@link ItemOrder#ItemOrder(Item)}. */
    @Test(expected = IllegalArgumentException.class)
    public void testItemOrderItemIntIllegalArgumentQuantity() {
        new ItemOrder(myTestItem, -2);
    }
    
    /** Test method for NullPointer{@link ItemOrder#ItemOrder(Item)}. */
    @Test(expected = NullPointerException.class)
    public void testItemOrderItemIntNullPointerItem() {
        new ItemOrder(null, 20);
    }
    
    /** Test method for {@link ItemOrder#calculateOrderTotal()}. */
    @Test
    public void testCalculateOrderTotal() {
        assertEquals(new BigDecimal("40.00"), myItemOrder.calculateOrderTotal());
    }
    
    /** Test method for {@link ItemOrder#getItem()}.*/
    @Test
    public void testGetItem() {
        assertEquals(myTestItem, myItemOrder.getItem());
    }
    
    /** Test method for {@link ItemOrder#getQuantity()}.*/
    @Test
    public void testGetQuantity() {
        assertEquals(20, myItemOrder.getQuantity());
    }
    
    /** Test method for {@link ItemOrder#toString()}.*/
    @Test
    public void testToString() {
        assertEquals("Item: Tape, Quantity: 20, Item Total: $40.00", myItemOrder.toString());
    }

}
