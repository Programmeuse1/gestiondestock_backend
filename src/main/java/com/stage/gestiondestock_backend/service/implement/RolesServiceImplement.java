package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.Dto.RolesDto;
import com.stage.gestiondestock_backend.Validator.RolesValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Roles;
import com.stage.gestiondestock_backend.repository.RolesRepository;
import com.stage.gestiondestock_backend.service.RolesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RolesServiceImplement implements RolesService {

    private RolesRepository rolesRepository;
    @Autowired
    public RolesServiceImplement(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public RolesServiceImplement() {super();}

    @Override
    public RolesDto save(RolesDto dto) {
        System.out.println("\nroles dto: "+dto.toString()+"\n");
        List<String> errors = RolesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Roles is not valid{}", dto);
            throw new InvalidEntityException("Le Role n'est pas valide", ErrorCodes.ROLES_NOT_VALID, errors);
        }
        return RolesDto.fromEntity(
                rolesRepository.save(
                        RolesDto.toEntity(dto)
                )
        );
    }

    @Override
    public RolesDto findById(Long id) {
        if (id == null){
            log.error("Role ID is null");
            return null;
        }
        Optional<Roles> article = rolesRepository.findById(id);

        return Optional.of(RolesDto.fromEntity(article.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun role avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.ROLES_NOT_FOUND)
        );
    }

    @Override
    public RolesDto findByRoleName(String roleName) {
        if (!StringUtils.hasLength(roleName)) {
            log.error("Role ROLENAME is null");
            return null;
        }
        Optional<Roles> roles = rolesRepository.findRolesByRoleName(roleName);

        return Optional.of(RolesDto.fromEntity(roles.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun role avec le CODE = "+ roleName + " n' ete trouve dans la bd",
                        ErrorCodes.ROLES_NOT_FOUND)
        );
    }

    @Override
    public List<RolesDto> findAll() {

        return rolesRepository.findAll().stream()
                .map(RolesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Roles ID is null");
            return;
        }
        rolesRepository.deleteById(id);

    }
}
