package manage.JavaClass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFtoClient {
	
	public static void main(String[] args) {
		
		Warenkorb wk = new Warenkorb();
		ArrayList<Zimmer> ziArrList = new ArrayList<>();
		
		Zimmer zi1 = new Zimmer();
		zi1.setzID("zimm10");
		zi1.setPreis(10.5);
		String p1 = "1";
		
		Zimmer zi2 = new Zimmer();
		zi2.setzID("zimm20");
		zi2.setPreis(20.5);
//		String p2 = "2";
		
		Zimmer zi3 = new Zimmer();
		zi3.setzID("zimm30");
		zi3.setPreis(30.5);
//		String p3 = "3";
		
		
		ziArrList.add(zi1);ziArrList.add(zi2);ziArrList.add(zi3);
		wk.setZimmerArrayList(ziArrList);
		float gesamtbetrag = (float) wk.getGesamtPreis();
//		float gesamtpreis = zimmer.getPreis()* p2;
		
		System.out.println(gesamtbetrag);
		
		//Kopf der Rechnung
		String unternehmen = "WImaster_HOTEL";
		String strasse = "Killerstr.100";
		String ort = "67059 Ludwigshafen";
		String rechnungnummer = "RCH045";
		String kunde = "Mandfred Müller";
		
		String k_strasse = "Geilestr.100";
		String k_ort = "67061 LUdwigshafen";
		
		Font head1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				16, Font.BOLD);
		Font head2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				14, Font.BOLD);
		Font normalText = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				12, Font.NORMAL);
//		Font head2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
//				20, Font.BOLD);
		Font betragFont = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				10, Font.BOLD, BaseColor.BLUE);

		Document document = new Document(PageSize.A4_LANDSCAPE);
	try {
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Rechnung.pdf"));
	
	document.open();
	
	document.addTitle("Kundenrechnung");
	document.addSubject("Hotelzimmer Reservierung");
	document.addAuthor("Fresh Montana/Tity Zozur");	
	
	document.add(new Phrase(unternehmen, head1));
	document.add(new Paragraph("\n"));
	document.add(new Phrase(strasse, head2));
	document.add(new Paragraph("\n"));
	document.add(new Phrase(ort, head2));
	document.add(new Paragraph("\n"));
	document.add(new Paragraph("\n"));
	document.add(new Phrase("RechnungsNr. ", head2));
	document.add(new Phrase(rechnungnummer, normalText));
	document.add(new Paragraph("\n"));
	document.add(new Paragraph("\n"));
	document.add(new Phrase("Kunde: ", head2));
	document.add(new Phrase(kunde, normalText));
	document.add(new Paragraph("\n"));
	document.add(new Phrase(k_strasse, head2));
	document.add(new Paragraph("\n"));
	document.add(new Phrase(k_ort, head2));
	document.add(new Paragraph("\n"));
	document.add(new Paragraph("\n"));
	
	PdfPTable table = new PdfPTable(4);
	
	table.setWidthPercentage(75);
	table.setSpacingBefore(11f);
	table.setSpacingAfter(11f);
			
	float[] colWidth = {2f ,2f ,2f ,2f};
	table.setWidths(colWidth);
	
	PdfPCell c1 = new PdfPCell(new Phrase("ZimmerNr.", head2));
	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	PdfPCell c2 = new PdfPCell(new Phrase("Preis/Tag", head2));
	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	PdfPCell c3 = new PdfPCell(new Phrase("Tag(e)", head2));
	c3.setHorizontalAlignment(Element.ALIGN_CENTER);
	PdfPCell c4 = new PdfPCell(new Phrase("Gesamtpreis", head2));
	c4.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);table.addCell(c2);table.addCell(c3);table.addCell(c4);
	table.setHeaderRows(1);
	
//	List zimmerList = new List(List.ORDERED);

	Iterator<Zimmer> iter = ziArrList.iterator();
	while (iter.hasNext()) {
		Zimmer zimmer = (Zimmer) iter.next();
		c1 = new PdfPCell(new Phrase(zimmer.getzID(), normalText));
		c2 = new PdfPCell(new Phrase(zimmer.getPreis()+ " €", normalText));
		if(zimmer.getzID().contains("10")){
			c3 = new PdfPCell(new Phrase(p1));
			c4 = new PdfPCell(new Phrase( zi1.getPreis()*1 + " €", normalText));
		} else if (zimmer.getzID().contains("20")) {
			c3 = new PdfPCell(new Phrase(p1));
			c4 = new PdfPCell(new Phrase( zi2.getPreis()*1 + " €", normalText));
		} else {
			c3 = new PdfPCell(new Phrase(p1));
			c4 = new PdfPCell(new Phrase(zi3.getPreis()*1 + " €", normalText));
		}		
//		c3 = new PdfPCell(new Phrase(p2));
//		c4 = new PdfPCell(new Phrase( zi3.getPreis()*p3 + " €", normalText));
		table.addCell(c1);table.addCell(c2);table.addCell(c3);table.addCell(c4);		
	}
	
	c1 = new PdfPCell(new Phrase("", normalText));
	c2 = new PdfPCell(new Phrase("", normalText));
	c3 = new PdfPCell(new Phrase("RECHNUNG BETRAG", betragFont));
	c3.setHorizontalAlignment(Element.ALIGN_CENTER);
	c4 = new PdfPCell(new Phrase(gesamtbetrag + " €", betragFont));
	c4.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);table.addCell(c2);table.addCell(c3);table.addCell(c4);
	
	//Fügt Tabelle im Dokument
	document.add(table);
	document.close();
	writer.close();
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
