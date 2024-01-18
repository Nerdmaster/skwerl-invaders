import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

public class game extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener {
  static final int FRAMEDELAY = 40;
  
  static final int STATE_LOADING = 0;
  
  static final int STATE_PAUSED = 1;
  
  static final int STATE_INTRO = 2;
  
  static final int STATE_INTRO2 = 3;
  
  static final int STATE_WEAPONHELP = 4;
  
  static final int STATE_WEAPONS = 5;
  
  static final int STATE_MAINGAME = 6;
  
  static final int STATE_BOSS = 7;
  
  static final int STATE_ENDLEVEL = 8;
  
  static final int STATE_GAMEOVER = 9;
  
  static final int STATE_INTROMAIN = 10;
  
  static final int STATE_LOADING2 = 11;
  
  static final int STATE_STARTING = 12;
  
  static final int STATE_DEAD = 13;
  
  static final int STATE_NEXTLEVEL = 14;
  
  static final int STATE_CHOOSEDIFF = 15;
  
  static final int STATE_WAITINGFOREND = 16;
  
  static final int STATE_CHOOSEHSLIST = 17;
  
  static final int STATE_SHOWSCORES = 18;
  
  static final int STATE_GETPLAYERNAME = 19;
  
  static final int IMGBUTTONWIDTH = 256;
  
  static final int IMGBUTTONHEIGHT = 288;
  
  static final int IMGSTRIPWIDTH = 512;
  
  static final int IMGSTRIPHEIGHT = 896;
  
  static final int ENEMYSTRIPWIDTH = 256;
  
  static final int ENEMYSTRIPHEIGHT = 896;
  
  static final int BOSSIMGWIDTH = 1312;
  
  static final int BOSSIMGHEIGHT = 640;
  
  static final int MODE_NORMAL = 0;
  
  String[] AllowedHost = new String[] { "www.mywebgames.com", "www.scarysquirrel.org", "RESERVED" };
  
  Thread animation;
  
  Image ImageBack;
  
  Graphics DoubleBuffer;
  
  Image GameBoard;
  
  Image GameBackdrop;
  
  Image NextBackdrop;
  
  Cursor curDefault = Cursor.getDefaultCursor();
  
  Font SmallFont = new Font("Serif", 0, 12);
  
  Font MedFont = new Font("Serif", 0, 14);
  
  Font LargeFont = new Font("Serif", 0, 18);
  
  Font LargeFont2 = new Font("SansSerif", 1, 16);
  
  Font HugeFont = new Font("Dialog", 1, 20);
  
  GifButton btnStart;
  
  GifButton btnHighScores;
  
  GifButton btnHelp;
  
  GifButton btnOK;
  
  GifButton btnEasy;
  
  GifButton btnMedium;
  
  GifButton btnHard;
  
  GifButton btnMonthly;
  
  GifButton btnDaily;
  
  GifButton btnPermanent;
  
  ImageExtractor imgExtractor;
  
  int AppWidth;
  
  int AppHeight;
  
  int GlobalMult;
  
  boolean ItsAGo;
  
  MediaTracker Tracker;
  
  boolean showFPS = false;
  
  long TotalFrameCount;
  
  long FPS;
  
  long FirstFrame;
  
  boolean BGOff = false;
  
  boolean HitHighScores;
  
  int ScorePosition;
  
  String PlayerName;
  
  TextInput tiPlayerName;
  
  String[] Haha = new String[] { 
      "FUCK", "SHIT", "PUSSY", "ASSHOLE", "DICKHEAD", "CUNT", "BUTT", "PENIS", "VAGINA", "LICK", 
      "SCREW" };
  
  static final String INTROMUSIC = "intro.au";
  
  static final String THEMEMUSIC = "theme.au";
  
  static final String BOSSMUSIC = "boss.au";
  
  AudioClip IntroMusic;
  
  AudioClip ThemeMusic;
  
  AudioClip BossMusic;
  
  AudioClip CurrentClip;
  
  AudioClip EndGameSound;
  
  boolean BGMusicOn;
  
  MusicThread mtIntroMusic;
  
  MusicThread mtThemeMusic;
  
  MusicThread mtBossMusic;
  
  MusicThread mtEndGameSound;
  
  int GameState;
  
  TileGameObj GameScreen;
  
  WeaponsScreen WeaponStateScreen;
  
  Image WeaponsScreenBack;
  
  Image imgButtons;
  
  Image imgButtons2;
  
  Image imgSpriteStrip;
  
  Image[] imgEnemySprites = new Image[2];
  
  Image BossImage;
  
  ImageExtractor ieSprites;
  
  ImageExtractor ieBoss;
  
  boolean WeaponsScreenLoaded;
  
  MissileManager MissileManagerObject;
  
  int[] GameBoardArray;
  
  int GameMode;
  
  int GameLevel;
  
  boolean DiffChosen;
  
  char HSListChosen;
  
  Player CurrentPlayer;
  
  EnemyManager SquirrelManager;
  
  ShieldGenerator SGObj;
  
  String[] egEndGameLines = new String[] { "You have defeated the skwerls, and saved this planet.", "This is not yet the end, however, as there are other", "planets in need of your help.  Go now, human, and save", "another world from the dreaded rodents!", "", "Be warned, however, for rumor has it that the creatures", "you are about to face are even more fearsome (as", "fearsome as a skwerl can be, anyway) than those you", "just defeated..." };
  
  int egScrollLineNum = 0;
  
  int egTotalEndGameLines = this.egEndGameLines.length;
  
  int egLineSpacing = 20;
  
  int egScrollY;
  
  int WeaponPreviousState;
  
  String MessageText;
  
  int MessageDelay;
  
  String Host;
  
  int Difficulty;
  
  HSObj HighScoreThing;
  
  static int Frames;
  
  static String[] ItsAllGood = new String[] { 
      "THISAPPLETISCOPYRIGHTC2000", "BYJEREMYECHOLSALLRIGHTSRESERVED", "VISITWWWMYWEBGAMESCOMFOR", "MOREGAMINGFUNCLICKBELOWAHREFHTTPWWW", "MYWEBGAMES", "MYWEBGAMES/A", "SQUIRRELINVADERSORIGINALLY", "DESIGNEDFORSCARYSQUIRREL", "WORLDCLICKAHREF", "SCARYSQUIRREL", 
      "HERE/ATOVISIT" };
  
  static String CBText1;
  
  static String CBText2;
  
  static int CBLength1;
  
  static int CBLength2;
  
  static FontMetrics CBfm;
  
  public void AddHighScore() {
    int i = this.CurrentPlayer.TotalPoints * 3;
    int j = (this.CurrentPlayer.TotalPoints + 13) * 5;
    int k = this.CurrentPlayer.TotalPoints - 134;
    String str = "action=addscore&name=" + this.PlayerName;
    str = String.valueOf(str) + "&bananapoop=" + i;
    str = String.valueOf(str) + "&scoofy=" + j;
    str = String.valueOf(str) + "&yakingest=" + k;
    i += 12;
    j = i - 62;
    k = this.CurrentPlayer.TotalPoints * 3 + 29;
    str = String.valueOf(str) + "&smurfurific=" + i;
    str = String.valueOf(str) + "&semaphor=" + j;
    str = String.valueOf(str) + "&whosyourdaddy=" + k;
    i = this.CurrentPlayer.TotalPoints;
    j = this.CurrentPlayer.TotalPoints + 47 - k;
    k = (this.CurrentPlayer.TotalPoints - 172) * 3;
    str = String.valueOf(str) + "&mrpibber=" + i;
    str = String.valueOf(str) + "&goliathjoe=" + j;
    str = String.valueOf(str) + "&misterman=" + k;
    i = this.CurrentPlayer.TotalPoints * 7 - 888;
    j = i + 888;
    k /= 3;
    str = String.valueOf(str) + "&kittylitter=" + i;
    str = String.valueOf(str) + "&biteitnow=" + j;
    str = String.valueOf(str) + "&score1=" + k;
    i = j + 7;
    j = 888 - this.CurrentPlayer.TotalPoints;
    k = j * i;
    str = String.valueOf(str) + "&howlinator=" + i;
    str = String.valueOf(str) + "&goodfries=" + j;
    str = String.valueOf(str) + "&frenchwomen=" + k;
    this.HighScoreThing = new HSObj(getParameter("CGIHSPATH"), getParameter("CGIHSFILE"));
    this.ScorePosition = this.HighScoreThing.AddScore(str);
    this.HitHighScores = true;
  }
  
  public void ChooseMusic() {
    switch (this.GameState) {
      case 0:
      case 2:
      case 3:
      case 11:
        if (this.mtIntroMusic.done() && this.CurrentClip != this.IntroMusic && this.BGMusicOn == true) {
          if (this.CurrentClip != null)
            this.CurrentClip.stop(); 
          this.IntroMusic.loop();
          this.CurrentClip = this.IntroMusic;
        } 
        break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 12:
      case 14:
        if (this.GameLevel % 5 == 0) {
          if (this.mtBossMusic.done() && this.CurrentClip != this.BossMusic && this.BGMusicOn == true) {
            if (this.CurrentClip != null)
              this.CurrentClip.stop(); 
            this.BossMusic.loop();
            this.CurrentClip = this.BossMusic;
          } 
          break;
        } 
        if (this.mtThemeMusic.done() && this.CurrentClip != this.ThemeMusic && this.BGMusicOn == true) {
          if (this.CurrentClip != null)
            this.CurrentClip.stop(); 
          this.ThemeMusic.loop();
          this.CurrentClip = this.ThemeMusic;
        } 
        break;
      case 1:
        if (this.CurrentClip != null) {
          this.CurrentClip.stop();
          this.CurrentClip = null;
        } 
        break;
    } 
  }
  
  public void ClearBottom(Graphics paramGraphics) {
    paramGraphics.setColor(Color.black);
    paramGraphics.fillRect(0, this.AppHeight - 20, this.AppWidth, 20);
    paramGraphics.setColor(Color.gray);
    paramGraphics.drawRect(0, this.AppHeight - 20, this.AppWidth - 1, 19);
    if (this.GameState == 6 || this.GameState == 5) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.green);
      CBText1 = "Cash: " + this.CurrentPlayer.Cash;
      CBText2 = "Level: " + this.GameLevel;
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      CBLength2 = CBfm.stringWidth(CBText2);
      paramGraphics.drawString("Score: " + this.CurrentPlayer.TotalPoints, 10, this.AppHeight - 4);
      if (this.GameMode == 0)
        paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4); 
      paramGraphics.drawString(CBText2, this.AppWidth - 10 - CBLength2, this.AppHeight - 4);
    } else if (this.GameState == 0 || this.GameState == 11) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.red);
      CBText1 = "Please Wait...";
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } else if (this.GameState == 2 || this.GameState == 3) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.white);
      CBText1 = "Press Any Key To Skip Intro";
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } else if (this.GameState == 1) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.red);
      CBText1 = "Paused - Press 'P' to resume";
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } else if (this.GameState == 17) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.blue);
      CBText1 = "Choose which high scores List to view";
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } else if (this.GameState == 15) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.blue);
      CBText1 = "Choose a difficulty level to continue";
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } else if (this.GameState == 10) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.white);
      CBText1 = "Click 'Begin' to start a game";
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } else if (this.GameState == 4) {
      paramGraphics.setFont(this.LargeFont2);
      paramGraphics.setColor(Color.yellow);
      CBText1 = "Read what's above before continuing!";
      CBfm = paramGraphics.getFontMetrics(this.LargeFont2);
      CBLength1 = CBfm.stringWidth(CBText1);
      paramGraphics.drawString(CBText1, (this.AppWidth >> 1) - (CBLength1 >> 1), this.AppHeight - 4);
    } 
  }
  
  private void DrawIntroPage(Graphics paramGraphics) {
    this.GameScreen.CreateBack();
    this.DoubleBuffer.drawImage(this.GameScreen.GetImage(), 0, 0, this);
    ClearBottom(this.DoubleBuffer);
    Utils.CenterString(this.DoubleBuffer, Color.white, "Squirrel Invaders, Copyright (c) 2000 by Jeremy Echols", this.LargeFont, this.AppWidth, 60);
    Utils.SetDefCS(this.DoubleBuffer, Color.yellow, this.MedFont, this.AppWidth);
    Utils.CenterString("It was a time of chaos, a time of war.  Acorn and nut reserves were depleted", 90);
    Utils.CenterString("all over the galaxy.  Earthlings were forced to fight hordes of rabid extra-", 110);
    Utils.CenterString("terrestrial squirrels in order to preserve their own rations.  Rappy, once a", 130);
    Utils.CenterString("kind and generous squirrel, was unable to control his need for good acorns.", 150);
    Utils.CenterString("Rappy assembled an army of squirrels and other beasts to fight back against the", 170);
    Utils.CenterString("earthlings.  With his friends and their deadly arsenal of unripe green acorns,", 190);
    Utils.CenterString("they were unstoppable.  Rumors tore apart nations.  Governments allied with the", 210);
    Utils.CenterString("squirrels and began secret projects to create evil squirrel-human hybrids.  These", 230);
    Utils.CenterString("unspeakable events brought you to investigate.", 250);
    Utils.CenterString("After discovering the horrifying truth of the situation you've realized that you,", 270);
    Utils.CenterString("and ONLY you, will be able to destroy the alien squirrels and reclaim earth...", 290);
    this.btnStart.Paint(this.DoubleBuffer);
    this.btnHighScores.Paint(this.DoubleBuffer);
    this.btnHelp.Paint(this.DoubleBuffer);
    paramGraphics.drawImage(this.ImageBack, 0, 0, this);
  }
  
  private void InitWeaponsScreen() {
    if (!this.WeaponsScreenLoaded) {
      this.GameState = 11;
      Utils.ClearImage(this.DoubleBuffer, Color.black, this.AppWidth, this.AppHeight);
      Utils.ShowString("Loading Data", this.DoubleBuffer, this.SmallFont, this.AppWidth, 50);
      Utils.ShowString("Please wait... (up to 1 minute at 56k)", this.DoubleBuffer, this.SmallFont, this.AppWidth, 70);
      this.SquirrelManager = new EnemyManager(this.imgEnemySprites[0], 256, 896, this.MissileManagerObject, this.CurrentPlayer);
      this.MissileManagerObject.SetEnemyAndPlayer(this.SquirrelManager, this.CurrentPlayer);
      do {
      
      } while (!this.Tracker.checkAll(true));
      this.WeaponsScreenLoaded = true;
      this.WeaponStateScreen = new WeaponsScreen(this.CurrentPlayer, this.WeaponsScreenBack, this.imgButtons, this.imgButtons2);
      this.GameState = 4;
      this.btnOK = new GifButton(this.AppWidth / 2 - 16, 336, 32, 32, this.imgExtractor.ExtractImage(128, 160, 32, 32), this.imgExtractor.ExtractImage(160, 160, 32, 32), this.imgExtractor.ExtractImage(160, 160, 32, 32), this);
      this.btnOK.Enable();
      this.btnOK.SetHotKey('O');
    } else {
      this.GameState = 5;
    } 
    this.WeaponStateScreen.Mode = 0;
    this.WeaponStateScreen.SetupWeaponArray();
  }
  
  public void NextLevel() {
    this.CurrentPlayer.ResetShipStatus();
    this.MessageDelay = 25;
    this.GameLevel++;
    if (this.GameLevel > 25)
      this.GameLevel = 1; 
    ChooseMusic();
    if (this.GameLevel == 1) {
      this.NextBackdrop = getImage(getCodeBase(), "images/backdrop2.gif");
      this.Tracker.addImage(this.NextBackdrop, 0);
      do {
      
      } while (!this.Tracker.checkAll(true));
    } 
    if (this.GameLevel == 5) {
      this.ieBoss = new ImageExtractor(this.BossImage, 1312, 640);
      Boss.SetupBossImage(this.ieBoss);
      this.BossImage = null;
      this.ieBoss = null;
      System.gc();
    } 
    if (this.GameLevel == 6 && this.GlobalMult == 1)
      this.SquirrelManager.SetImage(1, this.imgEnemySprites[1]); 
    if (this.GameLevel == 11) {
      this.GameBackdrop = this.NextBackdrop;
      this.NextBackdrop = getImage(getCodeBase(), "images/backdrop3.gif");
      this.Tracker.addImage(this.NextBackdrop, 0);
      this.imgEnemySprites[0] = null;
      this.imgEnemySprites[1] = null;
    } 
    if (this.GameLevel == 21) {
      this.GameBackdrop = this.NextBackdrop;
      this.NextBackdrop = null;
    } 
    if (this.GameLevel == 21) {
      this.BossImage = getImage(getCodeBase(), "images/rappy.gif");
      this.Tracker.addImage(this.BossImage, 0);
      this.EndGameSound = getAudioClip(getCodeBase(), "invaderremix.au");
      this.mtEndGameSound = new MusicThread(this.EndGameSound, false);
      this.mtEndGameSound.start();
    } 
    if (this.GameLevel == 25) {
      this.ieBoss = new ImageExtractor(this.BossImage, 1312, 640);
      Boss.SetupBossImage(this.ieBoss);
      this.BossImage = null;
      this.ieBoss = null;
      System.gc();
      this.MessageText = "Prepare to face the wrath of Rappy!";
      this.GameState = 6;
      this.MissileManagerObject.ClearMissiles();
      this.SGObj.ClearShields();
      this.SquirrelManager.CreateBoss(this.GameLevel, this.GlobalMult);
    } else if (this.GameLevel % 5 != 0) {
      this.MessageText = "Entering Level " + this.GameLevel;
      this.GameState = 6;
      this.MissileManagerObject.ClearMissiles();
      this.SGObj.ClearShields();
      this.SquirrelManager.CreateEnemies(this.GameLevel, this.GlobalMult);
      System.gc();
    } else {
      this.MessageText = "Prepare for the boss...";
      this.GameState = 6;
      this.MissileManagerObject.ClearMissiles();
      this.SGObj.ClearShields();
      this.SquirrelManager.CreateBoss(this.GameLevel, this.GlobalMult);
    } 
  }
  
  public void OpenHelpWindow() {
    String str2;
    URL uRL1 = getCodeBase();
    String str1 = uRL1.getHost();
    if (str1 == "") {
      str2 = "file://c|/" + getParameter("HELPPAGE");
      System.out.println("File URL: '" + str2 + "'");
    } else {
      str2 = "http://" + str1 + "/" + getParameter("HELPPAGE");
    } 
    URL uRL2 = null;
    try {
      uRL2 = new URL(str2);
    } catch (Exception exception) {}
    AppletContext appletContext = getAppletContext();
    appletContext.showDocument(uRL2, "_blank");
  }
  
  private void ResetGame() {
    this.imgButtons = getImage(getCodeBase(), "images/buttons.gif");
    this.imgButtons2 = getImage(getCodeBase(), "images/buttons2.gif");
    this.imgEnemySprites[0] = getImage(getCodeBase(), "images/enemystrip0.gif");
    this.Tracker.addImage(this.imgEnemySprites[0], 0);
    this.Tracker.addImage(this.imgButtons, 0);
    this.Tracker.addImage(this.imgButtons2, 0);
    this.CurrentPlayer.Reset();
    this.WeaponStateScreen.Init();
    ChooseMusic();
    this.GameState = 2;
  }
  
  public void StartGame() {
    this.GameLevel = 1;
    this.GlobalMult = 1;
    this.MessageDelay = 0;
    this.MissileManagerObject.ClearMissiles();
    this.SGObj.ClearShields();
    setCursor(this.curDefault);
    if (this.CurrentPlayer.Cash >= (Weapon.WeaponArray[1]).Cost) {
      this.WeaponPreviousState = 6;
      InitWeaponsScreen();
      this.SquirrelManager.CreateEnemies(this.GameLevel, 1);
    } else {
      InitWeaponsScreen();
      this.WeaponsScreenLoaded = false;
      this.SquirrelManager.CreateEnemies(this.GameLevel, 1);
      this.GameState = 6;
    } 
    ChooseMusic();
    this.BossImage = getImage(getCodeBase(), "images/enemyboss1.gif");
    this.NextBackdrop = getImage(getCodeBase(), "images/backdrop2.gif");
    this.imgEnemySprites[1] = getImage(getCodeBase(), "images/enemystrip1.gif");
    this.Tracker.addImage(this.BossImage, 0);
    this.Tracker.addImage(this.NextBackdrop, 0);
    this.Tracker.addImage(this.imgEnemySprites[1], 0);
    this.BossMusic = getAudioClip(getCodeBase(), "boss.au");
    this.mtBossMusic = new MusicThread(this.BossMusic, false);
    this.mtBossMusic.start();
  }
  
  public void StartHighScores() {
    this.btnDaily.Enable();
    this.btnMonthly.Enable();
    this.btnPermanent.Enable();
    this.HSListChosen = ' ';
    this.GameState = 17;
  }
  
  public void ViewHighScores(int paramInt) {
    this.HighScoreThing = new HSObj(getParameter("CGIHSPATH"), getParameter("CGIHSFILE"));
    String str1 = "action=getlist&type=" + this.HSListChosen;
    this.HighScoreThing.GetScoresList(str1);
    String str2 = "";
    switch (this.HSListChosen) {
      case 'D':
        str2 = "Today's top scores";
        break;
      case 'M':
        str2 = "This month's top scores";
        break;
      case 'P':
        str2 = "All-time best skwerl killers";
        break;
    } 
    this.HighScoreThing.SetupScreen(10, 10, this.AppWidth - 20, this.AppHeight - 20, Color.black, Color.yellow, Color.white, str2, this.LargeFont, this.SmallFont, this.DoubleBuffer);
    this.HighScoreThing.SetupColumns(5, 35, 325, 425);
    this.HighScoreThing.ScorePosition = paramInt;
    this.GameState = 18;
    setCursor(this.curDefault);
    showStatus("");
  }
  
  public void init() {
    Utils.Init(this);
    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    this.Tracker = new MediaTracker(this);
    URL uRL = getCodeBase();
    this.Host = uRL.getHost();
    System.out.println("Running Squirrel Invaders v1.00.00 from: " + this.Host);
    this.AppWidth = (getBounds()).width;
    this.AppHeight = (getBounds()).height;
    this.egScrollY = this.AppHeight + this.egLineSpacing;
    this.ImageBack = createImage(this.AppWidth, this.AppHeight);
    this.DoubleBuffer = this.ImageBack.getGraphics();
    if (this.Host == "")
      this.Host = "???&*()[[]5]w[w]w*-55+///my5we(b6)g/am-es.....COM!!"; 
    this.ItsAGo = true;
    if (this.animation == null) {
      this.animation = new Thread(this);
      this.animation.start();
    } 
    this.GameBoardArray = new int[6];
    this.WeaponsScreenLoaded = false;
    Weapon.CreateWeapons();
    Upgrade.CreateUpgradeList();
    this.GameState = 0;
  }
  
  public void keyPressed(KeyEvent paramKeyEvent) {
    int i = paramKeyEvent.getKeyCode();
    if (i == 83 && this.GameState != 19)
      SoundMan.SoundsOn ^= 0x1; 
    if (i == 77 && this.GameState != 19) {
      if (this.BGMusicOn) {
        this.BGMusicOn = false;
        this.CurrentClip = null;
        this.IntroMusic.stop();
        this.ThemeMusic.stop();
        this.BossMusic.stop();
      } else {
        this.BGMusicOn = true;
        ChooseMusic();
      } 
    } else {
      byte b;
      switch (this.GameState) {
        case 19:
          this.tiPlayerName.KeyPressed(paramKeyEvent);
          break;
        case 2:
          this.GameBoardArray[0] = 0;
          for (b = 1; b < 6; b++)
            this.GameBoardArray[b] = -1; 
          Frames = 0;
          this.GameState = 3;
          break;
        case 1:
          if (i == 80) {
            this.GameState = 6;
            ChooseMusic();
          } 
          break;
        case 6:
          if (i == 113 || i == 114) {
            ResetGame();
            break;
          } 
          if (i == 80) {
            this.GameState = 1;
            ChooseMusic();
            break;
          } 
          this.CurrentPlayer.KeyPressed(paramKeyEvent);
          break;
      } 
    } 
  }
  
  public void keyReleased(KeyEvent paramKeyEvent) {
    int i = paramKeyEvent.getKeyCode();
    switch (this.GameState) {
      case 6:
        this.CurrentPlayer.KeyReleased(paramKeyEvent);
        break;
    } 
  }
  
  public void keyTyped(KeyEvent paramKeyEvent) {
    switch (this.GameState) {
      case 17:
        if (this.btnDaily.CheckKeyPress(paramKeyEvent))
          this.HSListChosen = 'D'; 
        if (this.btnMonthly.CheckKeyPress(paramKeyEvent))
          this.HSListChosen = 'M'; 
        if (this.btnPermanent.CheckKeyPress(paramKeyEvent))
          this.HSListChosen = 'P'; 
        break;
      case 15:
        if (this.btnEasy.CheckKeyPress(paramKeyEvent)) {
          this.CurrentPlayer.SetDifficulty(0);
          this.DiffChosen = true;
        } 
        if (this.btnMedium.CheckKeyPress(paramKeyEvent)) {
          this.CurrentPlayer.SetDifficulty(1);
          this.DiffChosen = true;
        } 
        if (this.btnHard.CheckKeyPress(paramKeyEvent)) {
          this.CurrentPlayer.SetDifficulty(2);
          this.DiffChosen = true;
        } 
        break;
      case 10:
        if (this.btnHelp.CheckKeyPress(paramKeyEvent))
          OpenHelpWindow(); 
        if (this.btnHighScores.CheckKeyPress(paramKeyEvent))
          StartHighScores(); 
        if (this.btnStart.CheckKeyPress(paramKeyEvent)) {
          this.GameMode = 0;
          this.GameState = 12;
        } 
        break;
      case 5:
        this.WeaponStateScreen.CheckKeys(paramKeyEvent);
        break;
      case 4:
        if (this.btnOK.CheckKeyPress(paramKeyEvent)) {
          this.GameState = 5;
          this.btnOK.Disable();
          this.btnOK = null;
        } 
        break;
    } 
  }
  
  public void mouseClicked(MouseEvent paramMouseEvent) {
    switch (this.GameState) {
      case 17:
        if (this.btnDaily.CheckClick(paramMouseEvent))
          this.HSListChosen = 'D'; 
        if (this.btnMonthly.CheckClick(paramMouseEvent))
          this.HSListChosen = 'M'; 
        if (this.btnPermanent.CheckClick(paramMouseEvent))
          this.HSListChosen = 'P'; 
        break;
      case 18:
        this.HighScoreThing.MouseClicked(paramMouseEvent);
        break;
      case 15:
        if (this.btnEasy.CheckClick(paramMouseEvent)) {
          this.CurrentPlayer.SetDifficulty(0);
          this.DiffChosen = true;
        } 
        if (this.btnMedium.CheckClick(paramMouseEvent)) {
          this.CurrentPlayer.SetDifficulty(1);
          this.DiffChosen = true;
        } 
        if (this.btnHard.CheckClick(paramMouseEvent)) {
          this.CurrentPlayer.SetDifficulty(2);
          this.DiffChosen = true;
        } 
        break;
      case 10:
        if (this.btnHelp.CheckClick(paramMouseEvent))
          OpenHelpWindow(); 
        if (this.btnHighScores.CheckClick(paramMouseEvent))
          StartHighScores(); 
        if (this.btnStart.CheckClick(paramMouseEvent)) {
          this.GameMode = 0;
          this.GameState = 12;
        } 
        break;
      case 5:
        this.WeaponStateScreen.CheckClicks(paramMouseEvent);
        break;
      case 4:
        if (this.btnOK.CheckClick(paramMouseEvent)) {
          this.GameState = 5;
          this.btnOK.Disable();
          this.btnOK = null;
        } 
        break;
    } 
  }
  
  public void mouseDragged(MouseEvent paramMouseEvent) {
    switch (this.GameState) {
      case 6:
        if (this.MessageDelay == 0)
          this.CurrentPlayer.MouseMoved(paramMouseEvent); 
        break;
    } 
  }
  
  public void mouseEntered(MouseEvent paramMouseEvent) {
    showStatus("");
  }
  
  public void mouseExited(MouseEvent paramMouseEvent) {
    showStatus("");
  }
  
  public void mouseMoved(MouseEvent paramMouseEvent) {
    switch (this.GameState) {
      case 17:
        GifButton.SetHovering(false);
        this.btnDaily.CheckHover(paramMouseEvent);
        this.btnMonthly.CheckHover(paramMouseEvent);
        this.btnPermanent.CheckHover(paramMouseEvent);
        if (!GifButton.GetHovering()) {
          setCursor(this.curDefault);
          showStatus("");
        } 
        break;
      case 18:
        this.HighScoreThing.MouseMoved(paramMouseEvent);
        break;
      case 15:
        GifButton.SetHovering(false);
        this.btnEasy.CheckHover(paramMouseEvent);
        this.btnMedium.CheckHover(paramMouseEvent);
        this.btnHard.CheckHover(paramMouseEvent);
        if (!GifButton.GetHovering()) {
          setCursor(this.curDefault);
          showStatus("");
        } 
        break;
      case 10:
        GifButton.SetHovering(false);
        this.btnHelp.CheckHover(paramMouseEvent);
        this.btnHighScores.CheckHover(paramMouseEvent);
        this.btnStart.CheckHover(paramMouseEvent);
        if (!GifButton.GetHovering()) {
          setCursor(this.curDefault);
          showStatus("");
        } 
        break;
      case 4:
        this.btnOK.CheckHover(paramMouseEvent);
        if (!this.btnOK.hovering) {
          setCursor(this.curDefault);
          showStatus("");
        } 
        break;
      case 5:
        this.WeaponStateScreen.CheckHover(paramMouseEvent);
        break;
      case 6:
        if (this.MessageDelay == 0)
          this.CurrentPlayer.MouseMoved(paramMouseEvent); 
        break;
    } 
  }
  
  public void mousePressed(MouseEvent paramMouseEvent) {
    switch (this.GameState) {
      case 6:
        if (this.MessageDelay == 0)
          this.CurrentPlayer.MousePressed(paramMouseEvent); 
        break;
    } 
  }
  
  public void mouseReleased(MouseEvent paramMouseEvent) {
    switch (this.GameState) {
      case 6:
        this.CurrentPlayer.MouseReleased(paramMouseEvent);
        break;
    } 
  }
  
  public void paint(Graphics paramGraphics) {
    update(paramGraphics);
  }
  
  public void run() {
    Frames = 0;
    this.animation.setPriority(1);
    this.TotalFrameCount = 0L;
    this.FirstFrame = System.currentTimeMillis();
    while (this.ItsAGo) {
      Graphics graphics;
      int i;
      int j;
      int k;
      int m;
      FontMetrics fontMetrics;
      String str1;
      String str2;
      String str3;
      byte b;
      String str4;
      String str5;
      String str6;
      int n;
      int i1;
      int i2;
      switch (this.GameState) {
        case 18:
          if (this.HighScoreThing.DoneViewing) {
            this.GameState = 10;
            break;
          } 
          this.HighScoreThing.Update();
          break;
        case 16:
          if (this.egScrollY == this.AppHeight + this.egLineSpacing) {
            if (this.CurrentClip != null) {
              this.CurrentClip.stop();
              this.CurrentClip = null;
            } 
            this.EndGameSound.play();
          } 
          if (this.egScrollLineNum != 666) {
            this.egScrollY--;
            if (this.egScrollY < 0) {
              this.egScrollLineNum++;
              if (this.egScrollLineNum > this.egTotalEndGameLines)
                this.egScrollLineNum = 666; 
              this.egScrollY += this.egLineSpacing;
            } 
            break;
          } 
          this.GameState = 8;
          this.egScrollY = this.AppHeight + this.egLineSpacing;
          this.egScrollLineNum = 0;
          break;
        case 8:
          this.GameBackdrop = createImage(this.AppWidth, this.AppHeight);
          graphics = this.GameBackdrop.getGraphics();
          graphics.drawImage(this.GameScreen.GetImage(), 0, 0, this);
          this.BossImage = getImage(getCodeBase(), "images/enemyboss1.gif");
          this.NextBackdrop = getImage(getCodeBase(), "images/backdrop2.gif");
          this.Tracker.addImage(this.BossImage, 0);
          this.Tracker.addImage(this.NextBackdrop, 0);
          this.GlobalMult++;
          if (this.CurrentPlayer.Cash >= (Weapon.WeaponArray[1]).Cost) {
            this.WeaponPreviousState = 14;
            InitWeaponsScreen();
            break;
          } 
          this.GameState = 14;
          break;
        case 13:
          this.CurrentPlayer = null;
          this.SquirrelManager = null;
          this.GameState = 6;
          break;
        case 6:
          if (this.MessageDelay > 0) {
            this.MessageDelay--;
          } else {
            this.CurrentPlayer.Update();
            this.MissileManagerObject.Update();
            this.SquirrelManager.Update();
            Explosion.Update();
            this.SGObj.Update();
          } 
          if (Enemy.EnemiesLeft == 0) {
            Enemy.EnemiesLeft = -1;
          } else if (Enemy.EnemiesLeft < 0) {
            Enemy.EnemiesLeft--;
            if (Enemy.EnemiesLeft == -25) {
              this.MessageText = "Level " + this.GameLevel + " clear!";
              this.MessageDelay = 25;
              Enemy.EnemiesLeft = 6000;
              if (this.GameLevel >= 25)
                this.GameState = 16; 
            } 
          } else if (Enemy.EnemiesLeft == 6000 && this.MessageDelay == 0) {
            if (this.CurrentPlayer.Cash >= (Weapon.WeaponArray[1]).Cost) {
              this.WeaponPreviousState = 14;
              InitWeaponsScreen();
            } else {
              this.GameState = 14;
            } 
          } 
          if (this.CurrentPlayer.SpriteDead) {
            this.GameState = 19;
            this.tiPlayerName = new TextInput("Enter name followed by [ENTER]: ", Color.black, Color.blue, this.MedFont, this.MedFont, 25, this.AppWidth - 200, 120, 10);
            this.tiPlayerName.Enable();
            this.tiPlayerName.Activate();
          } 
          break;
        case 14:
          NextLevel();
          break;
        case 19:
          this.CurrentPlayer.Update();
          this.MissileManagerObject.Update();
          this.SquirrelManager.Update();
          Explosion.Update();
          this.SGObj.Update();
          if (!this.tiPlayerName.Active()) {
            this.tiPlayerName.Disable();
            this.MessageDelay = 125;
            this.GameState = 9;
            this.PlayerName = this.tiPlayerName.GetInput();
            if (Utils.FilterWords(this.PlayerName, this.Haha))
              this.PlayerName = "Unknown"; 
            AddHighScore();
          } 
          break;
        case 9:
          this.CurrentPlayer.Update();
          this.MissileManagerObject.Update();
          this.SquirrelManager.Update();
          Explosion.Update();
          this.SGObj.Update();
          this.MessageDelay--;
          if (this.MessageDelay < 0) {
            this.HSListChosen = 'P';
            ResetGame();
            if (this.ScorePosition > 0) {
              ViewHighScores(this.ScorePosition - 1);
              break;
            } 
            ViewHighScores(0);
          } 
          break;
        case 12:
          this.HitHighScores = false;
          for (i = 0; i < 6; i++)
            this.GameBoardArray[i] = -1; 
          this.GameScreen.CreateBack();
          this.GameBackdrop = createImage(this.AppWidth, this.AppHeight);
          graphics = this.GameBackdrop.getGraphics();
          graphics.drawImage(this.GameScreen.GetImage(), 0, 0, this);
          this.DiffChosen = false;
          fontMetrics = this.DoubleBuffer.getFontMetrics(this.LargeFont);
          m = fontMetrics.getHeight();
          str1 = "1) Easy";
          str2 = "2) Medium";
          str3 = "3) Hard";
          i = fontMetrics.stringWidth(str1);
          j = fontMetrics.stringWidth(str2);
          k = fontMetrics.stringWidth(str3);
          this.btnEasy = new GifButton((this.AppWidth - i) / 2, 100, i, m, this.LargeFont, Color.blue, Color.red, str1, this);
          this.btnEasy.SetHotKey('1');
          this.btnMedium = new GifButton((this.AppWidth - j) / 2, 120, j, m, this.LargeFont, Color.blue, Color.red, str2, this);
          this.btnMedium.SetHotKey('2');
          this.btnHard = new GifButton((this.AppWidth - k) / 2, 140, k, m, this.LargeFont, Color.blue, Color.red, str3, this);
          this.btnHard.SetHotKey('3');
          this.btnEasy.Enable();
          this.btnMedium.Enable();
          this.btnHard.Enable();
          this.GameState = 15;
          break;
        case 17:
          if (this.HSListChosen != ' ')
            ViewHighScores(0); 
          break;
        case 15:
          if (this.DiffChosen)
            StartGame(); 
          break;
        case 0:
          this.GameBoard = getImage(getCodeBase(), "images/strip.gif");
          this.imgButtons = getImage(getCodeBase(), "images/buttons.gif");
          this.imgButtons2 = getImage(getCodeBase(), "images/buttons2.gif");
          this.imgSpriteStrip = getImage(getCodeBase(), "images/spritestrip.gif");
          this.Tracker = new MediaTracker(this);
          this.Tracker.addImage(this.GameBoard, 1);
          this.Tracker.addImage(this.imgButtons, 2);
          this.Tracker.addImage(this.imgButtons2, 3);
          this.Tracker.addImage(this.imgSpriteStrip, 4);
          Utils.ClearImage(this.DoubleBuffer, Color.black, this.AppWidth, this.AppHeight);
          Utils.ShowString("Loading...", this.DoubleBuffer, this.SmallFont, this.AppWidth, 50);
          Utils.ShowString("This may take up to 2 minutes on a 56k connection.", this.DoubleBuffer, this.SmallFont, this.AppWidth, 70);
          Utils.ShowString("Loading Image 1 / 4", this.DoubleBuffer, this.SmallFont, this.AppWidth, 90);
          do {
          
          } while (!this.Tracker.checkID(1, true));
          Utils.ShowString("Loading Image 2 / 4", this.DoubleBuffer, this.SmallFont, this.AppWidth, 110);
          do {
          
          } while (!this.Tracker.checkID(2, true));
          Utils.ShowString("Loading Image 3 / 4", this.DoubleBuffer, this.SmallFont, this.AppWidth, 130);
          do {
          
          } while (!this.Tracker.checkID(3, true));
          Utils.ShowString("Loading Image 4 / 4", this.DoubleBuffer, this.SmallFont, this.AppWidth, 150);
          do {
          
          } while (!this.Tracker.checkID(4, true));
          this.IntroMusic = getAudioClip(getCodeBase(), "intro.au");
          this.BGMusicOn = true;
          this.mtIntroMusic = new MusicThread(this.IntroMusic, true);
          this.mtIntroMusic.start();
          this.CurrentClip = this.IntroMusic;
          Utils.ShowString("Loading Intro Music", this.DoubleBuffer, this.SmallFont, this.AppWidth, 170);
          do {
          
          } while (!this.mtIntroMusic.done());
          Utils.ShowString("Loading Sound 1 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 190);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "laser1.au"), 11);
          SoundMan.LoadClip(11);
          Utils.ShowString("Loading Sound 2 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 210);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "rocket1.au"), 12);
          SoundMan.LoadClip(12);
          Utils.ShowString("Loading Sound 3 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 230);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "fire1.au"), 13);
          SoundMan.LoadClip(13);
          Utils.ShowString("Loading Sound 4 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 250);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "enemyrock.au"), 21);
          SoundMan.LoadClip(21);
          Utils.ShowString("Loading Sound 5 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 270);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "enemylaser.au"), 22);
          SoundMan.LoadClip(22);
          Utils.ShowString("Loading Sound 6 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 290);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "fire1.au"), 23);
          SoundMan.LoadClip(23);
          Utils.ShowString("Loading Sound 7 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 310);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "weregonnagetyou.au"), 0);
          SoundMan.LoadClip(0);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "explosion.au"), 31);
          SoundMan.LoadClip(31);
          Utils.ShowString("Loading Sound 8 / 8", this.DoubleBuffer, this.SmallFont, this.AppWidth, 330);
          SoundMan.AddClip(getAudioClip(getCodeBase(), "imgonnagetyou.au"), 1);
          SoundMan.LoadClip(1);
          this.ThemeMusic = getAudioClip(getCodeBase(), "theme.au");
          this.mtThemeMusic = new MusicThread(this.ThemeMusic, false);
          this.mtThemeMusic.start();
          this.ieSprites = new ImageExtractor(this.imgSpriteStrip, 512, 896);
          this.imgSpriteStrip = null;
          this.imgExtractor = new ImageExtractor(this.imgButtons, 256, 288);
          for (b = 0; b < 6; b++)
            this.GameBoardArray[b] = -1; 
          this.GameScreen = new TileGameObj(this.GameBoard, this.GameBoardArray);
          this.SGObj = new ShieldGenerator();
          this.MissileManagerObject = new MissileManager(this.AppWidth, this.AppHeight - 40, this.SGObj, this.ieSprites);
          this.CurrentPlayer = new Player(this.ieSprites, this.MissileManagerObject, this.AppWidth, this.AppHeight, this.SGObj);
          this.CurrentPlayer.StateSpriteInfo.Enable();
          Explosion.InitExplosion(this.ieSprites, 30);
          this.ieSprites = null;
          this.WeaponsScreenBack = getImage(getCodeBase(), "images/weaponstrip.gif");
          this.imgEnemySprites[0] = getImage(getCodeBase(), "images/enemystrip0.gif");
          this.GameState = 2;
          this.Tracker.addImage(this.WeaponsScreenBack, 0);
          this.Tracker.addImage(this.imgEnemySprites[0], 0);
          str4 = "Daily List";
          str5 = "Monthly List";
          str6 = "Permanent List";
          fontMetrics = this.DoubleBuffer.getFontMetrics(this.LargeFont);
          m = fontMetrics.getHeight();
          i1 = fontMetrics.stringWidth(str4);
          n = fontMetrics.stringWidth(str5);
          i2 = fontMetrics.stringWidth(str6);
          this.btnDaily = new GifButton((this.AppWidth - i1) / 2, 100, i1, m, this.LargeFont, Color.blue, Color.red, str4, this);
          this.btnDaily.SetHotKey('D');
          this.btnMonthly = new GifButton((this.AppWidth - n) / 2, 120, n, m, this.LargeFont, Color.blue, Color.red, str5, this);
          this.btnMonthly.SetHotKey('M');
          this.btnPermanent = new GifButton((this.AppWidth - i2) / 2, 140, i2, m, this.LargeFont, Color.blue, Color.red, str6, this);
          this.btnPermanent.SetHotKey('P');
          break;
        case 2:
          Frames++;
          if (Frames == 10) {
            this.GameBoardArray[0] = 0;
            break;
          } 
          if (Frames == 110) {
            this.GameBoardArray[4] = 4;
            break;
          } 
          if (Frames == 210) {
            this.GameBoardArray[2] = 2;
            break;
          } 
          if (Frames == 310) {
            this.GameBoardArray[1] = 1;
            break;
          } 
          if (Frames == 410) {
            this.GameBoardArray[3] = 3;
            break;
          } 
          if (Frames == 510) {
            this.GameBoardArray[5] = 5;
            break;
          } 
          if (Frames == 1000) {
            for (byte b1 = 1; b1 < 6; b1++)
              this.GameBoardArray[b1] = -1; 
            Frames = 0;
            this.GameState = 3;
          } 
          break;
        case 3:
          this.btnStart = new GifButton(16, 352, 64, 32, this.imgExtractor.ExtractImage(0, 192, 64, 32), this.imgExtractor.ExtractImage(64, 192, 64, 32), this.imgExtractor.ExtractImage(64, 192, 64, 32), this);
          this.btnHighScores = new GifButton(this.AppWidth - 16 - 64, 352, 64, 32, this.imgExtractor.ExtractImage(0, 160, 64, 32), this.imgExtractor.ExtractImage(64, 160, 64, 32), this.imgExtractor.ExtractImage(64, 160, 64, 32), this);
          this.btnHelp = new GifButton(this.AppWidth / 2 - 32, 352, 64, 32, this.imgExtractor.ExtractImage(0, 256, 64, 32), this.imgExtractor.ExtractImage(64, 256, 64, 32), this.imgExtractor.ExtractImage(64, 256, 64, 32), this);
          this.btnStart.SetHotKey('B');
          this.btnHighScores.SetHotKey('S');
          this.btnHelp.SetHotKey('H');
          this.btnStart.Enable();
          this.btnHighScores.Enable();
          this.btnHelp.Enable();
          this.GameState = 10;
          break;
        case 4:
          if (this.imgButtons != null)
            this.imgButtons = null; 
          if (this.imgButtons2 != null)
            this.imgButtons2 = null; 
          break;
      } 
      try {
        Thread.sleep(40L);
      } catch (Exception exception) {}
      repaint(0L, 0, 0, this.AppWidth, this.AppHeight);
    } 
  }
  
  public void start() {
    this.ItsAGo = true;
    if (this.animation == null) {
      this.animation = new Thread(this);
      this.animation.start();
    } 
  }
  
  public void stop() {
    this.BGMusicOn = false;
    this.IntroMusic.stop();
    this.ThemeMusic.stop();
    this.BossMusic.stop();
    this.ItsAGo = false;
    this.animation.stop();
    this.animation = null;
  }
  
  public void update(Graphics paramGraphics) {
    int i;
    int j;
    switch (this.GameState) {
      case 11:
        ClearBottom(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 0:
        ClearBottom(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 18:
        this.GameScreen.CreateBack();
        this.DoubleBuffer.drawImage(this.GameScreen.GetImage(), 0, 0, this);
        this.HighScoreThing.Paint(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 17:
        this.GameScreen.CreateBack();
        this.DoubleBuffer.drawImage(this.GameScreen.GetImage(), 0, 0, this);
        ClearBottom(this.DoubleBuffer);
        Utils.CenterString(this.DoubleBuffer, Color.yellow, "Choose the list to view:", this.LargeFont2, this.AppWidth, 80);
        this.btnDaily.Paint(this.DoubleBuffer);
        this.btnMonthly.Paint(this.DoubleBuffer);
        this.btnPermanent.Paint(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 15:
        this.GameScreen.CreateBack();
        this.DoubleBuffer.drawImage(this.GameScreen.GetImage(), 0, 0, this);
        ClearBottom(this.DoubleBuffer);
        Utils.CenterString(this.DoubleBuffer, Color.yellow, "Choose a difficulty level:", this.LargeFont2, this.AppWidth, 40);
        Utils.CenterString(this.DoubleBuffer, Color.yellow, "(easier levels give more money but less points)", this.MedFont, this.AppWidth, 60);
        this.btnEasy.Paint(this.DoubleBuffer);
        this.btnMedium.Paint(this.DoubleBuffer);
        this.btnHard.Paint(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 2:
        this.GameScreen.CreateBack();
        this.DoubleBuffer.drawImage(this.GameScreen.GetImage(), 0, 0, this);
        ClearBottom(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 1:
        this.DoubleBuffer.drawImage(this.GameBackdrop, 0, 0, this);
        ClearBottom(this.DoubleBuffer);
        this.CurrentPlayer.Paint(this.DoubleBuffer);
        this.SquirrelManager.Paint(this.DoubleBuffer);
        this.MissileManagerObject.Paint(this.DoubleBuffer);
        Explosion.Paint(this.DoubleBuffer);
        this.SGObj.Paint(this.DoubleBuffer);
        Utils.CenterString(this.DoubleBuffer, Color.red, "P A U S E D", this.LargeFont2, this.AppWidth, this.AppHeight / 2);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 16:
        if (this.BGOff) {
          this.DoubleBuffer.setColor(Color.black);
          this.DoubleBuffer.fillRect(0, 0, this.AppWidth, this.AppHeight);
        } else {
          this.DoubleBuffer.drawImage(this.GameBackdrop, 0, 0, this);
        } 
        ClearBottom(this.DoubleBuffer);
        i = 0;
        for (j = this.egScrollLineNum; j < this.egTotalEndGameLines; j++) {
          Utils.CenterString(this.DoubleBuffer, Color.white, this.egEndGameLines[j], this.LargeFont, this.AppWidth, i + this.egScrollY);
          i += this.egLineSpacing;
        } 
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 6:
        if (this.BGOff) {
          this.DoubleBuffer.setColor(Color.black);
          this.DoubleBuffer.fillRect(0, 0, this.AppWidth, this.AppHeight);
        } else {
          this.DoubleBuffer.drawImage(this.GameBackdrop, 0, 0, this);
        } 
        ClearBottom(this.DoubleBuffer);
        this.CurrentPlayer.Paint(this.DoubleBuffer);
        if (this.MessageText != "" && this.MessageDelay != 0) {
          Utils.CenterString(this.DoubleBuffer, Color.white, this.MessageText, this.LargeFont, this.AppWidth, 150);
        } else {
          this.SquirrelManager.Paint(this.DoubleBuffer);
          this.MissileManagerObject.Paint(this.DoubleBuffer);
          Explosion.Paint(this.DoubleBuffer);
          this.SGObj.Paint(this.DoubleBuffer);
        } 
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 19:
        if (this.BGOff) {
          this.DoubleBuffer.setColor(Color.black);
          this.DoubleBuffer.fillRect(0, 0, this.AppWidth, this.AppHeight);
        } else {
          this.DoubleBuffer.drawImage(this.GameBackdrop, 0, 0, this);
        } 
        ClearBottom(this.DoubleBuffer);
        this.CurrentPlayer.Paint(this.DoubleBuffer);
        this.SquirrelManager.Paint(this.DoubleBuffer);
        this.MissileManagerObject.Paint(this.DoubleBuffer);
        Explosion.Paint(this.DoubleBuffer);
        this.SGObj.Paint(this.DoubleBuffer);
        this.tiPlayerName.Paint(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 9:
        if (this.BGOff) {
          this.DoubleBuffer.setColor(Color.black);
          this.DoubleBuffer.fillRect(0, 0, this.AppWidth, this.AppHeight);
        } else {
          this.DoubleBuffer.drawImage(this.GameBackdrop, 0, 0, this);
        } 
        ClearBottom(this.DoubleBuffer);
        this.CurrentPlayer.Paint(this.DoubleBuffer);
        this.SquirrelManager.Paint(this.DoubleBuffer);
        this.MissileManagerObject.Paint(this.DoubleBuffer);
        Explosion.Paint(this.DoubleBuffer);
        this.SGObj.Paint(this.DoubleBuffer);
        if (this.HitHighScores) {
          if (this.ScorePosition == -1 || this.ScorePosition == 0) {
            Utils.SetDefCS(this.DoubleBuffer, Color.white, this.LargeFont, this.AppWidth);
            Utils.CenterString("GAME OVER", (this.AppHeight >> 1) - 20);
            Utils.CenterString("You didn't make the all-time high score list.", (this.AppHeight >> 1) + 20);
          } else {
            Utils.SetDefCS(this.DoubleBuffer, Color.white, this.LargeFont, this.AppWidth);
            Utils.CenterString("GAME OVER", (this.AppHeight >> 1) - 20);
            Utils.CenterString("You placed #" + this.ScorePosition + " on the all-time high score list!", (this.AppHeight >> 1) + 20);
          } 
        } else {
          Utils.SetDefCS(this.DoubleBuffer, Color.white, this.LargeFont, this.AppWidth);
          Utils.CenterString("GAME OVER", (this.AppHeight >> 1) - 20);
          Utils.CenterString("Checking High Score List, Please Wait", (this.AppHeight >> 1) + 20);
        } 
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 10:
        DrawIntroPage(paramGraphics);
        break;
      case 4:
        if (!this.WeaponsScreenLoaded) {
          ClearBottom(this.DoubleBuffer);
          paramGraphics.drawImage(this.ImageBack, 0, 0, this);
          break;
        } 
        this.WeaponStateScreen.DrawBack(this.DoubleBuffer);
        ClearBottom(this.DoubleBuffer);
        Utils.SetDefCS(this.DoubleBuffer, Color.red, this.HugeFont, this.AppWidth);
        Utils.CenterString("You will see this message only once,", 25);
        Utils.CenterString("so READ IT!!", 50);
        Utils.SetDefCS(this.DoubleBuffer, Color.yellow, this.LargeFont2, this.AppWidth);
        Utils.CenterString("This is the weapons purchase screen.  You can", 105);
        Utils.CenterString("buy any weapon/upgrade you can afford, and sell any", 125);
        Utils.CenterString("weapon you have.  Click on the '?' to see", 145);
        Utils.CenterString("exactly what each weapon's capabilites are.", 165);
        Utils.CenterString("Click on 'next' or 'prev' to view more weapons", 185);
        Utils.CenterString("if they're available.  When you're finished (you", 205);
        Utils.CenterString("don't have to spend all your money), click 'done'.", 225);
        Utils.CenterString("If you wish to view the upgrades available, click", 245);
        Utils.CenterString("the 'Install Upgrades' button.  Simply click on", 265);
        Utils.CenterString("the 'Purchase Weapons' button to return.", 285);
        Utils.SetDefCS(this.DoubleBuffer, Color.white, this.LargeFont, this.AppWidth);
        Utils.CenterString("Click 'OK' when you're ready to begin.", 325);
        this.btnOK.Paint(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        break;
      case 5:
        this.WeaponStateScreen.DrawAll(this.DoubleBuffer);
        ClearBottom(this.DoubleBuffer);
        paramGraphics.drawImage(this.ImageBack, 0, 0, this);
        if (this.WeaponStateScreen.done) {
          this.WeaponStateScreen.done = false;
          j = 0;
          for (byte b = 0; b < 45; b++) {
            if (this.CurrentPlayer.WeaponUses[b] != -2)
              j = 1; 
          } 
          if (j == 0)
            this.CurrentPlayer.WeaponUses[0] = (Weapon.WeaponArray[0]).Uses; 
          if (this.CurrentPlayer.WeaponUses[this.CurrentPlayer.CurrentWeapon] == -2) {
            this.CurrentPlayer.CurrentWeapon = 0;
            if (this.CurrentPlayer.WeaponUses[0] == -2)
              this.CurrentPlayer.NextWeapon(); 
          } 
          setCursor(this.curDefault);
          if (!this.Tracker.checkAll(true)) {
            Utils.ClearImage(this.DoubleBuffer, Color.black, this.AppWidth, this.AppHeight);
            Utils.ShowString("Loading Sprite Data", this.DoubleBuffer, this.SmallFont, this.AppWidth, 50);
            Utils.ShowString("Please wait... (<30 sec at 56k)", this.DoubleBuffer, this.SmallFont, this.AppWidth, 70);
            do {
            
            } while (!this.Tracker.checkAll(true));
          } 
          this.GameState = this.WeaponPreviousState;
        } 
        break;
    } 
    if (this.showFPS) {
      this.TotalFrameCount++;
      this.FPS = this.TotalFrameCount * 10000L / (System.currentTimeMillis() - this.FirstFrame);
      paramGraphics.setFont(this.SmallFont);
      paramGraphics.setColor(Color.white);
      paramGraphics.drawString(String.valueOf(this.FPS / 10L) + "." + (this.FPS % 10L) + " fps", this.AppWidth - 100, this.AppHeight - 2);
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\game.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */