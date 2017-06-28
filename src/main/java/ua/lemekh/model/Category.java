package ua.lemekh.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ostap on 01.06.2017.
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty
    private String name;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory", fetch = FetchType.EAGER)
    private List<Category> categorySet ;

    public int getId() {
        return id;
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(List<Category> categorySet) {
        this.categorySet = categorySet;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
