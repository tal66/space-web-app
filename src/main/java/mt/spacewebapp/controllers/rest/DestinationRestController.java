package mt.spacewebapp.controllers.rest;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.services.IDestinationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DestinationRestController {

    private IDestinationService destinationService;

    public DestinationRestController(IDestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping(value = "/api/destinations/{name}")
    public Destination destinationInfo(@PathVariable String name){
        return destinationService.findByName(name);
    }

    @GetMapping(value = "/api/search/destinations*")
    public List<Destination> destinationSearchResults(@RequestParam String option, @RequestParam Double gt){
        return destinationService.searchDestinationByFieldGreaterThan(gt, option);
    }
}
