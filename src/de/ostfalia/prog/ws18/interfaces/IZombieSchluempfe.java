package de.ostfalia.prog.ws18.interfaces;

import de.ostfalia.prog.ws18.enums.Farbe;
import de.ostfalia.prog.ws18.enums.Richtung;

/** … */
public interface IZombieSchluempfe {

	/**
	 * Bewegt die Figur mit dem angegebenen Namen um die gewürfelte Augenzahl
	 * weiter. ...
	 */
	public boolean bewegeFigur(String figurName, int augenzahl);

	public boolean bewegeFigur(String figurName, int augenzahl, Richtung richtung);

	/**
	 * Liefert die Position (die Feldnummer) der Figur mit dem angegebenen Namen...
	 */
	public int getFeldnummer(String name);

	/**
	 * Ermittelt, ob die Figur mit dem angegebenen Namen ein Zombie ist oder
	 * nicht...
	 */
	public boolean istZombie(String name);

	/**
	 * Methode liefert die Farbe des Spielers der an der Reihe ist und aktuell
	 * spielen darf...
	 */
	public Farbe getFarbeAmZug();

	/** Liefert die Farbe des Spielers, der das Spiel gewonnen hat... */
	public Farbe gewinner();

	/** Methode liefert eine String-Repräsentation des Spiels zurück... */
	@Override
	public String toString();}

