package various

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean):
  def apply(elem:A) = contains(elem)

  def contains(elem:A): Boolean
  def +(elem:A): MySet[A]
  def ++(set:MySet[A]):MySet[A]

  def map[B](f:A => B):MySet[B]
  def flatMap[B](f:A => MySet[B]):MySet[B]
  def filter(predicate: A => Boolean):MySet[A]
  def foreach(f:A => Unit):Unit

object MySet:
  def apply[A](as: A*):MySet[A] =
    @tailrec
    def buildSet(s: Seq[A], acc:MySet[A]): MySet[A] =
      if (s == Seq.empty) acc
      else buildSet(s.tail, acc + s.head)
    buildSet(as, new EmptySet[A]())


class EmptySet[A] extends MySet[A]:
  def contains(elem:A): Boolean = false
  def +(elem:A): MySet[A] = new NonEmptySet(elem, this)
  def ++(set:MySet[A]):MySet[A] = set

  def map[B](f:A => B):MySet[B] = new EmptySet[B]()
  def flatMap[B](f:A => MySet[B]):MySet[B] = new EmptySet[B]()
  def filter(predicate: A => Boolean):MySet[A] = this
  def foreach(f:A => Unit):Unit = ()

class NonEmptySet[A](val head: A, val tail: MySet[A]) extends MySet[A]:
  def contains(elem:A): Boolean = 
    if (elem == head) true
    else tail.contains(elem)

  def +(elem:A): MySet[A] = 
    if (this.contains(elem)) this
    else new NonEmptySet[A](elem, this)

  def ++(set:MySet[A]):MySet[A] = 
    tail ++ set + head

  def map[B](f:A => B):MySet[B] = 
    tail.map(f) + f(head)

  def flatMap[B](f:A => MySet[B]):MySet[B] = 
    tail.flatMap(f) ++ f(head)

  def filter(predicate: A => Boolean):MySet[A] = 
    val filteredTail = tail.filter(predicate)
    if (predicate(head)) filteredTail + head
    else filteredTail


  def foreach(f:A => Unit):Unit = 
    f(head)
    tail.foreach(f)


