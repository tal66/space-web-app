package mt.spacewebapp.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @GeneratedValue
    @Id
    private Integer id;
    private String address;
    private String text;

    public Image(String address, String text) {
        this.address = address;
        this.text = text;
    }

    public Image() {
    }

    public Image(String address) {
        this.address = address;
        this.text = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Image{" +
                "address='" + address + '\'' +
                ", text='" + text + '\'' +
                '}';
    }


}
