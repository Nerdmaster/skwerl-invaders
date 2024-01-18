public class Weapon {
  static final int FIRE_NORMAL = 0;
  
  static final int FIRE_DOUBLE = 1;
  
  static final int FIRE_W = 2;
  
  static final int FIRE_LASER = 3;
  
  static final int FIRE_FLAME = 4;
  
  static final int FIRE_SHIELD = 5;
  
  static final int FIRE_EXPLODE = 6;
  
  static final int FIRE_MIRV = 7;
  
  static final int FIRE_ICE = 8;
  
  static final int FIRE_DRAIN = 9;
  
  static final int NUMWEAPONS = 45;
  
  String Name;
  
  int Cost;
  
  int Damage;
  
  int Uses;
  
  int FireTime;
  
  int Special;
  
  public static Weapon[] WeaponArray = new Weapon[45];
  
  public Weapon(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.Name = paramString;
    this.Cost = paramInt1;
    this.Damage = paramInt2;
    this.Uses = paramInt3;
    this.FireTime = paramInt4;
    this.Special = paramInt5;
  }
  
  public static void BuyWeapon(Player paramPlayer, int paramInt1, int paramInt2) {
    if (paramPlayer.Cash >= (WeaponArray[paramInt1]).Cost) {
      paramPlayer.Cash += 50;
      paramPlayer.Cash /= 1;
      paramPlayer.Cash -= (WeaponArray[paramInt1]).Cost;
      paramPlayer.Cash -= (WeaponArray[paramInt2]).Cost;
      if ((WeaponArray[paramInt1]).Uses == -1) {
        paramPlayer.WeaponUses[paramInt1] = -1;
      } else {
        paramPlayer.WeaponUses[paramInt1] = (WeaponArray[paramInt1]).Uses;
      } 
    } else {
      paramPlayer.Cash += 1500;
      paramPlayer.Cash -= (WeaponArray[0]).Cost;
      paramPlayer.Cash -= 500;
      paramPlayer.Cash -= 20000;
    } 
  }
  
  public static void CreateWeapons() {
    byte b = 0;
    WeaponArray[b++] = new Weapon("Laser", 0, 1, -1, 15, 0);
    WeaponArray[b++] = new Weapon("Laser II", 125, 2, -1, 20, 0);
    WeaponArray[b++] = new Weapon("Laser III", 625, 5, -1, 25, 0);
    WeaponArray[b++] = new Weapon("Laser IV", 1600, 8, -1, 25, 0);
    WeaponArray[b++] = new Weapon("Laser V", 2080, 10, -1, 30, 0);
    WeaponArray[b++] = new Weapon("Laser VI", 3125, 10, -1, 20, 0);
    WeaponArray[b++] = new Weapon("Blaster", 250, 1, -1, 10, 1);
    WeaponArray[b++] = new Weapon("Blaster mk. 2", 650, 2, -1, 15, 1);
    WeaponArray[b++] = new Weapon("Blaster mk. 3", 1125, 3, -1, 20, 1);
    WeaponArray[b++] = new Weapon("Blaster mk. 4", 2500, 5, -1, 25, 1);
    WeaponArray[b++] = new Weapon("Blaster mk. 5", 8000, 8, -1, 20, 1);
    WeaponArray[b++] = new Weapon("L-III Laser", 375, 1, -1, 15, 2);
    WeaponArray[b++] = new Weapon("L-III Blaster", 1125, 2, -1, 20, 2);
    WeaponArray[b++] = new Weapon("L-III Phaser", 2025, 3, -1, 25, 2);
    WeaponArray[b++] = new Weapon("L-III Cannon", 3600, 4, -1, 25, 2);
    WeaponArray[b++] = new Weapon("L-III Death ray", 10125, 6, -1, 20, 2);
    WeaponArray[b++] = new Weapon("Annihilator", 360, 6, -1, 50, 3);
    WeaponArray[b++] = new Weapon("Devastator", 2050, 12, -1, 35, 3);
    WeaponArray[b++] = new Weapon("Decimator", 11520, 24, -1, 25, 3);
    WeaponArray[b++] = new Weapon("Freeze Ray", 4000, 30, -1, 10, 8);
    WeaponArray[b++] = new Weapon("Ice Beam", 8000, 60, -1, 10, 8);
    WeaponArray[b++] = new Weapon("Subzero Cannon", 12000, 90, -1, 10, 8);
    WeaponArray[b++] = new Weapon("L-V Laser", 780, 1, -1, 20, 7);
    WeaponArray[b++] = new Weapon("L-V Blaster", 2500, 2, -1, 25, 7);
    WeaponArray[b++] = new Weapon("L-V Phaser", 4680, 3, -1, 30, 7);
    WeaponArray[b++] = new Weapon("L-V Cannon", 10000, 4, -1, 25, 7);
    WeaponArray[b++] = new Weapon("L-V Death Ray", 15625, 5, -1, 25, 7);
    WeaponArray[b++] = new Weapon("Flamethrower", 1250, 1, 20, 40, 4);
    WeaponArray[b++] = new Weapon("Plasma Coil", 5000, 2, 20, 40, 4);
    WeaponArray[b++] = new Weapon("Disintegrator", 15000, 3, 20, 30, 4);
    WeaponArray[b++] = new Weapon("Hellfire", 20000, 2, -1, 50, 4);
    WeaponArray[b++] = new Weapon("Rocket", 900, 6, 10, 25, 6);
    WeaponArray[b++] = new Weapon("Missile", 3750, 10, 15, 25, 6);
    WeaponArray[b++] = new Weapon("Torpedo", 12800, 16, 20, 25, 6);
    WeaponArray[b++] = new Weapon("Photon Cannon", 40000, 16, -1, 40, 6);
    WeaponArray[b++] = new Weapon("Energy Shield", 500, 10, 2, 100, 5);
    WeaponArray[b++] = new Weapon("Barrier Shield", 2500, 20, 5, 100, 5);
    WeaponArray[b++] = new Weapon("Power Shield", 12500, 50, 10, 100, 5);
    WeaponArray[b++] = new Weapon("Protector", 50000, 100, 20, 100, 5);
    WeaponArray[b++] = new Weapon("Laser VII", 28125, 15, -1, 5, 0);
    WeaponArray[b++] = new Weapon("Supreme Cannon", 80000, 8, -1, 5, 6);
    WeaponArray[b++] = new Weapon("Blaster se", 12500, 5, -1, 5, 1);
    WeaponArray[b++] = new Weapon("L-III Gold", 28125, 5, -1, 5, 2);
    WeaponArray[b++] = new Weapon("L-V xl", 78125, 5, -1, 5, 7);
    WeaponArray[b++] = new Weapon("Mammoth Laser", 57600, 64, -1, 20, 3);
  }
  
  public static Weapon[] GetAllWeapons() {
    return WeaponArray;
  }
  
  public static int GetArraySize() {
    return 45;
  }
  
  public static String GetDamageType(int paramInt) {
    String str = "NULL";
    switch (paramInt) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 6:
      case 7:
        str = "Damage.";
        break;
      case 4:
        str = "Burn rate.";
        break;
      case 5:
        str = "Hit points.";
        break;
      case 8:
        str = "Ticks frozen.";
        break;
      case 9:
        str = "Hits drained.";
        break;
    } 
    return str;
  }
  
  public static Weapon[] GetList(int paramInt1, int paramInt2) {
    Weapon[] arrayOfWeapon = new Weapon[paramInt2 - paramInt1 + 1];
    for (int i = paramInt1; i <= paramInt2; i++)
      arrayOfWeapon[i - paramInt1] = WeaponArray[i]; 
    return arrayOfWeapon;
  }
  
  public static Weapon GetWeapon(int paramInt) {
    return WeaponArray[paramInt];
  }
  
  public static String GetWeaponType(int paramInt) {
    String str = "NULL";
    switch (paramInt) {
      case 0:
        str = "Normal";
        break;
      case 1:
        str = "Double Fire";
        break;
      case 2:
        str = "W Beam";
        break;
      case 3:
        str = "Laser";
        break;
      case 4:
        str = "Flamethrower";
        break;
      case 5:
        str = "Defensive Shield";
        break;
      case 6:
        str = "Exploding Rocket";
        break;
      case 7:
        str = "5-way Laser";
        break;
      case 8:
        str = "Ice Beam";
        break;
      case 9:
        str = "Energy Leech";
        break;
    } 
    return str;
  }
  
  public static void SellWeapon(Player paramPlayer, int paramInt1, int paramInt2) {
    if (paramPlayer.WeaponUses[paramInt1] == -1)
      paramPlayer.Cash += (WeaponArray[0]).Cost; 
    if (paramPlayer.WeaponUses[paramInt2] != -2) {
      paramPlayer.Cash -= (WeaponArray[0]).Cost;
      paramPlayer.WeaponUses[paramInt2] = -2;
    } else {
      paramPlayer.Cash -= (WeaponArray[(WeaponArray[0]).Cost]).Damage * (WeaponArray[(WeaponArray[0]).FireTime]).Cost * 2000;
    } 
  }
}


/* Location:              C:\Users\brody\Downloads\invader.jar!\Weapon.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */