public class GradMinutenSekunden extends GradMinuten {
    private int latSek;
    private int lonSek;

    public GradMinutenSekunden(int lat, int lon, int latMin, int lonMin, int latSek, int lonSek) {
        super(lat, lon, latMin, lonMin);

        if (latSek < 0 || latSek > 59 || lonSek < 0 || lonSek > 59) {
            throw new IllegalArgumentException("Falscher Wertebereich");
        }
        this.lonSek = lonSek;
        this.latSek = latSek;
    }
        @Override
        public Dezimalgrad toDezimalGrad () {
            return new Dezimalgrad(latSek / 3600.0 + latMin / 60.0 + lat, lonSek / 3600.0 + lonMin / 60.0 + lon);
        }

        @Override
        public GradMinuten toGradMinuten () {
            return new GradMinuten(lat, lon, latMin, lonMin);
        }

        @Override
        public GradMinutenSekunden toGradMinutenSekunden () {
            return this;
        }
    }

}