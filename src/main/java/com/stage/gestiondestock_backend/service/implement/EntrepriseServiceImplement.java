package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.Dto.EntrepriseDto;
import com.stage.gestiondestock_backend.Dto.RolesDto;
import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import com.stage.gestiondestock_backend.Validator.EntrepriseValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Entreprise;
import com.stage.gestiondestock_backend.repository.EntrepriseRepository;
import com.stage.gestiondestock_backend.repository.RolesRepository;
import com.stage.gestiondestock_backend.service.EntrepriseService;
import com.stage.gestiondestock_backend.service.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImplement implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RolesRepository rolesRepository;
    @Autowired
    public EntrepriseServiceImplement(
            EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService, RolesRepository rolesRepository
    ) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        System.out.println("\nentreprise dto: " + dto.toString() + "\n");
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error(" Entreprise is not valid{}", dto);
            throw new InvalidEntityException("l'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(EntrepriseDto.toEntity(dto))
        );
        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

        UtilisateurDto savedUser = utilisateurService.save(utilisateur);

        RolesDto rolesDto = RolesDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(RolesDto.toEntity(rolesDto));
        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateDeNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

        private String generateRandomPassword ()  {

            return "son3R@ndOmP@$$word";
        }


    @Override
    public EntrepriseDto findById(Long id) {
        if(id == null){
            log.error("Entreprise ID is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);

        return  Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow( ()->
                new EntityNotFoundException(
                        "Aucune entrprise avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
                );

    }

    @Override
    public EntrepriseDto findByNom(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Entreprise NOM is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findEntrepriseByNom(nom);

        return  Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow( ()->
                new EntityNotFoundException(
                        "Aucune entrprise avec le NOM = "+ nom + " n' ete trouve dans la bd",
                        ErrorCodes.ENTREPRISE_NOT_FOUND)
        );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Entreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);

    }
}
