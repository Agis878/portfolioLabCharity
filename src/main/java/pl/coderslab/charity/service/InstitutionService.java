package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Institution;

import java.util.List;
import java.util.Optional;

public interface InstitutionService {

    List<Institution> findAll();

    Long countAll();

    Optional<Institution> findById(Long id);

    void save(Institution institution);

    boolean deleteById(Long id);
}
