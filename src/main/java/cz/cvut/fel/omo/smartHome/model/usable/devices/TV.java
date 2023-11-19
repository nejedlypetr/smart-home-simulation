package cz.cvut.fel.omo.smartHome.model.usable.devices;


public class TV extends Device {
    @Override
    public void use() {
        System.out.println(" is watching TV.");
    }

    @Override
    public void repair() {

    }
}
