package com.emerson.propertyservice.Controllers;

import com.emerson.propertyservice.models.*;
import com.emerson.propertyservice.ErrorHandling.*;
import com.emerson.propertyservice.entities.Listing;
import com.emerson.propertyservice.Services.ListingCrudOperations;
import com.emerson.propertyservice.Validations.ListingManagementApiValidation;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1-crud")
@RequiredArgsConstructor
public class ListManagementApi {

    private final ListingCrudOperations listingsService;
    private final ObjectMapper objectMapper;
    private final ListingManagementApiValidation listingManagementApiValidation;
    @InitBinder
    public void loadValidationSettings(WebDataBinder webDataBinder){
        webDataBinder.addValidators(listingManagementApiValidation);
    }
    @GetMapping(value = "/preview-listings")
    public ResponseEntity<InternalConfirmationDTO<List<ListingPreviewDTO>>> getHello(@RequestParam(defaultValue = "0", name = "page") int page,
                                                                                     @RequestParam(defaultValue = "10", name = "size") int size,
                                                                                     @RequestParam(defaultValue = "propertyName", name = "sortby") String sortBy,
                                                                                     @RequestParam(defaultValue = "asc", name = "sortorder") String sortOrder){

        return ResponseEntity.ok(listingsService.getListingsPreview(page, size, sortBy, sortOrder));
    }
    @GetMapping(value ="/fetchAllListings", produces = "application/json")
    public ResponseEntity<InternalConfirmationDTO<List<ListingDTO>>> getAllListings(){
        System.out.println("HEYYYY I'M HERE FROM FETCH ALL LISTINGS");
        var confirmationDto = listingsService.retrieveAllListings();
        System.out.println("EHYY I'M READY TO SEND U GUYS A MESSAGE BACK");
        return ResponseEntity.ok(confirmationDto);
    }

    @GetMapping(value = {"/retrieve-listing/{id}"},
                produces = {"application/json"},
                headers = {"Authorization"})
    public ResponseEntity<InternalConfirmationDTO<ListingDTO>> fetchListingById(@NonNull @PathVariable("id") String val) {
         InternalConfirmationDTO<ListingDTO> confirmationDTO = listingsService.retrieveListing(val);
         return ResponseEntity.ok(confirmationDTO);

    }

    @GetMapping(
            value = "/host-listings",
            headers = { "Authentication",
                        "X-Content-Type-Options=nosniff"},
            produces ="application/json")
    @PreAuthorize ("hasRole('Host') and hasRole('User')")
    public ResponseEntity<InternalConfirmationDTO<List<ListingDTO>>> fetchListingByHost(Authentication authentication){
       var confirmationDTO = listingsService.retrieveListingByHostId(authentication.getName());
        return ResponseEntity.ok().body(confirmationDTO);
    }

    @PostMapping (
            value = "/create-listing",
            headers = { "Content-Type=application/json",
                        "Accept=application/json",
                        "X-Content-Type-Options=nosniff"},
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<InternalConfirmationDTO<?>> createListing(@Valid @RequestBody ListingDTO listing, BindingResult result, Authentication auth){
        if(result.hasErrors()){
            throw new ValidationException("Invalid data", result);
        }else{
            var confirmationDto = listingsService.createNewListing(auth.getName() ,listing);
            return ResponseEntity.ok(confirmationDto);
        }
    }

    @PutMapping("/update-listing/{id}")
    public ResponseEntity<?> updateListing(@PathVariable("id") String listingId, @RequestBody ListingDTO dto, Authentication auth){
        var confirmationDTO = listingsService.updateListing(dto, auth.getName(),  listingId);
        return ResponseEntity.ok(confirmationDTO);
    }


    @DeleteMapping(value = "delete-host-listings")
    public ResponseEntity<InternalConfirmationDTO<Integer>> deleteListingByHost(Authentication auth){
        var confirmationDTO = listingsService.removeListingsByHost(auth.getName());
        return ResponseEntity.ok().body(confirmationDTO);
    }

    @DeleteMapping( value = "/delete-listing/{listingId}")
    public ResponseEntity<InternalConfirmationDTO<Integer>> deleteListingById(@PathVariable("listingId") String listingId) throws ErrorDeletingUsersException, ListingDoesNotExistException{
       var confirmationDTO = listingsService.removeByListingId(listingId);
       return  ResponseEntity.ok().body(confirmationDTO);
    }

}
