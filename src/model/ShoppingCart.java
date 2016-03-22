/*
 * TCSS 305 - Autumn 2015
 * Assignment 2 - ShoppingCart
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a shopping cart. Shopping cart object stores all ItemOrder information.
 * @author Tung Kong
 * @version 1 10/13/15
 */
public class ShoppingCart {
    
    /** Zero value for BigDecimals. */
    private static final String ZERO = "0.00";
    
    /** Stores ItemOrder objects. */
    private final List<ItemOrder> myCart;
    
    /** Membership value. */
    private boolean myMembership;

    /**
     * Constructs an empty shopping cart.
     */
    public ShoppingCart() {
        myCart = new ArrayList<>();
    }

    /**
     * Checks if the shopping cart already has the item wanting to be added. 
     * If so, it removes the old item first before adding. 
     * @param theOrder item order being added.
     */
    public void add(final ItemOrder theOrder) {
        final ItemOrder tempItemOrder = Objects.requireNonNull(theOrder);
          
        for (int i = 0; i < myCart.size(); i++) {
            final Item tempItem = myCart.get(i).getItem();
            if (tempItem.equals(tempItemOrder.getItem())) {
                myCart.remove(i);
                break;
            }
        }
        myCart.add(theOrder);
        
    }

    /**
     * Sets membership status, true if they are member, false if not.
     * @param theMembership boolean value true or false.
     */
    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;
    }

    /**
     * Calculates the total of item orders in shopping cart.
     * @return the total in BigDecimal form.
     */
    public BigDecimal calculateTotal() {
        BigDecimal bulkTotal = new BigDecimal(ZERO);
        BigDecimal nonBulkTotal = new BigDecimal(ZERO);
        BigDecimal total = new BigDecimal(ZERO);
        for (int i = 0; i < myCart.size(); i++) {
            final ItemOrder tempItem = myCart.get(i);
            
            if (tempItem.getItem().isBulk()) {
                bulkTotal = bulkTotal.add(tempItem.calculateOrderTotal());
            } else {
                nonBulkTotal = nonBulkTotal.add(tempItem.calculateOrderTotal());
            }
        }
        if (myMembership) {
            final String discountString = "0.10";
            final BigDecimal discountAmount = 
                            nonBulkTotal.multiply(new BigDecimal(discountString));
            nonBulkTotal = nonBulkTotal.subtract(discountAmount);
        }
        total = bulkTotal.add(nonBulkTotal);

        return total.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * Removes all items from shopping cart.
     */
    public void clear() {
        myCart.clear();
    }
    
    /**
     * String representation of shopping cart formatted as:
     * Total ItemOrders in shopping cart: 5, ShoppingCart Total: $10.50. 
     * where 5 is the size of ArrayList.
     * 
     */
    @Override
    public String toString() {
        return "Total ItemOrders in shopping cart: " + myCart.size() 
            + ", ShoppingCart Total: $" + calculateTotal();
    }

}
