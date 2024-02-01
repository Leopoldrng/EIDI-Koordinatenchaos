public class GradMinuten implements Koordinaten{
    protected int lat;
    protected int lon;
    protected int latMin;
    protected int lonMin;

    public GradMinuten(int lat, int lon, int latMin, int lonMin){
        if (lat < 0 || lat > 90 || lon < 0 || lon > 180){
            throw new IllegalArgumentException("Falscher Wertebereich!");
        }
        if (latMin < 0 || latMin > 59 || lonMin < 0 || lonMin > 59){
            throw new IllegalArgumentException("Falscher Wertebereich!");
        }
        this.lat = lat;
        this.lon = lon;
        this.latMin = latMin;
        this.lonMin = lonMin;
    }
    @Override
    public Dezimalgrad toDezimalGrad() {
        return new Dezimalgrad(latMin /60.0 + lat, lonMin /60.0 + lon);
    }

    @Override
    public GradMinuten toGradMinuten() {
        return this;
    }

    @Override
    public GradMinutenSekunden toGradMinutenSekunden() {
        return new GradMinutenSekunden(lat,lon,latMin,lonMin,0,0);
    }
}
