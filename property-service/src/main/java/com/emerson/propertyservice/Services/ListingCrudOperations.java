package com.emerson.propertyservice.Services;

import com.emerson.propertyservice.models.InternalConfirmationDTO;
import com.emerson.propertyservice.models.ListingDTO;
import com.emerson.propertyservice.models.ListingPreviewDTO;

import java.util.List;

public interface ListingCrudOperations {

    // ------------------------- GET METHODS ------------------------
    InternalConfirmationDTO<List<ListingPreviewDTO>> getListingsPreview(int page, int size, String region, String sortOrder);
    InternalConfirmationDTO<ListingDTO> retrieveListing(String uuid) ;
    InternalConfirmationDTO<List<ListingDTO>> retrieveListingByHostId(String uuidHost);


    // ------------------------- UPDATE METHODS ------------------------
    InternalConfirmationDTO<ListingDTO> updateListing(ListingDTO updatedListing,String hostId, String listingId);


    // ------------------------- CREATE METHODS ------------------------
    InternalConfirmationDTO<ListingDTO> createNewListing(String hostId, ListingDTO listing);


    // ------------------------- DELETE METHODS ------------------------
    InternalConfirmationDTO<Integer> removeListingsByHost(String uuid);
    InternalConfirmationDTO<Integer> removeByListingId(String id);
    InternalConfirmationDTO<Integer> removeListingsById(List<String> ids);

    InternalConfirmationDTO<List<ListingDTO>> retrieveAllListings();
}
