package mt.spacewebapp.models.forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class SearchForm {

    private List<Option> options;
    private String selectedOption;
    private String userText;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date2;

    public SearchForm() {
    }
    public SearchForm(List<Option> options) {
        this.options = options;
    }

    public SearchForm(LocalDate date1) {
        this.date1 = date1;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public LocalDate getDate2() {
        return date2;
    }

    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }
}
