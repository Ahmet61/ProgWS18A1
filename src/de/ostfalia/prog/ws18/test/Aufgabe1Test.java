package de.ostfalia.prog.ws18.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import de.ostfalia.prog.ws18.ZombieSchluempfe;
import de.ostfalia.prog.ws18.enums.Farbe;
import de.ostfalia.prog.ws18.enums.Richtung;
// import de.ostfalia.prog.ws18.exceptions.FalscheSpielerzahlException;
// import de.ostfalia.prog.ws18.exceptions.UngueltigePositionException;
// import de.ostfalia.prog.ws18.exceptions.WiederholteFarbenException;
import de.ostfalia.prog.ws18.interfaces.IZombieSchluempfe;

/**
 * 
 * @author J. Weimar
 * @since WS 2018/19
 * 
 *         Feldfolge................:
 * 
 *  0-->1-->2-->3-->4-->5-->6-->7-->8-->16-->17-->18-->19-->20-->21-->22
 *      |       |                   |                                   |
 *      |       |                   <-------------                      |           
 *      |       |                                |                      |  
 *      |       --->9-->10-->11-->12-->13-->14-->15                     |
 *      |                                                               |    
 *      |                       -->36 (Dorf)                            |    
 *      |                       |                                       |    
 *      <--35<--34<--33<--32<--31<--30<--29<--28<--27<--26<--25<--24<--23
 * 
 *  Was wird getestet........:
 *      - Existenz von Konstruktor, Enums, Methoden im Interface
   *      
 *  Was wird nicht getestet..:
 * 
 *      - Funktionalität
 * 
 */
public class Aufgabe1Test {

    private boolean quiet = false;

    @Rule
    public TestRule timeout = new DisableOnDebug(new Timeout(500, TimeUnit.MILLISECONDS));

    @Before
    public void setUp() throws Exception {
    }

    /**
     * Konstruktoraufruf mit nur einer Spielerfarbe. Alle Figuren muessen sich
     * auf den festgelgten Feldern befinden.
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     */
    @Test
    public void testKonstruktor() {
       @SuppressWarnings("unused")
       IZombieSchluempfe z = new ZombieSchluempfe(Farbe.BLAU);
    }

    /**
     * Konstruktoraufruf mit zwei Spielerfarben. Alle Figuren muessen sich auf
     * den festgelgten Feldern befinden.
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     * @throws UngueltigePositionException 
     */
    @Test
    public void testKonstruktorZweiSpieler() {
       @SuppressWarnings("unused")
       IZombieSchluempfe z = new ZombieSchluempfe(Farbe.BLAU, Farbe.ROT);
    }

    /**
     * Konstruktoraufruf mit geaenderter Figurenstellung und einer Spielerfarbe.
     * Alle Schluempfe muessen sich auf dem Startfeld befinden. Die Fliege und
     * der Oberschlumpf bekommen neue Felder zugewiesen.
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     * @throws UngueltigePositionException 
     */
    @Test
    public void testKonstruktorString(){
        IZombieSchluempfe z = new ZombieSchluempfe("BLAU-A:0, BLAU-B:0, BLAU-C:0, BLAU-D:0, Bzz:4, Doc:6",
                Farbe.BLAU);
    }

    /**
     * Standard Spielstellung, eine Spielerfarbe. Der Spieler zieht nacheinander
     * seine Figuren auf unterschiedliche Felder.
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     * @throws UngueltigePositionException 
     */
    @Test
    public void testZieheSchluempfe()  {
        IZombieSchluempfe z = new ZombieSchluempfe(Farbe.BLAU);

        z.bewegeFigur("BLAU-A", 1);
    }

    /**
     * Standard Spielstellung, eine Spielerfarbe. Der Spieler zieht eine Figur
     * auf das Verzweigungsfeld 3 und im anschliessenden Zug in Richtung WEITER.
     * Der Spieler zieht eine andere Figur auf das Verzweigungsfeld und im
     * anschliessenden Zug in Richtung ABZWEIGEN.
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     * @throws UngueltigePositionException 
     */
    @Test
    public void testVerzweigung()  {
        IZombieSchluempfe z = new ZombieSchluempfe(Farbe.BLAU);
        z.bewegeFigur("BLAU-A", 3);
        z.bewegeFigur("BLAU-A", 1, Richtung.WEITER);
        z.bewegeFigur("BLAU-B", 3);
        z.bewegeFigur("BLAU-B", 1, Richtung.ABZWEIGEN);
     }

   /**
     * Konstruktoraufruf mit geaenderter Figurenstellung und einer Spielerfarbe.
     * Alle Figuren muessen sich auf den festgelgten Feldern befinden. BLAU
     * gewinnt das Spiel.
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     * @throws UngueltigePositionException 
     */
    @Test
    public void testGewinnerEinfach(){
        IZombieSchluempfe z = new ZombieSchluempfe(
                "BLAU-A:36, BLAU-B:36, BLAU-C:36, BLAU-D:36, Bzz:4, Doc:6",
                Farbe.BLAU);
        z.gewinner();
    }
    
    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     * source: https://stackoverflow.com/questions/520328/can-you-find-all-classes-in-a-package-using-reflection
     * source: https://dzone.com/articles/get-all-classes-within-package
     * 
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
   private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
   private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
    
    /**
     * Konstruktoraufruf mit geaenderter Figurenstellung und einer Spielerfarbe.
     * Alle Figuren muessen sich auf den festgelgten Feldern befinden. BLAU
     * gewinnt das Spiel.
    * @throws IOException 
    * @throws ClassNotFoundException 
     * @throws WiederholteFarbenException 
     * @throws FalscheSpielerzahlException 
     * @throws UngueltigePositionException 
     */
    @SuppressWarnings("rawtypes")
    @Test// (timeout = 1000)
    public void testAnzahlKlassen() throws ClassNotFoundException, IOException{
       Class[] classes = getClasses("de.ostfalia.prog.ws18");
       // remove those classes that contain ".test" in their FQN
       ArrayList<Class> classesList = new ArrayList<Class>();
       classesList.addAll(Arrays.asList(classes));
       long countValid = classesList.stream()
                .filter((c)->!(c.getName().contains(".test.")))
                .count();
       /*
       Iterator<Class> cit = classesList.iterator();
       while (cit.hasNext()) {
          Class c = cit.next();
          if (c.getName().contains(".test.")) {
             cit.remove();
          }
       }
       */
       assertTrue("Die Anzahl an Klassen (ohne Tests) sollte mindestens 8 sein, ist aber "+countValid
                , countValid >= 8);
       Class[] subclassed = findSubclasses(classes);
       assertTrue("Die Anzahl an Vererbungen innerhalb des Paketes sollte mindestens 2 sein, ist aber "+subclassed.length, subclassed.length >= 2);
    }

    /**
     * find all classes that are subclasses in this list
     * @author J.Weimar@ostfalia.de
     * @param classes input list
     * @return an array of classes that have a superclass in the current list.
     */
   @SuppressWarnings("rawtypes")
   private Class[] findSubclasses(Class[] classes) {
      HashSet<Class> hs = new HashSet<Class>(classes.length*2);
      for(Class c: classes) {
         hs.add(c);
      }
      List<Class> result = new ArrayList<Class>();
      for(Class c: classes) {
         if (hs.contains(c.getSuperclass())) {
            result.add(c);
         }
      }
     
      return result.toArray(new Class[result.size()]);
   }

}
