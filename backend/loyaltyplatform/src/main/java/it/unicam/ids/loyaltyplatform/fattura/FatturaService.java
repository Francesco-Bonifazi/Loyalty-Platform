package it.unicam.ids.loyaltyplatform.fattura;

import it.unicam.ids.loyaltyplatform.fattura.models.Fattura;
import it.unicam.ids.loyaltyplatform.puntovendita.models.PuntoVendita;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FatturaService {

    private final FatturaRepository fatturaRepository;


    public ByteArrayInputStream getFatturaPDF(int fattura_id) {
        Fattura fattura = findFattura(fattura_id);
        PdfGenerator pdfGenerator = new PdfGenerator();
        return pdfGenerator.generatePdf(List.of(fattura));
    }

    public ByteArrayInputStream getFatturaPDF(int fattura_id, PuntoVendita puntoVendita) {
        Fattura fattura = findFattura(fattura_id, puntoVendita);
        PdfGenerator pdfGenerator = new PdfGenerator();
        return pdfGenerator.generatePdf(List.of(fattura));
    }

    public ByteArrayInputStream getFatturaPDF(PuntoVendita puntoVendita) {
        List<Fattura> fatture = findFattura(puntoVendita);
        PdfGenerator pdfGenerator = new PdfGenerator();
        return pdfGenerator.generatePdf(fatture);
    }

    public Fattura findFattura(int fattura_id){
        Fattura fattura = fatturaRepository.findFatturaByFatturaId(fattura_id);
        if(fattura == null){
            throw new IllegalArgumentException("Couldn't find Fattura with ID: "+fattura_id+"!");
        } else {
            return fattura;
        }
    }

    public Fattura findFattura(int fattura_id, PuntoVendita puntoVendita){
        Fattura fattura = fatturaRepository.findFatturaByFatturaIdAndPuntoVendita(fattura_id, puntoVendita);
        if(fattura == null){
            throw new IllegalArgumentException("Couldn't find Fattura with ID: "+fattura_id+"!");
        } else {
            return fattura;
        }
    }

    public List<Fattura> findFattura(PuntoVendita puntoVendita){
        List<Fattura> fatture = fatturaRepository.findAllByPuntoVendita(puntoVendita);
        if(fatture.isEmpty()){
            throw new IllegalArgumentException("Couldn't find any Fatture for Punto Vendita: "+puntoVendita+"!");
        } else {
            return fatture;
        }
    }

    public Fattura addFattura(Fattura fattura) {
        if(fatturaRepository.findFatturaByFatturaId(fattura.getFatturaId()) == null){
            return fatturaRepository.save(fattura);
        } else {
            throw new IllegalArgumentException("Fattura already exists!");
        }
    }

    @Transactional
    public void removeFattura(Fattura fattura){
        Fattura f = fatturaRepository.findFatturaByFatturaId(fattura.getFatturaId());

        if(f != null){
            fatturaRepository.deleteFatturaByFatturaId(fattura.getFatturaId());
        } else {
            throw new IllegalArgumentException("Fattura with ID: "+fattura.getFatturaId()+" doesn't exist!");
        }
    }

    @Transactional
    public void removeFattura(int fattura_id){
        Fattura f = fatturaRepository.findFatturaByFatturaId(fattura_id);

        if(f != null){
            fatturaRepository.deleteFatturaByFatturaId(fattura_id);
        } else {
            throw new IllegalArgumentException("Fattura with ID: "+fattura_id+" doesn't exist!");
        }
    }

}
