import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public final class WeaponsScreen {
  static final int WEAPONSPERPAGE = 6;
  
  static final int IMAGEWIDTH = 512;
  
  static final int IMAGEHEIGHT = 384;
  
  static final int IMAGEBOTTOM = 384;
  
  static final int MODE_WEAPONS = 0;
  
  static final int MODE_UPGRADES = 1;
  
  boolean done = false;
  
  Cursor curDefault = Cursor.getDefaultCursor();
  
  Font LargeFont = new Font("Dialog", 1, 18);
  
  Font MedFont = new Font("Dialog", 1, 14);
  
  Font SmallFont = new Font("Dialog", 1, 12);
  
  int NumWeapons;
  
  Player PlayerData;
  
  Weapon[] WeaponArray = new Weapon[6];
  
  Upgrade[] UpgradeArray = new Upgrade[4];
  
  Image WeaponsScreenImage;
  
  GifButton[] btnBuy;
  
  GifButton[] btnSell;
  
  GifButton btnDone;
  
  GifButton[] btnInfo;
  
  GifButton[] btnReload;
  
  GifButton btnNext;
  
  GifButton btnPrev;
  
  GifButton btnPurchaseWeapons;
  
  GifButton btnInstallUpgrades;
  
  Applet App = Utils.GetApp();
  
  int Mode;
  
  int WeaponStart;
  
  int[] XPositions = new int[] { 64, 208, 288 };
  
  int YSize = 32;
  
  int YTextStart = 118;
  
  int YButtonStart = 96;
  
  static int TempCount;
  
  static int db_Count;
  
  static int InfoWeapon = -1;
  
  static String Info;
  
  static int ch_Count;
  
  public WeaponsScreen(Player paramPlayer, Image paramImage1, Image paramImage2, Image paramImage3) {
    this.PlayerData = paramPlayer;
    this.NumWeapons = Weapon.GetArraySize();
    this.WeaponsScreenImage = paramImage1;
    SetupButtons1(paramImage2);
    SetupButtons2(paramImage3);
    Init();
  }
  
  public void CheckClicks(MouseEvent paramMouseEvent) {
    InfoWeapon = -1;
    if (this.Mode == 0) {
      for (byte b = 0; b < 6; b++) {
        if (b + this.WeaponStart < 45)
          this.btnInfo[b].Enable(); 
        if (this.btnBuy[b].CheckClick(paramMouseEvent) && this.PlayerData.Cash >= (this.WeaponArray[b]).Cost)
          this.PlayerData.BuyWeapon(b + this.WeaponStart); 
        if (this.btnReload[b].CheckClick(paramMouseEvent)) {
          int i = GetReloadCost(this.WeaponArray[b], this.PlayerData.WeaponUses[b + this.WeaponStart]);
          if (this.PlayerData.Cash >= i) {
            this.PlayerData.Cash -= i;
            this.PlayerData.WeaponUses[b + this.WeaponStart] = (this.WeaponArray[b]).Uses;
          } 
        } 
        if (this.btnSell[b].CheckClick(paramMouseEvent) && this.PlayerData.WeaponUses[b + this.WeaponStart] >= (this.WeaponArray[b]).Uses)
          this.PlayerData.SellWeapon(b + this.WeaponStart); 
        if (this.btnInfo[b].CheckClick(paramMouseEvent)) {
          InfoWeapon = b;
          this.btnInfo[b].Disable();
        } 
      } 
      if (this.btnNext.CheckClick(paramMouseEvent)) {
        this.WeaponStart += 6;
        SetupWeaponArray();
      } 
      if (this.btnPrev.CheckClick(paramMouseEvent)) {
        this.WeaponStart -= 6;
        if (this.WeaponStart < 0)
          this.WeaponStart = 0; 
        SetupWeaponArray();
      } 
    } else if (this.Mode == 1) {
      for (byte b = 0; b < 4; b++) {
        this.btnInfo[b].Enable();
        if (this.btnBuy[b].CheckClick(paramMouseEvent) && this.PlayerData.Cash >= Upgrade.GetCost(b, this.PlayerData) && this.PlayerData.UpgradeLevel[b] < (this.UpgradeArray[b]).MaxUpgradeLevel) {
          Upgrade.DoUpgrade(b, this.PlayerData);
          SetupUpgradeArray();
        } 
        if (this.btnInfo[b].CheckClick(paramMouseEvent)) {
          InfoWeapon = b;
          this.btnInfo[b].Disable();
        } 
      } 
    } 
    if (this.btnDone.CheckClick(paramMouseEvent))
      this.done = true; 
    if (this.btnPurchaseWeapons.CheckClick(paramMouseEvent)) {
      this.Mode = 0;
      SetupWeaponArray();
    } 
    if (this.btnInstallUpgrades.CheckClick(paramMouseEvent)) {
      this.Mode = 1;
      SetupUpgradeArray();
    } 
    this.App.repaint();
  }
  
  public void CheckHover(MouseEvent paramMouseEvent) {
    GifButton.SetHovering(false);
    if (this.Mode == 0) {
      ch_Count = 0;
      while (ch_Count < 6) {
        this.btnBuy[ch_Count].CheckHover(paramMouseEvent);
        this.btnSell[ch_Count].CheckHover(paramMouseEvent);
        this.btnInfo[ch_Count].CheckHover(paramMouseEvent);
        this.btnReload[ch_Count].CheckHover(paramMouseEvent);
        ch_Count++;
      } 
      this.btnNext.CheckHover(paramMouseEvent);
      this.btnPrev.CheckHover(paramMouseEvent);
    } else if (this.Mode == 1) {
      ch_Count = 0;
      while (ch_Count < 4) {
        this.btnBuy[ch_Count].CheckHover(paramMouseEvent);
        this.btnInfo[ch_Count].CheckHover(paramMouseEvent);
        ch_Count++;
      } 
    } 
    this.btnDone.CheckHover(paramMouseEvent);
    this.btnPurchaseWeapons.CheckHover(paramMouseEvent);
    this.btnInstallUpgrades.CheckHover(paramMouseEvent);
    if (!GifButton.GetHovering()) {
      this.App.setCursor(this.curDefault);
      this.App.showStatus("");
    } 
  }
  
  public void CheckKeys(KeyEvent paramKeyEvent) {
    if (this.Mode == 0) {
      if (this.btnNext.CheckKeyPress(paramKeyEvent)) {
        this.WeaponStart += 6;
        SetupWeaponArray();
      } 
      if (this.btnPrev.CheckKeyPress(paramKeyEvent)) {
        this.WeaponStart -= 6;
        if (this.WeaponStart < 0)
          this.WeaponStart = 0; 
        SetupWeaponArray();
      } 
    } 
    if (this.btnDone.CheckKeyPress(paramKeyEvent))
      this.done = true; 
    if (this.btnPurchaseWeapons.CheckKeyPress(paramKeyEvent)) {
      this.Mode = 0;
      SetupWeaponArray();
    } 
    if (this.btnInstallUpgrades.CheckKeyPress(paramKeyEvent)) {
      this.Mode = 1;
      SetupUpgradeArray();
    } 
    this.App.repaint();
  }
  
  public void DrawAll(Graphics paramGraphics) {
    DrawBack(paramGraphics);
    DrawText(paramGraphics);
    DrawButtons(paramGraphics);
    DrawInfoBar(paramGraphics);
  }
  
  public void DrawBack(Graphics paramGraphics) {
    paramGraphics.drawImage(this.WeaponsScreenImage, 0, 0, this.App);
  }
  
  public void DrawButtons(Graphics paramGraphics) {
    if (this.Mode == 0) {
      db_Count = 0;
      while (db_Count < 6) {
        if (db_Count + this.WeaponStart >= 45) {
          this.btnBuy[db_Count].Disable();
          this.btnSell[db_Count].Disable();
          this.btnReload[db_Count].Disable();
          this.btnInfo[db_Count].Disable();
        } else {
          int i = this.PlayerData.WeaponUses[db_Count + this.WeaponStart];
          if (this.PlayerData.Cash >= (this.WeaponArray[db_Count]).Cost && i == -2) {
            this.btnBuy[db_Count].Enable();
            this.btnReload[db_Count].Disable();
            this.btnBuy[db_Count].Paint(paramGraphics);
          } else if (i != (this.WeaponArray[db_Count]).Uses && i != -2) {
            this.btnBuy[db_Count].Disable();
            if (this.PlayerData.Cash >= GetReloadCost(this.WeaponArray[db_Count], i)) {
              this.btnReload[db_Count].Enable();
            } else {
              this.btnReload[db_Count].Disable();
            } 
            this.btnReload[db_Count].Paint(paramGraphics);
          } else {
            this.btnReload[db_Count].Disable();
            this.btnBuy[db_Count].Disable();
            this.btnBuy[db_Count].Paint(paramGraphics);
          } 
          if (i >= (this.WeaponArray[db_Count]).Uses) {
            this.btnSell[db_Count].Enable();
          } else {
            this.btnSell[db_Count].Disable();
          } 
          this.btnSell[db_Count].Paint(paramGraphics);
          this.btnInfo[db_Count].Paint(paramGraphics);
        } 
        this.btnNext.Paint(paramGraphics);
        this.btnPrev.Paint(paramGraphics);
        db_Count++;
      } 
    } else if (this.Mode == 1) {
      db_Count = 0;
      while (db_Count < 4) {
        if (this.PlayerData.Cash >= Upgrade.GetCost(db_Count, this.PlayerData) && this.PlayerData.UpgradeLevel[db_Count] < (this.UpgradeArray[db_Count]).MaxUpgradeLevel) {
          this.btnBuy[db_Count].Enable();
        } else {
          this.btnBuy[db_Count].Disable();
        } 
        if (db_Count == 3 && this.PlayerData.Power == this.PlayerData.MaxPower)
          this.btnBuy[db_Count].Disable(); 
        this.btnBuy[db_Count].Paint(paramGraphics);
        this.btnInfo[db_Count].Paint(paramGraphics);
        db_Count++;
      } 
    } 
    this.btnDone.Paint(paramGraphics);
    this.btnPurchaseWeapons.Paint(paramGraphics);
    this.btnInstallUpgrades.Paint(paramGraphics);
  }
  
  public void DrawInfoBar(Graphics paramGraphics) {
    if (InfoWeapon != -1) {
      if (this.Mode == 0) {
        Weapon weapon = this.WeaponArray[InfoWeapon];
        Info = String.valueOf(weapon.Damage) + " " + Weapon.GetDamageType(weapon.Special) + "  Class: " + Weapon.GetWeaponType(weapon.Special) + ", Fire Delay of " + weapon.FireTime + ", " + ((weapon.Uses > 0) ? Integer.toString(weapon.Uses) : "Unlimited") + " Rounds / Unit";
      } else if (this.Mode == 1) {
        Info = this.UpgradeArray[InfoWeapon].GetDescription();
      } 
      paramGraphics.setFont(this.SmallFont);
      paramGraphics.setColor(new Color(255, 255, 0));
      paramGraphics.drawString(Info, 32, 6 * this.YSize + this.YTextStart);
    } 
  }
  
  public void DrawText(Graphics paramGraphics) {
    if (this.Mode == 0) {
      for (byte b = 0; b < 6; b++) {
        if (b + this.WeaponStart >= 45) {
          b = 6;
        } else {
          String str1 = (this.WeaponArray[b]).Name;
          String str2 = Integer.toString((this.WeaponArray[b]).Cost);
          String str3 = Integer.toString(this.PlayerData.WeaponUses[b + this.WeaponStart]);
          if (this.PlayerData.WeaponUses[b + this.WeaponStart] == -1)
            str3 = "Inf."; 
          if (this.PlayerData.WeaponUses[b + this.WeaponStart] == -2)
            str3 = "N/A"; 
          paramGraphics.setFont(this.MedFont);
          if ((this.WeaponArray[b]).Cost <= 10000) {
            paramGraphics.setColor(new Color(0, 225, 225));
          } else {
            paramGraphics.setColor(new Color(250, 195, 0));
          } 
          paramGraphics.drawString(str1, this.XPositions[0], b * this.YSize + this.YTextStart);
          paramGraphics.drawString(str2, this.XPositions[1], b * this.YSize + this.YTextStart);
          paramGraphics.drawString(str3, this.XPositions[2], b * this.YSize + this.YTextStart);
        } 
      } 
    } else if (this.Mode == 1) {
      for (byte b = 0; b < 4; b++) {
        String str1 = (this.UpgradeArray[b]).Name;
        int i = Upgrade.GetCost(b, this.PlayerData);
        int j = this.PlayerData.UpgradeLevel[b];
        String str2 = Integer.toString(i);
        String str3 = Integer.toString(j);
        paramGraphics.setFont(this.MedFont);
        if (i <= 10000) {
          paramGraphics.setColor(new Color(0, 225, 225));
        } else {
          paramGraphics.setColor(new Color(250, 195, 0));
        } 
        paramGraphics.drawString(str1, this.XPositions[0], b * this.YSize + this.YTextStart);
        paramGraphics.drawString(str2, this.XPositions[1], b * this.YSize + this.YTextStart);
        paramGraphics.drawString(str3, this.XPositions[2], b * this.YSize + this.YTextStart);
      } 
    } 
    Utils.CenterString(paramGraphics, new Color(200, 200, 255), "$" + this.PlayerData.Cash, this.LargeFont, 824, 80);
  }
  
  private int GetReloadCost(Weapon paramWeapon, int paramInt) {
    null = paramWeapon.Cost >> 1;
    int j = paramWeapon.Uses;
    if (paramInt == -2)
      paramInt = 0; 
    int i = j - paramInt;
    return null * i / j;
  }
  
  public void Init() {
    this.Mode = 0;
    this.WeaponStart = 0;
    SetupWeaponArray();
    SetupUpgradeArray();
  }
  
  private void SetupButtons1(Image paramImage) {
    ImageExtractor imageExtractor = new ImageExtractor(paramImage, 512, 384);
    Image image19 = imageExtractor.ExtractImage(0, 224, 64, 32);
    Image image20 = imageExtractor.ExtractImage(64, 224, 64, 32);
    Image image21 = imageExtractor.ExtractImage(128, 224, 64, 32);
    Image image1 = imageExtractor.ExtractImage(0, 0, 64, 32);
    Image image2 = imageExtractor.ExtractImage(64, 0, 64, 32);
    Image image3 = imageExtractor.ExtractImage(128, 0, 64, 32);
    Image image4 = imageExtractor.ExtractImage(0, 32, 64, 32);
    Image image5 = imageExtractor.ExtractImage(64, 32, 64, 32);
    Image image6 = imageExtractor.ExtractImage(128, 32, 64, 32);
    Image image7 = imageExtractor.ExtractImage(0, 64, 64, 32);
    Image image8 = imageExtractor.ExtractImage(64, 64, 64, 32);
    Image image9 = imageExtractor.ExtractImage(128, 64, 64, 32);
    Image image10 = imageExtractor.ExtractImage(0, 96, 64, 32);
    Image image11 = imageExtractor.ExtractImage(64, 96, 64, 32);
    Image image12 = imageExtractor.ExtractImage(128, 96, 64, 32);
    Image image13 = imageExtractor.ExtractImage(0, 128, 64, 32);
    Image image14 = imageExtractor.ExtractImage(64, 128, 64, 32);
    Image image15 = imageExtractor.ExtractImage(64, 128, 64, 32);
    Image image16 = imageExtractor.ExtractImage(128, 128, 32, 32);
    Image image17 = imageExtractor.ExtractImage(160, 128, 32, 32);
    Image image18 = imageExtractor.ExtractImage(160, 128, 32, 32);
    this.btnReload = new GifButton[6];
    this.btnBuy = new GifButton[6];
    this.btnSell = new GifButton[6];
    this.btnInfo = new GifButton[6];
    for (byte b = 0; b < 6; b++) {
      this.btnReload[b] = new GifButton(352, b * this.YSize + this.YButtonStart, 64, 32, image19, image20, image21, this.App);
      this.btnBuy[b] = new GifButton(352, b * this.YSize + this.YButtonStart, 64, 32, image1, image2, image3, this.App);
      this.btnSell[b] = new GifButton(416, b * this.YSize + this.YButtonStart, 64, 32, image4, image5, image6, this.App);
      this.btnInfo[b] = new GifButton(16, b * this.YSize + this.YButtonStart, 32, 32, image16, image17, image18, this.App);
      this.btnInfo[b].Enable();
      this.btnInfo[b].SetHoverString("Get specs for this weapon");
    } 
    this.btnNext = new GifButton(416, 7 * this.YSize + this.YButtonStart, 64, 32, image7, image8, image9, this.App);
    this.btnNext.Enable();
    this.btnNext.SetHoverString("Next page of weapons");
    this.btnNext.SetHotKey('N');
    this.btnPrev = new GifButton(32, 7 * this.YSize + this.YButtonStart, 64, 32, image10, image11, image12, this.App);
    this.btnPrev.Enable();
    this.btnPrev.SetHoverString("Previous page of weapons");
    this.btnPrev.SetHotKey('P');
    this.btnDone = new GifButton(224, 7 * this.YSize + this.YButtonStart, 64, 32, image13, image14, image15, this.App);
    this.btnDone.Enable();
    this.btnDone.SetHoverString("Start the level!");
    this.btnDone.SetHotKey('D');
  }
  
  private void SetupButtons2(Image paramImage) {
    ImageExtractor imageExtractor = new ImageExtractor(paramImage, 512, 384);
    Image image1 = imageExtractor.ExtractImage(0, 0, 128, 32);
    Image image2 = imageExtractor.ExtractImage(128, 0, 128, 32);
    Image image3 = imageExtractor.ExtractImage(256, 0, 128, 32);
    Image image4 = imageExtractor.ExtractImage(0, 32, 128, 32);
    Image image5 = imageExtractor.ExtractImage(128, 32, 128, 32);
    Image image6 = imageExtractor.ExtractImage(256, 32, 128, 32);
    this.btnPurchaseWeapons = new GifButton(64, this.YButtonStart - 80, 128, 32, image1, image2, image3, this.App);
    this.btnPurchaseWeapons.Disable();
    this.btnPurchaseWeapons.SetHoverString("Show weapons available");
    this.btnPurchaseWeapons.SetHotKey('W');
    this.btnInstallUpgrades = new GifButton(320, this.YButtonStart - 80, 128, 32, image4, image5, image6, this.App);
    this.btnInstallUpgrades.Enable();
    this.btnInstallUpgrades.SetHoverString("Show upgrades available");
    this.btnInstallUpgrades.SetHotKey('U');
  }
  
  public void SetupUpgradeArray() {
    this.btnPurchaseWeapons.Enable();
    this.btnInstallUpgrades.Disable();
    this.UpgradeArray = Upgrade.UpgradeArray;
    this.btnPrev.Disable();
    this.btnNext.Disable();
    for (byte b = 0; b < 4; b++) {
      this.btnInfo[b].Enable();
      this.btnBuy[b].SetHoverString("Buy " + (this.UpgradeArray[b]).Name + " for $" + Upgrade.GetCost(b, this.PlayerData));
    } 
    this.btnDone.hovering = false;
  }
  
  public void SetupWeaponArray() {
    this.btnPurchaseWeapons.Disable();
    this.btnInstallUpgrades.Enable();
    for (int i = this.WeaponStart; i < this.WeaponStart + 6; i++) {
      if (i >= 45) {
        i = this.WeaponStart + 6;
      } else {
        TempCount = i - this.WeaponStart;
        this.WeaponArray[TempCount] = Weapon.WeaponArray[i];
        this.btnBuy[TempCount].SetHoverString("Buy " + Utils.AN((this.WeaponArray[TempCount]).Name) + " for $" + (this.WeaponArray[TempCount]).Cost);
        this.btnSell[TempCount].SetHoverString("Sell your " + (this.WeaponArray[TempCount]).Name + " for $" + ((this.WeaponArray[TempCount]).Cost >> 1));
        this.btnReload[TempCount].SetHoverString("Reload your " + (this.WeaponArray[TempCount]).Name + " for $" + GetReloadCost(this.WeaponArray[TempCount], this.PlayerData.WeaponUses[i]));
      } 
    } 
    if (this.WeaponStart == 0) {
      this.btnPrev.Disable();
    } else {
      this.btnPrev.Enable();
    } 
    if (this.WeaponStart >= 39) {
      this.btnNext.Disable();
    } else {
      this.btnNext.Enable();
    } 
    for (byte b = 0; b < 6; b++)
      this.btnInfo[b].Enable(); 
    this.btnDone.hovering = false;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\WeaponsScreen.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */