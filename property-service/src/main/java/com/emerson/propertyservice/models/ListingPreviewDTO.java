package com.emerson.propertyservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingPreviewDTO {
    private String listingId;
    private String propertyName;
    private String city;
    private String state;
    private String country;
    private String pricePerNight;
    private List<ImageDTO> listingImages;

}
