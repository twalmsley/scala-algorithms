package assembly

import org.scalatest.funsuite.AnyFunSuite

class AssembliesTestSuite extends AnyFunSuite:

  test("test"):

    type Tool = AllenKey & HeadHex2mm

    def tightenUsingTool[A <: Tool, B <: M3SteelBolt10mmHexHead](
        key: A,
        bolt: B
    ): Unit = {}

    val bolt1: M3SteelBolt10mmHexHead = new M3SteelBolt10mmHexHead {}
    val allenKey2mm = new AllenKeyHex2mm {}
    val driverBit = new DriverBitHex2mm {}

    tightenUsingTool(allenKey2mm, bolt1)
    tightenUsingTool(driverBit, bolt1)

    val x: HeadHex2mm = bolt1
    val y: MaterialSteel = bolt1
    val z: ThreadSizeM3 = bolt1

    assert(x == bolt1)
