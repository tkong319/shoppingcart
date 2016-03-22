/*
 * TCSS 305 - Autumn 2015
 * Assignment 2 - ShoppingCart
 */


package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import model.ShoppingCart;
import org.junit.Before;
import org.junit.Test;



/**
 * Tests for the ShoppingCart class.
 * @author Tung Kong
 * @version 1 10/13/15
 */
public class ShoppingCartTest {
    /** Item object used for testing.*/
    private final Item myTestItem = new Item("Tape", new BigDecimal("2.00"));
    
    /** ItemOrder object used for testing.*/
    private final ItemOrder myTestItemOrder = new ItemOrder(myTestItem, 20);
    
    /** ShoppingCart object used for testing.*/
    private ShoppingCart myCart;
    
    /** Constructs a ShoppingCart object for testing.*/
    @Before
    public void setUp() {
        myCart = new ShoppingCart();
    }
    
    /** Test method for NullPointer {@link ShoppingCart#add(ItemOrder)}.*/
    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        myCart.add(null);
    }

    /** Test method for {@link ShoppingCart#add(ItemOrder)}.*/
    @Test
    public void testAdd() {
        
        final ItemOrder testItemOrder2 = new ItemOrder(myTestItem, 40);
        final ItemOrder testItemOrder3 = 
                        new ItemOrder(new Item("Silly Putty", new BigDecimal("4.41")), 1);
        myCart.add(myTestItemOrder);
        myCart.add(testItemOrder2);
        myCart.add(testItemOrder3);
        assertEquals("Total ItemOrders in shopping cart: 2, "
                        + "ShoppingCart Total: $84.41", myCart.toString());
    }
    
    /** Test method for {@link ShoppingCart#clear()}.*/
    @Test
    public void testClear() {
        myCart.clear();
        assertEquals("Total ItemOrders in shopping cart: 0, "
                        + "ShoppingCart Total: $0.00", myCart.toString());
    }
    
    /** Test method for {@link ShoppingCart#setMembership()}.*/
    @Test
    public void testSetMembership() {
        final ItemOrder bulkItemOrder = new ItemOrder(new Item("Buttons", 
                        new BigDecimal("0.95"), 10, new BigDecimal("5.00")), 10);
        myCart.add(bulkItemOrder);
        myCart.add(myTestItemOrder);
        myCart.setMembership(true);
        assertEquals(new BigDecimal("41.00"), myCart.calculateTotal());
        myCart.setMembership(false);
        assertEquals(new BigDecimal("45.00"), myCart.calculateTotal());
        
    }

}
