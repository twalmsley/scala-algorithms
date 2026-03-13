enum Tree[A]:
  case EmptyTree()
  case Node(a: A, left: Tree[A], right: Tree[A])
  case Leaf(a: A)

def treeInsert[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Tree[A] =
  import Tree.*

  t match
    case EmptyTree() => Leaf(x)
    case Leaf(y)     =>
      val compare = ord.compare(x, y)
      if compare == -1 then Node(y, Leaf(x), EmptyTree())
      else if compare == 1 then Node(y, EmptyTree(), Leaf(x))
      else t
    case Node(x, EmptyTree(), EmptyTree())               => t
    case Node(y, left, right) if ord.compare(x, y) == -1 =>
      Node(y, treeInsert(x, left), right)
    case Node(y, left, right) if ord.compare(x, y) == 1 =>
      Node(y, left, treeInsert(x, right))
    case _ =>
      println(s"Impossible tree in treeInsert(): $t")
      t
