package com.stage.gestiondestock_backend.service.implement;

import com.google.common.base.Strings;
import com.stage.gestiondestock_backend.dto.ClientDto;
import com.stage.gestiondestock_backend.Validator.ClientValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Client;
import com.stage.gestiondestock_backend.repository.ClientRepository;
import com.stage.gestiondestock_backend.repository.specification.ClientSpecification;
import com.stage.gestiondestock_backend.service.ClientService;
import com.stage.gestiondestock_backend.service.criteria.ClientCriteria;
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
public class ClientServiceImplement implements ClientService {

    private ClientRepository clientRepository;
    @Autowired
    public ClientServiceImplement(
       ClientRepository clientRepository
    ){
        this.clientRepository= clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        System.out.println("\nclient dto: "+dto.toString()+"\n");
        List<String> errors = ClientValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Client is not valid{}", dto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(dto)
                )
        );
    }

    @Override
    public ClientDto findById(Long id) {
        if (id == null){
            log.error("Client ID is null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun client avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.CLIENT_NOT_FOUND)
                );
    }

    @Override
    public ClientDto findByNom(String nom) {
        if (!StringUtils.hasLength(nom)) {
            log.error("Client NOM is null");
            return null;
        }
        Optional<Client> client = clientRepository.findClientByNom(nom);

        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun client avec le NOM = "+ nom + " n' ete trouve dans la bd",
                        ErrorCodes.CLIENT_NOT_FOUND)
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);

    }

    @Override
    public List<ClientDto> listingClient(ClientCriteria clientCriteria) {

        return clientRepository.findAll(ClientSpecification.getClient(clientCriteria),
                Strings.isNullOrEmpty(clientCriteria.getNombreDeResultat()) ? Pageable.unpaged() :
                        PageRequest.of(0, Integer.parseInt(clientCriteria.getNombreDeResultat()))).getContent().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }
}
