package hu.nye.structural.bridge;

import lombok.Data;

@Data
public class SamsungTV implements TV {
    private static final int VIOLENT_VOLUMNE = 10;
    int channel;
    boolean violent;
    int volume;

    @Override
    public void powerOn() {
        volume = violent ? VIOLENT_VOLUMNE : 20;
        System.out.println("Samsung Power On... Very slow and uses volume=" + volume);
        startShowingPicture();
    }

    @Override
    public void powerOff() {
        System.out.println("Samsung Power Off... And makes some funny noise as it turns off");
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        violent = channel % 2 == 0;
        if (violent && volume > VIOLENT_VOLUMNE) volume = VIOLENT_VOLUMNE;
        startShowingPicture();
    }

    @Override
    public void setVolume(int vol) {
        if (violent && vol > VIOLENT_VOLUMNE) {
            System.out.println("Samsung ignores " + vol + " because violent content, keeps " + volume);
        } else {
            volume = vol;
            System.out.println("Samsung sets Volume " + vol + " (will not remember)");
        }
    }

    @Override
    public void startShowingPicture() {
        if (violent) {
            System.out.println("Samsung is not showing the channel " + channel + " content and blurs it, vol=" + volume);
        } else {
            System.out.println("Samsung is showing the channel " + channel + " content vol=" + volume);
        }
    }
}