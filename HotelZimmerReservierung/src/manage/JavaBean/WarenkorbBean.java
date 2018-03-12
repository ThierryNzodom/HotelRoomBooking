package manage.JavaBean;

import java.util.ArrayList;
import java.util.Iterator;

import manage.JavaClass.Buchung;
import manage.JavaClass.Zimmer;


public class WarenkorbBean {
	
	Zimmer zimmer;
	Buchung buchung;
	
//	private static final long serialVersionUID = 1L; 
	ArrayList<Zimmer>  zimmerArrayList; 
	private boolean empty;

	/* Leeren Warenkorb erstellen. */ 
	 public WarenkorbBean() { 
	   zimmerArrayList = new ArrayList<Zimmer>(); 
	 } 
	 
	 /* Fügt ein Zimmer in den Warenkorb ein. */ 
	 public void addZimmer(Zimmer zimmer) {	 
		  zimmerArrayList.add(zimmer);
	 }
	 
	 /* Fügt mehrere Zimmer in den Warenkorb ein. */ 
	 public void addManyZimmer(ArrayList<Zimmer> zimmerList) {
		 Iterator<Zimmer> iter = zimmerList.iterator();
		 while (iter.hasNext()) {
			Zimmer zimmer = (Zimmer) iter.next();
			 this.addZimmer(zimmer);			
		}		 
	 }
	 
	 /*Liefert die Zimmermenge des Warenkorbs zurück.*/ 
	 public int getZimmerMenge() { 
		 int anzehalZimmer = 0;
		 ArrayList<Zimmer> ArrayList = this.getZimmerArrayList();
		 anzehalZimmer = ArrayList.size();
		 return anzehalZimmer;  
	 } 
	 
	 /*Gibt den Total-Preis für einen Warenkorb zurück.*/ 
	 public double getGesamtPreis(ArrayList<Zimmer> ArrayList) { 
	  double gesamtpreis = 0;
	  for (Zimmer zimmer : ArrayList) {
		gesamtpreis += zimmer.getPreis();
	}
	  return gesamtpreis; 
	 } 
	
	 /*Entfernt ein Zimmer von Warenkorb.*/ 
	 public void removeZimmer(Zimmer zimmer) {
		 int index = zimmerArrayList.indexOf(zimmer);
		 if(index != -1){
			 zimmerArrayList.remove(index);
		 }else
			 System.out.println(zimmer.toString() + "ist kein Element des ZimmerArrayLists");
	 }
	 
	 /*Entfernt mehrere Zimmer von Warenkorb.*/
	 public void removeManyZimmer(ArrayList<Zimmer> zList) {
		 Iterator<Zimmer> iter = zList.iterator();
		 while (iter.hasNext()) {
			Zimmer zimmer = (Zimmer) iter.next();
			this.removeZimmer(zimmer);			
		}
	 }
	 
	 /*Löscht den kompletten Warenkorb.*/ 
	 public void wwarenkorbLeeren() { 
	  zimmerArrayList.clear(); 
	 } 
	 
	 /*Prüft, ob Warenkorb leer oder nicht.*/ 
	 public boolean isEmpty() { 
	  empty = this.zimmerArrayList.isEmpty(); 
	  return empty; 
	 } 
	  
	  
	 /*Liefert eine Liste der Warenkorbpositionen.*/ 
	 public String getHolePositionUndZimmerAsHtml() { 
	  String posAndZimmerVAlue = "Liste der gebuchten Zimmer mit ihren Positionen: " + "\n";
	  int pos = 1;
	  ArrayList<Zimmer> zArrayList = zimmerArrayList;
	  Iterator<Zimmer> iter = zArrayList.iterator();
	  while (iter.hasNext()) {
		Zimmer zimmer = (Zimmer) iter.next();
		String zimmerID = zimmer.getzID();
		int zimmerPos = pos;
		String ausgabeListe = "Position:" + zimmerPos + " ZimmerID:" + zimmerID + " ";
		posAndZimmerVAlue += ausgabeListe;
		pos++;
	}	  
	  return posAndZimmerVAlue;
	 }
	 
	 //ANgzeige freie Zimmer als HTML with Ankunft und ABfahrt .
	public String getFreieRaeumeWKAsHtml(){
			String html = "\n";					
				ArrayList<Zimmer> myList = this.getZimmerArrayList();
				for (Zimmer zimmerfrei : myList) {
					html += zimmerfrei.getWKZimmeralsHtml(buchung.getZeit_von(), buchung.getZeit_bis()) + "\n";
				}
//			zimmerArrayList.clear();
//			this.setZimmerArrayList(zimmerArrayList);
			return html;		
	} 
	 //ANgzeige freie Zimmer als HTML ohne Ankunft und ABfahrt .
	public String getFreieZiWKAsHtml(){
			String html = "\n";
			int ez=0;int dz=0;int sz=0;
			Zimmer clz = new Zimmer();
			Zimmer sdz = new Zimmer();
			Zimmer cdz = new Zimmer();
				ArrayList<Zimmer> myList = this.getZimmerArrayList();
				for (Zimmer zimmerfrei : myList) {
					if(zimmerfrei.getzID().contains("CL")){
						clz = zimmerfrei;
						ez = buchung.getZahlE();
					}else if(zimmerfrei.getzID().contains("SD")){
						sdz = zimmerfrei;
						dz = buchung.getZahlD();
					}else{
						zimmerfrei.getzID().contains("CD");
						cdz = zimmerfrei;
						sz = buchung.getZahlS();
					}					
				}
				if(ez!=0){
					String cl = clz.getZahlAndZiAlsHtml(ez) + "\n";
					html = cl;
				}
				if(dz!=0){
					String sd = sdz.getZahlAndZiAlsHtml(dz) + "\n";
					html+=sd;
				}
				if(sz!=0){
					String cd = cdz.getZahlAndZiAlsHtml(sz) + "\n";
					html+=cd;
				}
			return html;		
	}
	 //ANgzeige freie Zimmer als HTML ohne Ankunft und ABfahrt .
	public String getFreieAAAlsHtml(){
			String html = "\n";					
				ArrayList<Zimmer> myList = this.getZimmerArrayList();
				for (Zimmer zimmerfrei : myList) {
					html = zimmerfrei.getAAAlsHtml(buchung.getZeit_von(), buchung.getZeit_bis()) + "\n";
				}
//			zimmerArrayList.clear();
//			this.setZimmerArrayList(zimmerArrayList);
			return html;		
	}
	 public ArrayList<Zimmer> getZimmerArrayList() {
		return  zimmerArrayList;
	}
	public void setZimmerArrayList(ArrayList<Zimmer> zimmerArrayList) {
		this.zimmerArrayList = zimmerArrayList;
	}
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
	public Buchung getBuchung() {
		return buchung;
	}
	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
	}
	
}
