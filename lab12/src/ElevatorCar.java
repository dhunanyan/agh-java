public class ElevatorCar extends Thread {
    int floor = 0;

    public int getFloor() {
        return floor;
    }

    enum Tour {
        UP,
        DOWN
    };

    Tour tour = Tour.UP;

    enum Movement {
        STOP,
        MOVING
    };

    Movement movementState = Movement.STOP;

    public void run() {
        for (; ; ) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //KIERUNEK W GÓRĘ + SPOCZYNEK
            if (movementState == Movement.STOP && tour == Tour.DOWN) {
                //NIE MA PIĘTER W KOLEJCE Z DOŁU
                if (!ElevatorStops.get().hasStopBelow(floor)) tour = Tour.UP;
                else movementState = Movement.MOVING;
                continue;
            }
            //KIERUNEK W DÓŁ + SPOCZYNEK
            if (movementState == Movement.STOP && tour == Tour.UP) {
                //NIE MA PIĘTER W KOLEJCE NAD DANYM PIĘTREM
                if (!ElevatorStops.get().hasStopAbove(floor)) tour = Tour.DOWN;
                else movementState = Movement.MOVING;
                continue;
            }
            //KIERUNEK W DÓŁ + RUCH
            if (movementState == Movement.MOVING && tour == Tour.DOWN) {
                //DANE PIĘTRO JEST WYŻSZE OD NAJWYŻSZEGO W KOLEJCE
                if (floor > ElevatorStops.get().getMinSetFloor()) {
                    floor--;
                    System.out.println("PIĘTRO: " + floor);
                } else {
                    movementState = Movement.STOP;
                    tour = Tour.UP;
                }

                //NACIŚNIĘTE PIĘTRO TO NAJWYŻSZE ALBO ZJEŻDZAJĄC NA DÓŁ POWINIEN ZATRZYMAĆ SIĘ NA ZACISNIĘTYM
                if (ElevatorStops.get().whileMovingDownSholudStopAt(floor)
                        || floor == ElevatorStops.get().getMinSetFloor()) {
                    movementState = Movement.STOP;
                    ElevatorStops.get().clearStopDown(floor);
                    System.out.println("SPOCZYNEK");
                }
                continue;
            }
            //RUCH W GÓRĘ
            if (movementState == Movement.MOVING && tour == Tour.UP) {
                //DANE PIĘTRO JEST NIŻSZE OD NAJWYŻSZEGO W KOLEJCE
                if (floor < ElevatorStops.get().getMaxSetFloor()) {
                    floor++;
                    System.out.println("PIĘTRO: " + floor);
                } else {
                    movementState = Movement.STOP;
                    tour = Tour.DOWN;
                }

                //RUSZAJĄC W GÓRĘ MA SIĘ ZATRZYMAĆ NA PODANYM PIĘTRZE ALBO PODANE PETRO JEST MAKSYMALNYM ZE WSZYSTKICH Z KOLEJKI
                if (ElevatorStops.get().whileMovingUpSholudStopAt(floor)
                        || floor == ElevatorStops.get().getMaxSetFloor()) {
                    movementState = Movement.STOP;
                    ElevatorStops.get().clearStopUp(floor);
                    System.out.println("SPOCZYNEK");
                }
            }
        }
    }
}