package com.stage.gestiondestock_backend.Dto;

import com.stage.gestiondestock_backend.model.Ventes;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class VentesDto {

    private Long id;

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
                //.id(ventes.getId())
                .code(ventes.getCode())
                .datevente(ventes.getDateVente())
                .observation(ventes.getObservation())
                .build();
    }

    public static Ventes toEntity(VentesDto ventesDto) {
        if ( ventesDto == null) {
            return null;
            //TODO throw exception
        }
        return Ventes.builder()
                .code(ventesDto.getCode())
                .dateVente(ventesDto.getDatevente())
                .observation(ventesDto.getObservation())
                .build();
    }
}
