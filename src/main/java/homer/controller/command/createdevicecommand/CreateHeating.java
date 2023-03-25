package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.environment.Environment;
import homer.model.temperaturechangers.Heating;

public record CreateHeating(Environment environment) implements CreateDeviceCommand {

    @Override
    public void execute(Controller controller) {
        controller.getDeviceManager().addDevice(new Heating(0, 0, environment));
    }

    @Override
    public String getStringRep() {
        return "Create heating";
    }
    
}