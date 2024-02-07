package it.unicam.ids.loyaltyplatform.fattura;


import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import it.unicam.ids.loyaltyplatform.fattura.models.Fattura;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfGenerator {

    public ByteArrayInputStream generatePdf(List<Fattura> fatture){

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontTiltle.setSize(20);

            Paragraph paragraph1 = new Paragraph("List of Fatture", fontTiltle);
            paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph1);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100f);
            table.setWidths(new int[] {3,3,3,3});
            table.setSpacingBefore(5);
            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(CMYKColor.BLUE);
            cell.setPadding(5);
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            font.setColor(CMYKColor.WHITE);

            cell.setPhrase(new Phrase("ID", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Data emissione", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Data scadenza", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Saldo", font));
            table.addCell(cell);

            for(Fattura fattura : fatture){
                table.addCell(String.valueOf(fattura.getFatturaId()));
                table.addCell(String.valueOf(fattura.getData_emissione()));
                table.addCell(String.valueOf(fattura.getData_scadenza()));
                table.addCell(String.valueOf(fattura.getSaldo()));
            }

            document.add(table);
            document.close();


        } catch (DocumentException e){
            throw new IllegalArgumentException("Error in generating PDF!");
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
