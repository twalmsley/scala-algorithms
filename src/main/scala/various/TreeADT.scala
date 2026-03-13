enum Tree[A]:
  case EmptyTree()
  case Node(a: A, left: Tree[A], right: Tree[A])

import Tree.*

def treeInsert[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Tree[A] =

  t match
    case EmptyTree() => Node(x, EmptyTree(), EmptyTree())
    case Node(y, left, right) if ord.compare(x, y) == 0  => t
    case Node(y, left, right) if ord.compare(x, y) == -1 =>
      Node(y, treeInsert(x, left), right)
    case Node(y, left, right) if ord.compare(x, y) == 1 =>
      Node(y, left, treeInsert(x, right))
    case _ =>
      println(s"Impossible tree in treeInsert(): $t")
      t

def treeContains[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Boolean =

  t match
    case EmptyTree()                                     => false
    case Node(y, left, right) if ord.compare(x, y) == 0  => true
    case Node(y, left, right) if ord.compare(x, y) == -1 =>
      treeContains(x, left)
    case Node(y, left, right) if ord.compare(x, y) == 1 =>
      treeContains(x, right)
    case _ => false

def treeFind[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Option[A] =

  t match
    case EmptyTree()                                     => None
    case Node(y, left, right) if ord.compare(x, y) == 0  => Some(y)
    case Node(y, left, right) if ord.compare(x, y) == -1 =>
      treeFind(x, left)
    case Node(y, left, right) if ord.compare(x, y) == 1 =>
      treeFind(x, right)
    case _ => None
