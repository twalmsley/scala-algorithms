enum Tree[A]:
  case Node(a: A, left: Option[Tree[A]], right: Option[Tree[A]])
  case Leaf(a: A)

