package model;

//import lombok.Getter;
//import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//@Getter
//@Setter

public class Lot {
    private Integer lotNumber;
    private String country;
    private Double purchasePrice;
    private Double salePrice;
    private List<Party> party;
    private Party buyer;
    private Party seller;


    public long getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Party> getParty() {
        return party;
    }

    public void setParty(List<Party> party) {
        this.party = party;
        init();
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Party getBuyer() {
        return buyer;
    }

    public Party getSeller() {
        return seller;
    }


    void init() {
        for (Party p : party) {
            if (p.getType().equalsIgnoreCase("MBR"))
                this.buyer = p;
            else if (p.getType().equalsIgnoreCase("SLR"))
                this.seller = p;
        }
    }

    public Double profit() {
        return salePrice - purchasePrice;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "lotNumber=" + lotNumber +
                ", profit = " + profit() +
                ", country='" + country + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", party=" + party +
                ", buyer=" + buyer +
                ", seller=" + seller +
                '}';
    }
}
