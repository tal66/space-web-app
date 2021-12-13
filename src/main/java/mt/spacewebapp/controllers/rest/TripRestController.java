package mt.spacewebapp.controllers.rest;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.controllers.shared.DtoUtil;
import mt.spacewebapp.dto.TripDto;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.services.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
@Slf4j
public class TripRestController {

    @Autowired
    private DtoUtil dtoUtil;
    @Autowired
    private ITripService tripService;


    @GetMapping
    public SimpleResponseWrapper<List<TripDto>> getAllTrips(){
        List<Trip> trips = tripService.findAll();
        List<TripDto> tripDtos = dtoUtil.mapList(trips, TripDto.class);
        return new SimpleResponseWrapper(tripDtos,
                trips.size() + " trips");
    }

    @GetMapping("/{id}")
    public SimpleResponseWrapper<TripDto> getTrip(@PathVariable Integer id){
        log.info(String.format("api call: get trip __ %s __", id));
        Optional<Trip> trip = tripService.findById(id);
        if (trip.isEmpty()){
            return new SimpleResponseWrapper("trip not found");
        }

        TripDto tripDto = dtoUtil.map(trip.get(), TripDto.class);
        return new SimpleResponseWrapper(tripDto);
    }

    @GetMapping("/{id}/stats")
    public SimpleResponseWrapper<TripStats> getTripStats(@PathVariable Integer id){
        Optional<Trip> trip = tripService.findById(id);
        if (trip.isEmpty()){
            return new SimpleResponseWrapper("trip not found");
        }

        int numberOfTicketsAvailable = tripService.getNumTicketsAvailable(trip.get());
        int plannedNumberOfPassengers = trip.get().getnTicketsMax();
        TripStats stats = new TripStats(numberOfTicketsAvailable, plannedNumberOfPassengers);

        return new SimpleResponseWrapper(stats);
    }

    @Getter
    private class TripStats{
        private int plannedNumberOfPassengers;
        private int numberOfTicketsAvailable;
        private int numberOfTicketsSold;

        TripStats(int numberOfTicketsAvailable, int plannedNumberOfPassengers) {
            this.numberOfTicketsAvailable = numberOfTicketsAvailable;
            this.plannedNumberOfPassengers = plannedNumberOfPassengers;
            this.numberOfTicketsSold = plannedNumberOfPassengers - numberOfTicketsAvailable;
        }
    }

}
