import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Missile {
  static final int TYPE_LINE = 0;
  
  static final int TYPE_BALL = 1;
  
  static final int TYPE_FIRE = 2;
  
  static final int TYPE_THICKLINE = 3;
  
  static final int TYPE_SWIVELLINE = 4;
  
  static final int TYPE_LINE2 = 5;
  
  static final int TYPE_ICE = 6;
  
  static final int TYPE_RING = 7;
  
  static final int TYPE_FIREBALL = 8;
  
  static final int FBALL_YPOS = 144;
  
  static final int FBALL_WIDTH = 10;
  
  static final int FBALL_HEIGHT = 14;
  
  static final int FBALL_FRAMES = 2;
  
  static final int EBALL_YPOS = 160;
  
  static final int EBALL_WIDTH = 6;
  
  static final int EBALL_HEIGHT = 9;
  
  static final int EBALL_FRAMES = 2;
  
  static final int ICE_YPOS = 192;
  
  static final int ICE_WIDTH = 16;
  
  static final int ICE_HEIGHT = 32;
  
  static final int ICE_FRAMES = 4;
  
  static final int ICE_DELAY = 0;
  
  static final int RING_YPOS = 240;
  
  static final int RING_WIDTH = 8;
  
  static final int RING_HEIGHT = 8;
  
  static final int RING_FRAMES = 6;
  
  static final int RING_DELAY = 2;
  
  static final int FIRE_YPOS = 272;
  
  static final int FIRE_WIDTH = 32;
  
  static final int FIRE_HEIGHT = 32;
  
  static final int FIRE_FRAMES = 4;
  
  static final int FIRE_DELAY = 1;
  
  static final int FIREBALL_YPOS = 304;
  
  static final int FIREBALL_WIDTH = 16;
  
  static final int FIREBALL_HEIGHT = 16;
  
  static final int FIREBALL_FRAMES = 3;
  
  static final int FIREBALL_DELAY = 1;
  
  static final int BALL_DELAY = 4;
  
  static final int FBALL_INITSPEED = -1;
  
  static final int FBALL_MAXSPEED = -7;
  
  static final double FBALL_ACCEL = -0.33D;
  
  int xpos;
  
  int ypos;
  
  int xoffset;
  
  int xvel;
  
  int yvel;
  
  double xvel_d;
  
  double yvel_d;
  
  int Damage;
  
  boolean Friendly;
  
  int Type;
  
  int FrameNumber;
  
  int FrameDelayCount;
  
  int ImageWidthDiv2;
  
  int ImageHeightDiv2;
  
  ImgObj ThisMissileGif;
  
  static Applet App;
  
  static ImgObj ioEnemyBall;
  
  static ImgObj ioFriendlyBall;
  
  static ImgObj ioEnemyFlame;
  
  static ImgObj ioFriendlyFlame;
  
  static ImgObj ioIceBeam;
  
  static ImgObj ioRing;
  
  static ImgObj ioFire;
  
  static ImgObj ioFireball;
  
  static Color CE = new Color(250, 250, 250);
  
  static Color CF = new Color(210, 100, 210);
  
  static int paint_sizemod;
  
  static int paint_tempintx;
  
  static int paint_tempinty;
  
  static int collx;
  
  static int colly;
  
  public Missile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, int paramInt7) {
    this.xpos = paramInt1;
    this.ypos = paramInt2;
    this.xoffset = paramInt3;
    this.xvel = paramInt4;
    this.yvel = paramInt5;
    this.xvel_d = this.xvel;
    this.yvel_d = this.yvel;
    this.Damage = paramInt6;
    this.Friendly = paramBoolean;
    this.Type = paramInt7;
    this.FrameNumber = 0;
    this.FrameDelayCount = 0;
    switch (this.Type) {
      case 0:
      case 3:
      case 4:
      case 5:
        this.ThisMissileGif = null;
        this.ImageWidthDiv2 = 0;
        this.ImageHeightDiv2 = 0;
        break;
      case 1:
        if (this.Friendly) {
          this.ThisMissileGif = ioFriendlyBall;
          this.ImageWidthDiv2 = 5;
          this.ImageHeightDiv2 = 7;
          break;
        } 
        this.ThisMissileGif = ioEnemyBall;
        this.ImageWidthDiv2 = 3;
        this.ImageHeightDiv2 = 4;
        break;
      case 6:
        this.ThisMissileGif = ioIceBeam;
        this.ImageWidthDiv2 = 8;
        this.ImageHeightDiv2 = 16;
        break;
      case 7:
        this.ThisMissileGif = ioRing;
        this.ImageWidthDiv2 = 4;
        this.ImageHeightDiv2 = 4;
        break;
      case 2:
        this.ThisMissileGif = ioFire;
        this.ImageWidthDiv2 = 16;
        this.ImageHeightDiv2 = 16;
        break;
      case 8:
        this.ThisMissileGif = ioFireball;
        this.ImageWidthDiv2 = 8;
        this.ImageHeightDiv2 = 8;
        break;
    } 
  }
  
  public boolean CheckCollision(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    switch (this.Type) {
      case 0:
        if (paramInt1 < this.xpos && paramInt3 > this.xpos && paramInt2 < this.ypos && paramInt4 > this.ypos)
          return true; 
        collx = this.xpos + this.xvel;
        colly = this.ypos + this.yvel;
        if (paramInt1 < collx && paramInt3 > collx && paramInt2 < colly && paramInt4 > colly)
          return true; 
        break;
      case 5:
        if (paramInt1 < this.xpos && paramInt3 > this.xpos && paramInt2 < this.ypos && paramInt4 > this.ypos)
          return true; 
        collx = this.xpos + this.xvel;
        colly = this.ypos + this.yvel;
        if (paramInt1 < collx && paramInt3 > collx && paramInt2 < colly && paramInt4 > colly)
          return true; 
        collx = this.xpos + (this.xvel << 1);
        colly = this.ypos + (this.yvel << 1);
        if (paramInt1 < collx && paramInt3 > collx && paramInt2 < colly && paramInt4 > colly)
          return true; 
        break;
      case 3:
        if (paramInt1 < this.xpos && paramInt3 > this.xpos && paramInt2 < this.ypos && paramInt4 > this.ypos)
          return true; 
        collx = this.xpos + (this.xvel << 1);
        colly = this.ypos + (this.yvel << 1);
        if (paramInt1 < collx && paramInt3 > collx && paramInt2 < colly && paramInt4 > colly)
          return true; 
        collx = this.xpos + (this.xvel << 2);
        colly = this.ypos + (this.yvel << 2);
        if (paramInt1 < collx && paramInt3 > collx && paramInt2 < colly && paramInt4 > colly)
          return true; 
        break;
      case 1:
      case 2:
      case 6:
      case 7:
      case 8:
        if (paramInt1 < this.xpos + this.ImageWidthDiv2 && paramInt3 > this.xpos - this.ImageWidthDiv2 && paramInt2 < this.ypos + this.ImageWidthDiv2 && paramInt4 > this.ypos - this.ImageWidthDiv2)
          return true; 
        break;
    } 
    return false;
  }
  
  static void Init(ImageExtractor paramImageExtractor, Applet paramApplet) {
    ioEnemyBall = new ImgObj(2, 4);
    ioFriendlyBall = new ImgObj(2, 4);
    ioIceBeam = new ImgObj(4, 0);
    ioRing = new ImgObj(6, 2);
    ioFire = new ImgObj(4, 1);
    ioFireball = new ImgObj(3, 1);
    ioEnemyBall.LoadImages(paramImageExtractor, 160, 6, 9);
    ioFriendlyBall.LoadImages(paramImageExtractor, 144, 10, 14);
    ioIceBeam.LoadImages(paramImageExtractor, 192, 16, 32);
    ioRing.LoadImages(paramImageExtractor, 240, 8, 8);
    ioFire.LoadImages(paramImageExtractor, 272, 32, 32);
    ioFireball.LoadImages(paramImageExtractor, 304, 16, 16);
    App = paramApplet;
  }
  
  public final void Paint(Graphics paramGraphics, int paramInt) {
    if (this.Friendly) {
      paramGraphics.setColor(CF);
    } else {
      paramGraphics.setColor(CE);
    } 
    switch (this.Type) {
      case 4:
        if (paramInt <= 12) {
          paint_sizemod = paramInt - 6;
        } else {
          paint_sizemod = 18 - paramInt;
        } 
        paramGraphics.drawLine(this.xpos - paint_sizemod, this.ypos, this.xpos + (this.xvel << 1) + paint_sizemod, this.ypos + (this.yvel << 1));
        break;
      case 5:
        paramGraphics.drawLine(this.xpos, this.ypos, this.xpos + (this.xvel << 1), this.ypos + (this.yvel << 1));
      case 0:
        paramGraphics.drawLine(this.xpos, this.ypos, this.xpos + this.xvel, this.ypos + this.yvel);
        break;
      case 3:
        paint_tempintx = this.xpos + (this.xvel << 2);
        paint_tempinty = this.ypos + (this.yvel << 2);
        paramGraphics.drawLine(this.xpos, this.ypos, paint_tempintx, paint_tempinty);
        paramGraphics.drawLine(this.xpos - 1, this.ypos - 1, paint_tempintx - 1, paint_tempinty - 1);
        paramGraphics.drawLine(this.xpos + 1, this.ypos + 1, paint_tempintx + 1, paint_tempinty + 1);
        break;
      case 1:
      case 2:
      case 6:
      case 7:
      case 8:
        paramGraphics.drawImage(this.ThisMissileGif.SpriteImage[this.FrameNumber], this.xpos - this.ImageWidthDiv2, this.ypos - this.ImageHeightDiv2, App);
        break;
    } 
  }
  
  public final void Update() {
    if (this.xoffset > 0) {
      this.xpos++;
      this.xoffset--;
    } else if (this.xoffset < 0) {
      this.xpos--;
      this.xoffset++;
    } 
    this.xpos += this.xvel;
    this.ypos += this.yvel;
    if (this.Type == 1 && this.Friendly && this.yvel > -7) {
      this.yvel_d += -0.33D;
      this.yvel = (int)this.yvel_d;
    } 
    if (this.ThisMissileGif != null) {
      this.FrameDelayCount++;
      if (this.FrameDelayCount >= this.ThisMissileGif.FrameDelay) {
        this.FrameDelayCount = 0;
        this.FrameNumber++;
        if (this.FrameNumber == this.ThisMissileGif.Frames)
          this.FrameNumber = 0; 
      } 
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Missile.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */