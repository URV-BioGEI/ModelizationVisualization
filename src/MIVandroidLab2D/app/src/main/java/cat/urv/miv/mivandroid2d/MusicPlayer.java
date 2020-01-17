package cat.urv.miv.mivandroid2d;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Class that plays sounds
 */
public class MusicPlayer {

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;

    public void PlayAudio(Context c, int id)
    {
        mediaPlayer = MediaPlayer.create(c, id);
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC,50);
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }

    public void PlaySound(Context c, int id)
    {
        mediaPlayer = MediaPlayer.create(c, id);
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC,50);
        mediaPlayer.start();

    }
}
