package digraph

import org.scalatest.funsuite.AnyFunSuite

class DigraphTestSuite extends AnyFunSuite:

  test("Can create minimal digraphs"):
    val v1: Vertex[String] = Vertex("v1", "Some Value 1")
    val v2: Vertex[String] = Vertex("v2", "Some Value 2")
    val e1: Edge[String] = Edge("e1", v1, v2)
    val dg = Digraph(Set(v1, v2), Set(e1), Set(), Set(), Set())

    assert(e1.source == v1)
    assertResult(e1.target, v2)

  test("Can create a more complex digraph"):
    val v1: Vertex[Int] = Vertex("v1", 1)
    val v2: Vertex[Int] = Vertex("v2", 2)
    val v3: Vertex[Int] = Vertex("v3", 3)
    val v4: Vertex[Int] = Vertex("v4", 4)
    val v5: Vertex[Int] = Vertex("v5", 5)
    val v6: Vertex[Int] = Vertex("v6", 6)
    val e1: Edge[Int] = Edge("e1", v1, v2)
    val e2: Edge[Int] = Edge("e2", v2, v3)
    val e3: Edge[Int] = Edge("e3", v3, v4)
    val e4: Edge[Int] = Edge("e4", v1, v3)
    val e5: Edge[Int] = Edge("e5", v3, v5)
    val e6: Edge[Int] = Edge("e6", v4, v6)
    val re1: ReifiedEdge[Int] =
      ReifiedEdge("re1", e1, v1)
    val me1: MetaEdge[Int] = MetaEdge("me1", e1, e2)
    val rme1: ReifiedMetaEdge[Int] = ReifiedMetaEdge("rme1", me1, v2)

    // Create directly
    val dg1 =
      Digraph(
        Set(v1, v2, v3, v4, v5, v6),
        Set(e1, e2, e3, e4, e5, e6),
        Set(re1),
        Set(rme1),
        Set(me1)
      )

    // Create inline
    val dg2: Digraph[Int] = Digraph()
    val dg3 = dg2
      .add(v1)
      .add(v2)
      .add(v3)
      .add(v4)
      .add(v5)
      .add(v6)
      .add(e1)
      .add(e2)
      .add(e3)
      .add(e4)
      .add(e5)
      .add(e6)
      .add(re1)
      .add(me1)
      .add(rme1)

    assertResult(dg1, dg3)
