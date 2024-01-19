package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "price_listings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (
            targetEntity = Listing.class,
            optional = false
    )
    @JoinColumn(
            name = "listing_id",
            nullable = false, unique = true,
            updatable = false,
            foreignKey = @ForeignKey(name = "price_listing")
    )
    private Listing listingId;
    @Column(name = "price_night", precision =10, scale=2)
    private BigDecimal priceNight;
    @Column(name = "price_week",   precision =10, scale =2)
    private BigDecimal priceWeek;
    @Column(name ="price_month", precision = 10, scale =2)
    private BigDecimal priceMonth;
    @Column(name ="clearning_fee", precision = 10, scale =2)
    private BigDecimal cleaningFee;
    @Column(name = "min_nights")
    private int minNights;
    @Column(name = "max_nights")
    private int maxNights;
}
