/*
  Description:
  The Fractional Knapsack problem is one of the most iconic greedy algorithms.
  You are given items, each with a weight and value, and a knapsack that can only
  carry a limited amount of weight. The twist is simple: you are allowed to take
  fractions of items.
 
  The greedy strategy is built on a sharp observation:
  always pick items based on the highest value-to-weight ratio.
  This ensures that every unit of weight added to the knapsack gives the maximum
  possible return.
 
  This algorithm is a perfect introduction to how choosing locally optimal steps
  can lead to globally optimal results.
 
  Use Cases:
  - Resource allocation where quantities can be divided.
  - Selecting investments with fractional shares.
  - Bandwidth distribution, load balancing, or maximizing efficiency under limits.
 
  Time Complexity:
  - O(n log n) → due to sorting items by value-to-weight ratio.
 
  Space Complexity:
  - O(1) → if sorting is done in-place.
 
  Approach:
  1. Compute value/weight ratio for each item.
  2. Sort items by decreasing ratio.
  3. Pick items from highest ratio to lowest:
       - If the whole item fits, take it.
       - If not, take the fraction that fits and stop.
  4. The sum of taken values is the maximum achievable.
 */

public class FractionalKnapsack {

    // Structure to store item details
    static class Item {
        int weight;
        int value;
        double ratio;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    // Function to calculate maximum value for fractional knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        int n = items.length;

        // Sort items by ratio in descending order
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (items[j].ratio < items[j + 1].ratio) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity == 0) {
                break;
            }

            // If item fits completely, take all of it
            if (item.weight <= capacity) {
                totalValue += item.value;
                capacity -= item.weight;
            } 
            // Otherwise take the fraction that fits and stop
            else {
                totalValue += item.ratio * capacity;
                capacity = 0;
            }
        }

        return totalValue;
    }

    // Main for demonstration
    public static void main(String[] args) {
        Item[] items = {
            new Item(10, 60),
            new Item(20, 100),
            new Item(30, 120)
        };

        int capacity = 50;

        double maxValue = getMaxValue(items, capacity);

        System.out.println("Maximum value in knapsack: " + maxValue);
    }
}