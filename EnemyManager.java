import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

class EnemyManager {
  static final int ENEMIESPERROW = 5;
  
  static final int ENEMYROWS = 4;
  
  static final int ENEMYXOFFSET = 56;
  
  static final int ENEMYYOFFSET = 32;
  
  static final int ENEMYWIDTH = 32;
  
  static final int ENEMYHEIGHT = 32;
  
  static final int ENEMYSPACEX = 39;
  
  static final int ENEMYSPACEY = 20;
  
  static final int BOSSLEVEL = 5;
  
  static final int ENDLEVEL = 25;
  
  int paint_xcount;
  
  int paint_ycount;
  
  int update_xcount;
  
  int update_ycount;
  
  ImageExtractor[] ieEnemySprites = new ImageExtractor[2];
  
  int ImageWidth;
  
  int ImageHeight;
  
  Enemy[][] EnemyList = new Enemy[5][4];
  
  Boss EnemyBoss;
  
  static final int MAXFLOATERS = 20;
  
  static Floater[] Scores = new Floater[20];
  
  static Player CurrentPlayer;
  
  static final int MAXSPEED = 2;
  
  int AccelX;
  
  int DirectionX;
  
  static int Px;
  
  static int Ux;
  
  static int CFx;
  
  static int CFTotal;
  
  static int CFPoints;
  
  static int CFCash;
  
  public EnemyManager(Image paramImage, int paramInt1, int paramInt2, MissileManager paramMissileManager, Player paramPlayer) {
    Enemy.SetPlayer(paramPlayer);
    this.ieEnemySprites[0] = new ImageExtractor(paramImage, paramInt1, paramInt2);
    this.ImageWidth = paramInt1;
    this.ImageHeight = paramInt2;
    Enemy.AllOffX = 0;
    Enemy.AllOffY = 0;
    this.AccelX = 0;
    this.DirectionX = 1;
    Floater.Init(Color.white, new Font("Serif", 1, 12));
    Enemy.MMObj = paramMissileManager;
    Boss.SetupMissileManager(paramMissileManager);
    Boss.SetupPlayer(paramPlayer);
    CurrentPlayer = paramPlayer;
  }
  
  public void CreateBoss(int paramInt1, int paramInt2) {
    Scores = new Floater[20];
    Enemy.EnemiesLeft = 1;
    this.EnemyBoss = new Boss(paramInt1, paramInt2);
    this.EnemyBoss.StateSpriteInfo.Enable();
  }
  
  public void CreateEnemies(int paramInt1, int paramInt2) {
    Scores = new Floater[20];
    Enemy.EnemiesLeft = 20;
    this.EnemyBoss = null;
    int[] arrayOfInt = new int[4];
    paramInt1 -= paramInt1 / 5;
    arrayOfInt[3] = (paramInt1 - 3) / 2;
    arrayOfInt[2] = (paramInt1 - 2) / 2;
    arrayOfInt[1] = (paramInt1 - 1) / 2;
    arrayOfInt[0] = paramInt1 / 2;
    Enemy[] arrayOfEnemy = new Enemy[4];
    for (byte b2 = 0; b2 < 4; b2++) {
      if (arrayOfInt[b2] < 0)
        arrayOfInt[b2] = 0; 
      if (arrayOfInt[b2] >= 10)
        arrayOfInt[b2] = 9; 
      arrayOfEnemy[b2] = EnemySprites.CreateEnemy(arrayOfInt[b2], this.ieEnemySprites, paramInt2);
    } 
    for (byte b1 = 0; b1 < 5; b1++) {
      for (byte b = 0; b < 4; b++) {
        byte b3 = b;
        this.EnemyList[b1][b] = new Enemy((arrayOfEnemy[b3]).Health, (arrayOfEnemy[b3]).Damage, (arrayOfEnemy[b3]).Value, (arrayOfEnemy[b3]).DefState, (arrayOfEnemy[b3]).State1, (arrayOfEnemy[b3]).State2, (arrayOfEnemy[b3]).State3, (arrayOfEnemy[b3]).Percent1, (arrayOfEnemy[b3]).Percent2, (arrayOfEnemy[b3]).Percent3, (arrayOfEnemy[b3]).WeaponType, (arrayOfEnemy[b3]).Smart, (arrayOfEnemy[b3]).MultiFire);
        (this.EnemyList[b1][b]).StateSpriteInfo = new StatedSprite(0, 0, (arrayOfEnemy[b3]).StateSpriteInfo.width, (arrayOfEnemy[b3]).StateSpriteInfo.height);
        (this.EnemyList[b1][b]).StateSpriteInfo.InitMain(8, (arrayOfEnemy[b3]).StateSpriteInfo.DefaultState);
        (this.EnemyList[b1][b]).HalfWidth = (arrayOfEnemy[b3]).StateSpriteInfo.width / 2;
        (this.EnemyList[b1][b]).StateSpriteInfo.StateSprites = (arrayOfEnemy[b3]).StateSpriteInfo.StateSprites;
        (this.EnemyList[b1][b]).defx = 56 + b1 * 32 + b1 * 39;
        (this.EnemyList[b1][b]).defy = 32 + b * 32 + b * 20;
        (this.EnemyList[b1][b]).StateSpriteInfo.Enable();
        (this.EnemyList[b1][b]).StateSpriteInfo.CurrentFrame = (int)Utils.GetRandom(0L, (((this.EnemyList[b1][b]).StateSpriteInfo.StateSprites[0]).Frames - 1));
        (this.EnemyList[b1][b]).StateSpriteInfo.Enable();
        this.EnemyList[b1][b].Update();
      } 
    } 
  }
  
  static void CreateFloater(int paramInt1, int paramInt2, int paramInt3) {
    CFTotal = paramInt3 << 1;
    CFCash = CFTotal * CurrentPlayer.CashMult / 100;
    CFPoints = CFTotal - CFCash;
    CFx = 0;
    while (CFx < 20) {
      if (Scores[CFx] == null) {
        Scores[CFx] = new Floater(paramInt1, paramInt2, "$" + CFCash, -2, 2, -1, 30);
        CFx = 20;
      } 
      CFx++;
    } 
    CurrentPlayer.Cash += CFCash;
    CurrentPlayer.TotalPoints += CFPoints;
  }
  
  public final void Paint(Graphics paramGraphics) {
    this.paint_xcount = 0;
    while (this.paint_xcount < 5) {
      this.paint_ycount = 0;
      while (this.paint_ycount < 4) {
        this.EnemyList[this.paint_xcount][this.paint_ycount].Paint(paramGraphics);
        this.paint_ycount++;
      } 
      this.paint_xcount++;
    } 
    Px = 0;
    while (Px < 20) {
      if (Scores[Px] != null)
        Scores[Px].Paint(paramGraphics); 
      Px++;
    } 
    if (this.EnemyBoss != null)
      this.EnemyBoss.Paint(paramGraphics); 
  }
  
  public void SetImage(int paramInt, Image paramImage) {
    this.ieEnemySprites[paramInt] = new ImageExtractor(paramImage, this.ImageWidth, this.ImageHeight);
  }
  
  public final void Update() {
    this.AccelX += this.DirectionX;
    if (this.AccelX > 2) {
      this.AccelX = 2;
    } else if (this.AccelX < -2) {
      this.AccelX = -2;
    } 
    Enemy.AllOffX += this.AccelX;
    if (Enemy.AllOffX >= 110 && this.DirectionX > 0)
      this.DirectionX = -this.DirectionX; 
    if (Enemy.AllOffX <= -30 && this.DirectionX < 0)
      this.DirectionX = -this.DirectionX; 
    Ux = 0;
    while (Ux < 20) {
      if (Scores[Ux] != null) {
        Scores[Ux].Update();
        if ((Scores[Ux]).done)
          Scores[Ux] = null; 
      } 
      Ux++;
    } 
    this.update_xcount = 0;
    while (this.update_xcount < 5) {
      this.update_ycount = 0;
      while (this.update_ycount < 4) {
        this.EnemyList[this.update_xcount][this.update_ycount].Update();
        this.update_ycount++;
      } 
      this.update_xcount++;
    } 
    if (this.EnemyBoss != null)
      this.EnemyBoss.Update(); 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\EnemyManager.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */