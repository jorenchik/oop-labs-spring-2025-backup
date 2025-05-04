package jtm.activity08;

import java.util.Comparator;
import java.util.Objects;

public class Order implements Comparable<Order> {
    private final String customer; // Name of the customer
    private final String name;     // Name of the requested item
    private final int count;       // Count of the requested items

    public Order(String orderer, String itemName, Integer count) {
        this.customer = Objects.requireNonNull(orderer, "orderer must not be null");
        this.name = Objects.requireNonNull(itemName, "itemName must not be null");
        this.count = Objects.requireNonNull(count, "count must not be null");
    }

    @Override
    public int compareTo(Order o) {
        // Compare by item name, then customer name, then count
        return Comparator
                .comparing((Order ord) -> ord.name)
                .thenComparing(ord -> ord.customer)
                .thenComparingInt(ord -> ord.count)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order other = (Order) obj;
        return count == other.count
                && name.equals(other.name)
                && customer.equals(other.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customer, count);
    }

    @Override
    public String toString() {
        return name + ": " + customer + ": " + count;
    }

    public String getCustomer() {
        return customer;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
