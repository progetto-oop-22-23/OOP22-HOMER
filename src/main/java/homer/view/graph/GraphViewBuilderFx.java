package homer.view.graph;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TabPane.TabDragPolicy;

/**
 * Utility class to build the graph tabs.
 */
public final class GraphViewBuilderFx {

    private final Map<String, Node> graphs = new HashMap<>();

    /**
     * Add a new tab with the given graph.
     * 
     * @param tabName the tab title name.
     * @param graph   the graph node.
     * @return the builder.
     */
    public GraphViewBuilderFx addGraph(final String tabName, final TemplateGraphViewFx<?> graph) {
        this.graphs.put(tabName, graph);
        return this;
    }

    /**
     * Builds the tabpane with the graph tabs.
     * 
     * @return the tabpane with the graph tabs.
     */
    public TabPane build() {
        final TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setTabDragPolicy(TabDragPolicy.REORDER);
        graphs.forEach((name, node) -> tabPane.getTabs().add(new Tab(name, node)));
        tabPane.autosize();
        return tabPane;
    }

}
