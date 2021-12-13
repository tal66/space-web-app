package mt.spacewebapp.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@ToString
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setText(String text) {
        this.text = text;
    }

}
