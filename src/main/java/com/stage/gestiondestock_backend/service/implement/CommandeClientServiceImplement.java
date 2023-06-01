package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.Dto.CommandeClientDto;
import com.stage.gestiondestock_backend.Dto.LigneCommandeClientDto;
import com.stage.gestiondestock_backend.Validator.CommandeClientValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.model.Client;
import com.stage.gestiondestock_backend.model.CommandeClient;
import com.stage.gestiondestock_backend.model.LigneCommandeClient;
import com.stage.gestiondestock_backend.repository.ArticleRepository;
import com.stage.gestiondestock_backend.repository.ClientRepository;
import com.stage.gestiondestock_backend.repository.CommandeClientRepository;
import com.stage.gestiondestock_backend.repository.LigneCommandeClientRepository;
import com.stage.gestiondestock_backend.service.CommandeClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImplement implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImplement(CommandeClientRepository commandeClientRepository, LigneCommandeClientRepository ligneCommandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {
        List<String> errors = CommandeClientValidator.validate(dto);

        if(!errors.isEmpty()) {
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }

        Optional<Client> client = clientRepository.findById((dto.getClient().getId()));
        if(client.isEmpty()){
            log.warn("Client with ID {} was not found in the BD", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() + "n'a ete trouve dans la BD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneCommandeClients()!= null){
            dto.getLigneCommandeClients().forEach(ligCmdClt ->{
                if(ligCmdClt.getArticle()!= null){
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if(article.isEmpty()){
                        articleErrors.add("L'article avec l'ID" + ligCmdClt.getArticle().getId()+ "n'existe pas");
                    }
                }
            });
        }

        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BD",ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

        if(dto.getLigneCommandeClients()!= null){
            dto.getLigneCommandeClients().forEach(ligCmdClt ->{
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
        });
        }
        return CommandeClientDto.fromEntity(savedCmdClt);
}

    @Override
    public CommandeClientDto findById(Long id) {
       if(id == null){
           log.error("Commande client ID is NULL");
           return null;
       }
       return commandeClientRepository.findById(id)
               .map(CommandeClientDto::fromEntity)
               .orElseThrow(() -> new EntityNotFoundException(
                       "Aucune commande client n'a ete trouve avec l'ID" + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
               ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande client CODE is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a ete trouve avec le CODE" + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
       return commandeClientRepository.findAll().stream()
               .map(CommandeClientDto::fromEntity)
               .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            log.error("Commande client ID is NULL");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
