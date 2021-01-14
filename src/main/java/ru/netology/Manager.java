package ru.netology;

import static ru.netology.Main.*;

public class Manager {


    private CarShop carShop;

    public Manager(CarShop carShop) {
        this.carShop = carShop;
    }

    public synchronized void receiveCar() {
            while (carShop.getCars().size() >= MIN_CAR_IN_SHOP) {
                try {
                    wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

            try {
                Thread.sleep(CAR_IMPORT_DELAY);
                carShop.getCars().add(new Car());
                System.out.print("Manager: receiving Cars from Factory >>>>> ");
                System.out.print("New car was imported to the Car Shop. >>>>> ");
                System.out.println("New Cars in Shop: " + carShop.getCars().size() + ".");
                notifyAll();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");;
            }
    }


    public synchronized void sellCar() {

            try {
                while (carShop.getCars().isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + ": comes to the Car Shop");
                    System.out.print("Manager: checking car in the Car shop >>>>>> ");
                    System.out.println("Cars in the shop: " + carShop.getCars().size());
                    System.out.println("Manager: Sorry, __" + Thread.currentThread().getName() + "__ we haven't got any cars in the shop(");
                    wait();
                }
                Thread.sleep(CAR_CHECK_DELAY);
                System.out.println("Manager: car sold to the __" + Thread.currentThread().getName() + "__. >>>");
                carShop.setSoldCars(carShop.getSoldCars() + 1);
                System.out.println("Cars sold: " + carShop.getSoldCars());
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            carShop.getCars().remove(0);
            notify();
    }
}

