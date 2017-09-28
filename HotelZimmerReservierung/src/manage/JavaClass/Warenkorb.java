package manage.JavaClass;

import java.util.ArrayList;
import java.util.Iterator;


public class Warenkorb {

//	-Speicherung der gew�hlten Artikel
//	-Methoden zum Erstellen des Warenkorbs
//	-Methoden zum Hinzuf�gen und L�schen von Artikel
//	-Methoden zum Berechnen des Gesamtpreises aller Artikel
//	-Methode zur Angabe der Gesamtanzahl der Artikel in einem Warenkorb
	
	Zimmer zimmer;
	
//	private static final long serialVersionUID = 1L; 
	private ArrayList<Zimmer>  zimmerArrayList; 
	private boolean empty;

	/* Leeren Warenkorb erstellen. */ 
	 public Warenkorb() { 
	   zimmerArrayList = new ArrayList<Zimmer>(); 
	 } 
	 
	 /* F�gt ein Zimmer in den Warenkorb ein. */ 
	 public void addZimmer(Zimmer zimmer) {	 
		  zimmerArrayList.add(zimmer);
	 }
	 
	 /* F�gt mehrere Zimmer in den Warenkorb ein. */ 
	 public void addManyZimmer(ArrayList<Zimmer> zimmerList) {
		 Iterator<Zimmer> iter = zimmerList.iterator();
		 while (iter.hasNext()) {
			Zimmer zimmer = (Zimmer) iter.next();
			 this.addZimmer(zimmer);			
		}		 
	 }
	 
	 /*Liefert die Zimmermenge des Warenkorbs zur�ck.*/ 
	 public int getZimmerMenge() { 
		 int anzehalZimmer = 0;
		 ArrayList<Zimmer> ArrayList = this.getZimmerArrayList();
		 anzehalZimmer = ArrayList.size();
		 return anzehalZimmer;  
	 } 
	 
	 /*Gibt den Total-Preis f�r einen Warenkorb zur�ck.*/ 
	 public double getGesamtPreis() { 
	  double gesamtpreis = 0;
	  ArrayList<Zimmer> ArrayList = this.getZimmerArrayList();
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
	 
	 /*L�scht den kompletten Warenkorb.*/ 
	 public void leeren() { 
	  zimmerArrayList.clear(); 
	 } 
	 
	 /*Pr�ft, ob Warenkorb leer oder nicht.*/ 
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
	 
	 public ArrayList<Zimmer> getZimmerArrayList() {
		return  zimmerArrayList;
	}

	public void setZimmerArrayList(ArrayList<Zimmer> zimmerArrayList) {
		this.zimmerArrayList = zimmerArrayList;
	}
	 
}
