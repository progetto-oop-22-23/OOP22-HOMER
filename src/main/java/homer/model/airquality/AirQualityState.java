package homer.model.airquality;

/**
 * Models the air quality information we want to return to users.
 */
public interface AirQualityState {

    /**
     * 
     * @return current co2 level
     */
    double getCO2();

    /**
     * 
     * @param co2 new co2 level
     */
    void setCO2(double co2);

    /**
     * 
     * @return current pm10 level
     */
    double getPM10();

    /**
     * 
     * @param pm10 new pm10 level
     */
    void setPM10(double pm10);

    /**
     * 
     * @return toxic gas percentage level
     */
    double getToxicGasPercentage();

    /**
     * 
     * @param toxicGasPercentage new toxic gas percentage level
     */
    void setToxicGasPercentage(double toxicGasPercentage);

    /**
     * 
     * @return pm2.5 level
     */
    double getPM25();

    /**
     * 
     * @param pm25 new pm 2.5 level
     */
    void setPM25(double pm25);
}
