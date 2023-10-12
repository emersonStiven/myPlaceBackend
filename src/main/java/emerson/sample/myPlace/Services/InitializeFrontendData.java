package emerson.sample.myPlace.Services;

import emerson.sample.myPlace.Entities.GeographyInfoView;
import emerson.sample.myPlace.Repositories.ViewsDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InitializeFrontendData {

    @Autowired
    private ViewsDBRepository viewsDBRepository;
    public Map<String, List<GeographyInfoView>> geographicData (){
        List<GeographyInfoView> list = viewsDBRepository.getGeoLocations();
        Map<String, List<GeographyInfoView>> myMap = new HashMap<>();
        myMap.put("popularDestinations", list.subList(1, 10));
        myMap.put("destinations", list);
        return myMap;
    }
}
