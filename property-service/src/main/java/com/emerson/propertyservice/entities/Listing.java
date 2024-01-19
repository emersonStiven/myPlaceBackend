package com.emerson.propertyservice.entities;

import com.emerson.propertyservice.Repositories.CreationStamp;
import com.emerson.propertyservice.Repositories.LastUpdate;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "listings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Listing implements CreationStamp, LastUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "host_id", nullable = false,updatable = false)
    private UUID hostId;
    @Column(name = "listing_id", nullable = false, updatable = false,unique = true)
    private UUID listingId;
    @Column(name ="property_name", nullable = false)
    private String propertyName;
    @Column(name = "description", nullable = false)
    private String description;
    @Column (name="surrounding_area_overview")
    private String surroundingAreaOverview;
    private String notes;
    private String transit;
    @Column(name="bathroom_ctn", nullable = false)//calculated in runtime
    private int bathroomCtn;
    @Column(name = "guest_capacity", nullable = false)
    private int guestCapacity;
    private List<String> hostRules;
    @Column(name = "tax_rate", nullable = false)//assigned in runtime
    private int taxRate;
    @OneToOne(targetEntity = PriceListing.class,optional = false, mappedBy = "listingId", cascade = CascadeType.ALL)
    private PriceListing pricing;
    @OneToOne(targetEntity = Location.class, optional = false, mappedBy = "listingId", cascade = CascadeType.ALL)
    private Location location;
    @OneToOne(targetEntity = CancellationPolicy.class, optional = false, mappedBy="listingId", cascade = CascadeType.ALL)
    private CancellationPolicy cancellationPolicy;
    @OneToMany(targetEntity = FreeService.class,mappedBy = "listingId", cascade = CascadeType.ALL)
    private List<FreeService> freeServices;
    @OneToMany(targetEntity = PaidService.class,mappedBy = "listingId", cascade = CascadeType.ALL)
    private List<PaidService> paidServices;
    @OneToMany(targetEntity = Room.class, mappedBy = "listingId", cascade = CascadeType.ALL)
    private List<Room> rooms;
    @OneToMany(targetEntity = Image.class,cascade = CascadeType.ALL, mappedBy = "listingId")
    private List<Image> images;
    private float averageReviews;//se va
    private int bookingsCount;//se va
    private int viewsCount;//se va
    private int reviewsCount;


    //-------------------- One to Many relationships methods --------------------
    public void addRoom(Room room){
        if(this.rooms == null){
            this.rooms = new ArrayList<Room>();
        }
        this.rooms.add(room);
    }

    public void addFreeService(FreeService freeService){
        if(this.freeServices == null){
            this.freeServices = new ArrayList<FreeService>();
        }
        this.freeServices.add(freeService);
    }

    public void addPaidService(PaidService paidService){
        if(this.paidServices == null){
            this.paidServices = new ArrayList<PaidService>();
        }
        this.paidServices.add(paidService);
    }

    //-------------------- Automatically generated fields --------------------
    private Date createdAt;
    private Date lastUpdated;

    @Override
    public Date getCreationStamp(){
        return createdAt;
    }
    @Override
    public void setCreationStamp(Date newCreationStamp){
        this.createdAt = newCreationStamp;
    }

    @Override
    public void setLastUpdate(Date lastUpdate){
        this.lastUpdated = lastUpdate;
    }

    @Override
    public Date getLastUpdate( ){
        return this.lastUpdated;
    }
}
