/*
 * TCSS 305 - Autumn 2015
 * Assignment 2 - ShoppingCart
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents an item and stores its information.
 * @author Tung Kong
 * @version 1 10/13/15
 */
public final class Item {
    // Instance fields
    
    /** The name of item. */
    private final String myItemName;
    
    /** The price of one item. */
    private final BigDecimal myPrice;
    
    /** The quantity needed for an item to get bulk pricing. */
    private int myBulkQuantity;
    
    /** The price for bulk quantity. */
    private BigDecimal myBulkPrice;
    
    /** 
     * Constructs item with specified name and price.
     * @param theName name of item.
     * @param thePrice price of one item.
     * @throws IllegalArgumentException if theName is empty and/or thePrice is negative.
     * @throws NullPointerException if theName and/or thePrice is null.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        
        if ("".equals(theName)) {
            throw new IllegalArgumentException("Item name cannot be blank.");
        }
  
        if (thePrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("Prices must be positive.");
        }
        
        myItemName = Objects.requireNonNull(theName);
        myPrice = Objects.requireNonNull(thePrice);
  

    }

    /**
     * Constructs an item with specified name, price, bulk quantity and bulk price.
     * Calls the other Item constructor.
     * @param theName name of item.
     * @param thePrice price of one item.
     * @param theBulkQuantity quantity needed for item to get bulk pricing.
     * @param theBulkPrice bulk price of item.
     * @throws IllegalArgumentException if theBulkQuantity 
     * is negative or theBulkPrice is negative.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity, 
                final BigDecimal theBulkPrice) {
        this(theName, thePrice);
        if (theBulkQuantity < 0) {
            throw new IllegalArgumentException("Bulk quantity must be positive.");
        }
        if (theBulkPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("Bulk price must be positive.");
        }
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = Objects.requireNonNull(theBulkPrice);

    }

    /**
     * Calculates the total cost of item for the given quantity.
     * @param theQuantity quantity of item.
     * @return the total cost as a BigDecimal.
     */
    public BigDecimal calculateItemTotal(final int theQuantity) {
        if (theQuantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        BigDecimal total;
        if (isBulk() && theQuantity >= myBulkQuantity) {
            final BigDecimal numberOfBulk = new BigDecimal(theQuantity / myBulkQuantity);
            final BigDecimal bulkPriceTotal = numberOfBulk.multiply(myBulkPrice);
            final int remainder = theQuantity % myBulkQuantity;
            final BigDecimal remainderItem = new BigDecimal(remainder);
            final BigDecimal nonBulkPriceTotal = remainderItem.multiply(myPrice);
            total = bulkPriceTotal.add(nonBulkPriceTotal);
        } else {
            final BigDecimal quantity = new BigDecimal(theQuantity);
            total = quantity.multiply(myPrice);
        }
        
        return total.setScale(2, RoundingMode.HALF_EVEN);
 
    }
    
    /**
     * An item is a bulk item if it has bulk pricing.
     * @return true if item has bulk pricing, false otherwise.
     */
    public boolean isBulk() { 
        
        return myBulkPrice != null;
    }
    
    /**
     * String representation of Item object formatted as: 
     * Non Bulk item - X, $19.99 with X being item name and per item price $19.99.
     * Bulk item - X, $19.99 (5 for $89.99) with bulk quantity 5 and bulk price $89.99.
     * 
     */
    @Override
    public String toString() {
        final StringBuilder itemNamePrice = new StringBuilder();
        final NumberFormat priceFormat = NumberFormat.getCurrencyInstance(Locale.US);
        
        itemNamePrice.append(myItemName);
        itemNamePrice.append(", ");
        itemNamePrice.append(priceFormat.format(myPrice));
        if (isBulk()) {
            itemNamePrice.append(" (");
            itemNamePrice.append(myBulkQuantity);
            itemNamePrice.append(" for ");
            itemNamePrice.append(priceFormat.format(myBulkPrice));
            itemNamePrice.append(')');
        } 
        return itemNamePrice.toString();
    }

    /**
     * Checks if two items are equivalent.
     * @return True if they are equivalent, false otherwise.
     */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }
        if (!(theOther instanceof Item)) {
            return false;
        }
        final Item otherItem = (Item) theOther;
        return Objects.equals(myItemName, otherItem.myItemName)
                        && Objects.equals(myPrice, otherItem.myPrice) 
                        && Objects.equals(myBulkPrice, otherItem.myBulkPrice) 
                        && Objects.equals(myBulkQuantity, otherItem.myBulkQuantity);
    }

    /**
     * @return A hashcode for item.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myBulkQuantity, myBulkPrice, myItemName, myPrice);
    }

}
