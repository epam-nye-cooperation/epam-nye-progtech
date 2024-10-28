package hu.nye.structural.bridge;

public class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(TV tv) {
        super(tv);
    }

    @Override
    public void setChannel(int channel) {
        System.out.print("** Advanced RC: ");
        setVolume(50);
        super.setChannel(channel);
    }
}