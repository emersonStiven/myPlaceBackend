package com.emerson.propertyservice.models;

import com.emerson.propertyservice.entities.CancellationPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingDTO {
    private String propertyName;
    private String description;
    private String surroundingAreaOverview;
    private int guestCapacity;

    private int maxNights;
    private int minNights;
    private BigDecimal priceWeek;
    private BigDecimal priceMonth;
    private BigDecimal priceNight;
    private BigDecimal cleaningFee;

    private String zipcode;
    private String city;
    private String state;
    private String country;
    private String address;
    private List<FreeServiceDTO> freeServiceDTO;
    private List<PaidServiceDTO> paidServiceDTO;
    private CancellationPolicy policy;

}
