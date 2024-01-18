import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player {
  static final int PLAYERWIDTH = 32;
  
  static final int PLAYERHEIGHT = 32;
  
  static final int HALFWIDTH = 16;
  
  static final int HALFHEIGHT = 16;
  
  static final int PLAYERMAXY = 320;
  
  static final int PLAYERMINY = 280;
  
  static final int STARTING_CASH = 0;
  
  static final int PLAYERNORMS = 0;
  
  static final int PLAYERFIRINGS = 1;
  
  static final int PLAYERHITS = 2;
  
  static final int PLAYERDYINGS = 3;
  
  static final int NUMSTATES = 4;
  
  static final int PLAYERNORMY = 0;
  
  static final int PLAYERFIRINGY = 32;
  
  static final int PLAYERHITY = 64;
  
  static final int PLAYERDYINGY = 96;
  
  static final int PLAYERNORMFR = 6;
  
  static final int PLAYERFIRINGFR = 4;
  
  static final int PLAYERHITFR = 6;
  
  static final int PLAYERDYINGFR = 2;
  
  int[] WeaponUses;
  
  int[] UpgradeLevel;
  
  int Cash;
  
  int TotalPoints;
  
  int CurrentWeapon;
  
  int Power;
  
  int MaxPower;
  
  int DamageMod;
  
  int SpeedMod;
  
  int PlayerYPos;
  
  StatedSprite StateSpriteInfo;
  
  int TargetX;
  
  int TargetY;
  
  boolean Loaded;
  
  boolean FireNext;
  
  int FireDelay;
  
  boolean GoLeft;
  
  boolean GoRight;
  
  boolean GoUp;
  
  boolean GoDown;
  
  boolean MouseOn;
  
  boolean SpriteDead;
  
  int AppWidth;
  
  int AppHeight;
  
  MissileManager MissileManagerObject;
  
  ShieldGenerator SGObj;
  
  int CashMult;
  
  int PointMult;
  
  static Weapon TempWeapon;
  
  static final int YMIN = -3;
  
  static final int YMAX = 3;
  
  static final int YDELAY = 1;
  
  static int YDelayLeft = 0;
  
  static int YFluctuation = 0;
  
  static int YDirection = 1;
  
  static int DeathCount;
  
  static int u_Tempxpos;
  
  static int u_Tempypos;
  
  static Color colShowWeapon = new Color(255, 150, 150);
  
  static Font LargeFont = new Font("Serif", 0, 18);
  
  static Font MedFont = new Font("Serif", 0, 15);
  
  static Font SmallFont = new Font("Serif", 0, 12);
  
  static int Count;
  
  static int tempint;
  
  static String tempstr;
  
  static int mm_xtemp;
  
  static int mm_ytemp;
  
  public Player(ImageExtractor paramImageExtractor, MissileManager paramMissileManager, int paramInt1, int paramInt2, ShieldGenerator paramShieldGenerator) {
    this.MissileManagerObject = paramMissileManager;
    this.SGObj = paramShieldGenerator;
    this.AppWidth = paramInt1;
    this.AppHeight = paramInt2;
    this.StateSpriteInfo = new StatedSprite(240, 320, 32, 32);
    this.PlayerYPos = 320;
    this.StateSpriteInfo.InitMain(4, 0);
    this.StateSpriteInfo.InitState(0, 6, 2);
    this.StateSpriteInfo.InitState(1, 4, 1);
    this.StateSpriteInfo.InitState(2, 6, 2);
    this.StateSpriteInfo.InitState(3, 2, 3);
    this.StateSpriteInfo.LoadImages(paramImageExtractor, 0, 0);
    this.StateSpriteInfo.LoadImages(paramImageExtractor, 1, 32);
    this.StateSpriteInfo.LoadImages(paramImageExtractor, 2, 64);
    this.StateSpriteInfo.LoadImages(paramImageExtractor, 3, 96);
    Reset();
  }
  
  public void BuyWeapon(int paramInt) {
    this.Cash -= 50;
    this.Cash += (Weapon.WeaponArray[3]).Cost;
    Weapon.BuyWeapon(this, paramInt, 3);
  }
  
  public void Hit(int paramInt1, int paramInt2, int paramInt3) {
    if (this.StateSpriteInfo.active) {
      this.StateSpriteInfo.SetNewState(2);
      this.Power -= paramInt1;
      this.Power += this.DamageMod * paramInt1 / 100;
      Explosion.AddExplosion(paramInt2, paramInt3);
    } 
  }
  
  public void KeyPressed(KeyEvent paramKeyEvent) {
    this.MouseOn = false;
    if (this.StateSpriteInfo.active)
      switch (paramKeyEvent.getKeyCode()) {
        case 67:
          if (this.StateSpriteInfo.CurrentState != 1)
            NextWeapon(); 
          break;
        case 38:
        case 56:
          this.GoUp = true;
          break;
        case 40:
        case 50:
          this.GoDown = true;
          break;
        case 39:
        case 54:
          this.GoRight = true;
          break;
        case 37:
        case 52:
          this.GoLeft = true;
          break;
        case 32:
          if (this.WeaponUses[this.CurrentWeapon] != 0) {
            this.Loaded = true;
            this.FireNext = true;
          } 
          break;
      }  
  }
  
  public void KeyReleased(KeyEvent paramKeyEvent) {
    switch (paramKeyEvent.getKeyCode()) {
      case 38:
      case 56:
        this.GoUp = false;
        break;
      case 40:
      case 50:
        this.GoDown = false;
        break;
      case 39:
      case 54:
        this.GoRight = false;
        break;
      case 37:
      case 52:
        this.GoLeft = false;
        break;
      case 32:
        this.Loaded = false;
        break;
    } 
  }
  
  public void MouseMoved(MouseEvent paramMouseEvent) {
    this.MouseOn = true;
    if (this.StateSpriteInfo.active) {
      mm_xtemp = paramMouseEvent.getX() - 16;
      mm_ytemp = paramMouseEvent.getY() - 16;
      if (mm_xtemp + 32 > this.AppWidth) {
        mm_xtemp = this.AppWidth - 32;
      } else if (mm_xtemp < 0) {
        mm_xtemp = 0;
      } 
      if (mm_ytemp > 320) {
        mm_ytemp = 320;
      } else if (mm_ytemp < 280) {
        mm_ytemp = 280;
      } 
      this.TargetX = mm_xtemp;
      this.TargetY = mm_ytemp;
    } 
  }
  
  public void MousePressed(MouseEvent paramMouseEvent) {
    this.MouseOn = true;
    if (this.StateSpriteInfo.active) {
      if ((paramMouseEvent.getModifiers() & 0x10) == 16 && this.WeaponUses[this.CurrentWeapon] != 0) {
        this.Loaded = true;
        this.FireNext = true;
      } 
      if ((paramMouseEvent.getModifiers() & 0x4) == 4 && this.StateSpriteInfo.CurrentState != 1)
        NextWeapon(); 
    } 
  }
  
  public void MouseReleased(MouseEvent paramMouseEvent) {
    this.MouseOn = true;
    this.Loaded = false;
  }
  
  public void NextWeapon() {
    int i = this.CurrentWeapon;
    this.FireNext = false;
    this.Loaded = false;
    this.CurrentWeapon++;
    if (this.CurrentWeapon >= Weapon.GetArraySize())
      this.CurrentWeapon = 0; 
    while (this.WeaponUses[this.CurrentWeapon] == -2) {
      if (this.CurrentWeapon == i) {
        this.CurrentWeapon = -1;
        this.WeaponUses[0] = (Weapon.WeaponArray[0]).Uses;
      } 
      this.CurrentWeapon++;
      if (this.CurrentWeapon >= Weapon.GetArraySize())
        this.CurrentWeapon = 0; 
    } 
  }
  
  public final void Paint(Graphics paramGraphics) {
    if (this.StateSpriteInfo.visible) {
      this.StateSpriteInfo.Paint(paramGraphics);
      tempint = this.WeaponUses[this.CurrentWeapon];
      if (tempint == -1) {
        tempstr = (Weapon.WeaponArray[this.CurrentWeapon]).Name;
      } else {
        tempstr = String.valueOf((Weapon.WeaponArray[this.CurrentWeapon]).Name) + ": " + tempint;
      } 
      paramGraphics.setColor(Color.white);
      paramGraphics.setFont(MedFont);
      paramGraphics.drawString(tempstr, 10, this.AppHeight - 40);
      paramGraphics.fillRect(5, this.AppHeight - 30, 100, 5);
      paramGraphics.setColor(Color.red);
      paramGraphics.fillRect(Count + 5, this.AppHeight - 30, this.FireDelay, 5);
      paramGraphics.setColor(Color.green);
      paramGraphics.drawString("Hull: " + this.Power + " / " + this.MaxPower, this.AppWidth - 104, this.AppHeight - 40);
      paramGraphics.drawRect(this.AppWidth - 125, this.AppHeight - 32, 101, 7);
      paramGraphics.setColor(Color.red);
      paramGraphics.fillRect(this.AppWidth - 124, this.AppHeight - 31, 100 * this.Power / this.MaxPower, 5);
    } 
  }
  
  public void Reset() {
    SetPointRatio(50);
    this.Cash = 0;
    this.TotalPoints = this.Cash;
    this.CurrentWeapon = 0;
    this.WeaponUses = new int[Weapon.GetArraySize()];
    this.UpgradeLevel = new int[4];
    this.WeaponUses[0] = (Weapon.WeaponArray[0]).Uses;
    this.SpriteDead = false;
    for (byte b1 = 1; b1 < Weapon.GetArraySize(); b1++)
      this.WeaponUses[b1] = -2; 
    for (byte b2 = 0; b2 < 4; b2++)
      this.UpgradeLevel[b2] = 0; 
    this.MaxPower = 100;
    this.Power = this.MaxPower;
    this.SpeedMod = 5;
    this.DamageMod = 0;
    this.StateSpriteInfo.Enable();
    this.StateSpriteInfo.DefaultState = 0;
    this.StateSpriteInfo.SetNewState(this.StateSpriteInfo.DefaultState);
    this.StateSpriteInfo.NextState = this.StateSpriteInfo.DefaultState;
    this.StateSpriteInfo.Action = 0;
  }
  
  public void ResetShipStatus() {
    this.GoLeft = false;
    this.GoRight = false;
    this.StateSpriteInfo.xpos = 240;
    this.TargetX = this.StateSpriteInfo.xpos;
    this.PlayerYPos = 320;
    this.TargetY = this.PlayerYPos;
  }
  
  public void SellWeapon(int paramInt) {
    this.Cash += (Weapon.WeaponArray[paramInt]).Cost >> 1;
    Weapon.SellWeapon(this, 2, paramInt);
  }
  
  public void SetDifficulty(int paramInt) {
    switch (paramInt) {
      case 0:
        SetPointRatio(25);
        break;
      case 1:
        SetPointRatio(50);
        break;
      case 2:
        SetPointRatio(75);
        break;
    } 
  }
  
  public void SetPointRatio(int paramInt) {
    this.PointMult = paramInt;
    this.CashMult = 100 - paramInt;
  }
  
  public final void Update() {
    if (this.Power <= 0 && this.StateSpriteInfo.CurrentState != 3) {
      this.Power = 0;
      this.StateSpriteInfo.SetNewState(3);
      this.StateSpriteInfo.DefaultState = 3;
      DeathCount = 0;
      this.StateSpriteInfo.active = false;
      ResetShipStatus();
    } 
    if (this.GoUp && this.StateSpriteInfo.CurrentState != 3) {
      this.PlayerYPos -= this.SpeedMod >> 1;
      if (this.PlayerYPos < 280)
        this.PlayerYPos = 280; 
    } 
    if (this.GoDown && this.StateSpriteInfo.CurrentState != 3) {
      this.PlayerYPos += this.SpeedMod >> 1;
      if (this.PlayerYPos > 320)
        this.PlayerYPos = 320; 
    } 
    if (this.GoLeft && this.StateSpriteInfo.CurrentState != 3) {
      this.StateSpriteInfo.xpos -= this.SpeedMod;
      if (this.StateSpriteInfo.xpos < 0)
        this.StateSpriteInfo.xpos = 0; 
    } 
    if (this.GoRight && this.StateSpriteInfo.CurrentState != 3) {
      this.StateSpriteInfo.xpos += this.SpeedMod;
      if (this.StateSpriteInfo.xpos > this.AppWidth - this.StateSpriteInfo.width)
        this.StateSpriteInfo.xpos = this.AppWidth - this.StateSpriteInfo.width; 
    } 
    if (this.StateSpriteInfo.Action == 3 && DeathCount <= 60) {
      DeathCount++;
      if (DeathCount % 5 == 0)
        Explosion.AddExplosion(this.StateSpriteInfo.xpos + 16, this.StateSpriteInfo.ypos + 16); 
      if (DeathCount > 60) {
        this.StateSpriteInfo.visible = false;
        this.SpriteDead = true;
      } 
    } 
    this.StateSpriteInfo.Update();
    if (YDelayLeft-- <= 0) {
      YDelayLeft = 1;
      YFluctuation += YDirection;
      if (YFluctuation >= 3 || YFluctuation <= -3)
        YDirection = -YDirection; 
    } 
    this.StateSpriteInfo.ypos = this.PlayerYPos + YFluctuation;
    if (this.FireDelay > 0) {
      this.FireDelay--;
    } else if (this.StateSpriteInfo.Action == 1) {
      TempWeapon = Weapon.WeaponArray[this.CurrentWeapon];
      this.FireDelay = TempWeapon.FireTime;
      this.StateSpriteInfo.Action = 0;
      if (TempWeapon.Uses != -1)
        this.WeaponUses[this.CurrentWeapon] = this.WeaponUses[this.CurrentWeapon] - 1; 
      if (TempWeapon.Special == 5) {
        this.SGObj.AddShield(this.StateSpriteInfo.xpos + 16, this.PlayerYPos - 8, TempWeapon.Damage);
      } else {
        this.MissileManagerObject.LaunchFriendlyMissile(this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1) - 5, this.PlayerYPos, TempWeapon.Damage, TempWeapon.Special);
      } 
    } else if ((this.Loaded || this.FireNext) && this.StateSpriteInfo.CurrentState == 0) {
      this.StateSpriteInfo.SetNewState(1);
      this.FireNext = false;
    } 
    u_Tempxpos = this.StateSpriteInfo.xpos;
    u_Tempypos = this.PlayerYPos;
    if (this.MouseOn) {
      if (this.TargetX > u_Tempxpos) {
        if (this.TargetX - u_Tempxpos > this.SpeedMod) {
          this.StateSpriteInfo.xpos += this.SpeedMod;
        } else {
          this.StateSpriteInfo.xpos = this.TargetX;
        } 
      } else if (this.TargetX < u_Tempxpos) {
        if (u_Tempxpos - this.TargetX > this.SpeedMod) {
          this.StateSpriteInfo.xpos -= this.SpeedMod;
        } else {
          this.StateSpriteInfo.xpos = this.TargetX;
        } 
      } 
      if (this.TargetY > u_Tempypos) {
        if (this.TargetY - u_Tempypos > this.SpeedMod) {
          this.PlayerYPos += this.SpeedMod >> 1;
        } else {
          this.PlayerYPos = this.TargetY;
        } 
      } else if (this.TargetY < u_Tempypos) {
        if (u_Tempypos - this.TargetY > this.SpeedMod) {
          this.PlayerYPos -= this.SpeedMod >> 1;
        } else {
          this.PlayerYPos = this.TargetY;
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Player.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */