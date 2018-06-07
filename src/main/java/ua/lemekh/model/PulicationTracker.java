package ua.lemekh.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publication_tracker")
public class PulicationTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(targetEntity = Publication.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "publication_id")
    private Publication publication;
    @ManyToOne
    @JoinColumn(name = "published_by")
    private User publishedBy;
    @Column(name = "senn")
    private boolean seen;
    @Column(name = "seen_at")
    private LocalDateTime seenAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(User publishedBy) {
        this.publishedBy = publishedBy;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public LocalDateTime getSeenAt() {
        return seenAt;
    }

    public void setSeenAt(LocalDateTime seenAt) {
        this.seenAt = seenAt;
    }
}
