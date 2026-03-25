package monoid

trait Semigroup[T]:
  def combine(a: T, b: T): T

object Semigroup:
  def apply[T](using instance: Semigroup[T]): Semigroup[T] = instance

trait Monoid[T] extends Semigroup[T]:
  def empty: T

object Monoid:
  def apply[T](using instance: Monoid[T]): Monoid[T] = instance

object IntInstances:

  given intAdditiveSemigroup: Semigroup[Int] with
    override def combine(a: Int, b: Int): Int = a + b

  given intAdditiveMonoid: Monoid[Int] with
    override def combine(a: Int, b: Int): Int = a + b
    override def empty: Int = 0

object StringInstances:

  given stringAdditiveSemigroup: Semigroup[String] with
    override def combine(a: String, b: String): String = a + b

  given stringAdditiveMonoid: Monoid[String] with
    override def combine(a: String, b: String): String = a + b
    override def empty: String = ""

object SemigroupSyntax:
  extension [T](a: T)
    def |+|(b: T)(using semigroup: Semigroup[T]): T = semigroup.combine(a, b)

import SemigroupSyntax._

def fold[T: Semigroup](list: List[T]): T =
  list.reduce(_ |+| _)

