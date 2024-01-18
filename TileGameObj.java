import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public final class TileGameObj {
  static final int TILESIZEX = 512;
  
  static final int TILESIZEY = 64;
  
  static final int TILESHIFTX = 9;
  
  static final int TILESHIFTY = 6;
  
  static final int IMAGEWIDTH = 512;
  
  static final int IMAGEHEIGHT = 512;
  
  static final int OFFSET = 896;
  
  static final int IMAGEBOTTOM = 1280;
  
  static final int STRIPCOUNT = 1;
  
  static final int STRIPSHIFT = 0;
  
  static final int COLUMNS = 1;
  
  static final int ROWS = 6;
  
  static final int GAMESIZEX = 1;
  
  static final int GAMESIZEY = 6;
  
  static final int XSHIFT = 0;
  
  static final int MINCAMERAX = 0;
  
  static final int MINCAMERAY = 0;
  
  static final int MAXCAMERAX = 0;
  
  static final int MAXCAMERAY = 128;
  
  static final int GAMESIZEXY = 6;
  
  Image ScreenImage;
  
  Graphics Screen;
  
  int BackX;
  
  int BackY;
  
  int[] GameBoard;
  
  boolean Loop;
  
  int tempx1;
  
  int tempy1;
  
  int tempx2;
  
  int tempy2;
  
  int count1;
  
  int count2;
  
  int temp;
  
  int temprow;
  
  int tempcol;
  
  int sx;
  
  int sy;
  
  int tempInt;
  
  int tempCX;
  
  int tempCY;
  
  public TileGameObj(Image paramImage, int[] paramArrayOfint) {
    Applet applet = Utils.GetApp();
    this.ScreenImage = applet.createImage(512, 1280);
    this.Screen = this.ScreenImage.getGraphics();
    this.Screen.drawImage(paramImage, 0, 0, applet);
    this.GameBoard = paramArrayOfint;
    this.BackX = 0;
    this.BackY = 0;
  }
  
  public void CreateBack() {
    this.Screen.copyArea(0, 512, 512, 512, 0, -512);
    this.tempx1 = this.BackX >> 9;
    this.tempy1 = this.BackY >> 6;
    this.tempx2 = (this.tempx1 << 9) - this.BackX;
    this.tempy2 = (this.tempy1 << 6) - this.BackY;
    this.tempCX = this.tempx1 + 1;
    this.tempCY = this.tempy1 + 6;
    this.count1 = this.tempy1;
    while (this.count1 < this.tempCY) {
      this.count2 = this.tempx1;
      while (this.count2 < this.tempCX) {
        this.tempInt = this.count2 + this.count1;
        if (this.count2 < 0 || this.count2 >= 1 || this.count1 < 0 || this.count1 >= 6) {
          if (this.Loop) {
            if (this.count2 < 0) {
              this.count2++;
            } else if (this.count2 >= 1) {
              this.count2--;
            } 
            if (this.count1 < 0) {
              this.count1 += 6;
            } else if (this.count1 >= 6) {
              this.count1 -= 6;
            } 
          } else {
            this.temp = -1;
          } 
        } else {
          this.temp = this.GameBoard[this.tempInt];
        } 
        this.temprow = this.temp;
        this.tempcol = this.temp - this.temprow;
        this.sx = this.tempcol << 9;
        this.sy = (this.temprow << 6) + 896;
        if (this.temp != -1)
          this.Screen.copyArea(this.sx, this.sy, 512, 64, this.tempx2 + (this.count2 - this.tempx1 << 9) - this.sx, this.tempy2 + (this.count1 - this.tempy1 << 6) - this.sy); 
        this.count2++;
      } 
      this.count1++;
    } 
  }
  
  public Graphics GetGraphics() {
    return this.Screen;
  }
  
  public Image GetImage() {
    return this.ScreenImage;
  }
  
  public void MoveTo(int paramInt1, int paramInt2) {
    if (paramInt1 >= 0 && paramInt1 <= 0)
      this.BackX = paramInt1; 
    if (paramInt2 >= 0 && paramInt2 <= 128)
      this.BackY = paramInt2; 
  }
  
  public void Scroll(int paramInt1, int paramInt2) {
    this.BackX += paramInt1;
    if (this.BackX > 0) {
      this.BackX = 0;
    } else if (this.BackX < 0) {
      this.BackX = 0;
    } 
    this.BackY += paramInt2;
    if (this.BackY > 128) {
      this.BackY = 0;
    } else if (this.BackY < 0) {
      this.BackY = 128;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\TileGameObj.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */