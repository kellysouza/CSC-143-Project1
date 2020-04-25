package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Supermarket: Extends Store.
 * The supermarket is a simple storefront which sells
 * exactly what its inventory (supply) contains.
 */
public class Supermarket extends Store {

    public Supermarket(String location, String company) {
        super(location, company);
    }

    /**
     * getProducts(): Inherited from Store.
     * <p>
     * Hint: you can iterate over this.supplies.keySet() to get names and quantities.
     *
     * @return list of products (as pairs, name and quantity) that reflects the store inventory.
     */
    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        ArrayList<Pair<String, Integer>> productList = new ArrayList<>();
        for (String name : this.supplies.keySet()) {
            productList.add(new Pair(name, this.supplies.get(name)));
        }
        return productList;
    }

    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
        Collection<Pair<String, Integer>> itemsFulfilled = new ArrayList<>();
        for (Pair pair : order) {
            if (this.supplies.containsKey(pair.left)) {
//                String itemOrdered = (String)pair.left;
//                int quantityOrdered = (Integer)pair.right;
//                int quantityInStock = this.supplies.get(itemOrdered);
//                int quantity = quantityInStock >= quantityOrdered ? quantityOrdered : quantityInStock;
//                this.supplies.put(itemOrdered, quantityInStock - quantity);
//                itemsFulfilled.add(new Pair(pair.left, quantity));

                int quantity = this.supplies.get(pair.left) >= (int) pair.right ? (int) pair.right : this.supplies.get(pair.left);
                this.supplies.put((String) pair.left, this.supplies.get(pair.left) - quantity);
                itemsFulfilled.add(new Pair(pair.left, quantity));
            }
        }
        return itemsFulfilled;
    }
}
