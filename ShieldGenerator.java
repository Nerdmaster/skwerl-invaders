import java.awt.Graphics;
import java.util.Vector;

public class ShieldGenerator {
  Vector ShieldList;
  
  Shield TempShield;
  
  int count;
  
  int tempsize;
  
  static boolean CollHit;
  
  public ShieldGenerator() {
    Shield.InitColors();
    this.ShieldList = new Vector();
  }
  
  public void AddShield(int paramInt1, int paramInt2, int paramInt3) {
    this.TempShield = new Shield(paramInt1, paramInt2, paramInt3);
    this.ShieldList.addElement(this.TempShield);
  }
  
  public boolean CheckCollision(Missile paramMissile) {
    this.tempsize = this.ShieldList.size();
    CollHit = false;
    this.count = 0;
    while (this.count < this.tempsize) {
      this.TempShield = this.ShieldList.elementAt(this.count);
      CollHit = paramMissile.CheckCollision(this.TempShield.xpos, this.TempShield.ypos - 1, this.TempShield.xpos + this.TempShield.width, this.TempShield.ypos + this.TempShield.height + 2);
      if (CollHit) {
        Explosion.AddExplosion(paramMissile.xpos, paramMissile.ypos);
        this.TempShield.power--;
        if (this.TempShield.power <= 0)
          this.ShieldList.removeElement(this.TempShield); 
        return true;
      } 
      this.count++;
    } 
    return false;
  }
  
  public void ClearShields() {
    this.ShieldList = new Vector();
  }
  
  public void Paint(Graphics paramGraphics) {
    this.tempsize = this.ShieldList.size();
    this.count = 0;
    while (this.count < this.tempsize) {
      this.TempShield = this.ShieldList.elementAt(this.count);
      this.TempShield.Paint(paramGraphics);
      this.count++;
    } 
  }
  
  public void Update() {
    this.tempsize = this.ShieldList.size();
    this.count = 0;
    while (this.count < this.tempsize) {
      this.TempShield = this.ShieldList.elementAt(this.count);
      this.TempShield.Update();
      this.count++;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\ShieldGenerator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */