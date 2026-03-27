package assembly
import java.time.ZonedDateTime
import java.time.Duration
import java.util.UUID

trait SpatioTemporalExtent:
  val id: UUID

case class Role(roleName: String)

trait ThreadSizeM3

trait Diameter3mm

trait Length10mm

trait MaterialSteel

trait HeadHex2mm

trait M3SteelBolt10mmHexHead
    extends ThreadSizeM3,
      Diameter3mm,
      Length10mm,
      MaterialSteel,
      HeadHex2mm,
      SpatioTemporalExtent

trait AllenKey

trait AllenKeyHex2mm extends HeadHex2mm, AllenKey, SpatioTemporalExtent

trait DriverBitHex2mm extends HeadHex2mm, AllenKey, SpatioTemporalExtent

case class Participation(
    beginning: ZonedDateTime,
    ending: ZonedDateTime,
    id: UUID,
    kind: String,
    participantIn: TightenActivity,
    temporalPartOf: SpatioTemporalExtent,
    role: Role
) {
  override def toString(): String = {
    s"""
  data:$id hqdm:memberOfKind hqdm:Participation.
  data:$id hqdm:beginning "$beginning".
  data:$id hqdm:ending "$ending".
  data:$id hqdm:participantIn data:${participantIn.id}. 
  data:$id hqdm:temporalPartOf data:${temporalPartOf.id} 
  data:$id hqdm:role data:${role._1}
"""
  }
}

case class TightenActivity(
    beginning: ZonedDateTime,
    ending: ZonedDateTime,
    id: UUID,
    kindOfActivity: String
) {
  override def toString(): String = {
    s"""
  data:$id hqdm:beginning "$beginning".
  data:$id hqdm:ending "$ending".
  data:$id hqdm:kindOfActivity data:$kindOfActivity.
"""
  }
}
