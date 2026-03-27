package assembly

import org.scalatest.funsuite.AnyFunSuite
import java.time.ZonedDateTime
import java.time.Duration
import java.util.UUID

class AssembliesTestSuite extends AnyFunSuite:

  test("test"):

    type TighteningTool = AllenKey & HeadHex2mm & SpatioTemporalExtent

    def tighten[A <: TighteningTool, B <: M3SteelBolt10mmHexHead](
        tool: A,
        bolt: B
    ): (TightenActivity, Set[Participation]) = {
      val beginning = ZonedDateTime.now()
      Thread.sleep(Duration.ofMillis(100))
      val ending = ZonedDateTime.now()

      val activity =
        TightenActivity(beginning, ending, UUID.randomUUID(), "tighten")
      val participants = Set(
        Participation(
          beginning,
          ending,
          UUID.randomUUID(),
          "StateOfTighteningTool",
          activity,
          tool,
          Role("TighteningTool")
        ),
        Participation(
          beginning,
          ending,
          UUID.randomUUID(),
          "StateOfM3SteelBolt",
          activity,
          bolt,
          Role("TightenedBolt")
        )
      )
      (activity, participants)
    }

    val bolt1: M3SteelBolt10mmHexHead = new M3SteelBolt10mmHexHead {
      val id = UUID.randomUUID()
    }
    val allenKey2mm = new AllenKeyHex2mm {
      val id = UUID.randomUUID()
    }
    val driverBit = new DriverBitHex2mm {
      val id = UUID.randomUUID()
    }

    val result = tighten(allenKey2mm, bolt1)
    println("--------------------------------")
    println(result)
    println("--------------------------------")

    val x: HeadHex2mm = bolt1

    assert(x == bolt1)
