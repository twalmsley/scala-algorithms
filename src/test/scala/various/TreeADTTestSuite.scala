import Tree.*
import org.scalatest.funsuite.AnyFunSuite

class TreeADTTestSuite extends AnyFunSuite:

  test("Can create a tree structure of ints"):
    val tree: Tree[Int] =
      Node(
        3,
        Node(2, Node(1, EmptyTree(), EmptyTree()), EmptyTree()),
        Node(5, Node(4, EmptyTree(), EmptyTree()), EmptyTree())
      )
    println(tree)

  test("Can create a tree structure of strings"):
    val tree: Tree[String] =
      Node(
        "3",
        Node("2", Node("1", EmptyTree(), EmptyTree()), EmptyTree()),
        Node("5", Node("4", EmptyTree(), EmptyTree()), EmptyTree())
      )
    println(tree)

  test("Can insert elements into a Tree and find them"):
    val tree1 = treeInsert(1, EmptyTree())
    assert(tree1 == Node(1, EmptyTree(), EmptyTree()))

    val tree2 = treeInsert(2, tree1)
    assert(tree2 == Node(1, EmptyTree(), Node(2, EmptyTree(), EmptyTree())))

    val tree3 = treeInsert(0, tree2)
    assert(
      tree3 ==
        Node(
          1,
          Node(0, EmptyTree(), EmptyTree()),
          Node(2, EmptyTree(), EmptyTree())
        )
    )

    val tree4 = treeInsert(-1, tree3)
    assert(
      tree4 ==
        Node(
          1,
          Node(0, Node(-1, EmptyTree(), EmptyTree()), EmptyTree()),
          Node(2, EmptyTree(), EmptyTree())
        )
    )

    val tree5 = treeInsert(3, tree4)
    assert(
      tree5 ==
        Node(
          1,
          Node(0, Node(-1, EmptyTree(), EmptyTree()), EmptyTree()),
          Node(2, EmptyTree(), Node(3, EmptyTree(), EmptyTree()))
        )
    )

    assert(treeContains(-1, tree5))
    assert(treeContains(0, tree5))
    assert(treeContains(1, tree5))
    assert(treeContains(2, tree5))
    assert(treeContains(3, tree5))
    assert(treeContains(4, tree5) == false)

    assertResult(treeFind(-1, tree5), Some(-1))
    assertResult(treeFind(0, tree5), Some(0))
    assertResult(treeFind(1, tree5), Some(1))
    assertResult(treeFind(2, tree5), Some(2))
    assertResult(treeFind(3, tree5), Some(3))
    assertResult(treeFind(4, tree5), None)

    assertResult(treeMin(tree5), Some(-1))
    assertResult(treeMax(tree5), Some(3))

    assertResult(inOrder(tree5), List(-1, 0, 1, 2, 3))
    assertResult(size(tree5), 5)
    assertResult(size(EmptyTree()), 0)

    assertResult(ithEntry(-1, tree5), None)
    assertResult(ithEntry(0, tree5), Some(-1))
    assertResult(ithEntry(1, tree5), Some(0))
    assertResult(ithEntry(2, tree5), Some(1))
    assertResult(ithEntry(3, tree5), Some(2))
    assertResult(ithEntry(4, tree5), Some(3))
    assertResult(ithEntry(5, tree5), None)
