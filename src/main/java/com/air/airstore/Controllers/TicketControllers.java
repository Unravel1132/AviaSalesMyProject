package com.air.airstore.Controllers;


import com.air.airstore.EntityDTO.TicketEntityDTO;
import com.air.airstore.Service.EntitiesService.ServiceImpl.TicketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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


    @GetMapping("/all/search")
    public ResponseEntity<List<TicketEntityDTO>> searchTicketsByNameAndPrice(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price) {

        List<TicketEntityDTO> ticketEntityDTOS = tickerService.findByAirPlaneEntityNameAndPrice(name, price);
        return ResponseEntity.ok(ticketEntityDTOS);
    }

    @Operation(
            summary = "Метод, который добавляет тикет к самолету"
    )
    @PostMapping("/add/{id}")
    public ResponseEntity<TicketEntityDTO> addTicket(@RequestBody TicketEntityDTO ticketDTO, @PathVariable Long id) {
        TicketEntityDTO createdTicketEntityDTO = tickerService.createTicket(ticketDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTicketEntityDTO);
    }


    @PutMapping("/put/{id}")
    public ResponseEntity<TicketEntityDTO> updateTicket(@RequestBody TicketEntityDTO ticketDTO, @PathVariable Long id) {
        TicketEntityDTO updatedTicketEntityDTO = tickerService.updateTicket(id, ticketDTO);
        return ResponseEntity.ok(updatedTicketEntityDTO);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
      tickerService.deleteTicket(id);
      return ResponseEntity.noContent().build();
}
}
