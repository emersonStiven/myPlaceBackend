package emerson.sample.myPlace.repositoryLayer;

import emerson.sample.myPlace.DataBaseEntities.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
