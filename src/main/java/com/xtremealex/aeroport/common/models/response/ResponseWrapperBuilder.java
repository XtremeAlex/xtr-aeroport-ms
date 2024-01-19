package com.xtremealex.aeroport.common.models.response;

import com.xtremealex.aeroport.common.models.ErrorCode;

public class ResponseWrapperBuilder<T> {


    public ResponseWrapper<T> buildResponse(ErrorCode errorCode, String msg) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setCode(errorCode.getCode());
        responseWrapper.setDescription(errorCode.getDescription());
        responseWrapper.setMessage(msg);
        return responseWrapper;
    }

    public ResponseWrapper<T> buildResponse(ErrorCode errorCode,  T filters, String msg) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setCode(errorCode.getCode());
        responseWrapper.setDescription(errorCode.getDescription());
        responseWrapper.setFilters(filters);
        responseWrapper.setMessage(msg);
        return responseWrapper;
    }

    public ResponseWrapper<T> buildResponse(ErrorCode errorCode, T playload) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setCode(errorCode.getCode());
        responseWrapper.setDescription(errorCode.getDescription());
        responseWrapper.setPayload(playload);
        return responseWrapper;
    }



    public ResponseWrapper<T> buildResponse(ErrorCode errorCode,T filters, T playload) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>();
        responseWrapper.setCode(errorCode.getCode());
        responseWrapper.setDescription(errorCode.getDescription());
        responseWrapper.setFilters(filters);
        responseWrapper.setPayload(playload);
        return responseWrapper;
    }

}