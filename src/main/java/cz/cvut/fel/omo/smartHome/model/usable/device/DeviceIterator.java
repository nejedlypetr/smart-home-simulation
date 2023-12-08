package cz.cvut.fel.omo.smartHome.model.usable.device;

import cz.cvut.fel.omo.smartHome.model.house.House;
import cz.cvut.fel.omo.smartHome.model.house.Room;
import cz.cvut.fel.omo.smartHome.model.house.Floor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeviceIterator implements Iterator {
    private List<Device> devices;
    private int currentPos;

    public DeviceIterator(House house) {
        devices = initDevices(house);
        currentPos = 0;
    }

    public List<Device> initDevices(House house) {
        ArrayList<Device> result = new ArrayList<>();
        for (Floor floor : house.getFloors()) {
            for (Room room : floor.getRooms()) {
                result.addAll(room.getDevices());
            }
        }
        return result;
    }

    public void updateDevices(House house) {
        devices = new ArrayList<>();
        for (Floor floor : house.getFloors()) {
            for (Room room : floor.getRooms()) {
                devices.addAll(room.getDevices());
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (currentPos < devices.size()) {
            return true;
        } else {
            currentPos = 0;
            return false;
        }
    }

    @Override
    public Device next() {
        if (hasNext()) {
            Device result = devices.get(currentPos);
            currentPos++;
            return result;
        }
        return null;
    }
}
