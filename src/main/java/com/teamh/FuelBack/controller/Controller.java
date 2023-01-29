package com.teamh.FuelBack.controller;

import com.teamh.FuelBack.dtos.RequestDto;
import com.teamh.FuelBack.dtos.ResponseDto;
import com.teamh.FuelBack.dtos.UserDto;
import com.teamh.FuelBack.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fuelApp/v1/")
public class Controller {

    @Autowired
    private Service service;

    @PostMapping("/createUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDto dto){
        UserDto response = service.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/request")
    public ResponseEntity<?> request(@RequestBody RequestDto requestDto){
        ResponseDto response = service.getNearestStation(requestDto);
        System.out.println(response.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @GetMapping("/statistics")
//    public ResponseEntity<?> getStatistics(){
//
//    }
}
