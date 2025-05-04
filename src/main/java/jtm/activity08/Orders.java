package jtm.activity08;


/*- TODO #2
 * Implement Iterator interface with Orders class
 * Hint! Use generic type argument of iterateable items in form: Iterator<Order>
 *
 * TODO #3 Override/implement public methods for Orders class:
 * - Orders()                — create new empty Orders
 * - add(Order item)            — add passed order to the Orders
 * - List<Order> getItemsList() — List of all customer orders
 * - Set<Order> getItemsSet()   — calculated Set of Orders from list (look at description below)
 * - sort()                     — sort list of orders according to the sorting rules
 * - boolean hasNext()          — check is there next Order in Orders
 * - Order next()               — get next Order from Orders, throw NoSuchElementException if can't
 * - remove()                   — remove current Order (order got by previous next()) from list, throw IllegalStateException if can't
 * - String toString()          — show list of Orders as a String
 *
 * Hints:
 * 1. To convert Orders to String, reuse .toString() method of List.toString()
 * 2. Use built in Collections.sort(...) method to sort list of orders
 *
 * TODO #4
 * When implementing getItemsSet() method, join all requests for the same item from different customers
 * in following way: if there are two requests:
 *  - ItemN: Customer1: 3
 *  - ItemN: Customer2: 1
 *  Set of orders should be:
 *  - ItemN: Customer1,Customer2: 4
 */

//public class Orders {
    /*-
     * TODO #1
     * Create data structure to hold:
     *   1. some kind of collection of Orders (e.g. some List)
     *   2. index to the current order for iterations through the Orders in Orders
     *   Hints:
     *   1. you can use your own implementation or rely on .iterator() of the List
     *   2. when constructing list of orders, set number of current order to -1
     *      (which is usual approach when working with iterateable collections).
     */
//}

import java.util.*;

public class Orders implements Iterator<Order> {
    private final List<Order> items;
    private int currentIndex;
    private boolean canRemove;

    public Orders() {
        this.items = new ArrayList<>();
        this.currentIndex = -1;
        this.canRemove = false;
    }

    public void add(Order item) {
        Objects.requireNonNull(item, "Order item must not be null");
        items.add(item);
    }

    public List<Order> getItemsList() {
        return new ArrayList<>(items);
    }

    public Set<Order> getItemsSet() {
        Map<String, Order> agg = new LinkedHashMap<>();
        sort();
        
        for (Order o : items) {
            String itemName = o.getName();
            if (agg.containsKey(itemName)) {
                Order existing = agg.get(itemName);
                String mergedCustomers = existing.getCustomer() + "," + o.getCustomer();
                int mergedCount = existing.getCount() + o.getCount();
                // Note: constructor parameters: (orderer, itemName, count)
                agg.put(itemName, new Order(mergedCustomers, itemName, mergedCount));
            } else {
                // copy original order
                agg.put(itemName, new Order(o.getCustomer(), itemName, o.getCount()));
            }
        }
        return new LinkedHashSet<>(agg.values());
    }

    public void sort() {
        Collections.sort(items);
        this.currentIndex = -1;
        this.canRemove = false;
    }

    @Override
    public boolean hasNext() {
        return currentIndex + 1 < items.size();
    }

    @Override
    public Order next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more orders available");
        }
        currentIndex++;
        canRemove = true;
        return items.get(currentIndex);
    }

    @Override
    public void remove() {
        if (!canRemove) {
            throw new IllegalStateException("remove() can only be called once after next()");
        }
        items.remove(currentIndex);
        currentIndex--;
        canRemove = false;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}

