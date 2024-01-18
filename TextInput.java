import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class TextInput {
  private String Prompt;
  
  private Color PromptColor;
  
  private Color TextColor;
  
  private Font PromptFont;
  
  private Font TextFont;
  
  private int MaxChars;
  
  private int xpos;
  
  private int ypos;
  
  private int FocusKey;
  
  private StringBuffer Input;
  
  private String InputString;
  
  private int StringPos;
  
  private boolean enabled;
  
  private boolean active;
  
  FontMetrics fm;
  
  private int PromptWidth;
  
  static int frames = 0;
  
  public TextInput(String paramString, Color paramColor1, Color paramColor2, Font paramFont1, Font paramFont2, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.Prompt = paramString;
    this.PromptColor = paramColor1;
    this.TextColor = paramColor2;
    this.PromptFont = paramFont1;
    this.TextFont = paramFont2;
    this.MaxChars = paramInt1;
    this.xpos = paramInt2;
    this.ypos = paramInt3;
    this.FocusKey = paramInt4;
    this.Input = new StringBuffer("");
    this.StringPos = 0;
    this.active = false;
    this.enabled = false;
  }
  
  public void Activate() {
    this.active = true;
  }
  
  public boolean Active() {
    return this.active;
  }
  
  public void Deactivate() {
    this.active = false;
  }
  
  public void Disable() {
    this.enabled = false;
  }
  
  public void Enable() {
    this.enabled = true;
  }
  
  public boolean Enabled() {
    return this.enabled;
  }
  
  public int GetFocusKey() {
    return this.FocusKey;
  }
  
  public String GetInput() {
    return this.InputString;
  }
  
  public void KeyPressed(KeyEvent paramKeyEvent) {
    int i = paramKeyEvent.getKeyCode();
    char c = paramKeyEvent.getKeyChar();
    if (this.enabled && this.active) {
      if (i == this.FocusKey) {
        this.active = false;
      } else {
        if (i == 8 && this.StringPos > 0) {
          this.Input.setLength(this.StringPos - 1);
        } else if (((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ' || c == '_') && this.StringPos < this.MaxChars) {
          this.Input.append(c);
        } 
        this.StringPos = this.Input.length();
      } 
    } else if (this.enabled && i == this.FocusKey) {
      this.active = true;
    } 
    this.InputString = new String(this.Input);
  }
  
  public void Paint(Graphics paramGraphics) {
    if (this.enabled) {
      int i;
      byte b;
      this.fm = paramGraphics.getFontMetrics(this.PromptFont);
      this.PromptWidth = this.fm.stringWidth(this.Prompt);
      if (this.InputString != null) {
        this.fm = paramGraphics.getFontMetrics(this.TextFont);
        i = this.PromptWidth + this.fm.stringWidth(this.InputString);
      } else {
        i = this.PromptWidth;
      } 
      int j = (this.xpos - i) / 2;
      if (this.active) {
        b = 15;
      } else {
        b = 5;
      } 
      paramGraphics.setColor(Color.white);
      paramGraphics.fillRect(j - 2, this.ypos - 16, i + b, 20);
      paramGraphics.setColor(Color.blue);
      paramGraphics.drawRect(j - 3, this.ypos - 17, i + b + 1, 21);
      paramGraphics.setFont(this.PromptFont);
      paramGraphics.setColor(this.PromptColor);
      paramGraphics.drawString(this.Prompt, j, this.ypos);
      if (this.InputString != null) {
        paramGraphics.setFont(this.TextFont);
        paramGraphics.setColor(this.TextColor);
        paramGraphics.drawString(this.InputString, j + this.PromptWidth, this.ypos);
      } 
      if (this.active && frames++ >= 20) {
        paramGraphics.setColor(this.PromptColor);
        paramGraphics.drawLine(j + i + 2, this.ypos, j + i + 10, this.ypos);
      } 
      if (frames >= 40)
        frames = 0; 
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\TextInput.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */