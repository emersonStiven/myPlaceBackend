package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            targetEntity =Listing.class,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "listing_id",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "FR_IMAGE_LISTING")
    )
    private Listing listingId;
    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] image;

}
