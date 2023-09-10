package emerson.sample.myPlace.repositoryLayer;

import emerson.sample.myPlace.DataBaseEntities.GeographyInfoView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewsDBRepository extends ReadOnlyRepository<GeographyInfoView, Integer> {
    @Query( value = "select id, state_name, city_name from geography_info", nativeQuery = true)
    public List<GeographyInfoView> getGeoLocations();
    @Query( value = "select * from geography_info",nativeQuery = true)
    public List<GeographyInfoView> getPopularLocations();



}
