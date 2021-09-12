package mt.spacewebapp.models.forms;

public class Option {
    String value;
    String text;

    public Option() {
    }

    public Option(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public Option(String value) {
        this.value = value;
        this.text = value;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return String.format("Option[%s, text:%s]", value, text);
    }
}
