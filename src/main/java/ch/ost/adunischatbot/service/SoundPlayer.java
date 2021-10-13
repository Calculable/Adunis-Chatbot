package ch.ost.adunischatbot.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.apache.log4j.Logger;

//From http://www.java2s.com/example/java/applet/plays-the-sound-clip-contained-in-the-given-byte-array.html
public class SoundPlayer {
    public static void main(String[] argv) throws Exception{
        byte[] data = new byte[]{34,35,36,37,37,37,67,68,69};
        playClip(data);//from   w  w  w.ja  v a 2s .c o  m
    }
    private static final Logger LOG = Logger.getLogger(SoundPlayer.class);
    /** Plays the sound clip contained in the given <code>byte</code> array. */
    public static void playClip(final byte[] data) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("AudioHelper.playClip(num bytes="
                    + (data == null ? "null" : data.length) + ")");
        }

        // got this sound-playing code from: http://www.onjava.com/onjava/excerpt/jenut3_ch17/examples/SoundPlayer.java
        try {
            final AudioInputStream ain = AudioSystem
                    .getAudioInputStream(new ByteArrayInputStream(data));
            try {
                final DataLine.Info info = new DataLine.Info(Clip.class,
                        ain.getFormat());
                final Clip clip = (Clip) AudioSystem.getLine(info);
                clip.open(ain);
                clip.start();
            } catch (LineUnavailableException e) {
                LOG.error(
                        "LineUnavailableException while playing the sound",
                        e);
            } finally {
                try {
                    ain.close();
                } catch (IOException e) {
                    LOG.warn(
                            "Ignoring IOException while trying to close the AudioInputStream",
                            e);
                }
            }
        } catch (UnsupportedAudioFileException e) {
            LOG.error(
                    "UnsupportedAudioFileException while playing the sound",
                    e);
        } catch (IOException e) {
            LOG.error("IOException while playing the sound", e);
        }
    }
}