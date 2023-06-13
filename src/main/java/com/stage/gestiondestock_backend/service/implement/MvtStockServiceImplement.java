package com.stage.gestiondestock_backend.service.implement;

import com.google.common.base.Strings;
import com.stage.gestiondestock_backend.dto.MvtStockDto;
import com.stage.gestiondestock_backend.Validator.MvtStockValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.MvtStock;
import com.stage.gestiondestock_backend.repository.MvtStockRepository;
import com.stage.gestiondestock_backend.repository.specification.MvtStockSpecification;
import com.stage.gestiondestock_backend.service.MvtStockService;
import com.stage.gestiondestock_backend.service.criteria.MvtStockCriteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MvtStockServiceImplement implements MvtStockService {

    private MvtStockRepository mvtStockRepository;
    @Autowired
    public MvtStockServiceImplement(
           MvtStockRepository mvtStockRepository
    ){
        this.mvtStockRepository= mvtStockRepository;
    }

    @Override
    public MvtStockDto save(MvtStockDto dto) {
        System.out.println("\nmvtStock dto: "+dto.toString()+"\n");
        List<String> errors = MvtStockValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("MvtStock is not valid{}", dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.MVTSTOCK_NOT_VALID, errors);
        }
        return MvtStockDto.fromEntity(
                mvtStockRepository.save(
                        MvtStockDto.toEntity(dto)
                )
        );
    }

    @Override
    public MvtStockDto findById(Long id) {
        if (id == null){
            log.error("Mvtstock ID is null");
            return null;
        }
       Optional<MvtStock> mvtStock = mvtStockRepository.findById(id);

        return Optional.of(MvtStockDto.fromEntity(mvtStock.get())).orElseThrow(()->
        new EntityNotFoundException(
                "Aucun mouvement de stock avec l'ID =" + id + "n'a ete trouve dans la BD",
                ErrorCodes.MVTSTOCK_NOT_FOUND)
        );
    }

    @Override
    public List<MvtStockDto> findAll() {
        return mvtStockRepository.findAll().stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<MvtStockDto> listingMvtStock(MvtStockCriteria mvtStockCriteria) {

        return mvtStockRepository.findAll(MvtStockSpecification.getMvtStock(mvtStockCriteria),
                Strings.isNullOrEmpty(mvtStockCriteria.getNombreDeResultat()) ? Pageable.unpaged() :
                        PageRequest.of(0, Integer.parseInt(mvtStockCriteria.getNombreDeResultat()))).getContent().stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("MvtStock ID is null");
            return;
        }
        mvtStockRepository.deleteById(id);
    }
}
