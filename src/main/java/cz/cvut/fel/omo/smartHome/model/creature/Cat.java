package cz.cvut.fel.omo.smartHome.model.creature;

import java.util.Random;

public class Cat extends Creature {

    private Random random;

    public Cat(String name) {
        super(name);
        random = new Random();
    }

    public void sleep() {

    }

    public void meow() {

    }

    public void eat() {

    }

    @Override
    public void findActivity() {
        int num = random.nextInt(5);
        switch (num) {
            case 0:
                eat();
                break;
            case 1:
                meow();
                break;
            case 2:
                sleep();
            default:
                System.out.println("Invalid random number");
        }
    }

    @Override
    public void generateEvent() {

    }
}
