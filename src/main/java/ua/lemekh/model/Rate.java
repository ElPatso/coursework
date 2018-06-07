package ua.lemekh.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User ratedBy;
    @ManyToOne
    @JoinColumn(name ="publication_id")
    private Publication publication;
    @Column(name = "rated_at")
    private LocalDateTime ratedAt;
    @Column(name = "rating")
    private Integer rating;

    public Integer getId() {
        return id;
    }

    public Rate setId(Integer id) {
        this.id = id;
        return this;
    }

    public User getRatedBy() {
        return ratedBy;
    }

    public Rate setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
        return this;
    }

    public Publication getPublication() {
        return publication;
    }

    public Rate setPublication(Publication publication) {
        this.publication = publication;
        return this;
    }

    public LocalDateTime getRatedAt() {
        return ratedAt;
    }

    public Rate setRatedAt(LocalDateTime ratedAt) {
        this.ratedAt = ratedAt;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public Rate setRating(Integer rating) {
        this.rating = rating;
        return this;
    }
}
