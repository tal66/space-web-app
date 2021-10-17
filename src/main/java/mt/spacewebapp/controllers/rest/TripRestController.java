package mt.spacewebapp.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.services.ITripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/trips")
@Slf4j
public class TripRestController {
    private final ITripService tripService;

    public TripRestController(ITripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<Trip> getAllTrips(){
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    public Trip get(@PathVariable Integer id){
        log.info("api call: get trip " + id);
        Trip trip = tripService.findById(id).get();
        return trip;
    }


}
