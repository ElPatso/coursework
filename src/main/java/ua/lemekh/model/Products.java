package ua.lemekh.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Ostap on 16.06.2017.
 */
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "category")
    private String category;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Comments> commentsSet;
    public int getId() {
        return id;
    }

    public List<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(List<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
