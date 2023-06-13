package com.stage.gestiondestock_backend.bean;

import com.stage.gestiondestock_backend.dto.CommandeClientDto;
import com.stage.gestiondestock_backend.dto.LigneCommandeClientDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommandeClientUpdate {
    private CommandeClientDto commandeClientDto;
    private List<LigneCommandeClientDto> ligneCommandeClientDtoList;
}
