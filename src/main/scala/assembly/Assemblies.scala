package assembly

trait ThreadSizeM3

trait Diameter3mm

trait Length10mm

trait MaterialSteel

trait HeadHex2mm

trait M3SteelBolt10mmHexHead extends ThreadSizeM3, Diameter3mm, Length10mm, MaterialSteel, HeadHex2mm

trait AllenKey

trait AllenKeyHex2mm extends HeadHex2mm, AllenKey

trait DriverBitHex2mm extends HeadHex2mm, AllenKey
