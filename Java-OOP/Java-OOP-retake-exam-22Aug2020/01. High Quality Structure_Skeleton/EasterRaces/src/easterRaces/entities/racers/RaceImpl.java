package easterRaces.entities.racers;

import easterRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

import static easterRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().length() < 5){
            throw new IllegalArgumentException(String.format(INVALID_NAME,name,5));
        }
        this.name = name;
    }

    public void setLaps(int laps) {
        if (laps < 1){
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_LAPS,1));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        Driver driver1 = drivers.stream().filter(d -> d.equals(driver)).findFirst().orElse(null);

        if (driver == null){
            throw new IllegalArgumentException(DRIVER_INVALID);
        }else if (!driver.getCanParticipate()){
            throw new IllegalArgumentException(String.format(DRIVER_NOT_PARTICIPATE,driver.getName()));
        }else if (driver1 != null){
            throw new IllegalArgumentException(String.format(DRIVER_ALREADY_ADDED,driver.getName(),this.getName()));
        }
        drivers.add(driver);
    }
}
