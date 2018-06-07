package ua.lemekh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lemekh.model.PulicationTracker;

public interface PublicationTrackerRepository extends JpaRepository<PulicationTracker, Integer> {
}
