package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Service.EntitiesService.ServiceImpl.AirPlaneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class AviaController {

    private final AirPlaneServiceImpl airPlaneService;

    @Autowired
    public AviaController(AirPlaneServiceImpl airPlaneService) {
        this.airPlaneService = airPlaneService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<AirPlaneEntityDTO>> getAllAirPlanes() {

        try {
            List<AirPlaneEntityDTO> airPlanes = airPlaneService.getAllAirPlanes();
            return ResponseEntity.ok(airPlanes);

        }        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<TicketEntityDTO>> findAllTicketsById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(airPlaneService.findAllTicketsByAirPlaneId(id));

        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<AirPlaneEntityDTO> addAirPlane(@RequestBody AirPlaneEntityDTO airPlane) {
        try{
            AirPlaneEntityDTO airPlaneEntityDTO = airPlaneService.createAirPlane(airPlane);
            return ResponseEntity.ok(airPlaneEntityDTO);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }

    }




}
