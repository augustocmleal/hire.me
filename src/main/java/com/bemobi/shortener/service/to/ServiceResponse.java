package com.bemobi.shortener.service.to;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServiceResponse{

    public ResultTO result;

    public ServiceResponse() {
    	
    }

    public ServiceResponse(UrlServiceResponseTO responseMapping) {
        ResultTO result = new ResultTO(responseMapping);
        this.result = result;
    }


    public static class ResultTO {

        public boolean success;
        public String code;
        public String message;

        @JsonIgnore
        public Integer httpStatus;

        public ResultTO(UrlServiceResponseTO responseMapping) {
            this.success = responseMapping.isSuccess();
            this.code = responseMapping.getCode();
            this.message = responseMapping.getMessage();
            this.httpStatus = responseMapping.getHttpStatus();
        }

    }

}