package homer.view.graph;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TabPane.TabDragPolicy;

/**
 * Utility class to build a tab pane.
 */
public final class TabViewBuilderFx {

    private final Map<String, Node> nodes = new HashMap<>();

    /**
     * Add a new tab with the given node.
     * 
     * @param tabName the tab title name.
     * @param node    the node.
     * @return the builder.
     */
    public TabViewBuilderFx addNode(final String tabName, final Node node) {
        this.nodes.put(tabName, node);
        return this;
    }

    /**
     * Builds the tabpane with the tabs.
     * 
     * @return the tabpane with the tabs.
     */
    public TabPane build() {
        final TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setTabDragPolicy(TabDragPolicy.REORDER);
        nodes.forEach((name, node) -> tabPane.getTabs().add(new Tab(name, node)));
        tabPane.autosize();
        return tabPane;
    }

}
