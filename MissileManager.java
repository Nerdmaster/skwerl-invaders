import java.awt.Graphics;
import java.util.Vector;

class MissileManager {
  static final int TOTALFRAMES = 24;
  
  static final int STARTMISSILES = 150;
  
  Vector MissileList = new Vector(150);
  
  int MaxX;
  
  int MaxY;
  
  static int FrameNum = 0;
  
  EnemyManager Squirrels;
  
  Player PlayerSprite;
  
  ShieldGenerator SGObj;
  
  static int update_Count;
  
  static int xv;
  
  static int yv;
  
  static Missile TempMissile;
  
  static double lemYM;
  
  static int tempsize;
  
  static int update_xCount;
  
  static int update_yCount;
  
  static int u_x;
  
  static int u_y;
  
  static int u_d;
  
  static int u_d2;
  
  static StatedSprite u_sprite;
  
  static int paint_Count;
  
  public MissileManager(int paramInt1, int paramInt2, ShieldGenerator paramShieldGenerator, ImageExtractor paramImageExtractor) {
    this.MaxX = paramInt1;
    this.MaxY = paramInt2;
    this.SGObj = paramShieldGenerator;
    Missile.Init(paramImageExtractor, Utils.GetApp());
  }
  
  public void ClearMissiles() {
    this.MissileList = new Vector(150);
  }
  
  public void LaunchEnemyMissile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
    switch (paramInt4) {
      case 0:
        SoundMan.PlayClip(21);
        xv = paramInt5 - paramInt1;
        yv = paramInt6 - paramInt2;
        lemYM = (yv / 5);
        if (yv > 0) {
          yv = 5;
          xv = (int)(xv / lemYM);
        } else {
          yv = -5;
          xv = (int)(xv / -lemYM);
        } 
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, false, 1);
        this.MissileList.addElement(TempMissile);
        break;
      case 1:
        SoundMan.PlayClip(22);
        xv = paramInt5 - paramInt1;
        yv = paramInt6 - paramInt2;
        lemYM = (yv / 4);
        if (yv > 0) {
          yv = 4;
          xv = (int)(xv / lemYM);
        } else {
          yv = -4;
          xv = (int)(xv / -lemYM);
        } 
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, false, 5);
        this.MissileList.addElement(TempMissile);
        break;
      case 2:
        SoundMan.PlayClip(21);
        xv = paramInt5 - paramInt1;
        yv = paramInt6 - paramInt2;
        lemYM = (yv / 4);
        if (yv > 0) {
          yv = 4;
          xv = (int)(xv / lemYM);
        } else {
          yv = -4;
          xv = (int)(xv / -lemYM);
        } 
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, false, 7);
        this.MissileList.addElement(TempMissile);
        break;
      case 3:
        SoundMan.PlayClip(23);
        xv = paramInt5 - paramInt1;
        yv = paramInt6 - paramInt2;
        lemYM = (yv / 5);
        if (yv > 0) {
          yv = 5;
          xv = (int)(xv / lemYM);
        } else {
          yv = -5;
          xv = (int)(xv / -lemYM);
        } 
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, false, 8);
        this.MissileList.addElement(TempMissile);
        break;
    } 
  }
  
  public void LaunchFriendlyMissile(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    switch (paramInt4) {
      case 0:
        SoundMan.PlayClip(11);
        xv = 0;
        yv = -8;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        break;
      case 1:
        SoundMan.PlayClip(11);
        xv = 0;
        yv = -8;
        TempMissile = new Missile(paramInt1, paramInt2, -8, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        TempMissile = new Missile(paramInt1, paramInt2, 8, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        break;
      case 2:
        SoundMan.PlayClip(11);
        xv = 0;
        yv = -8;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        xv = -2;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        xv = 2;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        break;
      case 3:
        SoundMan.PlayClip(11);
        xv = 0;
        yv = -4;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 3);
        this.MissileList.addElement(TempMissile);
        break;
      case 4:
        SoundMan.PlayClip(13);
        xv = 0;
        yv = -6;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 2);
        this.MissileList.addElement(TempMissile);
        break;
      case 6:
        SoundMan.PlayClip(12);
        xv = 0;
        yv = -1;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 1);
        this.MissileList.addElement(TempMissile);
        break;
      case 7:
        SoundMan.PlayClip(11);
        xv = 0;
        yv = -6;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        xv = -1;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        xv = -2;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        xv = 1;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        xv = 2;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 0);
        this.MissileList.addElement(TempMissile);
        break;
      case 8:
        SoundMan.PlayClip(11);
        xv = 0;
        yv = -8;
        TempMissile = new Missile(paramInt1, paramInt2, 0, xv, yv, paramInt3, true, 6);
        this.MissileList.addElement(TempMissile);
        break;
    } 
  }
  
  public final void Paint(Graphics paramGraphics) {
    tempsize = this.MissileList.size();
    paint_Count = 0;
    while (paint_Count < tempsize) {
      try {
        TempMissile = this.MissileList.elementAt(paint_Count);
        TempMissile.Paint(paramGraphics, FrameNum);
      } catch (Exception exception) {}
      paint_Count++;
    } 
  }
  
  public void SetEnemyAndPlayer(EnemyManager paramEnemyManager, Player paramPlayer) {
    this.Squirrels = paramEnemyManager;
    this.PlayerSprite = paramPlayer;
  }
  
  public final void Update() {
    FrameNum++;
    if (FrameNum == 24)
      FrameNum = 0; 
    tempsize = this.MissileList.size();
    update_Count = tempsize - 1;
    while (update_Count >= 0) {
      TempMissile = this.MissileList.elementAt(update_Count);
      TempMissile.Update();
      if ((TempMissile.xpos < 0 && TempMissile.xvel < 0) || (TempMissile.xpos > this.MaxX && TempMissile.xvel > 0) || (TempMissile.ypos < 0 && TempMissile.yvel < 0) || (TempMissile.ypos > this.MaxY && TempMissile.yvel > 0))
        this.MissileList.removeElement(TempMissile); 
      if (TempMissile.Friendly) {
        update_xCount = 0;
        while (update_xCount < 5) {
          update_yCount = 0;
          while (update_yCount < 4) {
            u_sprite = (this.Squirrels.EnemyList[update_xCount][update_yCount]).StateSpriteInfo;
            if (u_sprite.active && u_sprite.CurrentState != 3 && u_sprite.CurrentState != 5 && u_sprite.CurrentState != 7 && TempMissile.CheckCollision(u_sprite.xpos, u_sprite.ypos, u_sprite.xpos + u_sprite.width, u_sprite.ypos + u_sprite.height))
              if (TempMissile.Type == 3) {
                u_d2 = TempMissile.Damage;
                TempMissile.Damage -= (this.Squirrels.EnemyList[update_xCount][update_yCount]).Health;
                this.Squirrels.EnemyList[update_xCount][update_yCount].Hit(u_d2);
                if (TempMissile.Damage < 1) {
                  this.MissileList.removeElement(TempMissile);
                  update_xCount = 99999;
                  update_yCount = 99999;
                } 
              } else if (TempMissile.Type == 1) {
                u_d = TempMissile.Damage;
                this.MissileList.removeElement(TempMissile);
                this.Squirrels.EnemyList[update_xCount][update_yCount].Hit(u_d);
                u_x = TempMissile.xpos;
                u_y = TempMissile.ypos;
                u_d = TempMissile.Damage - 1;
                Explosion.AddExplosion(u_x, u_y);
                xv = 4;
                yv = 2;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                xv = -4;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                yv = -2;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                xv = 4;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                xv = 2;
                yv = 3;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                xv = -2;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                yv = -3;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                xv = 2;
                TempMissile = new Missile(u_x, u_y, 0, xv, yv, u_d, true, 0);
                this.MissileList.addElement(TempMissile);
                update_xCount = 99999;
                update_yCount = 99999;
              } else if (TempMissile.Type == 6) {
                u_d = TempMissile.Damage;
                this.MissileList.removeElement(TempMissile);
                this.Squirrels.EnemyList[update_xCount][update_yCount].Freeze(u_d);
                update_xCount = 99999;
                update_yCount = 99999;
              } else if (TempMissile.Type == 2) {
                u_d = TempMissile.Damage;
                this.Squirrels.EnemyList[update_xCount][update_yCount].Burn(u_d);
              } else {
                u_d = TempMissile.Damage;
                this.MissileList.removeElement(TempMissile);
                this.Squirrels.EnemyList[update_xCount][update_yCount].Hit(TempMissile.Damage);
                update_xCount = 99999;
                update_yCount = 99999;
              }  
            update_yCount++;
          } 
          update_xCount++;
        } 
        if (this.Squirrels.EnemyBoss != null) {
          u_sprite = this.Squirrels.EnemyBoss.StateSpriteInfo;
          if (u_sprite.active && u_sprite.CurrentState != 5 && TempMissile.CheckCollision(u_sprite.xpos, u_sprite.ypos, u_sprite.xpos + u_sprite.width, u_sprite.ypos + u_sprite.height))
            if (TempMissile.Type == 3) {
              u_d2 = TempMissile.Damage;
              TempMissile.Damage -= this.Squirrels.EnemyBoss.Health;
              this.Squirrels.EnemyBoss.Hit(u_d2);
              if (TempMissile.Damage < 1) {
                this.MissileList.removeElement(TempMissile);
                update_xCount = 99999;
                update_yCount = 99999;
              } 
            } else if (TempMissile.Type == 1) {
              this.Squirrels.EnemyBoss.Hit(TempMissile.Damage << 1);
              u_x = TempMissile.xpos;
              u_y = TempMissile.ypos;
              Explosion.AddExplosion(u_x, u_y);
              Explosion.AddExplosion(u_x - 5, u_y);
              Explosion.AddExplosion(u_x, u_y - 5);
              Explosion.AddExplosion(u_x + 5, u_y);
              this.MissileList.removeElement(TempMissile);
              update_xCount = 99999;
              update_yCount = 99999;
            } else if (TempMissile.Type == 6) {
              this.MissileList.removeElement(TempMissile);
              update_xCount = 99999;
              update_yCount = 99999;
            } else if (TempMissile.Type == 2) {
              this.Squirrels.EnemyBoss.Burn(TempMissile.Damage);
              this.MissileList.removeElement(TempMissile);
              update_xCount = 99999;
              update_yCount = 99999;
            } else {
              this.Squirrels.EnemyBoss.Hit(TempMissile.Damage);
              this.MissileList.removeElement(TempMissile);
              update_xCount = 99999;
              update_yCount = 99999;
            }  
        } 
      } else if (this.SGObj.CheckCollision(TempMissile)) {
        this.MissileList.removeElement(TempMissile);
      } else {
        u_sprite = this.PlayerSprite.StateSpriteInfo;
        if (u_sprite.active && u_sprite.CurrentState != 2 && u_sprite.CurrentState != 3 && (TempMissile.CheckCollision(u_sprite.xpos + 8, u_sprite.ypos, u_sprite.xpos + u_sprite.width - 8, u_sprite.ypos + u_sprite.height) || TempMissile.CheckCollision(u_sprite.xpos, u_sprite.ypos + 16, u_sprite.xpos + u_sprite.width, u_sprite.ypos + u_sprite.height))) {
          this.PlayerSprite.Hit(TempMissile.Damage, TempMissile.xpos, TempMissile.ypos);
          this.MissileList.removeElement(TempMissile);
        } 
      } 
      update_Count--;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\MissileManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */