package it.unibo.pps.tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private Integer pin = null;
    private int failedAttempts = 0;
    boolean unlocked = false;
    boolean blocked = false;
    public final static int MAX_FAILED_ATTEMPTS = 10;

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin) {
            unlocked = true;
        } else {
            failedAttempts++;
        }

        if (failedAttempts >= 3) {
            this.unlocked = false;
        }
    }

    @Override
    public void lock() {
        if (pin != null) {
            this.unlocked = false;
        } else {
            throw new NullPointerException("pin is null");
        }
    }

    @Override
    public boolean isLocked() {
        return !unlocked;
    }

    @Override
    public boolean isBlocked() {
        return blocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_FAILED_ATTEMPTS - failedAttempts;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void reset() {
        this.unlocked = false;
        this.blocked = false;
        this.failedAttempts = 0;
        this.pin = null;
    }
}