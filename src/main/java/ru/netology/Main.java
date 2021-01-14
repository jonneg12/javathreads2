package ru.netology;

public class Main {

    public static final long CAR_CHECK_DELAY = 2000L;
    public static final long CAR_IMPORT_DELAY = 3000L;
    public static final int MIN_CAR_IN_SHOP = 1;
    public static final int CARS_TO_SOLD = 10;
    public static final int NUMBER_OF_BUYERS = 2;

    public static void main(String[] args) {

        final CarShop carShop = new CarShop();

        //buyers
        for (int i = 0; i < NUMBER_OF_BUYERS; i++) {
            new Thread(null,
                    () -> {
                        while (carShop.getSoldCars() <= (CARS_TO_SOLD - NUMBER_OF_BUYERS)) {
                            carShop.sellCar();
                            try {
                                Thread.sleep((long) (Math.random() * 200));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    "Buyer " + i).start();
        }

//seller
        new Thread(null,
                () -> {
                    for (int i = 0; i < CARS_TO_SOLD; i++) {

                        carShop.acceptCar();
                        try {
                            Thread.sleep((long) (Math.random() * 2000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },
                "Seller").start();
    }
}
