import java.applet.AudioClip;

public class MusicThread implements Runnable {
  AudioClip Audio;
  
  Thread kicker;
  
  boolean done;
  
  boolean Loop;
  
  public MusicThread(AudioClip paramAudioClip, boolean paramBoolean) {
    this.Audio = paramAudioClip;
    this.done = false;
    this.Loop = paramBoolean;
  }
  
  public boolean done() {
    return this.done;
  }
  
  public void run() {
    if (this.Loop) {
      this.Audio.loop();
    } else {
      this.Audio.play();
      this.Audio.stop();
    } 
    this.done = true;
  }
  
  public void start() {
    this.kicker = new Thread(this);
    this.kicker.start();
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\MusicThread.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */