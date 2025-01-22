package com.tekarch.tafFlightService.Controller;


import com.tekarch.tafFlightService.DTO.FlightDTO;
import com.tekarch.tafFlightService.Services.Interfaces.FlightServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    @Autowired
    private FlightServiceInterface flightService;

    public FlightController(FlightServiceInterface flightService) {
        this.flightService = flightService;
    }
    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDTO> getFlightById(@PathVariable Long flightId) {
        FlightDTO flightDTO = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flightDTO);
    }

//    @GetMapping("/{flightId}")
//    public FlightDTO getFlightById(@PathVariable Long flightId) {
//        return flightService.getFlightById(flightId);
//    }

    @PostMapping
    public FlightDTO addFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.addFlight(flightDTO);
    }

    @PutMapping("/{flightId}")
    public void updateFlight(@PathVariable Long flightId, @RequestBody FlightDTO flightDTO) {
        flightService.updateFlight(flightId, flightDTO);
    }

    @DeleteMapping("/{flightId}")
    public void deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
    }
}

