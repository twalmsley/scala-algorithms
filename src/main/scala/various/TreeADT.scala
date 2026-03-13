enum Tree[A]:
  case EmptyTree()
  case Node(a: A, left: Tree[A], right: Tree[A])
  case Leaf(a: A)

import Tree.*

def treeInsert[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Tree[A] =

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

def treeContains[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Boolean =

  t match
    case EmptyTree() => false
    case Leaf(y)     => ord.compare(x, y) == 0
    case Node(y, left, right) if ord.compare(x, y) == 0  => true
    case Node(y, left, right) if ord.compare(x, y) == -1 =>
      treeContains(x, left)
    case Node(y, left, right) if ord.compare(x, y) == 1 =>
      treeContains(x, right)
    case _ => false

def treeFind[A](x: A, t: Tree[A])(implicit ord: Ordering[A]): Option[A] =

  t match
    case EmptyTree()                                     => None
    case Leaf(y) if ord.compare(x, y) == 0               => Some(y)
    case Node(y, left, right) if ord.compare(x, y) == 0  => Some(y)
    case Node(y, left, right) if ord.compare(x, y) == -1 =>
      treeFind(x, left)
    case Node(y, left, right) if ord.compare(x, y) == 1 =>
      treeFind(x, right)
    case _ => None

