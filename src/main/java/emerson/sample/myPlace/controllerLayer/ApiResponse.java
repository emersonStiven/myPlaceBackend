package emerson.sample.myPlace.controllerLayer;

import org.springframework.stereotype.Component;

@Component
public class ApiResponse <T>{
    private ApiResponse(){}
    public boolean success;
    public String message;
    public T data;
    public static class Builder<T> {
        private ApiResponse<T> apiResponse;
        public Builder(){
            apiResponse = new ApiResponse<T>();
        }
        public Builder<T> sucess (boolean success){
            this.apiResponse.success = success;
            return this;
        }
        public Builder<T> message (String msg){
            this.apiResponse.message = msg;
            return this;
        }
        public Builder<T> data (T data){
            this.apiResponse.data = data;
            return this;
        }
        public ApiResponse<T> build(){
            return apiResponse;
        }
    }

}
