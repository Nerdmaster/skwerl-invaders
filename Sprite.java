import java.awt.Graphics;

abstract class Sprite {
  protected int xpos;
  
  protected int ypos;
  
  protected int width;
  
  protected int height;
  
  protected boolean visible;
  
  protected boolean active;
  
  public Sprite(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.xpos = paramInt1;
    this.ypos = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
  }
  
  public void Disable() {
    this.visible = false;
    this.active = false;
  }
  
  public void Enable() {
    this.visible = true;
    this.active = true;
  }
  
  public int GetH() {
    return this.height;
  }
  
  public int GetW() {
    return this.width;
  }
  
  public int GetX() {
    return this.xpos;
  }
  
  public int GetY() {
    return this.ypos;
  }
  
  public boolean IsActive() {
    return this.active;
  }
  
  public boolean IsVisible() {
    return this.visible;
  }
  
  abstract void Paint(Graphics paramGraphics);
  
  public void SetActive(boolean paramBoolean) {
    this.active = paramBoolean;
  }
  
  public void SetPosition(int paramInt1, int paramInt2) {
    if (this.xpos != -1)
      this.xpos = paramInt1; 
    if (this.ypos != -1)
      this.ypos = paramInt2; 
  }
  
  public void SetVisible(boolean paramBoolean) {
    this.visible = paramBoolean;
  }
  
  public void Translate(int paramInt1, int paramInt2) {
    this.xpos += paramInt1;
    this.ypos += paramInt2;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Sprite.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */