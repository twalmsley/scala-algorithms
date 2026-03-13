import Tree.*
class TreeADTTestSuite extends munit.FunSuite:

  test("Can create a tree structure of ints"):
    val tree: Tree[Int] =
      Node(
        3,
        Node(2, Leaf(1), EmptyTree()),
        Node(5, Leaf(4), EmptyTree())
      )
    println(tree)

  test("Can create a tree structure of strings"):
    val tree: Tree[String] =
      Node(
        "3",
        Node("2", Leaf("1"), EmptyTree()),
        Node("5", Leaf("4"), EmptyTree())
      )
    println(tree)

  test("Can insert elements into a Tree"):
    val tree1 = treeInsert(1, EmptyTree())
    assertEquals(tree1, Leaf(1))

    val tree2 = treeInsert(2, tree1)
    assertEquals(tree2, Node(1, EmptyTree(), Leaf(2)))

    val tree3 = treeInsert(0, tree2)
    assertEquals(tree3, Node(1, Leaf(0), Leaf(2)))

    val tree4 = treeInsert(-1, tree3)
    assertEquals(tree4, Node(1, Node(0, Leaf(-1), EmptyTree()), Leaf(2)))

    val tree5 = treeInsert(3, tree4)
    assertEquals(
      tree5,
      Node(1, Node(0, Leaf(-1), EmptyTree()), Node(2, EmptyTree(), Leaf(3)))
    )
