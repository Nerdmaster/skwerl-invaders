import java.awt.Color;
import java.awt.Graphics;

public class Shield {
  static final int SHIELDWIDTH = 64;
  
  static final int SHIELDHEIGHT = 8;
  
  static final int SWINC = 4;
  
  static final int SHIELDCOLORS = 32;
  
  static Color[] ShieldColor = new Color[32];
  
  int xpos;
  
  int ypos;
  
  int power;
  
  int width;
  
  int height;
  
  int frame;
  
  public Shield(int paramInt1, int paramInt2, int paramInt3) {
    this.xpos = paramInt1;
    this.ypos = paramInt2;
    this.power = paramInt3;
    this.width = 0;
    this.height = 8;
  }
  
  static void InitColors() {
    byte b2 = 16;
    byte b1;
    for (b1 = 0; b1 < 16; b1++)
      ShieldColor[b1] = new Color(b2 * b1, b2 * b1, b2 * b1); 
    for (b1 = 16; b1 < 32; b1++)
      ShieldColor[b1] = ShieldColor[32 - b1 - 1]; 
  }
  
  public void Paint(Graphics paramGraphics) {
    paramGraphics.setColor(ShieldColor[this.frame]);
    paramGraphics.fillRect(this.xpos, this.ypos, this.width, this.height);
  }
  
  public void Update() {
    this.frame++;
    if (this.frame >= 32)
      this.frame = 0; 
    if (this.width < 64) {
      this.width += 4;
      this.xpos -= 2;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Shield.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */