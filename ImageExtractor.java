import java.applet.Applet;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

public class ImageExtractor {
  int[] pixels;
  
  int Width;
  
  int Height;
  
  public ImageExtractor(Image paramImage, int paramInt1, int paramInt2) {
    this.pixels = new int[paramInt1 * paramInt2];
    this.pixels = ImageToPixels(paramImage, paramInt1, paramInt2);
    this.Width = paramInt1;
    this.Height = paramInt2;
  }
  
  public Image ExtractImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int[] arrayOfInt = new int[paramInt3 * paramInt4];
    for (byte b = 0; b < paramInt3; b++) {
      for (byte b1 = 0; b1 < paramInt4; b1++) {
        arrayOfInt[b + paramInt3 * b1] = this.pixels[paramInt1 + b + (paramInt2 + b1) * this.Width];
        int i = arrayOfInt[b + paramInt3 * b1];
        int j = this.pixels[paramInt1 + b + (paramInt2 + b1) * this.Width];
      } 
    } 
    Applet applet = Utils.GetApp();
    return applet.createImage(new MemoryImageSource(paramInt3, paramInt4, arrayOfInt, 0, paramInt3));
  }
  
  public int[] ImageToPixels(Image paramImage, int paramInt1, int paramInt2) {
    int[] arrayOfInt = new int[paramInt1 * paramInt2];
    PixelGrabber pixelGrabber = new PixelGrabber(paramImage, 0, 0, paramInt1, paramInt2, arrayOfInt, 0, paramInt1);
    try {
      pixelGrabber.grabPixels();
    } catch (InterruptedException interruptedException) {
      System.out.println("interrupted  waiting  for  pixels!");
      return null;
    } 
    if ((pixelGrabber.status() & 0x80) != 0) {
      System.out.println("image  fetch  aborted  or  errored");
      return null;
    } 
    byte b1 = 0;
    for (byte b2 = 0; b2 < arrayOfInt.length; b2++) {
      if ((arrayOfInt[b2] & 0xFFFFFF) == 0) {
        arrayOfInt[b2] = 0;
        b1++;
      } 
    } 
    return arrayOfInt;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\ImageExtractor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */