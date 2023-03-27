package homer.view.javafx;

import java.util.ArrayList;
import java.util.List;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.model.outlets.Outlet;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ElectricalMeterView extends BorderPane {

    private ElectricalMeter controller;
    private TableView<List<Outlet>> outletTable;
    private TableColumn<List<Outlet>, ?> outletNameColumn;
    private TableColumn<List<Outlet>, ?> outletStatusColumn;
    private TableColumn<List<Outlet>, Void> outletActionColumn;
    private Label globalConsumptionLabel;
    private Label averagePowerLabel;

    public ElectricalMeterView(ElectricalMeter controller) {
        this.controller = controller;
        initView();
    }

    private void initView() {
        this.outletTable = new TableView<>();
        ObservableList<List<Outlet>> outlets = FXCollections.observableArrayList();
        outlets.add(controller.getOutlets());

        // set the table items
        outletTable.setItems(outlets);
        // Initialize outlet table
        outletTable = new TableView<>();
        outletTable.setEditable(false);
        outletTable.setItems(outlets);

        outletNameColumn = new TableColumn<>("Outlet Name");
        outletNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        outletStatusColumn = new TableColumn<>("Status");
        outletStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        outletActionColumn = new TableColumn<>("Action");
        outletActionColumn = new TableColumn<>("Action");
        outletActionColumn.setCellFactory(param -> new TableCell<List<Outlet>,Void>() {
            private final ToggleButton outletSwitch = new ToggleButton();
            {
                outletSwitch.setOnAction(event -> {
                    List<Outlet> outlet = getTableView().getItems().get(getIndex());
                    controller.cutPowerTo(outlet.get(0));
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    List<Outlet> outlet = getTableView().getItems().get(getIndex());
                    outletSwitch.setSelected(outlet.get(0).getState().getPower().get() > 0.0);
                    setGraphic(outletSwitch);
                }
            }
        });

        List<TableColumn<List<Outlet>, ?>> columns = new ArrayList<>();
        columns.add(outletNameColumn);
        columns.add(outletStatusColumn);
        columns.add(outletActionColumn);
        outletTable.getColumns().addAll(columns);

        // outletTable.getColumns().addAll(outletNameColumn, outletStatusColumn,
        // outletActionColumn);

        // Initialize global consumption label
        globalConsumptionLabel = new Label("Global Consumption: ");
        globalConsumptionLabel.textProperty()
                .bind(Bindings.format("Global Consumption: %.2f kWh", controller.getGlobalConsumption()));

        // Initialize average power label
        averagePowerLabel = new Label("Average Power: ");
        averagePowerLabel.textProperty().bind(Bindings.format("Average Power: %.2f W", controller.getAveragePower()));

        // Set up view
        this.setTop(outletTable);
        this.setBottom(new HBox(globalConsumptionLabel, averagePowerLabel));
    }
}
