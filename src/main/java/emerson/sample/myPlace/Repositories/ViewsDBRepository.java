package emerson.sample.myPlace.Repositories;

import emerson.sample.myPlace.Entities.GeographyInfoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewsDBRepository extends ReadOnlyRepository<GeographyInfoView, Integer> {
    @Query( value = "select id, state_name, city_name from geography_info", nativeQuery = true)
    public List<GeographyInfoView> getGeoLocations();




}
