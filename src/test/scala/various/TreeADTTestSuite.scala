class TreeADTTestSuite extends munit.FunSuite:

  test("Can create a tree structure"):
    import Tree.*
    val tree: Tree[Int] =
      Node(
        1,
        Some(Node(2, Some(Leaf(4)), None)),
        Some(Node(3, Some(Leaf(5)), None))
      )
    println(tree)
