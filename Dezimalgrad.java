public class Dezimalgrad implements Koordinaten{

    private double lat;
    private double lon;

    public Dezimalgrad(double lat, double lon){
        if (lat < 0 || lat > 90 || lon < 0 || lon > 180){
            throw new IllegalArgumentException("Falscher Wertebereich!");
        }
        this.lat = lat;
        this.lon = lon;
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
        int breite = (int) this.lat;
        int laenge = (int) this.lon;
        double breiteMinuteDouble = (this.lat -lat)*60;
        double laengeMinuteDouble = (this.lon -lon)*60;
        int breiteMinuten = (int) breiteMinuteDouble;
        int laengeMinuten = (int) laengeMinuteDouble;
        int breiteSekunden = (int) ((breiteMinuteDouble-breiteMinuten)*60);
        int laengeSekunden = (int) ((laengeMinuteDouble-breiteMinuten)*60);
        return new GradMinutenSekunden(breite,laenge,breiteMinuten,laengeMinuten,breiteSekunden,laengeSekunden);
    }
}
