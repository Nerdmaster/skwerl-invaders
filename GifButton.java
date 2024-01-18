import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class GifButton {
  int xpos;
  
  int ypos;
  
  int width;
  
  int height;
  
  boolean hovering;
  
  boolean enabled;
  
  Color Normal;
  
  Color Hover;
  
  boolean clicked;
  
  boolean hasgif;
  
  Image NormalGif;
  
  Image HoverGif;
  
  Image DisableGif;
  
  Applet App;
  
  String HoverString;
  
  String strBtnText;
  
  Font fntBtnText;
  
  char HotKey;
  
  Cursor curHand = new Cursor(12);
  
  static boolean AnyHovering;
  
  public GifButton(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Font paramFont, Color paramColor1, Color paramColor2, String paramString, Applet paramApplet) {
    this.xpos = paramInt1;
    this.ypos = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.fntBtnText = paramFont;
    this.Normal = paramColor1;
    this.Hover = paramColor2;
    this.hasgif = false;
    this.strBtnText = paramString;
    InitButton();
    this.App = paramApplet;
  }
  
  public GifButton(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Image paramImage1, Image paramImage2, Image paramImage3, Applet paramApplet) {
    this.xpos = paramInt1;
    this.ypos = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.NormalGif = paramImage1;
    this.HoverGif = paramImage2;
    this.DisableGif = paramImage3;
    this.hasgif = true;
    this.App = paramApplet;
    InitButton();
  }
  
  public boolean CheckClick(MouseEvent paramMouseEvent) {
    int i = paramMouseEvent.getX();
    int j = paramMouseEvent.getY();
    if (this.enabled && i >= this.xpos && i <= this.xpos + this.width && j >= this.ypos && j <= this.ypos + this.height) {
      this.clicked = true;
      return true;
    } 
    return false;
  }
  
  public void CheckHover(MouseEvent paramMouseEvent) {
    int i = paramMouseEvent.getX();
    int j = paramMouseEvent.getY();
    if (this.enabled && i > this.xpos && i < this.xpos + this.width && j > this.ypos && j < this.ypos + this.height) {
      this.hovering = true;
      this.App.showStatus(this.HoverString);
      this.App.setCursor(this.curHand);
      AnyHovering = true;
    } else {
      this.hovering = false;
    } 
  }
  
  public boolean CheckKeyPress(KeyEvent paramKeyEvent) {
    char c = Character.toUpperCase(paramKeyEvent.getKeyChar());
    if (c == this.HotKey && this.enabled) {
      this.clicked = true;
      return true;
    } 
    return false;
  }
  
  public void Disable() {
    this.enabled = false;
  }
  
  public void Enable() {
    this.enabled = true;
  }
  
  static boolean GetHovering() {
    return AnyHovering;
  }
  
  private void InitButton() {
    this.clicked = false;
    this.enabled = false;
    this.hovering = false;
    this.HoverString = new String("");
    this.HotKey = Character.MIN_VALUE;
  }
  
  public void Paint(Graphics paramGraphics) {
    if (this.hasgif) {
      if (!this.enabled) {
        paramGraphics.drawImage(this.DisableGif, this.xpos, this.ypos, this.App);
      } else if (this.hovering) {
        paramGraphics.drawImage(this.HoverGif, this.xpos, this.ypos, this.App);
      } else {
        paramGraphics.drawImage(this.NormalGif, this.xpos, this.ypos, this.App);
      } 
    } else {
      if (!this.enabled) {
        paramGraphics.setColor(Color.gray);
      } else if (this.hovering) {
        paramGraphics.setColor(this.Hover);
      } else {
        paramGraphics.setColor(this.Normal);
      } 
      paramGraphics.setFont(this.fntBtnText);
      paramGraphics.drawString(this.strBtnText, this.xpos, this.ypos + this.height);
    } 
  }
  
  public void SetHotKey(char paramChar) {
    this.HotKey = Character.toUpperCase(paramChar);
  }
  
  public void SetHoverString(String paramString) {
    this.HoverString = paramString;
  }
  
  static void SetHovering(boolean paramBoolean) {
    AnyHovering = paramBoolean;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\GifButton.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */