package homer.controller.scheduler;

import homer.controller.command.Command;

/**
 * Interface of the commands for the control of temperature.
 */
public interface TemperatureCommands {

        /**
         * Manages the devices so as to heat the environment.
         * 
         * @return the command.
         */
        Command heatCommand();

        /**
         * Stops both heating and cooling devices.
         * 
         * @return the command.
         */
        Command stopCommand();

        /**
         * Manages the devices so as to cool the environment.
         * 
         * @return the command.
         */
        Command coolCommand();

}
