public class Upgrade {
  static final int UG_POWER = 0;
  
  static final int UG_SPEED = 1;
  
  static final int UG_DAMAGE = 2;
  
  static final int UG_HEAL = 3;
  
  static final int NUM_UPGRADES = 4;
  
  String Name;
  
  int Cost;
  
  int Type;
  
  double Multiplier;
  
  int MaxUpgradeLevel;
  
  public static Upgrade[] UpgradeArray = new Upgrade[4];
  
  public Upgrade(String paramString, int paramInt1, int paramInt2, double paramDouble, int paramInt3) {
    this.Name = paramString;
    this.Cost = paramInt1;
    this.Type = paramInt2;
    this.Multiplier = paramDouble;
    this.MaxUpgradeLevel = paramInt3;
  }
  
  public static void CreateUpgradeList() {
    UpgradeArray[0] = new Upgrade("Hull Shielding", 500, 0, 1.5D, 20);
    UpgradeArray[1] = new Upgrade("Speed Boosters", 1500, 1, 2.4D, 5);
    UpgradeArray[2] = new Upgrade("Hull Armor", 2000, 2, 1.4D, 18);
    UpgradeArray[3] = new Upgrade("Repair Hull", 10, 3, 1.0D, 99);
  }
  
  public static void DoUpgrade(int paramInt, Player paramPlayer) {
    paramPlayer.Cash -= GetCost(paramInt, paramPlayer);
    paramPlayer.UpgradeLevel[paramInt] = paramPlayer.UpgradeLevel[paramInt] + 1;
    switch ((UpgradeArray[paramInt]).Type) {
      case 0:
        paramPlayer.MaxPower += 10;
        break;
      case 1:
        paramPlayer.SpeedMod++;
        break;
      case 2:
        paramPlayer.DamageMod += 5;
        break;
      case 3:
        paramPlayer.Power = paramPlayer.MaxPower;
        paramPlayer.UpgradeLevel[paramInt] = 0;
        break;
    } 
  }
  
  public static int GetCost(int paramInt, Player paramPlayer) {
    int i = (int)((UpgradeArray[paramInt]).Cost * Math.pow((UpgradeArray[paramInt]).Multiplier, paramPlayer.UpgradeLevel[paramInt]));
    if (paramInt == 3)
      i = (UpgradeArray[paramInt]).Cost * (paramPlayer.MaxPower - paramPlayer.Power); 
    return i;
  }
  
  public String GetDescription() {
    String str = null;
    switch (this.Type) {
      case 0:
        str = "Hull Strength";
        break;
      case 1:
        str = "Speed";
        break;
      case 2:
        str = "Damage Absorption";
        break;
      case 3:
        return "Repairs all hull damage";
    } 
    return "Modifies " + str + " to a maximum of " + this.MaxUpgradeLevel + " levels.";
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Upgrade.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */