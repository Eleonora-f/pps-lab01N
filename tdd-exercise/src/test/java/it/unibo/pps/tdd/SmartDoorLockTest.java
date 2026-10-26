package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl smartDoorLock;

    @BeforeEach
    void beforeEach(){
        this.smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    void testInitialState() {
        assertTrue(smartDoorLock.isLocked());
        assertFalse(smartDoorLock.isBlocked());
        assertEquals(0, smartDoorLock.getFailedAttempts());
        assertEquals(SmartDoorLockImpl.MAX_FAILED_ATTEMPTS, smartDoorLock.getMaxAttempts());
    }

    @Test
    void testPinSet() {
        smartDoorLock.setPin(1234);
        smartDoorLock.unlock(1234);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testUnlockWithCorrectPin() {
        smartDoorLock.setPin(1234);
        smartDoorLock.unlock(1234);
        assertFalse(smartDoorLock.isLocked());
        assertEquals(0, smartDoorLock.getFailedAttempts());
    }

    @Test
    void testUnlockWithWrongPin() {
        smartDoorLock.setPin(1234);
        smartDoorLock.unlock(9999);

        assertTrue(smartDoorLock.isLocked());
        assertEquals(1, smartDoorLock.getFailedAttempts());
        assertEquals(SmartDoorLockImpl.MAX_FAILED_ATTEMPTS - 1, smartDoorLock.getMaxAttempts());
    }

    @Test
    void testMultipleFailedAttempts() {
        smartDoorLock.setPin(1234);
        smartDoorLock.unlock(1111);
        smartDoorLock.unlock(2222);
        smartDoorLock.unlock(3333);

        assertTrue(smartDoorLock.isLocked());
        assertEquals(3, smartDoorLock.getFailedAttempts());
        assertEquals(SmartDoorLockImpl.MAX_FAILED_ATTEMPTS - 3, smartDoorLock.getMaxAttempts());
    }

    @Test
    void testLockAfterUnlock() {
        smartDoorLock.setPin(1234);
        smartDoorLock.unlock(1234);

        assertFalse(smartDoorLock.isLocked());
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testLockWithoutPin() {
        assertThrows(
                NullPointerException.class,
                () -> smartDoorLock.lock()
        );
    }

    @Test
    void testReset() {
        smartDoorLock.setPin(1234);
        smartDoorLock.unlock(9999);

        smartDoorLock.reset();

        assertTrue(smartDoorLock.isLocked());
        assertFalse(smartDoorLock.isBlocked());
        assertEquals(0, smartDoorLock.getFailedAttempts());
        assertEquals(SmartDoorLockImpl.MAX_FAILED_ATTEMPTS, smartDoorLock.getMaxAttempts());
    }

    @Test
    void testUnlockAfterReset() {
        smartDoorLock.setPin(1234);
        smartDoorLock.reset();

        assertThrows(
                NullPointerException.class,
                () -> smartDoorLock.lock()
        );
    }
}
