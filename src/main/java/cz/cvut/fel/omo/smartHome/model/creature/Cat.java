package cz.cvut.fel.omo.smartHome.model.creature;

public class Cat extends Animal {


    public void sleep() {

    }

    public void meow() {

    }

    public void eat() {

    }

    public void findActivity() {
//        int x = randomInt;
        int x = 3;
        switch (x) {
            case 1: sleep();
            case 2: meow();
            case 3: eat();
        }
    }
}
