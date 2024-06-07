package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.AirPlaneEntityDTO;
import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Service.EntitiesService.ServiceImpl.AirPlaneServiceImpl;
import com.air.airstore.Service.EntitiesService.ServiceImpl.TicketServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Контроллер работы с самолетами")
@RestController
@RequestMapping("/v2")
public class AviaController {

    private final AirPlaneServiceImpl airPlaneService;


    @Autowired
    public AviaController(AirPlaneServiceImpl airPlaneService, TicketServiceImpl ticketService) {
        this.airPlaneService = airPlaneService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<AirPlaneEntityDTO>> getAllAirPlanes() {
        return ResponseEntity.ok(airPlaneService.getAllAirPlanes());
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<TicketEntityDTO>> findAllTicketsById(@PathVariable Long id) {
        List<TicketEntityDTO> ticketEntityDTOList = airPlaneService.findAllTicketsByAirPlaneId(id);
        return ResponseEntity.ok(ticketEntityDTOList);
    }

    @PostMapping("/add")
    public ResponseEntity<AirPlaneEntityDTO> addAirPlane(@RequestBody AirPlaneEntityDTO airPlane) {
        AirPlaneEntityDTO airPlaneEntityDTO = airPlaneService.createAirPlane(airPlane);
        return ResponseEntity.status(HttpStatus.CREATED).body(airPlaneEntityDTO);

    }

    @PutMapping("/put/{id}")
    public ResponseEntity<AirPlaneEntityDTO> putAirPlane(@RequestBody AirPlaneEntityDTO airPlane, @PathVariable Long id) {

        AirPlaneEntityDTO airPlaneEntityDTO = airPlaneService.updateAirPlane(id, airPlane);
        return ResponseEntity.ok(airPlaneEntityDTO);


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAirPlane(@PathVariable Long id) {
        airPlaneService.deleteAirPlane(id);
        return ResponseEntity.noContent().build();
    }
}