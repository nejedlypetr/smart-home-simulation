package cz.cvut.fel.omo.smartHome.model.usable.devices;

public class Fridge extends Device {
    private int foodIn = 0;
    @Override
    public void use() {
        System.out.print(" gets food from Fridge.");
        super.use();
    }

    @Override
    public void repair() {

    }


}
