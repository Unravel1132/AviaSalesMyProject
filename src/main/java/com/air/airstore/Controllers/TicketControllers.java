package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Service.EntitiesService.ServiceImpl.TicketServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Контроллер работы с тикетами")
@RestController
@RequestMapping("/v1")
public class TicketControllers {

    private final TicketServiceImpl tickerService;


    private static final Logger logger = LoggerFactory.getLogger(TicketControllers.class);


    @Autowired
    public TicketControllers(TicketServiceImpl tickerService) {
        this.tickerService = tickerService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<TicketEntityDTO>> getAllTickets() {
        try{
            return ResponseEntity.ok(tickerService.getTickets());
    }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/all/{id}")
    public ResponseEntity<TicketEntityDTO> getTicketById(@PathVariable Long id) {

        try{
            return ResponseEntity.ok(tickerService.getTicketById(id));

        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<TicketEntityDTO> addTicket(@RequestBody TicketEntityDTO ticketDTO, @PathVariable Long id) {
        try{
            TicketEntityDTO ticketEntityDTO = tickerService.createTicket(ticketDTO,id);
            return new ResponseEntity<>(ticketEntityDTO, HttpStatus.CREATED);
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
