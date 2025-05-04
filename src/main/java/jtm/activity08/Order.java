package jtm.activity08;

import java.util.Comparator;
import java.util.Objects;

/*- TODO #1
 * Implement Comparable interface with Order class
 * Hint! Use generic type of comparable items in form: Comparable<Order>
 *
 * TODO #2 Override/implement necessary methods for Order class:
 * - public Order(String orderer, String itemName, Integer count) — constructor of the Order
 * - public int compareTo(Order order) — comparison implementation according to logic described below
 * - public boolean equals(Object object) — check equality of orders
 * - public int hashCode() — to be able to handle it in some hash... collection
 * - public String toString() — string in following form: "ItemName: OrdererName: Count"
 *
 * Hints:
 * 1. When comparing orders, compare their values in following order:
 *    - Item name
 *    - Customer name
 *    - Count of items
 * If item or customer is closer to start of alphabet, it is considered "smaller"
 *
 * 2. When implementing .equals() method, rely on compareTo() method, as for sane design
 * .equals() == true, if compareTo() == 0 (and vice versa).
 *
 * 3. Also Ensure that .hashCode() is the same, if .equals() == true for two orders.
 *
 */

public class Order implements Comparable<Order> {
    private final String customer; // Name of the customer
    private final String name;     // Name of the requested item
    private final int count;       // Count of the requested items

    /**
     * Constructor of the Order
     * @param orderer    Name of the customer
     * @param itemName   Name of the requested item
     * @param count      Count of the requested items
     */
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
