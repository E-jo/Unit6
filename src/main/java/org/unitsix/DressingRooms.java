package org.unitsix;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DressingRooms {
    private final Semaphore availableRoom;

    public DressingRooms(int numRooms) {
        availableRoom = new Semaphore( numRooms, true );
    }

    public DressingRooms() {
        availableRoom = new Semaphore( 3, true );
    }

    public synchronized boolean requestRoom() throws InterruptedException {
        return availableRoom.tryAcquire(0, TimeUnit.MINUTES);
    }

    public synchronized void leaveRoom() {
        availableRoom.release();
    }

}
