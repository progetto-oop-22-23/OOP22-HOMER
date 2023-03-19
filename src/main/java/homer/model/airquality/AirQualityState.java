package homer.model.airquality;

/**
 * Models the air quality information we want to return to users.
 * Since the units of measurement are unlikely to change, they are defined as doubles.
 */
public interface AirQualityState {

    /**
     * 
     * @return co2, measured in mEq/L
     */
    double getCO2();

    /**
     * 
     * @param co2 new co2 level, measured in mEq/L
     */
    void setCO2(double co2);

    /**
     * 
     * @return current pm10 level, measured in ug/m^3
     */
    double getPM10();

    /**
     * 
     * @param pm10 new pm10 level, measured in ug/m^3
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
     * @return pm2.5 level, measured in ug/m^3
     */
    double getPM25();

    /**
     * 
     * @param pm25 new pm 2.5 level, measured in ug/m^3
     */
    void setPM25(double pm25);
}
