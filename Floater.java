import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Floater {
  static Color TextColor;
  
  static Font TextFont;
  
  int Frames;
  
  int XMin;
  
  int XMax;
  
  int YSpeed;
  
  int xpos;
  
  int ypos;
  
  int XSpeed;
  
  int Direction;
  
  String TextString;
  
  boolean done;
  
  public Floater(int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    this.Frames = paramInt6;
    this.TextString = paramString;
    this.XMin = paramInt3;
    this.XMax = paramInt4;
    this.YSpeed = paramInt5;
    this.XSpeed = 1;
    this.Direction = 1;
    this.xpos = paramInt1;
    this.ypos = paramInt2;
    this.done = false;
  }
  
  static void Init(Color paramColor, Font paramFont) {
    TextColor = paramColor;
    TextFont = paramFont;
  }
  
  public void Paint(Graphics paramGraphics) {
    paramGraphics.setColor(TextColor);
    paramGraphics.setFont(TextFont);
    paramGraphics.drawString(this.TextString, this.xpos, this.ypos);
  }
  
  public void Update() {
    this.ypos += this.YSpeed;
    this.xpos += this.XSpeed;
    if (this.Frames % 3 == 0) {
      this.XSpeed += this.Direction;
      if (this.XSpeed == this.XMax) {
        this.Direction = -1;
      } else if (this.XSpeed == this.XMin) {
        this.Direction = 1;
      } 
    } 
    this.Frames--;
    if (this.Frames < 0)
      this.done = true; 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Floater.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */