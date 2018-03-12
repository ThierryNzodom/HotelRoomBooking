package manage.JavaClass;

import manage.JavaClass.Zimmer;
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

public class RechnungToClient {
	
	ArrayList<Zimmer> ziList = new ArrayList<>();
	Double gesamtpreis;
	int anzahlNacht;
		
	//Kopf der Rechnung
	String unternehmen = "HS LU HOTEL";
	String strasse = "Killerstr. 100";
	String ort = "67059 LUdwigshafen";
	
	String rechnungnummer;
	
	//Kundendaten
	String kundenname;
	String k_adresse;
	String k_ort;

	
	public RechnungToClient() {
		super();
	}
	
	public RechnungToClient(String rechnungsnummer, String kundenname, ArrayList<Zimmer> ziList, Double gesamtpreis, String k_adresse, String k_ort, int anzahlNacht) {
		super();
		this.rechnungnummer = rechnungsnummer;
		this.kundenname = kundenname;
		this.ziList = ziList;
		this.gesamtpreis = gesamtpreis;
		this.k_adresse = k_adresse;
		this.k_ort = k_ort;
		this.anzahlNacht = anzahlNacht;
		
		Font head1 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				20, Font.BOLD);
		Font head2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				18, Font.BOLD);
		Font normalText = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				14, Font.NORMAL);
//		Font head2 = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
//				20, Font.BOLD);
		Font betragFont = FontFactory.getFont("Arial", BaseFont.IDENTITY_H,
				12, Font.NORMAL, BaseColor.BLUE);

		Document document = new Document(PageSize.A4.rotate());
	try {
	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(rechnungsnummer+"_"+"Rechnung.pdf"));
	
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
	document.add(new Paragraph("\n"));
	document.add(new Phrase("RechnungsNr. ", head2));
	document.add(new Phrase(rechnungnummer, normalText));
	document.add(new Paragraph("\n"));
	document.add(new Paragraph("\n"));
	document.add(new Paragraph("\n"));
	document.add(new Phrase("Kunde: ", head2));
	document.add(new Phrase(kundenname, normalText));
	document.add(new Paragraph("\n"));
	document.add(new Phrase(k_adresse, head2));
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
	PdfPCell c2 = new PdfPCell(new Phrase("Preis/Nacht", head2));
	c2.setHorizontalAlignment(Element.ALIGN_CENTER);
	PdfPCell c3 = new PdfPCell(new Phrase("Anzahl", head2));
	c3.setHorizontalAlignment(Element.ALIGN_CENTER);
	PdfPCell c4 = new PdfPCell(new Phrase("Preis", head2));
	c4.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);table.addCell(c2);table.addCell(c3);table.addCell(c4);
	table.setHeaderRows(1);
	
//	List zimmerList = new List(List.ORDERED);

	Iterator<Zimmer> iter = ziList.iterator();
	while (iter.hasNext()) {
		Zimmer zimmer = (Zimmer) iter.next();
		String einzelnpreis = String.valueOf(zimmer.getPreis())+ " €";
		Double preisMalNachtanzahl = zimmer.getPreis()*anzahlNacht;
		String preisMultiply = String.valueOf(preisMalNachtanzahl);
		String realPM = preisMultiply+" €";
		c1 = new PdfPCell(new Phrase(zimmer.getzID(), normalText));
		c2 = new PdfPCell(new Phrase(einzelnpreis, normalText));
		c3 = new PdfPCell(new Phrase("1", normalText));
		c4 = new PdfPCell(new Phrase(einzelnpreis, normalText));
		table.addCell(c1);table.addCell(c2);table.addCell(c3);table.addCell(c4);
	}
	
	c1 = new PdfPCell(new Phrase("", normalText));
	c2 = new PdfPCell(new Phrase("", normalText));
	c3 = new PdfPCell(new Phrase("RECHNUNG BETRAG", betragFont));
	c3.setHorizontalAlignment(Element.ALIGN_CENTER);
	c4 = new PdfPCell(new Phrase(gesamtpreis + " €", betragFont));
	c4.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(c1);table.addCell(c2);table.addCell(c3);table.addCell(c4);
	
	//Fügt Tabelle im Dokument
	document.add(table);
	document.close();
	writer.close();
		} 
		catch (DocumentException ex) {
			ex.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public int getAnzahlNacht() {
		return anzahlNacht;
	}
	public void setAnzahlNacht(int anzahlNacht) {
		this.anzahlNacht = anzahlNacht;
	}

	public ArrayList<Zimmer> getZiList() {
		return ziList;
	}
	public void setZiList(ArrayList<Zimmer> ziList) {
		this.ziList = ziList;
	}
	public Double getGesamtpreis() {
		return gesamtpreis;
	}
	public void setGesamtpreis(Double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}
	public String getK_adresse() {
		return k_adresse;
	}
	public void setK_adresse(String k_adresse) {
		this.k_adresse = k_adresse;
	}
	public String getK_ort() {
		return k_ort;
	}
	public void setK_ort(String k_ort) {
		this.k_ort = k_ort;
	}
	public String getKundenname() {
		return kundenname;
	}
	public void setKundenname(String kundenname) {
		this.kundenname = kundenname;
	}
	
}
