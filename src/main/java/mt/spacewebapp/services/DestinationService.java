package mt.spacewebapp.services;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.data.DestinationRepository;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.forms.Option;
import mt.spacewebapp.models.forms.SearchForm;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DestinationService {

    private DestinationRepository destinationRepository;
    private List<Destination> allDestinations;
    private LocalDateTime timeStamp;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public List<Destination> searchByFieldGreaterThan(Double num, String field){
        try {
            return destinationsWithFieldGreaterThan(num, field);
        } catch (Exception e){
            log.info("results exception " + e);
            return null;
        }
    }

    private List<Destination> destinationsWithFieldGreaterThan(Double num, String field){
        updateCache();
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

    public SearchForm getSearchByNumbersForm(){
        List<Option> options = getSearchOptionsForNumbersForm();
        SearchForm searchForm = new SearchForm(options);
        return searchForm;
    }

    public List<Option> getSearchOptionsForNumbersForm(){
        return List.of(
                new Option("Radius_km", "Radius (km)"),
                new Option("orbitPeriod_earthYears", "Orbit Period (Earth Years)"),
                new Option("avgOrbitDistance_km", "Average Orbit Distance (km)"));
    }

    @Cacheable("destinations")
    public Map<String, List<Destination>> DestinationsByTypeMap(){
        updateCache();
        log.info("preparing destinations by type map");
        Map<String, List<Destination>> result = allDestinations.stream()
                .collect(Collectors.groupingBy(d -> d.getType().toString()));
        return result;
    }

    public Destination findByName(String name){
        return destinationRepository.findByName(name);
    }

    @Cacheable("destinations") // only for outside calls
    public List<Destination> findAll(){
        log.info("finding all destinations");
        return destinationRepository.findAll();
    }


    private boolean updateCache(){
        if (!cacheIsUpdated()){
            this.allDestinations = this.findAll();
            this.timeStamp = LocalDateTime.now();
            return true;
        }
        return false;
    }

    private boolean cacheIsUpdated(){
        return (this.allDestinations != null
                && LocalDateTime.now().minusMinutes(5).compareTo(this.timeStamp) < 0);
    }

}

