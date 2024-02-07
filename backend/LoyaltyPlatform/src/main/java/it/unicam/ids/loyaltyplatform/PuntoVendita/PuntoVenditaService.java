package it.unicam.ids.loyaltyplatform.puntovendita;

import it.unicam.ids.loyaltyplatform.pf.models.ProgrammaFedelta;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PuntoVenditaService {
    private final PuntoVenditaRepository puntoVenditaRepository;

    public PuntoVendita findPuntoVendita(int id){
        PuntoVendita puntoVendita = puntoVenditaRepository.findPuntoVenditaById(id);
        if(puntoVendita == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita with ID: "+id);
        } else {
            return puntoVendita;
        }
    }

    public PuntoVendita findPuntoVendita(String nome){
        PuntoVendita puntoVendita = puntoVenditaRepository.findPuntoVenditaByNome(nome);
        if(puntoVendita == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita with Nome: "+nome);
        } else {
            return puntoVendita;
        }
    }

    public PuntoVendita findPuntoVendita(PuntoVendita puntoVendita){
        PuntoVendita pv = puntoVenditaRepository.findPuntoVenditaById(puntoVendita.getId());
        if(pv == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita: "+puntoVendita);
        } else {
            return pv;
        }
    }

    public PuntoVendita addPuntoVendita(PuntoVendita puntoVendita){
        if(puntoVenditaRepository.findPuntoVenditaById(puntoVendita.getId()) == null){
            return puntoVenditaRepository.save(puntoVendita);
        } else {
            throw new IllegalArgumentException("Punto Vendita: "+puntoVendita+" already exists!");
        }
    }

    public PuntoVendita updatePuntoVendita(PuntoVendita puntoVendita){
        if(puntoVenditaRepository.findPuntoVenditaById(puntoVendita.getId()) != null){
            return puntoVenditaRepository.save(puntoVendita);
        } else {
            throw new IllegalArgumentException("Couldn't find Punto Vendita ID: "+puntoVendita.getId()+"!");
        }
    }

    public List<ProgrammaFedelta> findProgrammiAttivi(int id){
        PuntoVendita puntoVendita = puntoVenditaRepository.findPuntoVenditaById(id);
        if(puntoVendita == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita with ID: "+id);
        } else {
            return puntoVendita.getProgrammiFedelta();
        }
    }

    public List<ProgrammaFedelta> findProgrammiAttivi(PuntoVendita puntoVendita){
        PuntoVendita pv = puntoVenditaRepository.findPuntoVenditaById(puntoVendita.getId());
        if(pv == null){
            throw new IllegalArgumentException("Couldn't find Punto Vendita with ID: "+puntoVendita.getId());
        } else {
            return pv.getProgrammiFedelta();
        }
    }

}
