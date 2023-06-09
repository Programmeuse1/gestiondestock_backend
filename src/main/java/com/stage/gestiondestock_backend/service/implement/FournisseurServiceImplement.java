package com.stage.gestiondestock_backend.service.implement;

import com.google.common.base.Strings;
import com.stage.gestiondestock_backend.dto.FournisseurDto;
import com.stage.gestiondestock_backend.Validator.FournisseurValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Fournisseur;
import com.stage.gestiondestock_backend.repository.FournisseurRepository;
import com.stage.gestiondestock_backend.repository.specification.FournisseurSpecification;
import com.stage.gestiondestock_backend.service.FournisseurService;
import com.stage.gestiondestock_backend.service.criteria.FournisseurCriteria;
import com.stage.gestiondestock_backend.utils.MethodUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImplement implements FournisseurService {

    private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurServiceImplement(
            FournisseurRepository fournisseurRepository
    ){
        this.fournisseurRepository= fournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        System.out.println("\nfournisseur dto: "+dto.toString()+"\n");
        List<String> errors = FournisseurValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Fournisseur is not valid{}", dto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }

        //Debut de l'enregistrement du code article
        Fournisseur fournisseur1 = fournisseurRepository.save(FournisseurDto.toEntity(dto));
        fournisseur1.setCode(fournisseur1.getCode() == null ? "FRS-1" + MethodUtils.format(fournisseur1.getId().intValue(), 6) : fournisseur1.getCode());
//        return FournisseurDto.fromEntity(fournisseurRepository.save(fournisseur1));
        //Fin de l'enregistrement du code article

        return FournisseurDto.fromEntity(
                fournisseurRepository.save(
                        fournisseur1
                )
        );
    }

    @Override
    public FournisseurDto findById(Long id) {
        if (id == null){
            log.error("Fournisseur ID is null");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);

        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun fournisseur avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public FournisseurDto findByNom(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Fournisseur NOM is null");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findFournisseurByNom(nom);

        return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun fournisseur avec le NOM = "+ nom + " n' ete trouve dans la bd",
                        ErrorCodes.FOURNISSEUR_NOT_FOUND)
        );
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Fournisseur ID is null");
            return;
        }
       fournisseurRepository.deleteById(id);
    }

    @Override
    public List<FournisseurDto> listingFournisseur(FournisseurCriteria fournisseurCriteria) {

        return fournisseurRepository.findAll(FournisseurSpecification.getFournisseur(fournisseurCriteria),
                Strings.isNullOrEmpty(fournisseurCriteria.getNombreDeResultat()) ? Pageable.unpaged() :
                        PageRequest.of(0, Integer.parseInt(fournisseurCriteria.getNombreDeResultat()))).getContent().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

}
