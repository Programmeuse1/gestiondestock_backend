package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.Ventes;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class VentesDto {

    private Long id;

    private LocalDateTime dateEnregistrement;

    private Long id_Entreprise;

    private String code;

    private Instant datevente;

    private String observation;

    private List<LigneVenteDto> LigneVentes;

    public static VentesDto fromEntity(Ventes ventes){
        if(ventes == null){
            return null;
        }

        return VentesDto.builder()
                .id(ventes.getId())
                .dateEnregistrement(ventes.getDateEnregistrement())
                .code(ventes.getCode())
                .datevente(ventes.getDateVente())
                .observation(ventes.getObservation())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto) {
        if ( ventesDto == null) {
            return null;
        }
        return Ventes.builder()
                .id(ventesDto.getId())
                .dateEnregistrement(ventesDto.getDateEnregistrement())
                .code(ventesDto.getCode())
                .dateVente(ventesDto.getDatevente())
                .observation(ventesDto.getObservation())
                .build();
    }
}
