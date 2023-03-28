package homer.view.javafx;

import java.util.ArrayList;
import java.util.List;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * View for {@link homer.controller.impl.electricalmeter}.
 */
public class ElectricalMeterView extends BorderPane {

    private ElectricalMeter meter;
    private TableView<List<Outlet>> outletTable;
    private TableColumn<List<Outlet>, Double> outletStatusColumn;
    //private TableColumn<List<Outlet>, Void> outletActionColumn;
    private Label globalConsumptionLabel;
    private Label averagePowerLabel;

    /**
     * View constructor
     * @param meter the electrical meter.
     */
    public ElectricalMeterView(ElectricalMeter meter) {
        this.meter = meter;
        initView();
    }

    /**
     * Initializes and builds the view.
     */
    private void initView() {
        this.outletTable = new TableView<>();
        ObservableList<List<Outlet>> outlets = FXCollections.observableArrayList();

        for (Outlet outlet : meter.getOutlets()) {
            List<Outlet> outletList = new ArrayList<>();
            outletList.add(outlet);
            outlets.add(outletList);
        }

        // set the table items
        outletTable.setItems(outlets);
        // Initialize outlet table
        outletTable = new TableView<>();
        outletTable.setEditable(false);
        outletTable.setItems(outlets);

        outletStatusColumn = new TableColumn<>("Status");
        outletStatusColumn.setCellFactory(param -> new TableCell<List<Outlet>, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    TableRow<List<Outlet>> row = getTableRow();
                    if (row != null) {
                        Outlet outlet = row.getItem().get(0);
                        Double power = outlet.getState().getPower().get();
                        setText(String.format("%.2f W", power));
                    }
                }
            }
        });

        /*outletActionColumn = new TableColumn<>("Action");
        outletActionColumn.setCellFactory(param -> new TableCell<List<Outlet>, Void>() {
            private final ToggleButton outletSwitch = new ToggleButton("Switch OFF");
            {
                outletSwitch.setOnAction(event -> {
                    List<Outlet> outlet = getTableView().getItems().get(getIndex());
                    meter.cutPowerTo(outlet.get(getIndex()));
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
*/
        List<TableColumn<List<Outlet>, ?>> columns = new ArrayList<>();
        columns.add(outletStatusColumn);
       // columns.add(outletActionColumn);

        final int outletTableMinColumns = 1;
        final int outletTableRowHeightOffset = 60;
        final int outletTableHeight = outlets.size() * outletTableRowHeightOffset;
        final int outletTableDeltaHeight = 90;

        outletTable.getColumns().addAll(columns.subList(0, Math.min(columns.size(), outletTableMinColumns)));
        outletTable.setPrefHeight(outletTableHeight + outletTableDeltaHeight);

        globalConsumptionLabel = new Label("Global Consumption: ");
        globalConsumptionLabel.setText("Global Consumption: " + meter.getGlobalConsumption() + "W");

        // Initialize average power label
        averagePowerLabel = new Label("Average Power: ");
        averagePowerLabel.setText("Average Power: " + meter.getAveragePower() + "Wh");

        Timeline updateTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            ((ElectricalMeterImpl) meter).updateTick(java.time.Duration.ofHours(13));
            updateUI();
        }));
        // Create a ToggleButton to start and stop the update timeline
        ToggleButton updateButton = new ToggleButton("Start Update");
        updateButton.setOnAction(event -> {
            if (updateButton.isSelected()) {
                updateTimeline.play();
                updateButton.setText("Stop Update");
            } else {
                updateTimeline.pause();
                updateButton.setText("Start Update");
            }
        });
        updateTimeline.setCycleCount(Animation.INDEFINITE);
        // Add the ToggleButton to the UI
        this.setTop(new HBox(outletTable));
        // Set up view
        this.setTop(outletTable);
        this.setBottom(new HBox(globalConsumptionLabel, averagePowerLabel, updateButton));
    }

    /**
     * Updates the view.
     */
    public void updateUI() {
        averagePowerLabel.setText("Average Power: " + meter.getAveragePower() + "Wh");
        globalConsumptionLabel.setText("Global Consumption: " + meter.getGlobalConsumption() + "W");
        outletStatusColumn.setCellFactory(param -> new TableCell<List<Outlet>, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    TableRow<List<Outlet>> row = getTableRow();
                    if (row != null) {
                        Outlet outlet = row.getItem().get(0);
                        Double power = outlet.getState().getPower().get();
                        setText(String.format("%.2f W", power));
                    }
                }
            }
        });
    }
}
