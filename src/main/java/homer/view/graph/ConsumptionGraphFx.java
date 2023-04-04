package homer.view.graph;

/**
 * Implementation of {@link TemplateGraphViewFx} for the view of energy global
 * consumption.
 */
public final class ConsumptionGraphFx extends TemplateGraphViewFx<Double> {

    private static final String TITLE = "Global energy consumption (Wh)";

    /**
     * Creates a new {@link ConsumptionGraphFx}.
     */
    public ConsumptionGraphFx() {
        super(TITLE, d -> d);
    }

}
