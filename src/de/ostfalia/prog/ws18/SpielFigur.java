package de.ostfalia.prog.ws18;

import de.ostfalia.prog.ws18.enums.Farbe;
import de.ostfalia.prog.ws18.enums.Buchstabe;

public class SpielFigur {

	private Farbe farbe;
	private int position = 0;
	private Buchstabe buchstabe;
	
	public SpielFigur() {
		super();
	}

	public SpielFigur(Farbe farbe, Buchstabe buchstabe) {
		super();
		this.farbe = farbe;
		this.buchstabe = buchstabe;
	}
	
	public SpielFigur(Farbe farbe, Buchstabe buchstabe, int position) {
		super();
		this.farbe = farbe;
		this.buchstabe = buchstabe;
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Farbe getFarbe() {
		return farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public Buchstabe getBuchstabe() {
		return buchstabe;
	}

	public void setBuchstabe(Buchstabe buchstabe) {
		this.buchstabe = buchstabe;
	}
	
	@Override
	public String toString() {
		return "SpielFigur [farbe=" + farbe + ", buchstabe=" + buchstabe + "]";
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpielFigur other = (SpielFigur) obj;
		if (buchstabe != other.buchstabe && farbe != other.farbe)
			return false;
		if(buchstabe == other.buchstabe && farbe == other.farbe)
			return true;
		return true;
	}
}
