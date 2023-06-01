package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.Dto.CommandeFournisseurDto;
import com.stage.gestiondestock_backend.Dto.LigneCommandeFournisseurDto;
import com.stage.gestiondestock_backend.Validator.CommandeClientValidator;
import com.stage.gestiondestock_backend.Validator.CommandeFournisseurValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.model.CommandeFournisseur;
import com.stage.gestiondestock_backend.model.Fournisseur;
import com.stage.gestiondestock_backend.model.LigneCommandeFournisseur;
import com.stage.gestiondestock_backend.repository.ArticleRepository;
import com.stage.gestiondestock_backend.repository.CommandeFournisseurRepository;
import com.stage.gestiondestock_backend.repository.FournisseurRepository;
import com.stage.gestiondestock_backend.repository.LigneCommandeFournisseurRepository;
import com.stage.gestiondestock_backend.service.CommandeFournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImplement implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFournisseurRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    public CommandeFournisseurServiceImplement(CommandeFournisseurRepository commandeFournisseurRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, FournisseurRepository fournisseurRepository, ArticleRepository articleRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto dto) {
        List<String> errors = CommandeFournisseurValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur = fournisseurRepository.findById((dto.getFournisseur().getId()));
        if(fournisseur.isEmpty()){
            log.warn("Client with ID {} was not found in the BD", dto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getFournisseur().getId() + "n'a ete trouve dans la BD", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneCommandeFournisseurs()!= null){

            dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs ->{
                if(ligCmdFrs.getArticle()!= null){
                    Optional<Article> article = articleRepository.findById(ligCmdFrs.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID" + ligCmdFrs.getArticle().getId()+ "n'existe pas");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors );
        }

        CommandeFournisseur savedCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));

        if(dto.getLigneCommandeFournisseurs()!= null){
            dto.getLigneCommandeFournisseurs().forEach(ligCmdFrs ->{
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligCmdFrs);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrs);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(savedCmdFrs);
    }

    @Override
    public CommandeFournisseurDto findById(Long id) {
        if(id == null){
            log.error("Commande fournisseur ID is NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a ete trouve avec l'ID" +id, ErrorCodes.COMMANDE_FOURNISSSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) {
       if(!StringUtils.hasLength(code)) {
           log.error("Commande fournisseur CODE is NULL");
           return null;
       }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande fournisseur n'a ete trouve avec le CODE" +code, ErrorCodes.COMMANDE_FOURNISSSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            log.error("Commande fournisseur ID is NULL");
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
