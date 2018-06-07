package ua.lemekh.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products", fetch = FetchType.EAGER)
    private List<Comments> commentsSet;
    @ManyToOne
    @JoinColumn(name = "published_by")
    private User publicatedBy;
    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
    @Column(name = "file_url")
    private String fileUrl;
    @Column(name = "logo_url")
    private String logoUrl;
    @Column(name = "public_publication")
    private boolean publicPublication;
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "publication_group", joinColumns = @JoinColumn(name = "publication_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Rate> rates;

    public List<Rate> getRates() {
        return rates;
    }

    public Publication setRates(List<Rate> rates) {
        this.rates = rates;
        return this;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public User getPublicatedBy() {
        return publicatedBy;
    }

    public void setPublicatedBy(User publicatedBy) {
        this.publicatedBy = publicatedBy;
    }

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

    public boolean isPublicPublication() {
        return publicPublication;
    }

    public void setPublicPublication(boolean publicPublication) {
        this.publicPublication = publicPublication;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getDescription() {
        return description;
    }

    public Publication setDescription(String description) {
        this.description = description;
        return this;
    }

    @PostPersist
    private void updateTime() {
        this.uploadedAt = LocalDateTime.now();
    }
}
