package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "cancellation_policies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancellationPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne (targetEntity = Listing.class)
    @JoinColumn (
            unique = true,
            nullable = false,
            name = "listing_id",
            updatable = false,
            foreignKey = @ForeignKey (name = "FR_CANCELLATION_POLICY")
    )
    private Listing listingId;
    @Column(name = "policy_type", nullable = false)
    private PolicyType policyType;
    @Column(name = "cancellation_timeline")
    private int customCancellationTimeline;
    private String description;

}

