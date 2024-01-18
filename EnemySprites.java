class EnemySprites {
  static final int SQUIRREL01 = 0;
  
  static final int MOUSE01 = 1;
  
  static final int SKUNK01 = 2;
  
  static final int SQUIRREL02 = 3;
  
  static final int MAGICIAN01 = 4;
  
  static final int MOUSE02 = 5;
  
  static final int SKUNK02 = 6;
  
  static final int SQUIRREL03 = 7;
  
  static final int MAGICIAN02 = 8;
  
  static final int SKWERLFACE = 9;
  
  static final int NUMSPRITES = 10;
  
  static int Health;
  
  static int Damage;
  
  static int Value;
  
  static int[] YLoc = new int[8];
  
  static int XSize;
  
  static int YSize;
  
  static int[] Frames = new int[8];
  
  static int[] Delay = new int[8];
  
  static int DefState;
  
  static int State1;
  
  static int State2;
  
  static int State3;
  
  static int Percent1;
  
  static int Percent2;
  
  static int Percent3;
  
  static int Weapon;
  
  static int ImageNum;
  
  static boolean Smart;
  
  static int MultiFire;
  
  static Enemy CreateEnemy(int paramInt1, ImageExtractor[] paramArrayOfImageExtractor, int paramInt2) {
    byte b;
    for (b = 0; b < 8; b++) {
      YLoc[b] = 0;
      Frames[b] = 1;
      Delay[b] = 4;
    } 
    switch (paramInt1) {
      case 0:
        Health = 2;
        Damage = 4;
        YLoc[0] = 0;
        YLoc[2] = 64;
        YLoc[3] = 32;
        YLoc[5] = 96;
        YLoc[7] = 128;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 1;
        Delay[0] = 4;
        Delay[2] = 3;
        Delay[3] = 4;
        Delay[5] = 2;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 80;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 0;
        Smart = false;
        MultiFire = 0;
        ImageNum = 0;
        break;
      case 1:
        Health = 3;
        Damage = 6;
        YLoc[0] = 160;
        YLoc[2] = 224;
        YLoc[3] = 192;
        YLoc[5] = 256;
        YLoc[7] = 288;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 1;
        Delay[0] = 4;
        Delay[2] = 3;
        Delay[3] = 4;
        Delay[5] = 2;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 110;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 1;
        Smart = false;
        MultiFire = 0;
        ImageNum = 0;
        break;
      case 2:
        Health = 4;
        Damage = 8;
        YLoc[0] = 320;
        YLoc[2] = 384;
        YLoc[3] = 352;
        YLoc[5] = 416;
        YLoc[7] = 448;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 1;
        Delay[0] = 5;
        Delay[2] = 4;
        Delay[3] = 3;
        Delay[5] = 3;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 150;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 0;
        Smart = false;
        MultiFire = 0;
        ImageNum = 0;
        break;
      case 3:
        Health = 3;
        Damage = 6;
        YLoc[0] = 480;
        YLoc[2] = 544;
        YLoc[3] = 512;
        YLoc[5] = 576;
        YLoc[7] = 608;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 8;
        Delay[0] = 4;
        Delay[2] = 3;
        Delay[3] = 4;
        Delay[5] = 2;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 40;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 0;
        Smart = true;
        MultiFire = 0;
        ImageNum = 0;
        break;
      case 4:
        Health = 3;
        Damage = 9;
        YLoc[0] = 0;
        YLoc[2] = 64;
        YLoc[3] = 32;
        YLoc[5] = 96;
        YLoc[7] = 128;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 7;
        Frames[5] = 8;
        Frames[7] = 6;
        Delay[0] = 3;
        Delay[2] = 2;
        Delay[3] = 1;
        Delay[5] = 2;
        Delay[7] = 1;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 50;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 3;
        Smart = true;
        MultiFire = 0;
        ImageNum = 1;
        break;
      case 5:
        Health = 3;
        Damage = 9;
        YLoc[0] = 160;
        YLoc[2] = 224;
        YLoc[3] = 192;
        YLoc[5] = 256;
        YLoc[7] = 288;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 1;
        Delay[0] = 3;
        Delay[2] = 2;
        Delay[3] = 1;
        Delay[5] = 2;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 70;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 1;
        Smart = true;
        MultiFire = 1;
        ImageNum = 0;
        break;
      case 6:
        Health = 5;
        Damage = 10;
        YLoc[0] = 320;
        YLoc[2] = 384;
        YLoc[3] = 352;
        YLoc[5] = 416;
        YLoc[7] = 448;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 1;
        Delay[0] = 3;
        Delay[2] = 2;
        Delay[3] = 1;
        Delay[5] = 2;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 90;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 0;
        Smart = true;
        MultiFire = 1;
        ImageNum = 0;
        break;
      case 7:
        Health = 7;
        Damage = 11;
        YLoc[0] = 480;
        YLoc[2] = 544;
        YLoc[3] = 512;
        YLoc[5] = 576;
        YLoc[7] = 608;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 8;
        Delay[0] = 3;
        Delay[2] = 2;
        Delay[3] = 1;
        Delay[5] = 2;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 110;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 0;
        Smart = true;
        MultiFire = 1;
        ImageNum = 0;
        break;
      case 8:
        Health = 7;
        Damage = 14;
        YLoc[0] = 0;
        YLoc[2] = 64;
        YLoc[3] = 32;
        YLoc[5] = 96;
        YLoc[7] = 128;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 7;
        Frames[5] = 8;
        Frames[7] = 6;
        Delay[0] = 4;
        Delay[2] = 2;
        Delay[3] = 1;
        Delay[5] = 2;
        Delay[7] = 1;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 130;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 3;
        Smart = true;
        MultiFire = 1;
        ImageNum = 1;
        break;
      case 9:
        Health = 10;
        Damage = 17;
        YLoc[0] = 160;
        YLoc[2] = 224;
        YLoc[3] = 192;
        YLoc[5] = 256;
        YLoc[7] = 288;
        Frames[0] = 8;
        Frames[2] = 8;
        Frames[3] = 8;
        Frames[5] = 8;
        Frames[7] = 6;
        Delay[0] = 4;
        Delay[2] = 3;
        Delay[3] = 2;
        Delay[5] = 2;
        Delay[7] = 1;
        XSize = 32;
        YSize = 32;
        DefState = 0;
        State1 = 2;
        State2 = 0;
        State3 = 0;
        Percent1 = 180;
        Percent2 = 0;
        Percent3 = 0;
        Weapon = 3;
        Smart = true;
        MultiFire = 1;
        ImageNum = 1;
        break;
    } 
    if (paramInt2 > 1) {
      Damage *= paramInt2;
      Health *= paramInt2;
      if (Percent1 > 0)
        Percent1 += 10 * paramInt2; 
      if (Percent2 > 0)
        Percent2 += 10 * paramInt2; 
      if (Percent3 > 0)
        Percent3 += 10 * paramInt2; 
    } 
    double d = (Health + Damage) * 2.5D;
    d *= MultiFire / 3.0D + 1.0D;
    if (Smart)
      d *= 2.0D; 
    Value = (int)d;
    Enemy enemy = new Enemy(Health, Damage, Value, DefState, State1, State2, State3, Percent1, Percent2, Percent3, Weapon, Smart, MultiFire);
    enemy.StateSpriteInfo = new StatedSprite(0, 0, XSize, YSize);
    enemy.StateSpriteInfo.InitMain(8, DefState);
    for (b = 0; b < 8; b++)
      enemy.StateSpriteInfo.InitState(b, Frames[b], Delay[b]); 
    for (b = 0; b < 8; b++) {
      if (YLoc[b] != -1) {
        enemy.StateSpriteInfo.LoadImages(paramArrayOfImageExtractor[ImageNum], b, YLoc[b]);
      } else {
        enemy.StateSpriteInfo.LoadImages(paramArrayOfImageExtractor[ImageNum], b, YLoc[0]);
      } 
    } 
    return enemy;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\EnemySprites.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */