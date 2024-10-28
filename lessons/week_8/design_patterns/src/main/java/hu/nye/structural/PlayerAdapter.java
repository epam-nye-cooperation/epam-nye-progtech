package hu.nye.structural;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.util.Locale;

class VLC {

    public VLC() {
        System.out.println("TODO: Run or connect to existing player of VLC");
    }

    public void play(File file) {
        System.out.println("TODO: Instruct VLC to play the file " + file);
    }
}

class MediaPlayer {
    public MediaPlayer() {
        System.out.println("TODO: Run or connect to existing player of MP");
    }

    public void start(File file) {
        System.out.println("TODO: Instruct MP to play the file " + file);
    }
}

public class PlayerAdapter {
    private static final String[] VLC_EXTENSIONS = "vlc,anyothervlc".split(",");

    public void play(File file) {
        String ext = FilenameUtils.getExtension(file.getName()).toLowerCase(Locale.ROOT);
        if (ArrayUtils.contains(VLC_EXTENSIONS, ext)) {
            VLC actualPlayer = new VLC();
            actualPlayer.play(file);
        } else {
            MediaPlayer actualPlayer = new MediaPlayer();
            actualPlayer.start(file);
        }
    }
}