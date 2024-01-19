package com.emerson.propertyservice;

import com.emerson.propertyservice.Repositories.ListingDAO;
import com.emerson.propertyservice.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@SpringBootTest
class PropertyServiceApplicationTests {

	@Autowired
	private ListingDAO listingDao;

	@Test
	void contextLoads() throws IOException{
		String folderPath = "C:\\Users\\dnate\\Downloads\\images";
		File folder = new File(folderPath);
		List<Image> store = new ArrayList<>();
		for(File f : folder.listFiles()){
			if(f.isDirectory()){
				Listing listing  =buildRandomListing();
				for(File ff : f.listFiles()){
					Path path = Path.of(ff.getPath());
					store.add(Image.builder().listingId(listing).image(Files.readAllBytes(path)).build());
				}
				listing.setImages(store);
				listingDao.save(listing);
			}
			store.clear();
		}

	}

	public static Listing buildRandomListing() {
		Listing l= Listing.builder()
				.hostId(UUID.randomUUID())
				.listingId(UUID.randomUUID())
				.propertyName("Cozy Apartment")
				.description("A comfortable and cozy apartment in the city center.")
				.surroundingAreaOverview("Close to restaurants, shops, and public transportation.")
				.notes("No smoking allowed.")
				.transit("Easy access to public transportation.")
				.bathroomCtn(2)
				.guestCapacity(4)
				.hostRules(Arrays.asList("No smoking", "No parties"))
				.taxRate(10)
				.averageReviews(4.5f)
				.bookingsCount(50)
				.viewsCount(100)
				.reviewsCount(30)
				.build();
		l.setCancellationPolicy(buildRandomCancellationPolicy(l));
		l.setFreeServices(buildRandomFreeServices(l));
		l.setPaidServices(buildRandomPaidServices(l));
		l.setRooms(buildRandomRooms(l));
		l.setLocation(buildRandomLocation(l));
		l.setPricing(buildRandomPriceListing(l));
		return l;
	}

	private static PriceListing buildRandomPriceListing(Listing l) {
		return PriceListing.builder()
				.priceNight(new BigDecimal(80))
				.priceWeek(new BigDecimal(500))
				.priceMonth(new BigDecimal(1800))
				.cleaningFee(new BigDecimal(50))
				.listingId(l)
				.minNights(1)
				.maxNights(14)
				.build();
	}

	private static Location buildRandomLocation(Listing l) {
		return Location.builder()
				.zipcode(12345)
				.city("Cityville")
				.listingId(l)
				.state("Stateville")
				.country("Countryville")
				.address("123 Main Street")
				.build();
	}

	private static CancellationPolicy buildRandomCancellationPolicy(Listing l) {
		return CancellationPolicy.builder()
				.policyType(PolicyType.FLEXIBLE)
				.customCancellationTimeline(15)
				.listingId(l)
				.description("Free cancellation up to 24 hours before check-in.")
				.build();
	}

	private static List<FreeService> buildRandomFreeServices(Listing l) {
		FreeService freeService1 = FreeService.builder()
				.freeServiceName("Wi-Fi")
				.iconUrl("wifi-icon.png")
				.listingId(l)
				.build();

		FreeService freeService2 = FreeService.builder()
				.freeServiceName("Parking")
				.iconUrl("parking-icon.png")
				.listingId(l)
				.build();

		return Arrays.asList(freeService1, freeService2);
	}

	private static List<PaidService> buildRandomPaidServices(Listing l) {
		PaidService paidService1 = PaidService.builder()
				.paidServiceName("Airport Shuttle")
				.description("Transportation to and from the airport.")
				.price(new BigDecimal(30))
				.listingId(l)
				.build();

		PaidService paidService2 = PaidService.builder()
				.paidServiceName("Breakfast")
				.description("Delicious breakfast options.")
				.price(new BigDecimal(15))
				.listingId(l)
				.build();

		return Arrays.asList(paidService1, paidService2);
	}

	private static List<Room> buildRandomRooms(Listing l) {
		Room room1 = Room.builder()
				.bedCtn(1)
				.bedType(Collections.singletonList("Queen"))
				.privateBathRoom(true)
				.listingId(l)
				.build();

		Room room2 = Room.builder()
				.bedCtn(2)
				.bedType(Arrays.asList("Single", "Single"))
				.privateBathRoom(false)
				.listingId(l)
				.build();

		return Arrays.asList(room1, room2);
	}

}
