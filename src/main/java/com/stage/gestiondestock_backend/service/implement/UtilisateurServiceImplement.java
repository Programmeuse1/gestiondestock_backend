package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.Dto.UtilisateurDto;
import com.stage.gestiondestock_backend.Validator.UtilisateurValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Utilisateur;
import com.stage.gestiondestock_backend.repository.UtilisateurRepository;
import com.stage.gestiondestock_backend.service.UtilisateurService;
import com.stage.gestiondestock_backend.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImplement implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImplement(
        UtilisateurRepository utilisateurRepository
    ){
        this.utilisateurRepository=utilisateurRepository;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        System.out.println("\nutilisateur dto: "+dto.toString()+"\n");
        List<String> errors = UtilisateurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Utilisateur is not valid{}", dto);
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }
        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(
                        UtilisateurDto.toEntity(dto)
                )
        );
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null){
            log.error("Utilisateur ID is null");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public UtilisateurDto findByNom(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Utilisateur NOM is null");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByNom(nom);

        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun utilisateur avec le NOM = "+ nom + " n' ete trouve dans la bd",
                        ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Utilisateur ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);

    }

    @Override
    public  UtilisateurDto findByEmail(String email){
        return utilisateurRepository.findUtilisateurByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email =" + email + "n'a ete trouve dans la BD",
                ErrorCodes.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public UtilisateurDto currentUser() {
        Optional<Utilisateur> currentUser = utilisateurRepository.findByEmail(JwtUtil.getCurrentUserLogin());
        return currentUser.map(UtilisateurDto::fromEntity).orElse(null);
    }

}
