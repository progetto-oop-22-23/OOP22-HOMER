package homer.api;

public interface Device<S> {

    DeviceInfo getInfo();

    S getState();

}
