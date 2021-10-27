package mt.spacewebapp.controllers.rest;

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
        List<TripDto> tripDtos = dtoUtil.mapList(trips, TripDto.class);
        return tripDtos;
    }

    @GetMapping("/{id}")
    public TripDto get(@PathVariable Integer id){
        log.info("api call: get trip " + id);
        Trip trip = tripService.findById(id).get();
        TripDto tripDto = dtoUtil.map(trip, TripDto.class);
        return tripDto;
    }




}
