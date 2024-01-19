package com.emerson.propertyservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Listing.class)
    @JoinColumn(name = "listing_id",
            foreignKey = @ForeignKey(name = "room_listing"),
            nullable = false,
            updatable = false
    )
    private Listing listingId;
    @Column(name="bed_ctn")
    private int bedCtn;
    private List<String> bedType;
    @Column(name="private_bathroom")
    private boolean privateBathRoom;


}
