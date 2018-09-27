package de.ostfalia.prog.ws18;

import java.util.ArrayList;
import java.util.List;

import de.ostfalia.prog.ws18.enums.Buchstabe;
import de.ostfalia.prog.ws18.enums.Farbe;
import de.ostfalia.prog.ws18.enums.Richtung;
import de.ostfalia.prog.ws18.interfaces.IZombieSchluempfe;

public class ZombieSchluempfe implements IZombieSchluempfe {
	Feld[] spielfeld = new Feld[37];
	List<SpielFigur> figuren = new ArrayList<>();
	Feld oberSchlumpf = new OberSchlumpf();
	SpielFigur fliege = new Fliege();

	/**
	 * Konstruktor mit einer Liste von Farben *
	 * 
	 * @param farben
	 *            Liste, aus der sich sowohl die Anzahl der Spieler als auch die
	 *            Reihenfolge der Spieler ergibt.
	 */
	public ZombieSchluempfe(Farbe... farben) {
		for (Farbe farbe : farben) {
			SpielFigur spielfigur = new SpielFigur(farbe, Buchstabe.A);
			figuren.add(spielfigur);
		}
		figuren.add(fliege);
	}

	/**
	 * * Konstruktor mit einer Anfangskonfiguration der Figuren und einer Liste von
	 * Farben, aus der sich sowohl die Anzahl der Spieler als auch die Reihenfolge
	 * der Spieler ergibt. * @param konfiguration Beispiel einer
	 * Anfangskonfiguration: * "BLAU-A:0, BLAU-B:0, BLAU-C:0, BLAU-D:0, Bzz:4,
	 * Doc:6" * * @param farben Liste, aus der sich sowohl die Anzahl der Spieler
	 * als auch die Reihenfolge der Spieler ergibt.
	 */
	public ZombieSchluempfe(String konfiguration, Farbe... farben) {
		// "BLAU-A:0, BLAU-B:0, BLAU-C:0, BLAU-D:0, Bzz:4, Doc:6",Farbe.BLAU

		String[] konf = konfiguration.split(", ");
		for (int i = 0; i < konf.length; i++) {
			String[] aufgeteiltesKonf;
			String[] aufgeteiltesKonf2;
			aufgeteiltesKonf = konf[i].split(":");
			aufgeteiltesKonf2 = aufgeteiltesKonf[0].split("-");
			String farbenString = aufgeteiltesKonf2[0];
			Farbe farbe = null;
			int position = Integer.valueOf((aufgeteiltesKonf[1]));
			if (farbenString.equals("Bzz")) {
				fliege.setPosition(position);
				figuren.add(fliege);
			}else if(farbenString.equals("Doc")){
				oberSchlumpf.setPosition(position);
			} else {
				Buchstabe buchstabe = Buchstabe.valueOf(aufgeteiltesKonf2[1]);
				farbe = Farbe.valueOf(farbenString);
				SpielFigur figur = new SpielFigur(farbe, buchstabe, position);
				figuren.add(figur);
			}

		}
	}

	@Override
	public boolean bewegeFigur(String figurName, int augenzahl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bewegeFigur(String figurName, int augenzahl, Richtung richtung) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFeldnummer(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean istZombie(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Farbe getFarbeAmZug() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Farbe gewinner() {
		// TODO Auto-generated method stub
		return null;
	}
}
