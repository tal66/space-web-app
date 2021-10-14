package mt.spacewebapp.models.forms;

public class FormValidation {
    private String errorMessage;
    private boolean isValid;

    public FormValidation(String errorMessage, boolean isValid) {
        this.errorMessage = errorMessage;
        this.isValid = isValid;
    }

    public boolean hasErrors(){
        return !this.isValid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
