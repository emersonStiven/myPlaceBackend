package com.emerson.propertyservice.Services;

import com.emerson.propertyservice.ErrorHandling.*;
import com.emerson.propertyservice.entities.*;
import com.emerson.propertyservice.models.*;
import com.emerson.propertyservice.Repositories.ListingDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.stream;

@Service(value = "ListingManagementService")
@RequiredArgsConstructor
public class ListingManagementService implements ListingCrudOperations {

    // ------------------------- Bean injections -------------------------
    private final ListingDAO listingRepositoryImp;


    // ------------------------- Update methods ------------------------
    @Override
    //@CachePut(cacheNames = "listing", key = "#listingId")
    public InternalConfirmationDTO<ListingDTO> updateListing(ListingDTO listingdto, String hostId, String listingId) {
        Listing listing = mapListingDTOToListing(listingdto, hostId, listingId);
        Optional<Listing> val = Optional.ofNullable(listingRepositoryImp.save(listing));
        return val.map(updateListing -> new InternalConfirmationDTO<>("Listing was successfully updated", true, listingdto))
                .orElseThrow(() -> new DataBaseInteractionException("Error! Listing was not updated"));
    }


    // ------------------------------- Create method ------------------------------
    public InternalConfirmationDTO<ListingDTO> createNewListing(String hostId , ListingDTO listingDto) {
        Listing listing = mapListingDTOToListing(listingDto, hostId, "");
         Optional.ofNullable(listingRepositoryImp.save(listing))
                 .orElseThrow(()-> new DataBaseInteractionException("Error when creating the lsiting"));
         return new InternalConfirmationDTO<>("Listing was saved successfully", true, listingDto);
    }


    // ------------------------------- Select methods -------------------------------
    @Override
    //@Cacheable(cacheNames = "listing", key = "#uuid")
    public InternalConfirmationDTO<ListingDTO> retrieveListing(String uuid){
        Optional<Listing> listOptional = Optional.of(listingRepositoryImp.findByListingId(UUID.fromString(uuid)));
        Listing list = listOptional.orElseThrow(()-> new DataBaseInteractionException("Listing does not exist"));
        ListingDTO listingDto = mapListingToListingDTO(list);
        return new InternalConfirmationDTO<ListingDTO>("Listing was successfully fetched", true, listingDto);
    }

    @Override
    public InternalConfirmationDTO<List<ListingDTO>> retrieveListingByHostId(String hostId){
        Optional<List<Listing>> listOptional =  Optional.of(listingRepositoryImp.findAllByHostId(UUID.fromString(hostId)));
        var listings = mapListingsToListingsDTOS(listOptional);
        return new InternalConfirmationDTO<>(listings.size() + " listings found",true, listings);
    }


    // ---------------------------------- Delete methods --------------------------------
    @Override
    //@CacheEvict(cacheNames = "listing", key = "#id")
    public InternalConfirmationDTO<Integer> removeByListingId(String id){
        int rowsDeleted = 0;
        boolean exist = listingRepositoryImp.existsByListingId(UUID.fromString(id));
        if(exist) {
            rowsDeleted = listingRepositoryImp.deleteByListingId(UUID.fromString(id));
            if(rowsDeleted == 0){
                throw new DataBaseInteractionException("internal error deleting listing");
            }
        }else{
            throw new DataBaseInteractionException("Listing with " + id +  " was not found");
        }
        return new InternalConfirmationDTO<>("listings deleted successfully", true, rowsDeleted);
    }
    @Override
    public InternalConfirmationDTO<Integer> removeListingsById(List<String> ids){
        int rowsDeleted = listingRepositoryImp.deleteAllByListingId(ids);
        if(rowsDeleted == 0){
            throw new DataBaseInteractionException("Listings were not deleted");
        }
        return new InternalConfirmationDTO<>("listings deleted successfully",true, rowsDeleted);
    }
    //sdkfajsdlfkjsdlfkjsdlfksjdf
    @Override
    public InternalConfirmationDTO<List<ListingDTO>> retrieveAllListings( ){
        var allListings = listingRepositoryImp.findAll();
        var result = mapListingsToListingsDTOS(Optional.of(allListings));
        result.forEach(item -> System.out.println(item.toString()));
        System.out.println("listings were mapped to dtos successfully");
        return new InternalConfirmationDTO<>("Listings fetched successfully", true, result);
    }

    @Override
    public InternalConfirmationDTO<Integer> removeListingsByHost(String uuid){
        int deletedRows = listingRepositoryImp.deleteAllByHostId(UUID.fromString(uuid));
        return new InternalConfirmationDTO<>("Rows deleted successfully", true, deletedRows);
    }

    @Override
    public InternalConfirmationDTO<List<ListingPreviewDTO>> getListingsPreview(int page, int size, String region, String sortOrder){//find by region will be a pending feature
        Pageable pageable = getPageable(page, size, region, sortOrder);

        Page<Listing> pages = listingRepositoryImp.findAll(pageable);

        List<ListingPreviewDTO>  listingPreviews = pages.stream().map(lis -> {
            Location location = lis.getLocation();
            List<Image>  images = lis.getImages();
            PriceListing price = lis.getPricing();


            return ListingPreviewDTO.builder()
                    .state(location.getState())
                    .city(location.getCity())
                    .propertyName(lis.getPropertyName())
                    .country(location.getCountry())
                    .pricePerNight(price.getPriceNight().toPlainString())
                    .listingId(lis.getListingId().toString())
                    .listingImages(lis.getImages().stream().map(img ->
                            ImageDTO.builder()
                                    .imageBytes(img.getImage())
                                    .id(img.getId())
                                    .build() )
                            .toList())
                    .build();
        }).toList();

        return new InternalConfirmationDTO<>("Listings fetched successfully",true, listingPreviews);

    }


    // ------------------------- Separate methods for mapping lists ------------------------------
    public Pageable getPageable(int page, int size, String sortBy, String sortOrder) {
        Sort sort = Sort.by(sortOrder.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        return PageRequest.of(page, size, sort);
    }
    private List<FreeService> mapFreeServiceDtoList(List<FreeServiceDTO> freeServiceDTOList) {
        return freeServiceDTOList.stream()
                .map(elem -> FreeService.builder()
                        .freeServiceName(elem.getFreeServiceName())
                        .iconUrl(elem.getIconUrl())
                        .build()
                ).toList();
    }

    private List<PaidService> mapPaidServiceDtoList(List<PaidServiceDTO> paidServiceDTOList) {
        return paidServiceDTOList.stream()
                .map(elem -> PaidService.builder()
                        .paidServiceName(elem.getPaidServiceName())
                        .price(BigDecimal.valueOf(Long.parseLong(elem.getPrice())))
                        .description(elem.getDescription())
                        .build()
                ).toList();
    }
    private Listing mapListingDTOToListing(ListingDTO listingDto, String hostId, String listingId){
        return   Listing.builder()
                .hostId(UUID.fromString(hostId))
                .listingId( listingId.equals("") ? UUID.randomUUID() : UUID.fromString(listingId))
                .propertyName(listingDto.getPropertyName())
                .description(listingDto.getDescription())
                .surroundingAreaOverview(listingDto.getSurroundingAreaOverview())
                .guestCapacity(listingDto.getGuestCapacity())
                .bookingsCount(0)
                .freeServices(mapFreeServiceDtoList(listingDto.getFreeServiceDTO()))
                .paidServices(mapPaidServiceDtoList(listingDto.getPaidServiceDTO()))
                .cancellationPolicy(listingDto.getPolicy())
                .build();
    }
    private ListingDTO mapListingToListingDTO(Listing savedListing){
        return ListingDTO.builder()
                .propertyName(savedListing.getPropertyName())
                .description(savedListing.getDescription())
                .surroundingAreaOverview(savedListing.getSurroundingAreaOverview())
                .guestCapacity(savedListing.getGuestCapacity())
                .maxNights(savedListing.getPricing().getMaxNights())
                .minNights(savedListing.getPricing().getMinNights())
                .priceWeek(savedListing.getPricing().getPriceWeek())
                .priceMonth(savedListing.getPricing().getPriceMonth())
                .priceNight(savedListing.getPricing().getPriceNight())
                .cleaningFee(savedListing.getPricing().getCleaningFee())
                .zipcode(String.valueOf(savedListing.getLocation().getZipcode()))
                .city(savedListing.getLocation().getCity())
                .state(savedListing.getLocation().getState())
                .country(savedListing.getLocation().getCountry())
                .address(savedListing.getLocation().getAddress())
                .freeServiceDTO(savedListing.getFreeServices().stream().map(elem ->{
                    return new FreeServiceDTO(elem.getFreeServiceName(), elem.getIconUrl());
                }).toList())
                .paidServiceDTO( savedListing.getPaidServices().stream().map(elem -> {
                    return new PaidServiceDTO(elem.getPaidServiceName(), elem.getDescription(), elem.getPrice().toString());
                }).toList() )
                .policy(savedListing.getCancellationPolicy())
                .build();
    }

    private List<ListingDTO> mapListingsToListingsDTOS(Optional<List<Listing>> listings)  {
        List<Listing> lis = listings.orElseThrow(() -> new DataBaseInteractionException("Host don't have listings"));
        return lis.stream().map(this::mapListingToListingDTO).toList();
    }
}
