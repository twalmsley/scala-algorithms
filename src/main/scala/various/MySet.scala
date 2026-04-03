package various

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean):
  def apply(elem: A) = contains(elem)

  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(set: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(predicate: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit

  def -(elem: A): MySet[A]
  def &(other: MySet[A]): MySet[A]
  def --(other: MySet[A]): MySet[A]

object MySet:
  def apply[A](as: A*): MySet[A] =
    @tailrec
    def buildSet(s: Seq[A], acc: MySet[A]): MySet[A] =
      if (s == Seq.empty) acc
      else buildSet(s.tail, acc + s.head)
    buildSet(as, new EmptySet[A]())

class EmptySet[A] extends MySet[A]:

  override def contains(elem: A): Boolean = false
  override def +(elem: A): MySet[A] = new NonEmptySet(elem, this)
  override def ++(set: MySet[A]): MySet[A] = set

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]()
  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]()
  override def filter(predicate: A => Boolean): MySet[A] = this
  override def foreach(f: A => Unit): Unit = ()

  override def -(elem: A): MySet[A] = this
  override def &(other: MySet[A]): MySet[A] = this
  override def --(other: MySet[A]): MySet[A] = this

class NonEmptySet[A](val head: A, val tail: MySet[A]) extends MySet[A]:
  override def contains(elem: A): Boolean =
    if (elem == head) true
    else tail.contains(elem)

  override def +(elem: A): MySet[A] =
    if (this.contains(elem)) this
    else new NonEmptySet[A](elem, this)

  override def ++(set: MySet[A]): MySet[A] =
    tail ++ set + head

  override def map[B](f: A => B): MySet[B] =
    tail.map(f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] =
    tail.flatMap(f) ++ f(head)

  override def filter(predicate: A => Boolean): MySet[A] =
    val filteredTail = tail.filter(predicate)
    if (predicate(head)) filteredTail + head
    else filteredTail

  override def foreach(f: A => Unit): Unit =
    f(head)
    tail.foreach(f)

  override def -(elem: A): MySet[A] =
    if (head == elem) tail
    else tail - elem + head

  override def &(other: MySet[A]): MySet[A] =
    val theRest = tail & other
    if (other.contains(head)) theRest + head
    else theRest

  override def --(other: MySet[A]): MySet[A] =
    if (other.contains(head)) tail -- (other - head)
    else tail -- other + head
