package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    @BeforeEach
    void beforeEach(){
        this.smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    public void setPin() {
        assertTrue(true);
    }

    @Test
    void testUnlock() {
        bankAccount.deposit(accountHolder.id(), 100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testLock() {
        bankAccount.deposit(accountHolder.id(), 100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testIslocked() {
        bankAccount.deposit(accountHolder.id(), 100);
        assertEquals(100, bankAccount.getBalance());
    }
}
