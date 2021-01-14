package ru.netology;

import java.util.*;

public class CarShop {
    private int SoldCars = 0;

    public int getSoldCars() {
        return SoldCars;
    }

    public void setSoldCars(int soldCars) {
        this.SoldCars = soldCars;
    }

    private Manager manager = new Manager(this);
    private List<Car> cars = new ArrayList<>(10);

    public void sellCar() {
        manager.sellCar();
    }

    public void acceptCar() {
            manager.receiveCar();
    }

    List<Car> getCars(){
        return cars;
    }

}
