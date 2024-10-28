package hu.nye.structural.bridge;

public interface TVControl {
    void powerOn();

    void powerOff();

    void setChannel(int channel);

    void setVolume(int vol);
}