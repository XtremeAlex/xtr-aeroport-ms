package com.xtremealex.aeroport.controller;

import com.xtremealex.aeroport.models.web.ErrorCode;
import com.xtremealex.aeroport.models.web.response.ResponseWrapper;
import com.xtremealex.aeroport.models.web.response.ResponseWrapperBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test Controller", description = "test controller")
public class TestController {

    @Autowired
    private ResponseWrapperBuilder responseWrapperBuilder;

    @GetMapping("/testOk")
    public ResponseWrapper gettestOk() {
        return responseWrapperBuilder.buildResponse(ErrorCode.E1, "TEST OK");
    }

    @GetMapping("/testKo")
    public ResponseWrapper getTestKo() {
        return responseWrapperBuilder.buildResponse(ErrorCode.E0, "TEST KO");
    }

    @GetMapping("/resEntityTestOk")
    public ResponseEntity getResEntityTestOk() {
        return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E1, "TEST OK"), null, HttpStatus.OK);
    }

    @GetMapping("/resEntityTestKo")
    public ResponseEntity getResEntityTestKo() {
        return new ResponseEntity(responseWrapperBuilder.buildResponse(ErrorCode.E0, "TEST KO"), null, HttpStatus.BAD_REQUEST);
    }

}
