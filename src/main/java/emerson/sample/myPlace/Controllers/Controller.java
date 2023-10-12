package emerson.sample.myPlace.Controllers;
import emerson.sample.myPlace.DTOs.JwtToken;
import emerson.sample.myPlace.DTOs.RequestCreateUser;
import emerson.sample.myPlace.Entities.GeographyInfoView;
import com.fasterxml.jackson.databind.ObjectMapper;
import emerson.sample.myPlace.ErrorHandling.EmailAlreadyExistException;
import emerson.sample.myPlace.Services.InitializeFrontendData;
import emerson.sample.myPlace.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequestMapping("/api-setup")
@RestController
public class Controller {
    @Autowired
    private InitializeFrontendData initializeFrontendData;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UsersService usersService;

    @GetMapping("/geographyInfo")
    @ResponseBody
        public ResponseEntity<Map<String, List<GeographyInfoView>>> getGeographicLocations(){
        Map<String, List<GeographyInfoView>> destinations = initializeFrontendData.geographicData();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("timestamp",new Date().toString())
                .cacheControl(CacheControl.maxAge(100, TimeUnit.SECONDS))
                .body(destinations);
        //String json = objectMapper.writeValueAsString(list_locations);
        //response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //response.setHeader("pragma", "no-cache");
        //response.setHeader("Expires", "0");
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<JwtToken> registerUser(@RequestBody RequestCreateUser requestRegister){
        JwtToken jwtToken= usersService.createUser(requestRegister);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/validate-email")
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> validateEmail(@RequestBody RequestCreateUser validation) throws EmailAlreadyExistException{

        if(usersService.validateEmailAddress(validation)){
            ApiResponse<String> response = new ApiResponse.Builder<String>()
                    .success(true)
                    .message("The email is valid")
                    .data("")
                    .build();
            return ResponseEntity.ok(response);
        }else{
            ApiResponse<String> response = new ApiResponse.Builder<String>()
                    .success(false)
                    .message("Email already exists")
                    .data("")
                    .build();
            return ResponseEntity.ok(response);
        }

    }



}

