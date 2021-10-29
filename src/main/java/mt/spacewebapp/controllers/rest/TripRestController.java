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

@RestController
@RequestMapping("/api/trips")
@Slf4j
public class TripRestController {

    @Autowired
    private DtoUtil dtoUtil;
    @Autowired
    private ITripService tripService;


    @GetMapping
    public List<TripDto> getAllTrips(){
        List<Trip> trips = tripService.findAll();
        return dtoUtil.mapList(trips, TripDto.class);
    }

    @GetMapping("/{id}")
    public TripDto getTrip(@PathVariable Integer id){
        log.info("api call: get trip " + id);
        Trip trip = tripService.findById(id).get();
        return dtoUtil.map(trip, TripDto.class);
    }

    @GetMapping("/{id}/stats")
    public Object getTripStats(@PathVariable Integer id){
        Trip trip = tripService.findById(id).get();

        int numberOfTicketsAvailable = tripService.getNumberOfTicketsAvailable(trip);
        int plannedNumberOfPassengers = trip.getPlannedNumberOfPassengers();
        return new TripStats(numberOfTicketsAvailable, plannedNumberOfPassengers);
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
