package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/Big_Egg_Collect_1.wav");
        soundURL[1] = getClass().getResource("/sound/Hit_damage_1.wav");
        soundURL[2] = getClass().getResource("/sound/Select_1.wav");
        soundURL[3] = getClass().getResource("/sound/Jump_1.wav");
        soundURL[4] = getClass().getResource("/sound/Bubble_1.wav");
        soundURL[5] = getClass().getResource("/sound/Block_Break_1.wav");
        soundURL[6] = getClass().getResource("/sound/Fruit_collect_1.wav");
        soundURL[7] = getClass().getResource("/sound/Boss_hit_1.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {

        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}
