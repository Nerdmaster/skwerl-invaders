import java.applet.Applet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

class ComboBox {
  String AnsiString;
  
  String Filename;
  
  String Interface;
  
  String EndString;
  
  public Vector Messages;
  
  Applet App;
  
  URL RelativePath;
  
  String URLHost;
  
  String FullURL;
  
  URL CGIAddress;
  
  public ComboBox(String paramString1, String paramString2) {
    this("cgi-bin/", paramString1, paramString2, "");
  }
  
  public ComboBox(String paramString1, String paramString2, String paramString3, String paramString4) {
    this.AnsiString = paramString1;
    this.Filename = paramString2;
    this.Interface = paramString3;
    this.EndString = paramString4;
    this.App = Utils.GetApp();
    this.Messages = new Vector();
    this.RelativePath = this.App.getDocumentBase();
    this.URLHost = this.RelativePath.getHost();
    this.FullURL = "http://" + this.URLHost + "/" + this.AnsiString + this.Filename;
    try {
      this.CGIAddress = new URL(this.FullURL);
    } catch (Exception exception) {
      System.out.println("Bad URL.");
      System.exit(1);
    } 
  }
  
  public boolean GetCText() {
    boolean bool = false;
    try {
      URLConnection uRLConnection = this.CGIAddress.openConnection();
      uRLConnection.setDoOutput(true);
      uRLConnection.setUseCaches(false);
      PrintWriter printWriter = new PrintWriter(uRLConnection.getOutputStream());
      printWriter.println(this.Interface);
      printWriter.close();
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRLConnection.getInputStream()));
      String str;
      while ((str = bufferedReader.readLine()) != null && bool == false) {
        if (str.equalsIgnoreCase(this.EndString)) {
          bool = true;
          continue;
        } 
        this.Messages.addElement(str);
      } 
      bufferedReader.close();
    } catch (Exception exception) {
      System.out.println("Exception in javacount.class: " + exception.getMessage());
    } 
    if (!bool)
      System.out.println("Didn't get 'finished' string."); 
    return bool;
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\ComboBox.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */