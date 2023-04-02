package homer.view.graph;

import homer.common.temperature.Temperature;

public final class TemperatureGraphFx extends AbstractGraphViewFx<Temperature> {

    private static final String TITLE = "Temperature (C)";

    public TemperatureGraphFx() {
        super(TITLE, data -> data.getCelsius());
    }

}
