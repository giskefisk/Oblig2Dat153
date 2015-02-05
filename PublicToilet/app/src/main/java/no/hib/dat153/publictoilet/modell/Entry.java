package no.hib.dat153.publictoilet.modell;

/**
 * Created by sindre on 21.01.15.
 */
// Static modell klasse
public  class Entry {
    public String adresse;
    public String longitude;
    public String latitude;

    private Entry(String adresse, String longitude, String latitude){
        this.adresse = adresse;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Entry() {
        adresse = "";
        longitude = "";
        latitude = "";
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

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}