package model;

import java.util.*;
import java.util.stream.Collectors;

/*
This Data Structure was discussed.
However, a better DS was deemed to be feasible for the use-case that we discussed keeping JSON serialization under consideration.
See LotSet.java which is currently being used
 */
public class LotsMap extends HashMap<Long, Lot> {

    // O(1)
    public Lot getLotByNumber(Long lotNumber) {
        return super.get(lotNumber);
    }


    // Time Complexity : O(n log  n)
    public List<Lot> sortByProfit() {
        Collection<Lot> values = super.values();
        return values.stream().sorted(Comparator.comparing(Lot::profit)).collect(Collectors.toList());
    }

    // Time Complexity : O(n)
    public Integer getSellerWithHighestLots() {

        Collection<Lot> lots = super.values();
        Map<Integer, Integer> sellerIdToVolumeMap = new HashMap<>();

        for (Lot lot : lots) {
            Integer id = lot.getSeller().getId();
            sellerIdToVolumeMap.compute(id, (k, v) -> (v == null) ? 1 : v + 1);
        }

        Entry<Integer, Integer> maxEntry = Collections.max(sellerIdToVolumeMap.entrySet(), Comparator.comparing(Entry::getValue));
        return maxEntry.getKey();
    }

    // Time Complexity : O(n)
    public Integer getSellerMaxProfit() {

        Collection<Lot> lots = super.values();
        Map<Integer, Double> sellerProfitMap = new HashMap<>();

        for (Lot lot : lots) {
            Integer id = lot.getSeller().getId();
            sellerProfitMap.compute(id, (k, v) -> (v == null) ? 1 : v + lot.profit());
        }

        Entry<Integer, Double> maxEntry = Collections.max(sellerProfitMap.entrySet(), Comparator.comparing(Entry::getValue));
        return maxEntry.getKey();
    }
}
