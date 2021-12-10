package mt.spacewebapp.services.impl;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.DestinationRepository;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.forms.Option;
import mt.spacewebapp.models.forms.SearchForm;
import mt.spacewebapp.services.IDestinationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DestinationService implements IDestinationService {

    private DestinationRepository destinationRepository;
    private List<Destination> allDestinations;
    private Map<String, List<Destination>> destinationsByType;
    private Instant timeStamp;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public List<Destination> searchDestinationByFieldGreaterThan(Double num, String field){
        try {
            return getDestinationsWithFieldGreaterThan(num, field);
        } catch (Exception e){
            log.error("results exception " + e);
            return null;
        }
    }

    // not optimal. done this way for learning purposes
    private List<Destination> getDestinationsWithFieldGreaterThan(Double num, String field){
        Method getter = fieldToGetter(field);
        List<Destination> result = allDestinations.stream()
                .filter(dest -> invokeMethod(getter, dest) > num)
                .collect(Collectors.toList());
        log.info(String.format("search results [%s > %.2f]: %d results", field, num, result.size()));
        return result;
    }

    private Double invokeMethod(Method method, Destination dest){
        try {
            return ((Number) method.invoke(dest)).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Method fieldToGetter(String str){
        for (Method m : Destination.class.getDeclaredMethods()) {
            if (m.getName().startsWith("get") && m.getName().toLowerCase().contains(str.toLowerCase())) {
                return m;
            }
        }
        log.info("method not found for: " + str);
        return null;
    }

    @Override
    public SearchForm createSearchByNumbersForm(){
        List<Option> options = getSearchOptionsForNumbersForm();
        SearchForm searchForm = new SearchForm(options);
        return searchForm;
    }

    private List<Option> getSearchOptionsForNumbersForm(){
        return List.of(
                new Option("radius_km", "Radius (km)"),
                new Option("orbitPeriod_earthYears", "Orbit Period (Earth Years)"),
                new Option("avgOrbitDistance_km", "Average Orbit Distance (km)"));
    }


    @Override
    public Map<String, List<Destination>> destinationsByTypeMap(){
        return this.destinationsByType;
    }


    @Override
    public Destination findByName(String name){
        return destinationRepository.findByNameIgnoreCase(name);
    }


    @Override
    public List<Destination> findAll(){
        return this.allDestinations;
    }

    @Scheduled(fixedRate = 1000 * 60 * 15)
    private void updateDestinationsVariables(){
        this.allDestinations = destinationRepository.findAll();
        log.info("update allDestinations: " + this.allDestinations.size() + " destinations");
        this.destinationsByType = allDestinations.stream()
                .collect(Collectors.groupingBy(d -> d.getType().toString()));
        this.timeStamp = Instant.now();
    }

    private boolean isCacheUpdated(int minutes){
        return (this.allDestinations != null
                && Instant.now().minusSeconds(minutes * 60).compareTo(this.timeStamp) < 0);
    }

}

