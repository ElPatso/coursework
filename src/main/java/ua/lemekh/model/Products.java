package ua.lemekh.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    @Column(name = "name")
    private String name;
    @NotEmpty
    @Column(name = "category")
    private String category;
    @NotNull
    @Column(name = "price")
    private double price;
    @NotEmpty
    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "products", fetch = FetchType.EAGER)
    private List<Comments> commentsSet;
    public int getId() {
        return id;
    }

    @Transient
    private MultipartFile image;

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
