package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "paid_services",
        indexes = @Index(name = "idx_listing_id", columnList = "listing_id"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaidService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Listing.class)
    @JoinColumn(name = "listing_id",
            foreignKey = @ForeignKey(name = "PaidService_listing"),
            nullable = false)
    private Listing listingId;

    @Column(name = "service_name", nullable = false, length = 50)
    private String paidServiceName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}
