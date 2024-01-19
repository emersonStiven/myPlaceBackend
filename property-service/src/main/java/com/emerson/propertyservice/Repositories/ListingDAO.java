package com.emerson.propertyservice.Repositories;

import com.emerson.propertyservice.entities.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ListingDAO extends JpaRepository<Listing, Long> {

    // -------- SELECT METHODS
    List<Listing> findAll();
    List<Listing> findAllByHostId(UUID hostId);
    Listing findByListingId(UUID id);
    Page<Listing> findAll(Pageable pageable);

    // --------- DELETE METHODS
    @Modifying
    @Query(value ="delete  from listings l where l.listing_id in :listingIds", nativeQuery = true)
    int deleteAllByListingId(@Param("listingIds") List<String> listingIds);
    @Modifying
    @Query(value ="delete  from listings l where l.listing_id = uuid", nativeQuery = true)
    int deleteByListingId(@Param ("uuid") UUID listingId);
    @Modifying
    @Query(value = "delete  from listings l where l.host_id = id", nativeQuery = true)
    int deleteAllByHostId(@Param("id") UUID hostId);

    // --------- INSERT METHODS  || UPDATE METHODS
    Listing save(Listing listing);

    // --------- VERIFY EXISTENCE
    boolean existsByListingId(UUID listingId);


}
