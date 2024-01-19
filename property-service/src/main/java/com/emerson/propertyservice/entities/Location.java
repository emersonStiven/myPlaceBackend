package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "locations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Listing.class)
    @JoinColumn(
            unique = true,
            nullable = false,
            name = "listing_id",
            updatable = false,
            foreignKey = @ForeignKey(name = "location_listing")
    )
    private Listing listingId;
    @Column(name = "zipcode", nullable = false)
    private int zipcode;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state_province", nullable = false)
    private String state;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "address", nullable = false)
    private String address;

}
