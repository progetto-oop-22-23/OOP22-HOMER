package homer.model.airquality;

public interface AirQualityState {
    double getCO2();

    void setCO2(double CO2);

    double getPM10();

    void setPM10(double PM10);

    double getToxicGasPercentage();

    void setToxicGasPercentage(double toxicGasPercentage);

    double getPM25();

    void setPM25(double PM25);   
}
