package com.example.cs496_2.data.DTO;

public class TravelUserPair {
    public int travelUserPairId;
    public int personalTotalSpend;
    public int personalMealSpend;
    public int personalShopSpend;
    public int personalTourSpend;
    public int personalTransportSpend;
    public int personalHotelSpend;
    public int personalEtcSpend;

    public TravelUserPair(int travelUserPairId, int personalTotalSpend, int personalMealSpend, int personalShopSpend, int personalTourSpend, int personalTransportSpend, int personalHotelSpend, int personalEtcSpend) {
        this.travelUserPairId = travelUserPairId;
        this.personalTotalSpend = personalTotalSpend;
        this.personalMealSpend = personalMealSpend;
        this.personalShopSpend = personalShopSpend;
        this.personalTourSpend = personalTourSpend;
        this.personalTransportSpend = personalTransportSpend;
        this.personalHotelSpend = personalHotelSpend;
        this.personalEtcSpend = personalEtcSpend;
    }

    public int getTravelUserPairId() {
        return travelUserPairId;
    }

    public void setTravelUserPairId(int travelUserPairId) {
        this.travelUserPairId = travelUserPairId;
    }

    public int getPersonalTotalSpend() {
        return personalTotalSpend;
    }

    public void setPersonalTotalSpend(int personalTotalSpend) {
        this.personalTotalSpend = personalTotalSpend;
    }

    public int getPersonalMealSpend() {
        return personalMealSpend;
    }

    public void setPersonalMealSpend(int personalMealSpend) {
        this.personalMealSpend = personalMealSpend;
    }

    public int getPersonalShopSpend() {
        return personalShopSpend;
    }

    public void setPersonalShopSpend(int personalShopSpend) {
        this.personalShopSpend = personalShopSpend;
    }

    public int getPersonalTourSpend() {
        return personalTourSpend;
    }

    public void setPersonalTourSpend(int personalTourSpend) {
        this.personalTourSpend = personalTourSpend;
    }

    public int getPersonalTransportSpend() {
        return personalTransportSpend;
    }

    public void setPersonalTransportSpend(int personalTransportSpend) {
        this.personalTransportSpend = personalTransportSpend;
    }

    public int getPersonalHotelSpend() {
        return personalHotelSpend;
    }

    public void setPersonalHotelSpend(int personalHotelSpend) {
        this.personalHotelSpend = personalHotelSpend;
    }

    public int getPersonalEtcSpend() {
        return personalEtcSpend;
    }

    public void setPersonalEtcSpend(int personalEtcSpend) {
        this.personalEtcSpend = personalEtcSpend;
    }

    @Override
    public String toString() {
        return "TravelUserPair{" +
                "travelUserPairId=" + travelUserPairId +
                ", personalTotalSpend=" + personalTotalSpend +
                ", personalMealSpend=" + personalMealSpend +
                ", personalShopSpend=" + personalShopSpend +
                ", personalTourSpend=" + personalTourSpend +
                ", personalTransportSpend=" + personalTransportSpend +
                ", personalHotelSpend=" + personalHotelSpend +
                ", personalEtcSpend=" + personalEtcSpend +
                '}';
    }
}
