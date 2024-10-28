package hu.nye.structural.bridge;

import lombok.Data;
import org.apache.commons.lang3.RandomUtils;

import java.time.LocalTime;

@Data
public class SonyTV implements TV {
    int channel = RandomUtils.nextInt(1, 100); // Recovered saved value
    int volume = RandomUtils.nextInt(1, 100); // Recovered saved value

    @Override
    public void powerOn() {
        System.out.println("Sony Power On");
        if (LocalTime.now().getHour() > 21 || LocalTime.now().getHour() < 3) {
            setVolume(20); // Sony tries to be gentle with the neighbours
        } else {
            System.out.println("Sony restored saved volume " + volume);
        }
        startShowingPicture();
    }

    @Override
    public void powerOff() {
        System.out.println("Sony Power Off");
    }

    @Override
    public void setVolume(int vol) {
        this.volume = vol;
        System.out.println("Sony saves volume " + vol);
    }

    public void setChannel(int channel) {
        this.channel = channel;
        startShowingPicture();
    }

    @Override
    public void startShowingPicture() {
        System.out.println("Sony is showing the channel " + channel + " on vol=" + volume);
    }
}