package ua.lemekh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lemekh.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer> {

}
