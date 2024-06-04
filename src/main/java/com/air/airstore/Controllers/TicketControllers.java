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

        return ResponseEntity.ok(tickerService.getTickets());
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<TicketEntityDTO> getTicketById(@PathVariable Long id) {

        TicketEntityDTO ticketEntityDTO = tickerService.getTicketById(id);
        return ResponseEntity.ok(ticketEntityDTO);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<TicketEntityDTO> addTicket(@RequestBody TicketEntityDTO ticketDTO, @PathVariable Long id) {

            TicketEntityDTO createdTicketEntityDTO = tickerService.createTicket(ticketDTO, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTicketEntityDTO);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<TicketEntityDTO> updateTicket(@RequestBody TicketEntityDTO ticketDTO, @PathVariable Long id) {
        try {
            TicketEntityDTO ticketEntityDTO = tickerService.updateTicket(id, ticketDTO);
            return ResponseEntity.ok(ticketEntityDTO);
        } catch (Exception e) {
            logger.error("Ошибка при добавлении тикела к самолету: ", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        if (id != null) {
            tickerService.deleteTicket(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
