/*
 * TCSS 305 - Autumn 2015
 * Assignment 2 - ShoppingCart
 */

package model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Stores information for an item order.
 * @author Tung Kong
 * @version 1 10/13/15
 */
public final class ItemOrder {
    
    /** The given item. */
    private final Item myItem;
    
    /** The given desired quantity. */
    private final int myItemQuantity;
    

    /**
     * Constructs an item order with specified item and quantity.
     * @param theItem the item to be ordered.
     * @param theQuantity the desired quantity of item.
     * @throws IllegalArgumentException if quantity is negative.
     * @throws NullPointerException if item passed is null.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        myItem = Objects.requireNonNull(theItem);
        myItemQuantity = theQuantity;

    }
    

    /**
     * Calculates total price of item for the desired quantity.
     * @return the order total as a BigDecimal.
     */
    public BigDecimal calculateOrderTotal() {
        return myItem.calculateItemTotal(myItemQuantity);
    }

    /**
     * @return reference to the item of this item order.
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * Returns desired quantity for item.
     * @return item quantity.
     */
    public int getQuantity() {
        
        return myItemQuantity;
    }

    /**
     * String representation of the item order in the format:
     * Item: itemName, Quantity: 2, Item Total: $6.50.
     */
    @Override
    public String toString() {
        final int lastIndexOfItemName = myItem.toString().indexOf(",");
        final String itemName = myItem.toString().substring(0, lastIndexOfItemName);
        
        return "Item: " + itemName + ", Quantity: " + getQuantity() 
            + ", Item Total: $" + calculateOrderTotal();

        
    }

}
