package dev.tchiba.ddd.base

/**
 * DDDにおけるエンティティID
 *
 * @tparam A 内部の値の型
 */
trait EntityId[A] {

  val value: A

  /**
   */
  def canEqual(that: Any): Boolean

  override def equals(other: Any): Boolean = other match {
    case that: EntityId[_] => that.canEqual(this) && value == that.value
    case _                 => false
  }

  override def hashCode(): Int = 31 * value.##
}
