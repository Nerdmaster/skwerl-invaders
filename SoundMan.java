import java.applet.AudioClip;

public class SoundMan {
  static final int TAUNT01 = 0;
  
  static final int TAUNT02 = 1;
  
  static final int LASER01 = 11;
  
  static final int ROCKET01 = 12;
  
  static final int FIRE01 = 13;
  
  static final int ENEMYROCK = 21;
  
  static final int ENEMYLASER = 22;
  
  static final int ENEMYFIRE = 23;
  
  static final int EXPLOSION = 31;
  
  static final int ENDGAME = 32;
  
  static final int MAXCLIPS = 50;
  
  static AudioClip[] Clips = new AudioClip[50];
  
  static boolean SoundsOn = true;
  
  static void AddClip(AudioClip paramAudioClip, int paramInt) {
    Clips[paramInt] = paramAudioClip;
  }
  
  static void LoadAll() {
    for (byte b = 0; b < 50; b++) {
      if (Clips[b] != null) {
        Clips[b].play();
        Clips[b].stop();
      } 
    } 
  }
  
  static void LoadClip(int paramInt) {
    if (Clips[paramInt] != null) {
      Clips[paramInt].play();
      Clips[paramInt].stop();
    } else {
      System.out.println("SMERR(42): Tried to load nonexistant audio clip");
    } 
  }
  
  static void PlayClip(int paramInt) {
    if (SoundsOn && Clips[paramInt] != null) {
      Clips[paramInt].play();
    } else if (Clips[paramInt] == null) {
      System.out.println("SMERR(41): Tried to play nonexistant audio clip");
    } 
  }
  
  static void RemoveClip(int paramInt) {
    Clips[paramInt] = null;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\SoundMan.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */