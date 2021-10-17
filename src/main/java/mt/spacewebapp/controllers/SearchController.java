package mt.spacewebapp.controllers;

import lombok.extern.slf4j.Slf4j;
import mt.spacewebapp.models.Destination;
import mt.spacewebapp.models.Trip;
import mt.spacewebapp.models.forms.FormValidation;
import mt.spacewebapp.models.forms.SearchForm;
import mt.spacewebapp.services.IDestinationService;
import mt.spacewebapp.services.ITripService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
public class SearchController {
    private IDestinationService destinationService;
    private ITripService tripService;

    public SearchController(IDestinationService destinationService, ITripService tripService) {
        this.destinationService = destinationService;
        this.tripService = tripService;
    }

    @GetMapping("/search")
    public String search(Model model){
        SearchForm searchDestForm = destinationService.createSearchByNumbersForm();
        SearchForm searchTripForm = new SearchForm();
        model.addAttribute("searchDestForm", searchDestForm);
        model.addAttribute("searchTripForm", searchTripForm);
        return "search";
    }

    @PostMapping(value = "/search", params = "search-dest")
    public String destinationSearchResults(Model model, @Valid @ModelAttribute SearchForm searchForm, BindingResult errors){
        searchForm.setOptions(destinationService.getSearchOptionsForNumbersForm());
        model.addAttribute("searchDestForm", searchForm);
        FormValidation formValidation = validateDestinationSearchParams(searchForm, errors);
        if (!formValidation.hasErrors()){
            List<Destination> results = getDestinationResults(searchForm);
            model.addAttribute("destResults", results);
            log.info("search results: " + results.size());
        } else {
            model.addAttribute("errorMessage", formValidation.getErrorMessage());
        }
        return "search";
    }


    @PostMapping(value = "api/search/destination*")
    @ResponseBody
    public List<Destination> destinationSearchResultsApi(@RequestParam String option, @RequestParam Double number){
        log.info("search api");
        return destinationService.searchByFieldGreaterThan(number, option);
    }

    private List<Destination> getDestinationResults(SearchForm searchForm){
        String selectedField = searchForm.getSelectedOption();
        String userText = searchForm.getUserText();
        double userNumber = Double.parseDouble(userText.replace(",",""));
        List<Destination> results = destinationService.searchByFieldGreaterThan(userNumber, selectedField);
        return results;
    }

    private FormValidation validateDestinationSearchParams(SearchForm searchForm, BindingResult errors){
        String errorMessage = "";

        if (errors.hasErrors()) {
            errorMessage = "binding error";
        }
        if (searchForm.getUserText().isBlank()){
            errorMessage = "search is blank";
        }

        try {
            Double.parseDouble(searchForm.getUserText().replace(",",""));
        } catch (Exception e){
            errorMessage = "numbers only";
        }
        return new FormValidation(errorMessage, errorMessage.isBlank());
    }

    @PostMapping(value = "/search", params = "search-trip")
    public String tripSearchResults(Model model, @Valid @ModelAttribute SearchForm searchForm, BindingResult errors){
        model.addAttribute("searchTripForm", searchForm);
        FormValidation formValidation = validateTripSearchParams(searchForm);
        if (!formValidation.hasErrors()){
            List<Trip> results = processTripFormAndGetResults(searchForm);
            model.addAttribute("tripResults", results);
        } else {
            model.addAttribute("errorMessage", formValidation.getErrorMessage());
        }
        return "search";
    }

    private FormValidation validateTripSearchParams(SearchForm searchForm){
        String errorMessage = "";
        if (searchForm.getDate1() == null || searchForm.getDate2() == null){
            errorMessage = "Select 2 dates";
        }

        return new FormValidation(errorMessage, errorMessage.isBlank());
    }

    private List<Trip> processTripFormAndGetResults(SearchForm searchForm){
        LocalDate date1 = searchForm.getDate1();
        LocalDate date2 = searchForm.getDate2();
        List<Trip> results = tripService.findByDateAfterAndDateBeforeOrderByDate(date1, date2);
        return results;
    }


}
