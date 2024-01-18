import java.applet.Applet;
import java.awt.Graphics;

public class Explosion {
  static final int EXPLOSIONY = 128;
  
  static final int EXPLOSIONWIDTH = 16;
  
  static final int EXPLOSIONHEIGHT = 16;
  
  static final int EXPLOSIONFRAMES = 6;
  
  static final int EXPLOSIONDELAY = 1;
  
  static final int EXPLOSIONW2 = 8;
  
  static final int EXPLOSIONH2 = 8;
  
  static int[] xpos;
  
  static int[] ypos;
  
  static int[] CurrentFrame;
  
  static int[] FrameNum;
  
  static int MaxExplosions;
  
  static ImgObj ExpGraphic;
  
  static Applet App;
  
  static int count;
  
  static int temp;
  
  static void AddExplosion(int paramInt1, int paramInt2) {
    paramInt1 -= 8;
    paramInt2 -= 8;
    SoundMan.PlayClip(31);
    count = 0;
    while (count < MaxExplosions - 1) {
      temp = count + 1;
      xpos[count] = xpos[temp];
      ypos[count] = ypos[temp];
      CurrentFrame[count] = CurrentFrame[temp];
      FrameNum[count] = FrameNum[temp];
      count++;
    } 
    temp = MaxExplosions - 1;
    xpos[temp] = paramInt1;
    ypos[temp] = paramInt2;
    CurrentFrame[temp] = 0;
    FrameNum[temp] = 0;
  }
  
  static void InitExplosion(ImageExtractor paramImageExtractor, int paramInt) {
    MaxExplosions = paramInt;
    ExpGraphic = new ImgObj(6, 1);
    ExpGraphic.LoadImages(paramImageExtractor, 128, 16, 16);
    xpos = new int[paramInt];
    ypos = new int[paramInt];
    CurrentFrame = new int[paramInt];
    FrameNum = new int[paramInt];
    App = Utils.GetApp();
  }
  
  static void Paint(Graphics paramGraphics) {
    count = 0;
    while (count < MaxExplosions) {
      if (xpos[count] != 0)
        paramGraphics.drawImage(ExpGraphic.SpriteImage[CurrentFrame[count]], xpos[count], ypos[count], App); 
      count++;
    } 
  }
  
  static void Update() {
    count = 0;
    while (count < MaxExplosions) {
      if (xpos[count] != 0) {
        FrameNum[count] = FrameNum[count] + 1;
        if (FrameNum[count] >= 1) {
          FrameNum[count] = 0;
          CurrentFrame[count] = CurrentFrame[count] + 1;
          if (CurrentFrame[count] == 6) {
            temp = count;
            while (temp > 0) {
              xpos[temp] = xpos[temp - 1];
              ypos[temp] = ypos[temp - 1];
              CurrentFrame[temp] = CurrentFrame[temp - 1];
              FrameNum[temp] = FrameNum[temp - 1];
              temp--;
            } 
            xpos[0] = 0;
          } 
        } 
      } 
      count++;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Explosion.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */