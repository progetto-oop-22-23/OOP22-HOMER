package homer.view.graph;

import homer.common.temperature.Temperature;

/**
 * Implementation of the temperature graph in javafx.
 */
public final class TemperatureGraphFx extends TemplateGraphViewFx<Temperature> {

    private static final String TITLE = "Temperature (C)";

    /**
     * Creates a new {@link TemperatureGraphFx}.
     */
    public TemperatureGraphFx() {
        super(TITLE, data -> data.getCelsius());
    }

}
