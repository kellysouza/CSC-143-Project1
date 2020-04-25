package world;

import lib.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Restaurant extends Store {
    public HashMap<String, Collection<Pair<String, Integer>>> recipes = new HashMap<>();

    public Restaurant(String location, String company) {
        super(location, company);
    }

    @Override
    public Collection<Pair<String, Integer>> purchase(Collection<Pair<String, Integer>> order) {
        Collection<Pair<String, Integer>> purchase = new ArrayList<>();
        for (Pair orderItem : order) {
            int qtyAvail = getQuantityAvailable((String) orderItem.left);
            if (qtyAvail > (int) orderItem.right) {
                purchase.add(orderItem);
                deductSupplies((String) orderItem.left, (Integer) orderItem.right);
            } else if (qtyAvail == 0) {
                //do nothing
            } else {
                purchase.add(new Pair<>((String) orderItem.left, qtyAvail));
                deductSupplies((String) orderItem.left, qtyAvail);
            }
        }
        return purchase;
    }

    public void deductSupplies(String orderItem, int qtyPurchased) {
        Collection<Pair<String, Integer>> ingredients = recipes.get(orderItem);
        for (Pair ingredient : ingredients) {
            this.supplies.put((String) ingredient.left, this.supplies.get(ingredient.left) - qtyPurchased * ((int) ingredient.right));
        }
    }

    @Override
    public Collection<Pair<String, Integer>> getProducts() {
        Collection<Pair<String, Integer>> products = new ArrayList<>();
        for (String recipe : recipes.keySet()) {
            int quantityAvailable = getQuantityAvailable(recipe);
            products.add(new Pair(recipe, quantityAvailable));
        }
        return products;
    }

    private int getQuantityAvailable(String recipe) {
        if (!recipes.containsKey(recipe)) {
            return 0;
        }
        int quantityAvailable = Integer.MAX_VALUE;
        for (Pair ingredient : recipes.get(recipe)) {
            if (!supplies.containsKey(ingredient.left)) {
                quantityAvailable = 0;
            } else {
                int supplyQty = this.supplies.get(ingredient.left);
                if (quantityAvailable > supplyQty / (int) ingredient.right) {
                    quantityAvailable = supplyQty / (int) ingredient.right;
                }
            }
        }
        return quantityAvailable;
    }

    public void learnRecipe(String name, Collection<Pair<String, Integer>> ingredients) {
        recipes.put(name, ingredients);
    }
}
