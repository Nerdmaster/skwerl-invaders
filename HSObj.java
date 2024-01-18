import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class HSObj {
  static final int MAXSCORES = 1000;
  
  static final int MAXFIELDS = 3;
  
  static final String DONESTRING = "DONE";
  
  static final String STARTSCORES = "START";
  
  boolean DoneViewing;
  
  boolean AtBottom;
  
  String[] Field1 = new String[1000];
  
  String[] Field2 = new String[1000];
  
  String[] Field3 = new String[1000];
  
  String[] Messages = new String[1000];
  
  String[] Fields = new String[3];
  
  boolean[] FieldOn = new boolean[3];
  
  String Delimiter;
  
  String ScriptLocation;
  
  String ScriptName;
  
  int Left;
  
  int Top;
  
  int Width;
  
  int Height;
  
  Color colBackground;
  
  Color colTitle;
  
  Color colScores;
  
  String TitleString;
  
  Font fntTitle;
  
  Font fntScores;
  
  Graphics GraphicsObject;
  
  int StartScoresY;
  
  int ScoreFontHeight;
  
  int ScoreSpacing;
  
  int TitleHeight;
  
  int StartHeaderY;
  
  int ScorePosition;
  
  int Score1YPos;
  
  boolean ShowScreen;
  
  int ScrollFactor = 0;
  
  int RankLeft;
  
  int Field1Left;
  
  int Field2Left;
  
  int Field3Left;
  
  int ScrollUp1;
  
  int ScrollUp2;
  
  int ScrollUp3;
  
  int ScrollUp4;
  
  int ScrollUp5;
  
  int ScrollDown1;
  
  int ScrollDown2;
  
  int ScrollDown3;
  
  int ScrollDown4;
  
  int ScrollDown5;
  
  public HSObj(String paramString1, String paramString2) {
    this.ScriptLocation = paramString1;
    this.ScriptName = paramString2;
    this.DoneViewing = false;
  }
  
  public int AddScore(String paramString) {
    byte b;
    ComboBox comboBox = new ComboBox(this.ScriptLocation, this.ScriptName, paramString, "DONE");
    if (comboBox.GetCText() == false)
      return -1; 
    Vector vector = comboBox.Messages;
    String str = vector.firstElement();
    try {
      b = Integer.parseInt(str, 10);
    } catch (Exception exception) {
      b = -1;
    } 
    return b;
  }
  
  public void GetScoresList(String paramString) {
    for (byte b1 = 0; b1 < 'Ϩ'; b1++) {
      this.Field1[b1] = "--";
      this.Field2[b1] = "--";
      this.Field3[b1] = "--";
    } 
    ComboBox comboBox = new ComboBox(this.ScriptLocation, this.ScriptName, paramString, "DONE");
    if (comboBox.GetCText() == false) {
      this.ShowScreen = false;
      return;
    } 
    this.ShowScreen = true;
    Vector vector = comboBox.Messages;
    int i = vector.size();
    this.Delimiter = vector.firstElement();
    int j = 0;
    byte b2 = 0;
    for (byte b3 = 0; b3 < 3; b3++)
      this.FieldOn[b3] = false; 
    for (int k = 1; k < i; k++) {
      String str = vector.elementAt(k);
      if (str.equals("START")) {
        j = k + 1;
        k = i + 1;
      } else if (b2 < 3) {
        this.FieldOn[b2] = true;
        this.Fields[b2++] = str;
      } 
    } 
    if (j == 0)
      return; 
    for (int m = j; m < i; m++)
      this.Messages[m - j] = vector.elementAt(m); 
    int n = i - j;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    for (byte b4 = 0; b4 < n; b4++) {
      i1 = this.Messages[b4].indexOf(this.Delimiter);
      this.Field1[b4] = this.Messages[b4].substring(0, i1);
      if (i1 > -1) {
        i2 = this.Messages[b4].indexOf(this.Delimiter, i1 + 1);
        this.Field2[b4] = this.Messages[b4].substring(i1 + 1, i2);
      } 
      if (i2 > -1) {
        i3 = this.Messages[b4].indexOf(this.Delimiter, i2 + 1);
        this.Field3[b4] = this.Messages[b4].substring(i2 + 1, i3);
      } 
    } 
  }
  
  public void MouseClicked(MouseEvent paramMouseEvent) {
    this.DoneViewing = true;
  }
  
  public void MouseMoved(MouseEvent paramMouseEvent) {
    int i = paramMouseEvent.getY();
    if (i < this.ScrollUp1) {
      this.ScrollFactor = 50;
    } else if (i < this.ScrollUp2) {
      this.ScrollFactor = 25;
    } else if (i < this.ScrollUp3) {
      this.ScrollFactor = 10;
    } else if (i < this.ScrollUp4) {
      this.ScrollFactor = 5;
    } else if (i < this.ScrollUp5) {
      this.ScrollFactor = 2;
    } else if (i > this.ScrollDown1) {
      this.ScrollFactor = -50;
    } else if (i > this.ScrollDown2) {
      this.ScrollFactor = -25;
    } else if (i > this.ScrollDown3) {
      this.ScrollFactor = -10;
    } else if (i > this.ScrollDown4) {
      this.ScrollFactor = -5;
    } else if (i > this.ScrollDown5) {
      this.ScrollFactor = -2;
    } else {
      this.ScrollFactor = 0;
    } 
  }
  
  public void Paint(Graphics paramGraphics) {
    paramGraphics.setColor(Color.white);
    paramGraphics.drawRect(this.Left - 2, this.Top - 2, this.Width + 4, this.Height + 4);
    paramGraphics.setColor(Color.gray);
    paramGraphics.drawRect(this.Left - 1, this.Top - 1, this.Width + 2, this.Height + 2);
    paramGraphics.setColor(this.colBackground);
    paramGraphics.fillRect(this.Left, this.Top, this.Width + 1, this.Height + 1);
    int i = this.Score1YPos;
    int j = this.ScorePosition;
    while (i < this.Top + this.Height - 8) {
      if ((this.Field1[j] == "--" && this.Field2[j] == "--" && this.Field3[j] == "--") || j == 1000) {
        i = this.Top + this.Height;
        this.AtBottom = true;
        continue;
      } 
      paramGraphics.setColor(this.colScores);
      paramGraphics.setFont(this.fntScores);
      paramGraphics.drawString(Integer.toString(j + 1), this.RankLeft + this.Left, i);
      if (this.FieldOn[0])
        paramGraphics.drawString(this.Field1[j], this.Field1Left + this.Left, i); 
      if (this.FieldOn[1])
        paramGraphics.drawString(this.Field2[j], this.Field2Left + this.Left, i); 
      if (this.FieldOn[2])
        paramGraphics.drawString(this.Field3[j], this.Field3Left + this.Left, i); 
      j++;
      i += this.ScoreSpacing;
      this.AtBottom = false;
    } 
    paramGraphics.setColor(this.colBackground);
    paramGraphics.fillRect(this.Left, this.Top, this.Width + 1, this.StartScoresY - this.Top + 1);
    paramGraphics.setColor(this.colTitle);
    paramGraphics.setFont(this.fntScores);
    paramGraphics.drawString("#", this.RankLeft + this.Left, this.StartHeaderY);
    if (this.FieldOn[0])
      paramGraphics.drawString(this.Fields[0], this.Field1Left + this.Left, this.StartHeaderY); 
    if (this.FieldOn[1])
      paramGraphics.drawString(this.Fields[1], this.Field2Left + this.Left, this.StartHeaderY); 
    if (this.FieldOn[2])
      paramGraphics.drawString(this.Fields[2], this.Field3Left + this.Left, this.StartHeaderY); 
    Utils.CenterString(paramGraphics, this.colTitle, this.TitleString, this.fntTitle, this.Width + this.Left * 2, (int)(this.TitleHeight * 1.2D));
    paramGraphics.setColor(this.colBackground);
    paramGraphics.fillRect(this.Left, this.Top + this.Height - this.ScoreSpacing, this.Width + 1, this.ScoreSpacing + 1);
    FontMetrics fontMetrics = paramGraphics.getFontMetrics(this.fntScores);
    int k = fontMetrics.getHeight();
    Utils.CenterString(paramGraphics, this.colTitle, "Click the mouse anywhere to exit this screen", this.fntScores, this.Width + this.Left * 2, this.Top + this.Height - this.ScoreSpacing + k + 2);
    if (!this.ShowScreen)
      Utils.CenterString(paramGraphics, this.colTitle, "HIGH SCORE SERVER UNAVAILABLE", this.fntTitle, this.Width + this.Left * 2, this.Top + this.Height / 2); 
  }
  
  public void SetupColumns(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.RankLeft = paramInt1;
    this.Field1Left = paramInt2;
    this.Field2Left = paramInt3;
    this.Field3Left = paramInt4;
  }
  
  public void SetupScreen(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Color paramColor1, Color paramColor2, Color paramColor3, String paramString, Font paramFont1, Font paramFont2, Graphics paramGraphics) {
    this.Left = paramInt1;
    this.Top = paramInt2;
    this.Width = paramInt3;
    this.Height = paramInt4;
    this.colBackground = paramColor1;
    this.colScores = paramColor2;
    this.colTitle = paramColor3;
    this.TitleString = paramString;
    this.fntTitle = paramFont1;
    this.fntScores = paramFont2;
    this.GraphicsObject = paramGraphics;
    FontMetrics fontMetrics = this.GraphicsObject.getFontMetrics(this.fntScores);
    this.ScoreFontHeight = fontMetrics.getHeight();
    this.ScoreSpacing = (int)(this.ScoreFontHeight * 1.33D);
    fontMetrics = this.GraphicsObject.getFontMetrics(this.fntTitle);
    this.TitleHeight = fontMetrics.getHeight();
    this.StartScoresY = this.TitleHeight + this.ScoreSpacing * 2;
    this.StartHeaderY = this.TitleHeight + this.ScoreSpacing;
    this.ScorePosition = 0;
    this.Score1YPos = this.StartScoresY + this.ScoreSpacing;
    this.ScrollUp1 = (int)(this.Top + this.Height * 0.05D);
    this.ScrollUp2 = (int)(this.Top + this.Height * 0.1D);
    this.ScrollUp3 = (int)(this.Top + this.Height * 0.15D);
    this.ScrollUp4 = (int)(this.Top + this.Height * 0.2D);
    this.ScrollUp5 = (int)(this.Top + this.Height * 0.3D);
    this.ScrollDown5 = (int)(this.Top + this.Height * 0.7D);
    this.ScrollDown4 = (int)(this.Top + this.Height * 0.8D);
    this.ScrollDown3 = (int)(this.Top + this.Height * 0.85D);
    this.ScrollDown2 = (int)(this.Top + this.Height * 0.9D);
    this.ScrollDown1 = (int)(this.Top + this.Height * 0.95D);
    this.AtBottom = false;
  }
  
  public void Update() {
    this.Score1YPos += this.ScrollFactor;
    if (this.AtBottom && this.ScrollFactor < 0) {
      this.ScrollFactor = 0;
      this.Score1YPos = this.StartScoresY + this.ScoreSpacing;
    } 
    if (this.ScrollFactor > 0 && this.Score1YPos > this.StartScoresY + this.ScoreSpacing && this.ScorePosition == 0) {
      this.ScrollFactor = 0;
      this.Score1YPos = this.StartScoresY + this.ScoreSpacing;
    } 
    while (this.Score1YPos > this.StartScoresY + this.ScoreSpacing) {
      this.Score1YPos -= this.ScoreSpacing;
      this.ScorePosition--;
    } 
    while (this.Score1YPos < this.StartScoresY - this.ScoreSpacing) {
      this.Score1YPos += this.ScoreSpacing;
      this.ScorePosition++;
    } 
    if (this.ScorePosition < 0)
      this.ScorePosition = 0; 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\HSObj.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */