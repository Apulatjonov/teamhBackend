package com.teamh.FuelBack.services;

import com.teamh.FuelBack.dtos.RequestDto;
import com.teamh.FuelBack.dtos.ResponseDto;
import com.teamh.FuelBack.dtos.UserDto;
import com.teamh.FuelBack.entities.FuelEntity;
import com.teamh.FuelBack.entities.FuelStation;
import com.teamh.FuelBack.entities.UserEntity;
import com.teamh.FuelBack.exceptions.UserAlreadyExistException;
import com.teamh.FuelBack.repositories.FuelRepo;
import com.teamh.FuelBack.repositories.FuelStationRepo;
import com.teamh.FuelBack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FuelRepo fuelRepo;

    @Autowired
    private FuelStationRepo fuelStationRepo;

    public UserDto createUser(UserDto dto){
        Optional<UserEntity> entity = userRepo.findByUsername(dto.getUsername());
        if (entity.isPresent()){
            throw new UserAlreadyExistException("User with this username already exist!", UserEntity.class, "username");
        }
        UserEntity user = new UserEntity();
        if (dto.getUsername() != null)
            user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)
            user.setLastName(dto.getLastName());
        user.setChatId(dto.getChatId());
        if (dto.getPhoneNumber() != null)
            user.setPhoneNumber(dto.getPhoneNumber());
        return userToDto(userRepo.save(user));
    }

    private UserDto userToDto(UserEntity user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        if (user.getUsername() != null){
            dto.setUsername(user.getUsername());
        }
        if (user.getFirstName() != null){
            dto.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null){
            dto.setLastName(user.getLastName());
        }
        if (user.getPhoneNumber() != null){
            dto.setPhoneNumber(user.getPhoneNumber());
        }
        dto.setChatId(user.getChatId());
        return dto;
    }

    public ResponseDto getNearestStation(RequestDto request){
        FuelStation station = null;
        ResponseDto response = new ResponseDto();
        List<FuelStation> stations = fuelStationRepo.findAll();
        for (FuelStation st: stations) {
            if (station == null){
                Optional<FuelEntity> fuel = fuelRepo.findByFuelStationAndName(st, request.getFuelType());
                if (fuel.isPresent()){
                    station = st;
                    double distance = Math.sqrt(Math.pow(Math.abs(request.getLatitude() - station.getLatitude()), 2) + Math.pow(Math.abs(request.getLongitude() - station.getLongitude()), 2));
                    response.setDistance(distance);
                    response.setFound(true);
                    response.setLatitude(station.getLatitude());
                    response.setLongitude(station.getLongitude());
                    response.setName(station.getName());
                    response.setAmount(fuel.get().getAmount());
                    response.setPrice(fuel.get().getPrice());
                    response.setFuelType(fuel.get().getName());
                    response.setMsg(station.getName());
                    response.setLastUpdated(fuel.get().getUpdatedDate());
                    response.setOpenDate(station.getOpenTime());
                    response.setCloseDate(station.getClosedTime());
                }
            }else{
                Optional<FuelEntity> fuel = fuelRepo.findByFuelStationAndName(st, request.getFuelType());
                if (fuel.isPresent()){
                    double distance = Math.sqrt(Math.pow(Math.abs(request.getLatitude() - st.getLatitude()), 2) + Math.pow(Math.abs(request.getLongitude() - st.getLongitude()), 2));
                    if (distance < response.getDistance()){
                        station = st;
                        response.setDistance(distance);
                        response.setFound(true);
                        response.setLatitude(station.getLatitude());
                        response.setLongitude(station.getLongitude());
                        response.setAmount(fuel.get().getAmount());
                        response.setPrice(fuel.get().getPrice());
                        response.setName(station.getName());
                        response.setFuelType(fuel.get().getName());
                        response.setMsg(station.getName());
                        response.setLastUpdated(fuel.get().getUpdatedDate());
                        response.setOpenDate(station.getOpenTime());
                        response.setCloseDate(station.getClosedTime());
                    }
                }
            }
        }
        if (station == null){
            response.setFound(false);
            response.setMsg(request.getFuelType() + " fuel type is not available!");
        }
        return response;
    }
}
