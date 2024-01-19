package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "free_services", indexes = @Index(name = "idx_listing_id", columnList = "listing_id"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreeService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            targetEntity = Listing.class)
    @JoinColumn(
            name = "listing_id",
            foreignKey = @ForeignKey(name = "FR_FREE-SERVICE_LISTING"))
    private Listing listingId;

    @Column(name = "service_name", nullable = false, length = 50)
    private String freeServiceName;
    @Column(name = "icon_url", nullable = false)
    private String iconUrl;
}

