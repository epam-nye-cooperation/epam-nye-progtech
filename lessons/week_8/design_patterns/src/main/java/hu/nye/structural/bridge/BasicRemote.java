package hu.nye.structural.bridge;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicRemote implements TVControl {
    protected final TV tv;

    @Override
    public void powerOn() {
        tv.powerOn();
    }

    @Override
    public void powerOff() {
        tv.powerOff();
    }

    @Override
    public void setChannel(int channel) {
        tv.setChannel(channel);
    }

    @Override
    public void setVolume(int vol) {
        tv.setVolume(vol);
    }
}