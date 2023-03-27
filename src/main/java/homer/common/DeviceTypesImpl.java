package homer.common;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
    Pre-populated valid device types.
 */
public final class DeviceTypesImpl implements DeviceTypes {

    private final Set<String> validTypes;
    private final Set<String> startingTypes = new LinkedHashSet<>(
        List.of("LIGHT", "OUTLET", "COUTLET", "LOUTLET", "A/C", "HEATER")
    );

    /**
     * Maybe we could pass startingTypes as a parameter here.
     */
    public DeviceTypesImpl() {
        validTypes = new LinkedHashSet<>(startingTypes);
    }

    @Override
    public boolean isValid(final String type) {
        return validTypes.contains(type);
    }

    @Override
    public void addType(final String type) {
        validTypes.add(type);
    }

    @Override
    public Set<String> getValidTypes() {
        return new LinkedHashSet<>(validTypes);
    }

}
