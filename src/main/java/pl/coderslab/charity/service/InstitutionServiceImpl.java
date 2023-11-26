package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repositories.InstitutionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }


    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Long countAll() {
        return institutionRepository.count();
    }

    @Override
    public Optional<Institution> findById(Long id) {
        return institutionRepository.findById(id);
    }

    @Override
    public void save(Institution institution) {
        if (institution.getId() != null) {
            Optional<Institution> existingInstitution = institutionRepository.findById(institution.getId());
            if (existingInstitution.isPresent()) {
                Institution oldInstitution = existingInstitution.get();
                oldInstitution.setName(institution.getName());
                oldInstitution.setDescription(institution.getDescription());
                institutionRepository.save(oldInstitution);
            }
        } else {
            institutionRepository.save(institution);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Institution> institution = institutionRepository.findById(id);
        if (institution.isPresent()) {
            institutionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
