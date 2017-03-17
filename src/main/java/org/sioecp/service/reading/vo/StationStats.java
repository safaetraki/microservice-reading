package org.sioecp.service.reading.vo;

/**
 * Created by Safae on 16/03/2017.
 */
public class StationStats {
    private Integer idStation;
    private String stationName;
    private String longitude;
    private String latitude;
    private String address;
    private Float movementMean;
    private Float availabilityMean;
    private Float velibNbMean;
    // weather is not null if the data we're getting is by period ans station id
    private String weather;
    // meanRain and meanSun is not null if the stats data we're getting is global
    private Float movementMeanRain;
    private Float movementMeanSun;

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getMovementMean() {
        return movementMean;
    }

    public void setMovementMean(Float movementMean) {
        this.movementMean = movementMean;
    }

    public Float getAvailabilityMean() {
        return availabilityMean;
    }

    public void setAvailabilityMean(Float availabilityMean) {
        this.availabilityMean = availabilityMean;
    }

    public Float getVelibNbMean() {
        return velibNbMean;
    }

    public void setVelibNbMean(Float velibNbMean) {
        this.velibNbMean = velibNbMean;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Float getMovementMeanRain() {
        return movementMeanRain;
    }

    public void setMovementMeanRain(Float movementMeanRain) {
        this.movementMeanRain = movementMeanRain;
    }

    public Float getMovementMeanSun() {
        return movementMeanSun;
    }

    public void setMovementMeanSun(Float movementMeanSun) {
        this.movementMeanSun = movementMeanSun;
    }
}
