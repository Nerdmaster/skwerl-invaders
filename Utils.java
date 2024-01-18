import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Random;

public class Utils {
  static Random rNumber;
  
  static Applet App;
  
  static Graphics DefGraphics;
  
  static Color DefColor;
  
  static Font DefFont;
  
  static int DefWidth;
  
  static String AN(String paramString) {
    String str = paramString.toUpperCase();
    char c1 = str.charAt(0);
    char c2 = str.charAt(1);
    return (c2 < 'A' || c2 > 'Z') ? ((c1 == 'F' || c1 == 'H' || c1 == 'L' || c1 == 'M' || c1 == 'N' || c1 == 'R' || c1 == 'S' || c1 == 'X' || c1 == 'A' || c1 == 'E' || c1 == 'I' || c1 == 'O') ? ("an " + paramString) : ("a " + paramString)) : ((c1 == 'A' || c1 == 'E' || c1 == 'I' || c1 == 'O' || c1 == 'U') ? ("an " + paramString) : ("a " + paramString));
  }
  
  static void CenterString(Graphics paramGraphics, Color paramColor, String paramString, Font paramFont, int paramInt1, int paramInt2) {
    paramGraphics.setFont(paramFont);
    FontMetrics fontMetrics = paramGraphics.getFontMetrics(paramFont);
    int i = fontMetrics.stringWidth(paramString);
    paramGraphics.setColor(paramColor);
    paramGraphics.drawString(paramString, (paramInt1 - i) / 2, paramInt2);
  }
  
  static void CenterString(String paramString, int paramInt) {
    DefGraphics.setFont(DefFont);
    FontMetrics fontMetrics = DefGraphics.getFontMetrics(DefFont);
    int i = fontMetrics.stringWidth(paramString);
    DefGraphics.setColor(DefColor);
    DefGraphics.drawString(paramString, (DefWidth - i) / 2, paramInt);
  }
  
  static void ClearImage(Graphics paramGraphics, Color paramColor, int paramInt1, int paramInt2) {
    paramGraphics.setColor(paramColor);
    paramGraphics.fillRect(0, 0, paramInt1, paramInt2);
  }
  
  static boolean FilterWords(String paramString, String[] paramArrayOfString) {
    int i = paramArrayOfString.length;
    String str = paramString.toUpperCase();
    for (byte b = 0; b < i; b++) {
      byte b1 = 0;
      for (int j = 0; j < str.length(); j++) {
        char c = str.charAt(j);
        if (c >= 'A' && c <= 'Z')
          if (c == paramArrayOfString[b].charAt(b1)) {
            if (++b1 == paramArrayOfString[b].length())
              return true; 
          } else {
            b1 = 0;
            j = str.length();
          }  
      } 
    } 
    return false;
  }
  
  static Applet GetApp() {
    return App;
  }
  
  static long GetRandom(long paramLong1, long paramLong2) {
    long l = paramLong2 - paramLong1 + 1L;
    double d = rNumber.nextDouble();
    d *= l;
    d += paramLong1;
    return (long)d;
  }
  
  static void Init(Applet paramApplet) {
    App = paramApplet;
    rNumber = new Random();
  }
  
  static void SetDefCS(Graphics paramGraphics, Color paramColor, Font paramFont, int paramInt) {
    DefGraphics = paramGraphics;
    DefColor = paramColor;
    DefFont = paramFont;
    DefWidth = paramInt;
    paramGraphics.setFont(paramFont);
    paramGraphics.setColor(paramColor);
  }
  
  static void ShowString(String paramString, Graphics paramGraphics, Font paramFont, int paramInt1, int paramInt2) {
    CenterString(paramGraphics, Color.white, paramString, paramFont, paramInt1, paramInt2);
    Thread.currentThread();
    Thread.yield();
    App.repaint();
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Utils.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */