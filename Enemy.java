import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Enemy {
  static final int ST_DANCE = 0;
  
  static final int ST_WAIT = 1;
  
  static final int ST_ATTACK = 2;
  
  static final int ST_HURT = 3;
  
  static final int ST_DIVE = 4;
  
  static final int ST_DYING = 5;
  
  static final int ST_DEAD = 6;
  
  static final int ST_FROZEN = 7;
  
  static final int NUMSTATES = 8;
  
  static final int NUMENEMIES = 10;
  
  static final int WEP_ROCK = 0;
  
  static final int WEP_BEAM = 1;
  
  static final int WEP_RING = 2;
  
  static final int WEP_FIRE = 3;
  
  static final int MAXWAYPOINTS = 10;
  
  static final int MAXVELOCITY = 3;
  
  static final int WAYDIST = 2;
  
  static final int PATTERN_CIRCLE = 0;
  
  static final int PATTERN_DIVE = 1;
  
  int MaxHealth;
  
  int Health;
  
  int Damage;
  
  int Value;
  
  StatedSprite StateSpriteInfo;
  
  int DefState;
  
  int State1;
  
  int State2;
  
  int State3;
  
  int Percent1;
  
  int Percent2;
  
  int Percent3;
  
  int TableX;
  
  int TableY;
  
  int defx;
  
  int defy;
  
  int TimeFrozen;
  
  int Burning;
  
  int WeaponType;
  
  boolean Smart;
  
  int MultiFire;
  
  int CurrentPattern;
  
  Point Target;
  
  int HalfWidth;
  
  Enemy Leader;
  
  static MissileManager MMObj;
  
  static int EnemiesLeft;
  
  static Player CurrentPlayer;
  
  int update_tempx;
  
  int update_tempy;
  
  int update_temppx;
  
  int update_temppy;
  
  int update_waydx;
  
  int update_waydy;
  
  static int AllOffX;
  
  static int AllOffY;
  
  static int p_x;
  
  static int p_y;
  
  static long UpdateTempLong;
  
  public Enemy(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, boolean paramBoolean, int paramInt12) {
    this.Health = paramInt1;
    this.MaxHealth = paramInt1;
    this.Damage = paramInt2;
    this.Value = paramInt3;
    this.DefState = paramInt4;
    this.State1 = paramInt5;
    this.State2 = paramInt6;
    this.State3 = paramInt7;
    this.Percent1 = paramInt8;
    this.Percent2 = paramInt9;
    this.Percent3 = paramInt10;
    this.WeaponType = paramInt11;
    this.Smart = paramBoolean;
    this.MultiFire = paramInt12;
  }
  
  public void Burn(int paramInt) {
    this.Burning = paramInt;
  }
  
  public void Freeze(int paramInt) {
    this.StateSpriteInfo.SetNewState(7);
    this.StateSpriteInfo.NextState = 7;
    this.TimeFrozen = paramInt * 10;
  }
  
  public int GetNumEnemies() {
    return 10;
  }
  
  public void Hit(int paramInt) {
    this.Health -= paramInt;
    if (this.Health < 1) {
      this.StateSpriteInfo.SetNewState(5);
      int i = (int)Utils.GetRandom(0L, 19L);
      switch (i) {
        case 0:
          SoundMan.PlayClip(0);
          break;
        case 1:
          SoundMan.PlayClip(1);
          break;
      } 
    } else {
      this.StateSpriteInfo.SetNewState(3);
    } 
  }
  
  public final void Paint(Graphics paramGraphics) {
    this.StateSpriteInfo.Paint(paramGraphics);
    if (this.StateSpriteInfo.visible) {
      p_x = this.StateSpriteInfo.xpos + this.HalfWidth - 10;
      p_y = this.StateSpriteInfo.ypos + this.StateSpriteInfo.height + 4;
      paramGraphics.setColor(Color.yellow);
      paramGraphics.drawRect(p_x, p_y, 21, 4);
      paramGraphics.setColor(Color.red);
      paramGraphics.fillRect(p_x + 1, p_y + 1, this.Health * 20 / this.MaxHealth, 3);
    } 
  }
  
  static void SetPlayer(Player paramPlayer) {
    CurrentPlayer = paramPlayer;
  }
  
  public final void Update() {
    if (this.StateSpriteInfo.active) {
      this.StateSpriteInfo.Update();
      if (this.TimeFrozen != 0) {
        this.TimeFrozen--;
        if (this.TimeFrozen == 0) {
          this.StateSpriteInfo.CurrentState = 0;
          this.StateSpriteInfo.NextState = 0;
        } else {
          this.StateSpriteInfo.NextState = 7;
        } 
      } 
      if (this.Burning != 0 && Utils.GetRandom(1L, 50L) <= this.Burning) {
        Explosion.AddExplosion(this.StateSpriteInfo.xpos + 10, this.StateSpriteInfo.ypos + 16);
        Explosion.AddExplosion(this.StateSpriteInfo.xpos + 16, this.StateSpriteInfo.ypos + 16);
        Explosion.AddExplosion(this.StateSpriteInfo.xpos + 22, this.StateSpriteInfo.ypos + 16);
        this.Health--;
        if (this.Health < 1) {
          this.StateSpriteInfo.SetNewState(5);
          this.Burning = 0;
        } 
      } 
      if (this.StateSpriteInfo.Action == 5) {
        this.StateSpriteInfo.CurrentState = 6;
        this.StateSpriteInfo.visible = false;
        this.StateSpriteInfo.active = false;
        this.StateSpriteInfo.Action = 0;
        EnemyManager.CreateFloater(this.StateSpriteInfo.xpos + 16, this.StateSpriteInfo.ypos + 26, this.Value);
        EnemiesLeft--;
      } else if (this.StateSpriteInfo.CurrentState == 2 && this.StateSpriteInfo.FrameCount == 0 && (this.StateSpriteInfo.CurrentFrame == 5 || (this.StateSpriteInfo.CurrentFrame == 7 && this.MultiFire >= 1) || (this.StateSpriteInfo.CurrentFrame == 6 && this.MultiFire >= 2))) {
        if (this.Smart) {
          MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + 16, this.StateSpriteInfo.ypos + 36, this.Damage, this.WeaponType, CurrentPlayer.StateSpriteInfo.xpos + 16, CurrentPlayer.StateSpriteInfo.ypos + 16);
        } else {
          MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + 16, this.StateSpriteInfo.ypos + 36, this.Damage, this.WeaponType, this.StateSpriteInfo.xpos + 16, 500);
        } 
      } 
      if (this.StateSpriteInfo.CurrentState != 5 && this.CurrentPattern == 0) {
        this.StateSpriteInfo.xpos = this.defx + AllOffX;
        this.StateSpriteInfo.ypos = this.defy + AllOffY;
      } 
    } 
    if (this.StateSpriteInfo.CurrentState == 0) {
      UpdateTempLong = Utils.GetRandom(0L, 10000L);
      if (UpdateTempLong <= this.Percent1) {
        this.StateSpriteInfo.NextState = this.State1;
      } else if (UpdateTempLong <= this.Percent2) {
        this.StateSpriteInfo.NextState = this.State2;
      } else if (UpdateTempLong <= this.Percent3) {
        this.StateSpriteInfo.NextState = this.State3;
      } 
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Enemy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */