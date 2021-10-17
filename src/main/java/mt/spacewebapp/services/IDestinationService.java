package mt.spacewebapp.services;

import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.forms.Option;
import mt.spacewebapp.models.forms.SearchForm;

import java.util.List;
import java.util.Map;

public interface IDestinationService {
    List<Destination> searchByFieldGreaterThan(Double num, String field);
    SearchForm createSearchByNumbersForm();
    List<Option> getSearchOptionsForNumbersForm();

    Map<String, List<Destination>> destinationsByTypeMap();
    Destination findByName(String name);
    List<Destination> findAll();
}
