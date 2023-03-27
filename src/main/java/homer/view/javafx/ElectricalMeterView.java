package homer.view.javafx;

import java.util.List;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import javafx.application.Application;
import javafx.stage.Stage;

public class ElectricalMeterView extends Application {

    List<Outlet> outlets = List.of();
    private final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
    
}
