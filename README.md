# Koordinatenchaos ⚓️

Es gibt verschiedene Darstellungsformen von Koordinaten. Sie sollen drei dieser Darstellungsformen, inklusive der Transformationen zwischen diesen, umsetzen. Ein Beispiel der drei Darstellungsformen von Koordinaten können Sie im Folgenden für Garching sehen:

- **Dezimalgrad**: {breite=48.2489, laenge=11.6532}
- **GradMinuten**: {breite=48, breiteMinuten=14, laenge=11, laengeMinuten=39}
- **GradMinutenSekunden**: {breite=48, breiteMinuten=14, breiteSekunden=56, laenge=11, laengeMinuten=39, laengeSekunden=11}

Die Dezimalgrad-Darstellungsform speichert die Längen- und Breitenwerte als Fließkommazahl. Die GradMinuten- und GradMinutenSekunden-Darstellungsformen speichern nur ganzzahlige Werte für Grad, Minuten und Sekunden. Der Einfachheit halber interessieren wir uns im Folgenden nur für Koordinaten in der östlichen Hemisphäre der Nordhalbkugel. Breitenwerte liegen somit im Bereich von 0 bis 90 und Längenwerte im Bereich von 0 bis 180. Minuten und Sekunden haben den Wertebereich 0 bis 59.

Das folgende Interface beschreibt die Transformationen.
```java
public interface Koordinaten {
    Dezimalgrad toDezimalGrad();
    GradMinuten toGradMinuten();
    GradMinutenSekunden toGradMinutenSekunden();

    
    default void ausgabe() {
        // TODO - Wird in Teilaufgabe b) implementiert.
    }
}
```
## Implementierung der Klassen Dezimalgrad, GradMinuten und GradMinutenSekunden

Die Klassen `Dezimalgrad`, `GradMinuten` und `GradMinutenSekunden` setzen das `Koordinaten`-Interface um. Hierbei wird eine sinnvolle Vererbungshierarchie verwendet. Alle notwendigen Attribute haben restriktive Access Modifier. Im Falle fehlerhafter Argumente werfen die Konstruktoren eine `IllegalArgumentException`. In der Methode `public GradMinuten toGradMinuten()` der Klasse `GradMinutenSekunden` können die Sekunden ignoriert werden.

#### Lösungsvorschlag
```java
public class Dezimalgrad implements Koordinaten {
    private double breite;
    private double laenge;

    public Dezimalgrad(double lat, double lon) {
        if (breite < 0 || breite > 90 || laenge > 180 || laenge < 0) {
            throw new IllegalArgumentException();
        }
        this.laenge = laenge;
        this.breite = breite;
    }

    @Override
    public Dezimalgrad toDezimalGrad() {
        return this;
    }

    @Override
    public GradMinuten toGradMinuten() {
        return toGradMinutenSekunden().toGradMinuten();
    }

    @Override
    public GradMinutenSekunden toGradMinutenSekunden() {
        int breite = (int) this.breite;
        int laenge = (int) this.laenge;
        double breiteMinutenDouble = (this.breite - breite) * 60);
        double laengeMinutenDouble = (this.laenge - laenge) * 60);
        int breiteMinuten = (int) breiteMinutenDouble;
        int laengeMinuten = (int) laengeMinutenDouble;
        int breiteSekunden = (int) ((breiteMinutenDouble - breiteMinuten) * 60);
        int laengeSekunden = (int) ((laengeMinutenDouble - laengeMinuten) * 60);
        return new GradMinutenSekunden(breite, laenge, breiteMinuten, laengeMinuten, breiteSekunden, laengeSekunden);
    }
}

// GRADMINUTEN
public class GradMinuten implements Koordinaten {
    protected int laenge;
    protected int breite;
    protected int laengeMinuten;
    protected int breiteMinuten;

    public GradMinuten(int breite, int laenge, int breiteMinuten, int laengeMinuten) {
        if (laengeMinuten > 59 || laengeMinuten < 0 || breiteMinuten > 59 || breiteMinuten < 0) {
            throw new IllegalArgumentException("Falscher Wertebereich!");
        }
        if (breite < 0 || breite > 90 || laenge > 180 || laenge < 0) {
            throw new IllegalArgumentException("Falscher Wertebereich!");
        }
        this.laenge = laenge;
        this.breite = breite;
        this.laengeMinuten = laengeMinuten;
        this.breiteMinuten = breiteMinuten;
    }

    @Override
    public Dezimalgrad toDezimalGrad() {
        return new Dezimalgrad(laengeMinuten / 60.0 + laenge, breiteMinuten / 60.0 + breite);
    }

    @Override
    public GradMinuten toGradMinuten() {
        return this;
    }

    @Override
    public GradMinutenSekunden toGradMinutenSekunden() {
        return new GradMinutenSekunden(breite, laenge, breiteMinuten, laengeMinuten, 0, 0);
    }
}

// GRADMINUTENSEKUNDEN
public class GradMinutenSekunden extends GradMinuten {
    private int laengeSekunden;
    private int breiteSekunden;

    public GradMinutenSekunden(int breite, int laenge, int breiteMinuten, int laengeMinuten, int breiteSekunden, int laengeSekunden) {
        super(breite, laenge, breiteMinuten, laengeMinuten);
        if (laengeSekunden > 59 || laengeSekunden < 0 || laengeSekunden > 59 || breiteSekunden < 0) {
            throw new IllegalArgumentException("Falscher Wertebreich!");
        }
        this.laengeSekunden = laengeSekunden;
        this.breiteSekunden = breiteSekunden;
    }

    @Override
    public Dezimalgrad toDezimalGrad() {
        return new Dezimalgrad(breiteSekunden / 3600.0 + breiteMinuten / 60.0 + breite, laengeSekunden / 3600.0 + laengeMinuten / 60.0 + laenge);
    }

    @Override
    public GradMinuten toGradMinuten() {
        return new GradMinuten(breite, laenge, breiteMinuten, laengeMinuten);
    }

    @Override
    public GradMinutenSekunden toGradMinutenSekunden() {
        return this;
    }
}
```

#### Teilaufgabe b)

Implementieren Sie die Methodeausgabedes InterfacesKoordinaten. Diese Methode gibt eine beliebigeKoordinate in allen drei Darstellungsformen aus. Sie können davon ausgehen, dass es für die einzelnenKlassen eine sinnvolletoString()gibt, die Sie nicht implementieren müssen.

```java
public default void ausgabe() {
    System.out.println("Koordinaten: " + System.lineSeparator() +
            toDezimalGrad().toString() +
            System.lineSeparator() +
            toGradMinuten().toString() +
            System.lineSeparator() +
            toGradMinutenSekunden());
}
```