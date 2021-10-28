package mt.spacewebapp.models.forms;

public class FormValidation {
    private String errorMessage;
    private boolean valid;

    public FormValidation(String errorMessage, boolean valid) {
        this.errorMessage = errorMessage;
        this.valid = valid;
    }

    public boolean hasErrors(){
        return !isValid();
    }

    public boolean isValid(){
        return this.valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
