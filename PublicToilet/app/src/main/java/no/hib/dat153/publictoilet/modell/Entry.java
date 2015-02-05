package no.hib.dat153.publictoilet.modell;

/**
 * Created by sindre on 21.01.15.
 */
// Static modell klasse
public  class Entry {

    public String pissoir_only;
    public String adresse;
    public String rullestol;
    public String plassering;
    public String stellerom;
    public String pris;
    public String longitude;
    public String latitude;
    public String place;

    private Entry(String pissoir_only, String adresse, String rullestol, String plassering, String stellerom, String pris, String longitude, String latitude, String place){
        this.pissoir_only = pissoir_only;
        this.adresse = adresse;
        this.rullestol = rullestol;
        this.plassering = plassering;
        this.stellerom = stellerom;
        this.pris = pris;
        this.longitude = longitude;
        this.latitude = latitude;
        this.place = place;
    }

    public Entry() {
        pissoir_only = "";
        adresse = "";
        rullestol = "";
        longitude = "";
        latitude = "";

    }

    public String getPissoir_only() {
        return pissoir_only;
    }

    public void setPissoir_only(String pissoir_only) {
        this.pissoir_only = pissoir_only;
    }

    public String getRullestol() {
        return rullestol;
    }

    public void setRullestol(String rullestol) {
        this.rullestol = rullestol;
    }

    public String getPlassering() {
        return plassering;
    }

    public void setPlassering(String plassering) {
        this.plassering = plassering;
    }

    public String getStellerom() {
        return stellerom;
    }

    public void setStellerom(String stellerom) {
        this.stellerom = stellerom;
    }

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
        this.pris = pris;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) { this.latitude = latitude; }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}