package homer.controller.scheduler;

import homer.model.scheduler.SchedulerCommand;

/**
 * Interface of the commands for the control of temperature.
 */
public interface TemperatureCommands {

        /**
         * Manages the devices so as to heat the environment.
         * 
         * @return the command.
         */
        SchedulerCommand heatCommand();

        /**
         * Stops both heating and cooling devices.
         * 
         * @return the command.
         */
        SchedulerCommand stopCommand();

        /**
         * Manages the devices so as to cool the environment.
         * 
         * @return the command.
         */
        SchedulerCommand coolCommand();

}
