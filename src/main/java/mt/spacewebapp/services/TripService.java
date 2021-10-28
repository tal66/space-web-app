package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.TripRepository;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TripService implements ITripService {

    private TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> availableTripsToDestination(Destination destination){
        List<Trip> tripList = findByDestination(destination);
        List<Trip> availableTripList = tripList.stream()
                .filter(Trip::isAvailable).collect(Collectors.toList());
        return availableTripList;
    }

    @Override
    public List<Trip> findByDestination(Destination destination){
        List<Trip> tripList = tripRepository.findByTo(destination);
        log.info(String.format("All Trips to %s: %d", destination.getName(), tripList.size()));
        return tripList;
    }

    @Override
    public Optional<Trip> findById(Integer id){
        return tripRepository.findById(id);
    }

    @Override
    public List<Trip> findAll(){
        List<Trip> tripList = tripRepository.findAll();
        return tripList;
    }

    @Override
    public List<Trip> findByDateBetweenOrderByDate(LocalDate date1, LocalDate date2){
        return tripRepository.findByDateBetweenOrderByDate(date1, date2);
    }
}
