import java.awt.Color;
import java.awt.Graphics;

public class Boss {
  static final int BOSS_SPACESHIP = 0;
  
  static final int BOSS_HIPPY = 1;
  
  static final int BOSS_RAPPY = 2;
  
  static final int ST_MOVE1 = 0;
  
  static final int ST_MOVE2 = 1;
  
  static final int ST_MOVE3 = 2;
  
  static final int ST_PREATTACK = 3;
  
  static final int ST_ATTACK = 4;
  
  static final int ST_DYING = 5;
  
  static final int ST_DEAD = 6;
  
  static final int NUMSTATES = 7;
  
  static final int SPR_MOVE = 0;
  
  static final int SPR_PREATTACK = 1;
  
  static final int SPR_ATTACK = 2;
  
  static final int SPR_DYING = 3;
  
  static final int SPR_DEAD = 4;
  
  static final int NUMSPRITES = 5;
  
  static final int MAX_SPAWNS = 5;
  
  static final int SUB_REGULAR = 0;
  
  static final int SUB_RADIAL = 1;
  
  static final int SUB_COMPONENT = 2;
  
  int BossType;
  
  int MaxHealth;
  
  int Health;
  
  int Damage1;
  
  int Damage2;
  
  int DamageSpecial;
  
  int Value;
  
  int WeaponType1;
  
  int WeaponType2;
  
  int MovePercent1;
  
  int MovePercent2;
  
  int MovePercent3;
  
  int Attack1Percent;
  
  int Attack2Percent;
  
  int SpecAtkPercent;
  
  int Turret1XOffset;
  
  int Turret1YOffset;
  
  int Turret2XOffset;
  
  int Turret2YOffset;
  
  boolean SubCreaturesOn;
  
  Enemy[] SubCreature = new Enemy[5];
  
  int[] SubCreatureType = new int[5];
  
  int ActualState;
  
  StatedSprite StateSpriteInfo;
  
  int width;
  
  int height;
  
  int[] YPos = new int[5];
  
  int[] Frames = new int[5];
  
  int[] Delay = new int[5];
  
  int Burning;
  
  int PatternFrame;
  
  int PatternLeg;
  
  boolean PatternOn;
  
  boolean AlreadyDead;
  
  int RappyFireNext;
  
  boolean Smart;
  
  int OriginX;
  
  int OriginY;
  
  static ImageExtractor BossImageExtractor;
  
  static MissileManager MMObj;
  
  static Player CurrentPlayer;
  
  static int p_x;
  
  static int p_y;
  
  static long u_Rand;
  
  static int u_State;
  
  static int u_x;
  
  static int u_y;
  
  static long us_Rand;
  
  static int us_TargetX;
  
  static int us_TargetY;
  
  static int us_PosX;
  
  static int us_PosY;
  
  static int us_count;
  
  static long uh_Rand;
  
  static int uh_TargetX;
  
  static int uh_TargetY;
  
  static int uh_PosX;
  
  static int uh_PosY;
  
  static int uh_count;
  
  static long ur_Rand;
  
  static int ur_TargetX;
  
  static int ur_TargetY;
  
  static int ur_PosX;
  
  static int ur_PosY;
  
  static int ur_count;
  
  public Boss(int paramInt1, int paramInt2) {
    for (byte b1 = 0; b1 < 5; b1++) {
      this.YPos[b1] = 0;
      this.Frames[b1] = 1;
      this.Delay[b1] = 4;
    } 
    this.AlreadyDead = false;
    this.RappyFireNext = 0;
    switch (paramInt1) {
      case 5:
      case 10:
        this.BossType = 0;
        this.Health = 40 * paramInt1 / 5;
        this.Damage1 = 6 * paramInt1 / 5;
        this.Damage2 = 6 * paramInt1 / 5;
        this.DamageSpecial = 10 * paramInt1 / 5;
        this.WeaponType1 = 1;
        this.WeaponType2 = 1;
        this.MovePercent1 = 40;
        this.MovePercent2 = 80;
        this.MovePercent3 = 0;
        this.SpecAtkPercent = 100;
        this.Value = 200 * paramInt1 / 5;
        this.width = 164;
        this.height = 68;
        this.YPos[0] = 0;
        this.YPos[1] = 68;
        this.YPos[3] = 136;
        this.Frames[0] = 6;
        this.Frames[1] = 8;
        this.Frames[3] = 2;
        this.Delay[0] = 2;
        this.Delay[1] = 2;
        this.Delay[3] = 3;
        this.SubCreaturesOn = false;
        this.Turret1XOffset = 32;
        this.Turret1YOffset = 68;
        this.Turret2XOffset = this.width - 32;
        this.Turret2YOffset = 68;
        if (paramInt1 >= 10) {
          this.Attack1Percent = 20;
          this.Attack2Percent = 20;
          this.Smart = true;
        } else {
          this.Attack1Percent = 40;
          this.Attack2Percent = 40;
          this.Smart = false;
        } 
        this.OriginX = 174;
        this.OriginY = 30;
        break;
      case 15:
      case 20:
        this.BossType = 1;
        this.Health = 50 * paramInt1 / 5;
        this.Damage1 = 8 * paramInt1 / 5;
        this.Damage2 = 8 * paramInt1 / 5;
        this.WeaponType1 = 0;
        this.WeaponType2 = 0;
        this.MovePercent1 = 40;
        this.MovePercent2 = 80;
        this.MovePercent3 = 100;
        this.SpecAtkPercent = 100;
        this.Value = 200 * paramInt1 / 5;
        this.width = 120;
        this.height = 148;
        this.YPos[0] = 205;
        this.YPos[3] = 353;
        this.Frames[0] = 4;
        this.Frames[3] = 7;
        this.Delay[0] = 4;
        this.Delay[3] = 10;
        this.SubCreaturesOn = false;
        this.Turret1XOffset = 19;
        this.Turret1YOffset = 33;
        this.Turret2XOffset = 32;
        this.Turret2YOffset = 33;
        this.Attack1Percent = 25;
        this.Attack2Percent = 25;
        this.Smart = true;
        this.OriginX = 230;
        this.OriginY = 10;
        break;
      case 25:
        this.BossType = 2;
        this.Health = 40 * paramInt1 / 5;
        this.Damage1 = 10 * paramInt1 / 5;
        this.Damage2 = 10 * paramInt1 / 5;
        this.DamageSpecial = paramInt1 / 5;
        this.WeaponType1 = 3;
        this.WeaponType2 = 3;
        this.MovePercent1 = 50;
        this.MovePercent2 = 0;
        this.MovePercent3 = 0;
        this.SpecAtkPercent = 100;
        this.Value = 400 * paramInt1 / 5;
        this.width = 128;
        this.height = 196;
        this.YPos[0] = 0;
        this.YPos[1] = 196;
        this.YPos[3] = 392;
        this.Frames[0] = 6;
        this.Frames[1] = 2;
        this.Frames[3] = 8;
        this.Delay[0] = 4;
        this.Delay[1] = 4;
        this.Delay[3] = 5;
        this.SubCreaturesOn = false;
        this.Turret1XOffset = 48;
        this.Turret1YOffset = 153;
        this.Turret2XOffset = 78;
        this.Turret2YOffset = 156;
        this.Attack1Percent = 0;
        this.Attack2Percent = 0;
        this.Smart = true;
        this.OriginX = 230;
        this.OriginY = 10;
        break;
    } 
    this.Health *= paramInt2;
    this.Damage1 *= paramInt2;
    this.Damage2 *= paramInt2;
    this.DamageSpecial *= paramInt2;
    this.Value *= paramInt2;
    if (this.Attack1Percent > 0)
      this.Attack1Percent += (paramInt2 - 1) * 5; 
    if (this.Attack2Percent > 0)
      this.Attack2Percent += (paramInt2 - 1) * 5; 
    if (this.Smart)
      this.Value *= 2; 
    this.MaxHealth = this.Health;
    this.StateSpriteInfo = new StatedSprite(this.OriginX, this.OriginY, this.width, this.height);
    this.StateSpriteInfo.InitMain(5, -1);
    for (byte b2 = 0; b2 < 5; b2++)
      this.StateSpriteInfo.InitState(b2, this.Frames[b2], this.Delay[b2]); 
    for (byte b3 = 0; b3 < 5; b3++)
      this.StateSpriteInfo.LoadImages(BossImageExtractor, b3, this.YPos[b3]); 
    this.StateSpriteInfo.CurrentState = 0;
    this.StateSpriteInfo.NextState = 0;
    this.ActualState = 0;
    this.PatternOn = false;
    this.PatternFrame = 0;
    if (this.BossType == 1) {
      this.width /= 2;
      this.StateSpriteInfo.width /= 2;
    } 
  }
  
  public void Burn(int paramInt) {
    this.Burning = paramInt;
  }
  
  public void Hit(int paramInt) {
    if (this.BossType == 2 && this.StateSpriteInfo.CurrentState == 0) {
      if (this.Health < this.MaxHealth)
        this.Health++; 
      this.RappyFireNext++;
    } else {
      this.Health -= paramInt;
      if (this.Health <= 0) {
        if (this.StateSpriteInfo.CurrentState != 3) {
          this.StateSpriteInfo.SetNewState(3);
          this.StateSpriteInfo.NextState = 3;
          this.ActualState = 5;
          this.PatternOn = true;
          this.PatternFrame = 0;
        } 
        this.Health = 0;
      } 
    } 
  }
  
  public void Paint(Graphics paramGraphics) {
    this.StateSpriteInfo.Paint(paramGraphics);
    if (this.StateSpriteInfo.visible) {
      p_x = this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1) - 51;
      p_y = this.StateSpriteInfo.ypos + this.StateSpriteInfo.height + 10;
      paramGraphics.setColor(Color.yellow);
      paramGraphics.drawRect(p_x, p_y, 101, 5);
      paramGraphics.setColor(Color.red);
      paramGraphics.fillRect(p_x + 1, p_y + 1, this.Health * 100 / this.MaxHealth, 4);
    } 
  }
  
  static void SetupBossImage(ImageExtractor paramImageExtractor) {
    BossImageExtractor = paramImageExtractor;
  }
  
  static void SetupMissileManager(MissileManager paramMissileManager) {
    MMObj = paramMissileManager;
  }
  
  static void SetupPlayer(Player paramPlayer) {
    CurrentPlayer = paramPlayer;
  }
  
  public void Update() {
    this.StateSpriteInfo.Update();
    u_State = this.StateSpriteInfo.CurrentState;
    if (this.Burning != 0 && Utils.GetRandom(1L, 250L) <= this.Burning) {
      Explosion.AddExplosion(this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1) + 20, this.StateSpriteInfo.ypos + 40);
      Explosion.AddExplosion(this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1), this.StateSpriteInfo.ypos + 40);
      Explosion.AddExplosion(this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1) - 20, this.StateSpriteInfo.ypos + 40);
      this.Health--;
      if (this.Health < 1) {
        this.StateSpriteInfo.SetNewState(3);
        this.StateSpriteInfo.NextState = 3;
        this.Burning = 0;
      } 
    } 
    if (u_State == 0)
      if (this.Smart) {
        u_Rand = Utils.GetRandom(1L, 1000L);
        if (this.Attack1Percent < 0) {
          if (this.StateSpriteInfo.FrameCount == 0 && this.StateSpriteInfo.CurrentFrame == -this.Attack1Percent) {
            u_x = CurrentPlayer.StateSpriteInfo.xpos;
            u_y = CurrentPlayer.StateSpriteInfo.ypos;
            MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + this.Turret1XOffset, this.StateSpriteInfo.ypos + this.Turret1YOffset, this.Damage1, this.WeaponType1, u_x + 16, u_y + 16);
          } 
        } else if (u_Rand <= this.Attack1Percent) {
          u_x = CurrentPlayer.StateSpriteInfo.xpos;
          u_y = CurrentPlayer.StateSpriteInfo.ypos;
          MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + this.Turret1XOffset, this.StateSpriteInfo.ypos + this.Turret1YOffset, this.Damage1, this.WeaponType1, u_x + 16, u_y + 16);
        } 
        u_Rand = Utils.GetRandom(1L, 1000L);
        if (this.Attack2Percent < 0) {
          if (this.StateSpriteInfo.FrameCount == 0 && this.StateSpriteInfo.CurrentFrame == -this.Attack2Percent) {
            u_x = CurrentPlayer.StateSpriteInfo.xpos;
            u_y = CurrentPlayer.StateSpriteInfo.ypos;
            MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + this.Turret2XOffset, this.StateSpriteInfo.ypos + this.Turret2YOffset, this.Damage2, this.WeaponType2, u_x + 16, u_y + 16);
          } 
        } else if (u_Rand <= this.Attack2Percent) {
          u_x = CurrentPlayer.StateSpriteInfo.xpos;
          u_y = CurrentPlayer.StateSpriteInfo.ypos;
          MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + this.Turret2XOffset, this.StateSpriteInfo.ypos + this.Turret2YOffset, this.Damage2, this.WeaponType2, u_x + 16, u_y + 16);
        } 
      } else {
        u_Rand = Utils.GetRandom(1L, 1000L);
        u_x = this.StateSpriteInfo.xpos;
        u_y = this.StateSpriteInfo.ypos;
        if (u_Rand <= this.Attack1Percent)
          MMObj.LaunchEnemyMissile(u_x + this.Turret1XOffset, u_y + this.Turret1YOffset, this.Damage1, this.WeaponType1, u_x + this.Turret1XOffset, 512); 
        u_Rand = Utils.GetRandom(1L, 1000L);
        if (u_Rand <= this.Attack2Percent)
          MMObj.LaunchEnemyMissile(u_x + this.Turret2XOffset, u_y + this.Turret2YOffset, this.Damage2, this.WeaponType2, u_x + this.Turret2XOffset, 512); 
      }  
    switch (this.BossType) {
      case 0:
        UpdateSpaceship();
        break;
      case 1:
        UpdateHippy();
        break;
      case 2:
        UpdateRappy();
        break;
    } 
  }
  
  public void UpdateHippy() {
    u_State = this.StateSpriteInfo.CurrentState;
    if (u_State == 0 && this.PatternOn == false) {
      uh_Rand = Utils.GetRandom(0L, 100L);
      if (uh_Rand <= this.MovePercent1) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 0;
      } else if (uh_Rand <= this.MovePercent2) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 1;
      } else if (uh_Rand <= this.MovePercent3) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 2;
      } else {
      
      } 
    } 
    if (this.PatternOn)
      switch (this.ActualState) {
        case 0:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame--;
              if (this.PatternFrame == -50)
                this.PatternLeg = 2; 
              break;
            case 2:
              this.PatternFrame++;
              if (this.PatternFrame == 50)
                this.PatternLeg = 3; 
              break;
            case 3:
              this.PatternFrame--;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
              } 
              break;
          } 
          this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
          this.StateSpriteInfo.ypos = this.OriginY;
          break;
        case 1:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame--;
              if (this.PatternFrame == -50)
                this.PatternLeg = 2; 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + 25 - (int)(Math.sqrt(-Math.pow(this.PatternFrame, 2.0D) + 2500.0D) / 2.0D);
              break;
            case 2:
              this.PatternFrame++;
              if (this.PatternFrame == 50)
                this.PatternLeg = 3; 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + 25 + (int)(Math.sqrt(-Math.pow(this.PatternFrame, 2.0D) + 2500.0D) / 2.0D);
              break;
            case 3:
              this.PatternFrame--;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
              } 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + 25 - (int)(Math.sqrt(-Math.pow(this.PatternFrame, 2.0D) + 2500.0D) / 2.0D);
              break;
          } 
          break;
        case 2:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame++;
              if (this.PatternFrame == 50)
                this.PatternLeg = 2; 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + this.PatternFrame;
              break;
            case 2:
              this.PatternFrame++;
              if (this.PatternFrame == 100)
                this.PatternLeg = 3; 
              this.StateSpriteInfo.xpos = this.OriginX + 100 - this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + this.PatternFrame;
              break;
            case 3:
              this.PatternFrame--;
              if (this.PatternFrame == 50)
                this.PatternLeg = 4; 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame - 100;
              this.StateSpriteInfo.ypos = this.OriginY + this.PatternFrame;
              break;
            case 4:
              this.PatternFrame--;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
              } 
              this.StateSpriteInfo.xpos = this.OriginX - this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + this.PatternFrame;
              break;
          } 
          break;
        case 5:
          this.PatternFrame++;
          if (this.StateSpriteInfo.Action == 3) {
            (this.StateSpriteInfo.StateSprites[3]).FrameDelay = -1;
            this.StateSpriteInfo.CurrentFrame = (this.StateSpriteInfo.StateSprites[3]).Frames - 1;
          } 
          if (this.PatternFrame > 120) {
            this.ActualState = 6;
            this.StateSpriteInfo.CurrentState = 4;
            this.StateSpriteInfo.NextState = 4;
            this.StateSpriteInfo.DefaultState = 4;
            this.StateSpriteInfo.Disable();
            this.PatternOn = false;
          } 
          break;
      }  
    if (this.StateSpriteInfo.CurrentState == 4 && !this.AlreadyDead) {
      EnemyManager.CreateFloater(this.StateSpriteInfo.xpos + (this.width >> 1), this.StateSpriteInfo.ypos + (this.height >> 1), this.Value);
      Enemy.EnemiesLeft--;
      this.AlreadyDead = true;
    } 
  }
  
  public void UpdateRappy() {
    u_State = this.StateSpriteInfo.CurrentState;
    if (this.RappyFireNext > 0) {
      MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1), this.StateSpriteInfo.ypos + this.StateSpriteInfo.height, this.Damage1, 2, CurrentPlayer.StateSpriteInfo.xpos + 16, CurrentPlayer.StateSpriteInfo.ypos + 16);
      this.RappyFireNext--;
    } 
    if (u_State == 0 && this.PatternOn == false) {
      ur_Rand = Utils.GetRandom(0L, 100L);
      if (ur_Rand <= this.MovePercent1) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 0;
      } else if (ur_Rand > this.MovePercent2 && ur_Rand > this.MovePercent3 && ur_Rand <= this.SpecAtkPercent) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 3;
        this.StateSpriteInfo.SetNewState(1);
        this.StateSpriteInfo.NextState = 1;
      } 
    } 
    if (this.PatternOn)
      switch (this.ActualState) {
        case 0:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame -= 2;
              if (this.PatternFrame == -120)
                this.PatternLeg = 2; 
              break;
            case 2:
              this.PatternFrame += 2;
              if (this.PatternFrame == 120)
                this.PatternLeg = 3; 
              break;
            case 3:
              this.PatternFrame -= 2;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
              } 
              break;
          } 
          this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
          this.StateSpriteInfo.ypos = this.OriginY;
          break;
        case 3:
          switch (this.PatternLeg) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8:
              this.PatternFrame++;
              if (this.PatternFrame == 60) {
                this.PatternLeg++;
                this.PatternFrame = 0;
                this.StateSpriteInfo.xpos = (int)Utils.GetRandom(44L, 340L);
                this.StateSpriteInfo.ypos = (int)Utils.GetRandom(20L, 60L);
              } 
              break;
            case 7:
              this.PatternFrame++;
              if (this.PatternFrame == 60) {
                this.PatternLeg++;
                this.PatternFrame = 0;
                this.StateSpriteInfo.xpos = this.OriginX;
                this.StateSpriteInfo.ypos = this.OriginY;
              } 
              break;
            case 9:
              this.PatternLeg = 0;
              this.PatternFrame = 0;
              this.PatternOn = false;
              this.StateSpriteInfo.SetNewState(0);
              this.StateSpriteInfo.NextState = 0;
              this.StateSpriteInfo.xpos = this.OriginX;
              this.StateSpriteInfo.ypos = this.OriginY;
              this.ActualState = 0;
              break;
          } 
          if (this.StateSpriteInfo.FrameCount == 0)
            switch (this.StateSpriteInfo.CurrentFrame) {
              case 0:
                MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + this.Turret1XOffset, this.StateSpriteInfo.ypos + this.Turret1YOffset, this.DamageSpecial, this.WeaponType1, CurrentPlayer.StateSpriteInfo.xpos + 16, CurrentPlayer.StateSpriteInfo.ypos + 16);
                break;
              case 1:
                MMObj.LaunchEnemyMissile(this.StateSpriteInfo.xpos + this.Turret2XOffset, this.StateSpriteInfo.ypos + this.Turret2YOffset, this.DamageSpecial, this.WeaponType2, CurrentPlayer.StateSpriteInfo.xpos + 16, CurrentPlayer.StateSpriteInfo.ypos + 16);
                break;
            }  
          break;
        case 5:
          this.PatternFrame++;
          if (this.StateSpriteInfo.Action == 3) {
            (this.StateSpriteInfo.StateSprites[3]).FrameDelay = -1;
            this.StateSpriteInfo.CurrentFrame = (this.StateSpriteInfo.StateSprites[3]).Frames - 1;
            this.StateSpriteInfo.visible = false;
          } 
          if (this.PatternFrame > 80) {
            this.ActualState = 6;
            this.StateSpriteInfo.CurrentState = 4;
            this.StateSpriteInfo.NextState = 4;
            this.StateSpriteInfo.DefaultState = 4;
            this.StateSpriteInfo.Disable();
            this.PatternOn = false;
          } 
          break;
      }  
    if (this.StateSpriteInfo.CurrentState == 4 && !this.AlreadyDead) {
      this.AlreadyDead = true;
      EnemyManager.CreateFloater(this.StateSpriteInfo.xpos + (this.width >> 1), this.StateSpriteInfo.ypos + (this.height >> 1), this.Value);
      Enemy.EnemiesLeft--;
    } 
  }
  
  public void UpdateSpaceship() {
    u_State = this.StateSpriteInfo.CurrentState;
    if (u_State == 0 && this.PatternOn == false) {
      us_Rand = Utils.GetRandom(0L, 100L);
      if (us_Rand <= this.MovePercent1) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 0;
      } else if (us_Rand <= this.MovePercent2) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 1;
      } else if (us_Rand > this.MovePercent3 && us_Rand <= this.SpecAtkPercent) {
        this.PatternOn = true;
        this.PatternFrame = 0;
        this.PatternLeg = 1;
        this.ActualState = 3;
        this.StateSpriteInfo.SetNewState(1);
        this.StateSpriteInfo.NextState = 1;
      } 
    } 
    if (this.PatternOn)
      switch (this.ActualState) {
        case 0:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame -= 2;
              if (this.PatternFrame == -100)
                this.PatternLeg = 2; 
              break;
            case 2:
              this.PatternFrame += 2;
              if (this.PatternFrame == 100)
                this.PatternLeg = 3; 
              break;
            case 3:
              this.PatternFrame -= 2;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
              } 
              break;
          } 
          this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
          this.StateSpriteInfo.ypos = this.OriginY + (int)Math.pow(this.PatternFrame / 10.0D, 2.0D);
          break;
        case 1:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame -= 2;
              if (this.PatternFrame == -100)
                this.PatternLeg = 2; 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + 50 - (int)(Math.sqrt(-Math.pow(this.PatternFrame, 2.0D) + 10000.0D) / 2.0D);
              break;
            case 2:
              this.PatternFrame += 2;
              if (this.PatternFrame == 100)
                this.PatternLeg = 3; 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + 50 + (int)(Math.sqrt(-Math.pow(this.PatternFrame, 2.0D) + 10000.0D) / 2.0D);
              break;
            case 3:
              this.PatternFrame -= 2;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
              } 
              this.StateSpriteInfo.xpos = this.OriginX + this.PatternFrame;
              this.StateSpriteInfo.ypos = this.OriginY + 50 - (int)(Math.sqrt(-Math.pow(this.PatternFrame, 2.0D) + 10000.0D) / 2.0D);
              break;
          } 
          break;
        case 3:
          switch (this.PatternLeg) {
            case 1:
              this.PatternFrame--;
              if (this.PatternFrame == -20)
                this.PatternLeg = 2; 
              break;
            case 2:
              this.PatternFrame++;
              if (this.PatternFrame == 40)
                this.PatternLeg = 3; 
              break;
            case 3:
              this.PatternFrame--;
              if (this.PatternFrame == 0) {
                this.PatternLeg = 0;
                this.PatternFrame = 0;
                this.PatternOn = false;
                this.StateSpriteInfo.SetNewState(0);
                this.StateSpriteInfo.NextState = 0;
                this.ActualState = 0;
              } 
              break;
          } 
          this.StateSpriteInfo.xpos = this.OriginX + (this.PatternFrame << 2);
          this.StateSpriteInfo.ypos = this.OriginY;
          us_TargetX = CurrentPlayer.StateSpriteInfo.xpos;
          us_TargetY = CurrentPlayer.StateSpriteInfo.ypos;
          us_PosX = this.StateSpriteInfo.xpos + (this.StateSpriteInfo.width >> 1);
          us_PosY = this.StateSpriteInfo.ypos + (this.StateSpriteInfo.height >> 1);
          if (Utils.GetRandom(0L, 5L) == 0L)
            MMObj.LaunchEnemyMissile(us_PosX, us_PosY, this.DamageSpecial, 2, us_TargetX + ((int)Utils.GetRandom(-3L, 4L) << 4), us_TargetY + 16); 
          break;
        case 5:
          this.PatternFrame++;
          this.StateSpriteInfo.xpos += (int)Utils.GetRandom(-4L, 4L);
          this.StateSpriteInfo.ypos += (int)Utils.GetRandom(-2L, 4L);
          if (Utils.GetRandom(1L, 5L) == 1L)
            Explosion.AddExplosion(this.StateSpriteInfo.xpos + (int)Utils.GetRandom(0L, this.StateSpriteInfo.width), this.StateSpriteInfo.ypos + (int)Utils.GetRandom(0L, this.StateSpriteInfo.height)); 
          if (this.PatternFrame > 80) {
            this.ActualState = 6;
            this.StateSpriteInfo.CurrentState = 4;
            this.StateSpriteInfo.NextState = 4;
            this.StateSpriteInfo.DefaultState = 4;
            this.StateSpriteInfo.Disable();
            this.PatternOn = false;
          } 
          break;
      }  
    if (this.StateSpriteInfo.CurrentState == 4 && !this.AlreadyDead) {
      this.AlreadyDead = true;
      EnemyManager.CreateFloater(this.StateSpriteInfo.xpos + (this.width >> 1), this.StateSpriteInfo.ypos + (this.height >> 1), this.Value);
      Enemy.EnemiesLeft--;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Boss.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */