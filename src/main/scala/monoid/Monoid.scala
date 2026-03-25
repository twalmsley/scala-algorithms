package monoid

trait Semigroup[A]:
  def combine(a: A, b: A): A

object Semigroup:
  def apply[A](using instance: Semigroup[A]): Semigroup[A] = instance

trait Monoid[A] extends Semigroup[A]:
  def empty: A

object Monoid:
  def apply[A](using instance: Monoid[A]): Monoid[A] = instance

object IntInstances:

  def intAddition(a:Int, b:Int):Int = a + b

  given intAdditiveSemigroup: Semigroup[Int] with
    override def combine(a: Int, b: Int): Int = intAddition(a, b)

  given intAdditiveMonoid: Monoid[Int] with
    override def combine(a: Int, b: Int): Int = intAddition(a, b)
    override def empty: Int = 0

object StringInstances:

  given stringAdditiveSemigroup: Semigroup[String] with
    override def combine(a: String, b: String): String = a + b

  given stringAdditiveMonoid: Monoid[String] with
    override def combine(a: String, b: String): String = a + b
    override def empty: String = ""

object SemigroupSyntax:
  extension [A](a: A)
    def |+|(b: A)(using semigroup: Semigroup[A]): A = semigroup.combine(a, b)

import SemigroupSyntax._

def fold[A: Semigroup](list: List[A]): A =
  list.reduce(_ |+| _)

