package emerson.sample.myPlace.repositoryLayer;

import emerson.sample.myPlace.DataBaseEntities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query( value = "select * from country where country_name = ?", nativeQuery = true)
    Country findByCountryName(String name);
}
