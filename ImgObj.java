import java.awt.Image;

public class ImgObj {
  Image[] SpriteImage;
  
  int Frames;
  
  int FrameDelay;
  
  public ImgObj(int paramInt1, int paramInt2) {
    this.Frames = paramInt1;
    this.FrameDelay = paramInt2;
    this.SpriteImage = new Image[paramInt1];
  }
  
  public void CreateImage(Image paramImage, int paramInt) {
    this.SpriteImage[paramInt] = paramImage;
  }
  
  public void LoadImages(ImageExtractor paramImageExtractor, int paramInt1, int paramInt2, int paramInt3) {
    for (byte b = 0; b < this.Frames; b++)
      this.SpriteImage[b] = paramImageExtractor.ExtractImage(b * paramInt2, paramInt1, paramInt2, paramInt3); 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\ImgObj.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */