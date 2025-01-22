package com.tekarch.tafFlightService.Services.Interfaces;



import com.tekarch.tafFlightService.DTO.FlightDTO;

import java.util.List;

public interface FlightServiceInterface {
    List<FlightDTO> getAllFlights();
    FlightDTO getFlightById(Long flightId);
    FlightDTO addFlight(FlightDTO flightDTO);
    FlightDTO updateFlight(Long flightId, FlightDTO flightDTO);
    void deleteFlight(Long flightId);
}

