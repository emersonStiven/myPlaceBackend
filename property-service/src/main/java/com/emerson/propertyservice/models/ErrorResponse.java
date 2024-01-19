package com.emerson.propertyservice.models;
import java.time.LocalDateTime;
import java.util.List;

public  class ErrorResponse {
    private ErrorResponse(Builder builder){
        this.timestamp = LocalDateTime.now();
        this.ok = builder.ok;
        this.message = builder.message;
    }
    private String message;
    private boolean ok;
    private LocalDateTime timestamp;
    public String getMessage(){
        return  this.message;
    }
    public boolean ok(){
        return this.ok;
    }
    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private  String message;
        private  boolean ok;
        private List<String> errors;
        public Builder withErrors(List<String> errors){
            this.errors = errors;
            return this;
        }
        public Builder withMessage(String message){
            this.message = message;
            return this;
        }
        public Builder withStatus(boolean ok){
            this.ok = ok;
            return this;
        }
        public ErrorResponse build(){
            return new ErrorResponse(this);
        }
    }

}
