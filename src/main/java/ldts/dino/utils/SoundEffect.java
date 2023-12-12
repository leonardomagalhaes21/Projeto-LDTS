package ldts.dino.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
public class SoundEffect {
    private static SoundEffect instance;
    private final Clip bombSound;
    private final Clip crouchSound;
    private final Clip gameOverSound;
    private final Clip jumpSound;
    private final Clip menuSelectionSound;
    private final Clip pickSound;
    private float musicVolume = 1.0F;
    private final float fxVolume = 1.0F;
    public SoundEffect() {
        this.bombSound = loadMusic("/src/main/resources/sound-effects/bomb.wav");
        this.crouchSound = loadMusic("/src/main/resources/sound-effects/crouch.wav");

        this.gameOverSound = loadMusic("/src/main/resources/sound-effects/game_over.wav");
        this.jumpSound = loadMusic("/src/main/resources/sound-effects/jump.wav");

        this.menuSelectionSound = loadMusic("/src/main/resources/sound-effects/menu_selection.wav");
        this.pickSound = loadMusic("/src/main/resources/sound-effects/pick.wav");

        //setMusicVolume(0.7F);
        setFxVolume();
    }
    public static SoundEffect getInstance() {
        if (instance == null) {
            instance = new SoundEffect();
        }
        return instance;
    }
    private Clip loadMusic(String filePath) throws NullPointerException {
        try {
            String rootPath = new File(System.getProperty("user.dir")).getPath();
            File musicFile = new File(rootPath+filePath);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0F);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading music file: " + filePath);
        }
        return null;
    }
    public void playBombSound() {
        bombSound.setMicrosecondPosition(0);
        bombSound.start();
        bombSound.loop(0);
    }
    public void playCrouchSound() {
        crouchSound.setMicrosecondPosition(0);
        crouchSound.start();
        crouchSound.loop(0);
    }

    public void playGameOverSound() {
        gameOverSound.setMicrosecondPosition(0);
        gameOverSound.start();
        gameOverSound.loop(0);
    }
    public void playJumpSound() {
        jumpSound.setMicrosecondPosition(0);
        jumpSound.start();
        jumpSound.loop(0);
    }

    public void playMenuSelectionSound() {
        menuSelectionSound.setMicrosecondPosition(0);
        menuSelectionSound.start();
        menuSelectionSound.loop(0);
    }
    public void playPickSound() {
        pickSound.setMicrosecondPosition(0);
        pickSound.start();
        pickSound.loop(0);
    }

/*
    public void setMusicVolume(float volume) {
        if (volume > 1.0F)
            volume = 1.0F;
        else if (volume < 0.0F)
            volume = 0.0F;
        musicVolume  = volume;
        setClipVolume(gameMusic, musicVolume);
    }
*/
    public float getMusicVolume() {
        return this.musicVolume;
    }
    public float getSFXVolume(){ return this.fxVolume;}
    public void setFxVolume() {
        setClipVolume(bombSound, 0.8F);
        setClipVolume(crouchSound,0.8F);
        setClipVolume(gameOverSound,fxVolume);
        setClipVolume(jumpSound,fxVolume);
        setClipVolume(menuSelectionSound,fxVolume);
        setClipVolume(pickSound,fxVolume);
    }
    private void setClipVolume(Clip clip, float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}