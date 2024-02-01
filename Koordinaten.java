public interface Koordinaten {
    Dezimalgrad toDezimalGrad();
    GradMinuten toGradMinuten();
    GradMinutenSekunden toGradMinutenSekunden();


    public default void ausgabe() {
        System.out.println("Koordinaten: " + System.lineSeparator() +
                toDezimalGrad().toString() +
                System.lineSeparator() +
                toGradMinuten().toString() +
                System.lineSeparator() +
                toGradMinutenSekunden());
    }
}