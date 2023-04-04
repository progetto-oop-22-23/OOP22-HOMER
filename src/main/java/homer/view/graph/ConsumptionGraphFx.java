package homer.view.graph;

public final class ConsumptionGraphFx extends TemplateGraphViewFx<Double> {

    private static final String TITLE = "Global energy consumption (Wh)";

    public ConsumptionGraphFx() {
        super(TITLE, d -> d);
    }

}
