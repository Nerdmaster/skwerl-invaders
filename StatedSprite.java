import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class StatedSprite extends Sprite {
  protected Applet App = Utils.GetApp();
  
  ImgObj[] StateSprites;
  
  ImgObj TempStateSprite;
  
  protected int CurrentState;
  
  protected int NextState;
  
  protected int DefaultState;
  
  protected int NumStates;
  
  protected int CurrentFrame = 0;
  
  public int Action;
  
  int FrameCount = 0;
  
  public StatedSprite(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void InitMain(int paramInt1, int paramInt2) {
    this.NumStates = paramInt1;
    this.CurrentState = paramInt2;
    this.DefaultState = paramInt2;
    this.NextState = paramInt2;
    this.StateSprites = new ImgObj[paramInt1];
  }
  
  public void InitState(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt2 > 0)
      this.StateSprites[paramInt1] = new ImgObj(paramInt2, paramInt3); 
  }
  
  public void LoadImage(Image paramImage, int paramInt1, int paramInt2) {
    (this.StateSprites[paramInt2]).SpriteImage[paramInt1] = paramImage;
  }
  
  public void LoadImages(ImageExtractor paramImageExtractor, int paramInt1, int paramInt2) {
    for (byte b = 0; b < (this.StateSprites[paramInt1]).Frames; b++)
      (this.StateSprites[paramInt1]).SpriteImage[b] = paramImageExtractor.ExtractImage(b * this.width, paramInt2, this.width, this.height); 
  }
  
  public final void Paint(Graphics paramGraphics) {
    if (this.visible)
      paramGraphics.drawImage((this.StateSprites[this.CurrentState]).SpriteImage[this.CurrentFrame], this.xpos, this.ypos, this.App); 
  }
  
  public void SetNewState(int paramInt) {
    this.CurrentFrame = 0;
    this.FrameCount = 0;
    this.CurrentState = paramInt;
  }
  
  public final void Update() {
    this.TempStateSprite = this.StateSprites[this.CurrentState];
    if (this.TempStateSprite.FrameDelay != -1) {
      this.FrameCount++;
      if (this.FrameCount >= this.TempStateSprite.FrameDelay) {
        this.FrameCount = 0;
        this.CurrentFrame++;
        if (this.CurrentFrame == this.TempStateSprite.Frames) {
          this.CurrentFrame = 0;
          this.Action = this.CurrentState;
          this.CurrentState = this.NextState;
          if (this.DefaultState != -1)
            this.NextState = this.DefaultState; 
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\StatedSprite.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */