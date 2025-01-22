package com.tekarch.tafFlightService.Services;

import com.tekarch.tafFlightService.DTO.FlightDTO;
import com.tekarch.tafFlightService.Services.Interfaces.FlightServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightServiceInterface {


    private final RestTemplate restTemplate;

    private static final String DATASTORE_URL = "http://localhost:8080/datastore/flights"; // TafDatastoreService URL

    @Autowired
    public FlightServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
//    @Override
//    public List<FlightDTO> getAllFlights() {
//        FlightDTO[] flights = restTemplate.getForObject(DATASTORE_URL + "/flights", FlightDTO[].class);
//        return Arrays.asList(flights);
//    }
    @Override
    public List<FlightDTO> getAllFlights() {
//    String url = "http://localhost:8080/datastore/flights";
    FlightDTO[] flights = restTemplate.getForObject(DATASTORE_URL , FlightDTO[].class);
    return Arrays.asList(flights);
    }


//    @Override
//    public FlightDTO getFlightById(@PathVariable Long flightId) {
//        String url = "http://localhost:8080/flights/" + flightId; // Ensure flightId is a Long
//        return restTemplate.getForObject(url, FlightDTO.class);
//    }

    @Override
    public FlightDTO getFlightById(Long flightId) {
//        String url = "http://localhost:8080/datastore/flights/" + flightId; // Ensure flightId is a Long

        ResponseEntity<FlightDTO> response = restTemplate.getForEntity(DATASTORE_URL + "/" + flightId,  FlightDTO.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch flight details for ID: " + flightId);
        }
    }


//    @Override
//    public FlightDTO getFlightById(Long flightId) {
//        return restTemplate.getForObject(DATASTORE_URL + "/flights/{flightId}", FlightDTO.class, flightId);
//    }

//    @Override
//    public FlightDTO addFlight(FlightDTO flightDTO) {
//        return restTemplate.postForObject(DATASTORE_URL + "/flights", flightDTO, FlightDTO.class);
//    }

    @Override
    public FlightDTO addFlight(FlightDTO flightDTO) {
//        String url = "http://localhost:8080/datastore/flights"; // Check this URL
        return restTemplate.postForObject(DATASTORE_URL, flightDTO, FlightDTO.class);
    }

    @Override
    public FlightDTO updateFlight(Long flightId, FlightDTO flightDTO) {
        restTemplate.put(DATASTORE_URL + "/" + flightId, flightDTO, flightId);
        return flightDTO;
    }

    @Override
    public void deleteFlight(Long flightId) {
        restTemplate.delete(DATASTORE_URL + "/" + flightId, flightId);
    }
}

