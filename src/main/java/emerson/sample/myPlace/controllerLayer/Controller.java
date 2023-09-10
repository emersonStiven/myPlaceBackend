package emerson.sample.myPlace.controllerLayer;
import emerson.sample.myPlace.DataBaseEntities.GeographyInfoView;
import com.fasterxml.jackson.databind.ObjectMapper;
import emerson.sample.myPlace.repositoryLayer.ViewsDBRepository;
import jakarta.mail.Header;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;



@RestController
public class Controller {
    @Autowired
    private ViewsDBRepository viewsDBRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/geographyInfo")
    @CrossOrigin( origins = "http://localhost:5173/")
    @ResponseBody
        public ResponseEntity<List<GeographyInfoView>> getGeographicLocations(){
        List<GeographyInfoView> list_locations = viewsDBRepository.getGeoLocations();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("timestamp",new Date().toString())
                .cacheControl(CacheControl.maxAge(100, TimeUnit.SECONDS))
                .body(list_locations);
        //String json = objectMapper.writeValueAsString(list_locations);
        //response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //response.setHeader("pragma", "no-cache");
        //response.setHeader("Expires", "0");
    }
    @GetMapping("/popularLocations")
    @CrossOrigin( origins = "http://localhost:5173/")
    public ResponseEntity<List<GeographyInfoView>> getPopularLocations() throws UnsatisfiedDependencyException {
        List<GeographyInfoView> list_locations = viewsDBRepository.getPopularLocations();




        int x = 0;
        x = 5+2;
        int resultado = x * 10; // 3 veces porque NO HAY NADA EXTERNO QUE HAGA QUE SE EJECUTEN MAS VECES


        for(int i = 0; i<10; i++){     //       n              veces, n = cantidad de repeticiones
            System.out.println("te amo"); //    n                veces
            for(int z = 0; i< 10; i++){     //  1n * 1n
                System.out.println("danna");//  n * n
            }//f(n) =  2n^2 + 2n   => O(n^2)
            //time complexity, tiempo de ejecucion
        }





        while(true){

        }
        do {

        }while(true);

        if(){

        }else{

        }









        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(list_locations);
    }

}

