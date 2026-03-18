import Tree.*
class TreeADTTestSuite extends munit.FunSuite:

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
    assertEquals(tree1, Node(1, EmptyTree(), EmptyTree()))

    val tree2 = treeInsert(2, tree1)
    assertEquals(tree2, Node(1, EmptyTree(), Node(2, EmptyTree(), EmptyTree())))

    val tree3 = treeInsert(0, tree2)
    assertEquals(
      tree3,
      Node(
        1,
        Node(0, EmptyTree(), EmptyTree()),
        Node(2, EmptyTree(), EmptyTree())
      )
    )

    val tree4 = treeInsert(-1, tree3)
    assertEquals(
      tree4,
      Node(
        1,
        Node(0, Node(-1, EmptyTree(), EmptyTree()), EmptyTree()),
        Node(2, EmptyTree(), EmptyTree())
      )
    )

    val tree5 = treeInsert(3, tree4)
    assertEquals(
      tree5,
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

    assertEquals(treeFind(-1, tree5), Some(-1))
    assertEquals(treeFind(0, tree5), Some(0))
    assertEquals(treeFind(1, tree5), Some(1))
    assertEquals(treeFind(2, tree5), Some(2))
    assertEquals(treeFind(3, tree5), Some(3))
    assertEquals(treeFind(4, tree5), None)

    assertEquals(treeMin(tree5), Some(-1))
    assertEquals(treeMax(tree5), Some(3))

    assertEquals(inOrder(tree5), List(-1, 0, 1, 2, 3))

