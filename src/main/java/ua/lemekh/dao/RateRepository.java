package ua.lemekh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lemekh.model.Publication;
import ua.lemekh.model.Rate;
import ua.lemekh.model.User;

public interface RateRepository extends JpaRepository<Rate,Integer> {

    Rate findByPublicationAndRatedBy(Publication publication, User user);
}
