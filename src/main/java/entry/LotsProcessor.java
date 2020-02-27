package entry;


import com.fasterxml.jackson.databind.ObjectMapper;
import model.LotsSet;

import java.io.File;
import java.io.IOException;

public class LotsProcessor {

    public static void main(String[] args) throws IOException {

        LotsSet lots = init();

        System.out.println("Total lots : " + lots.size());
        System.out.println(lots.getLotByNumber(500675));
        System.out.println("All Lots : " + lots.getAll());
        System.out.println("Lots sorted by Profit : " +lots.sortByProfit());
        System.out.println("Seller with Maximum Net Profit (Party ID) : " + lots.getSellerMaxProfit());
        System.out.println("Seller with highest volume sales (Party ID) : " + lots.getSellerWithHighestLots());


    }

    /**
     * This method serializes the json (from resources folder) to the Designed Data Structure
     *
     * @return
     * @throws IOException
     */
    static LotsSet init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/main/resources/test.json");
        return objectMapper.readValue(file, LotsSet.class);
    }
}
