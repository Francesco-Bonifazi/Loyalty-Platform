package it.unicam.ids.loyaltyplatform.premio.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremioPunti extends Premio{

    private int punti;

}
