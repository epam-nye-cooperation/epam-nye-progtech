package hu.nye.behavioral;

import java.util.LinkedHashSet;
import java.util.Set;

public class AirTrafficControlMediator {
    private final Set<Airplane> airplanes;

    public AirTrafficControlMediator() {
        this.airplanes = new LinkedHashSet<>();
    }

    public void sendMessage(String message, Airplane originator) {
        for (Airplane aircraft : airplanes) {
            // Do not inform the originator
            if (aircraft != originator) {
                aircraft.receive(message);
            }
        }
    }

    public class Airplane {
        private final String callSign;

        public Airplane(String callSign) {
            this.callSign = callSign;
            AirTrafficControlMediator.this.airplanes.add(this);
        }

        public void send(String message) {
            System.out.println(this.callSign + " sending message: " + message);
            AirTrafficControlMediator.this.sendMessage(message, this);
        }

        public void receive(String message) {
            System.out.println(this.callSign + " received message: " + message);
        }
    }

}