package pl.nogacz.tasks.integration.util;

public enum DriverType {
    CHROME,
    FIREFOX;

    public boolean isChrome() {
        return this == CHROME;
    }

    public boolean isFirefox() {
        return this == FIREFOX;
    }
}
