package model;

import java.util.*;
import java.util.stream.Collectors;

public class LotsSet extends HashSet<Lot> {

    public Set<Lot> getAll() {
        return this;
    }

    // Time Complexity O(N) : n -> number of transactions (lots)
    // This could have been improved to log(n) by implementing a TreeSet / TreeMap backed Data-structure
    // but that added JSON serialization over head hence using HashSet backed DS
    public Lot getLotByNumber(Integer lotNumber) {
        for (Lot lot : this) {
            if (lot.getLotNumber() == lotNumber) {
                return lot;
            }
        }
        return null;
    }

    // Time Complexity : O(n log  n) : n -> number of transactions (lots)
    public List<Lot> sortByProfit() {
        return this.stream().sorted(Comparator.comparing(Lot::profit)).collect(Collectors.toList());
    }

    // time complexity : O(n) : n -> number of transactions (lots)
    public Integer getSellerWithHighestLots() {
        Map<Integer, Integer> sellerIdToVolumeMap = new HashMap<>();

        // Time Complexity of this loop : O(N) : number of transactions (Lots)
        for (Lot lot : this) {
            Integer id = lot.getSeller().getId();
            sellerIdToVolumeMap.compute(id, (k, v) -> (v == null) ? 1 : v + 1);

            // the above operation is also done by below code
            // Integer count = sellerIdToVolumeMap.getOrDefault(id, 0);
            // sellerIdToVolumeMap.put(id, count + 1)
        }

        // This is the elaborate version of iterating, simpler version follows
        // Time Complexity : finding id of seller with max volume sales takes O(s) : s is the total number of sellers
        /*
        Iterator<Map.Entry<Integer, Integer>> iterator = sellerIdToVolumeMap.entrySet().iterator();
        Map.Entry<Integer, Integer> entryWithMaxValue = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (next.getValue() > entryWithMaxValue.getValue()) {
                entryWithMaxValue = next;
            }
        }
        return entryWithMaxValue.getKey(); // this is the id of seller with max volume sales
        */

        // Time Complexity : finding id of seller with max volume sales takes O(s) : s is the total number of sellers
        Map.Entry<Integer, Integer> maxEntry = Collections.max(sellerIdToVolumeMap.entrySet(), Comparator.comparing(Map.Entry::getValue));

        // number of sellers can be max = number of sellers hence time complexity is O(N)
        return maxEntry.getKey(); // this is the id of seller with max volume sales
    }

    // Time Complexity : O(s)
    public Integer getSellerMaxProfit() {

        Map<Integer, Double> sellerNetProfitMap = new HashMap<>();

        // Time Complexity of this loop : O(N) : number of transactions (Lots)
        for (Lot lot : this) {
            Integer id = lot.getSeller().getId();
            sellerNetProfitMap.compute(id, (k, v) -> (v == null) ? lot.profit() : v + lot.profit());

            // the above operation is also done by below code
            // Double profit = sellerNetProfitMap.getOrDefault(id,  lot.profit());
            // sellerNetProfitMap.put(id, profit + lot.profit());
        }

        // This is the elaborate version of iterating, simpler version follows
        // Time Complexity : finding id of seller with max net Profit takes O(s) -> s is the total number of sellers

        /*
        Iterator<Map.Entry<Integer, Double>> iterator = sellerNetProfitMap.entrySet().iterator();
        Map.Entry<Integer, Double> entryWithMaxNetProfit = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Double> next = iterator.next();
            if (next.getValue() > entryWithMaxNetProfit.getValue()) {
                entryWithMaxNetProfit = next;
            }
        }
        return entryWithMaxNetProfit.getKey(); // this is the id of seller with max net profit
        */

        Map.Entry<Integer, Double> maxEntry = Collections.max(sellerNetProfitMap.entrySet(), Comparator.comparing(Map.Entry::getValue));
        return maxEntry.getKey(); // this is the id of seller with max net profit
    }


    // This method  would have helped in sorting the Set based on the lot number if this DS was extended TreeSet
    //@Override
    //public Comparator<? super Lot> comparator() {
    //    return (Comparator<Lot>) (o1, o2) -> o1.getLotNumber() < o2.getLotNumber() ? -1
    //            : (o1.getLotNumber() == o2.getLotNumber() ? 0 : 1);
    //}
}
