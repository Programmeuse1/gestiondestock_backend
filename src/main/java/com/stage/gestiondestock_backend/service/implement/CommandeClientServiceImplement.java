package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.bean.CommandeClientUpdate;
import com.stage.gestiondestock_backend.dto.CommandeClientDto;
import com.stage.gestiondestock_backend.dto.LigneCommandeClientDto;
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
import com.stage.gestiondestock_backend.utils.MethodUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
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
    public CommandeClientDto save(CommandeClientUpdate dto) {
        List<String> errors = CommandeClientValidator.validate(dto.getCommandeClientDto());

        if(!errors.isEmpty()) {
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID,errors);
        }

        Optional<Client> client = clientRepository.findById((dto.getCommandeClientDto().getClient().getId()));
        if(client.isEmpty()){
            log.warn("Client with ID {} was not found in the BD", dto.getCommandeClientDto().getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getCommandeClientDto().getClient().getId() + "n'a ete trouve dans la BD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto.getCommandeClientDto()));
        savedCmdClt.setCode(savedCmdClt.getCode() == null ? "CMDCLT-" + MethodUtils.format(savedCmdClt.getId().intValue(), 6) : savedCmdClt.getCode());
        commandeClientRepository.save(savedCmdClt);

        if(dto.getLigneCommandeClientDtoList()!= null){
            dto.getLigneCommandeClientDtoList().forEach(ligCmdClt ->{
                if(ligCmdClt.getArticle()!= null){
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        throw new EntityNotFoundException("l'article spécifier n'existe pas en base de donnée");
                    }
                    LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                    Objects.requireNonNull(ligneCommandeClient).setCommandeClient(savedCmdClt);
                    ligneCommandeClient = ligneCommandeClientRepository.save(ligneCommandeClient);
                    ligneCommandeClient.setCode(ligneCommandeClient.getCode() == null ? "CMDCLT-" + MethodUtils.format(ligneCommandeClient.getId().intValue(), 6) : ligneCommandeClient.getCode());
                    ligneCommandeClientRepository.save(ligneCommandeClient);
                }
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
