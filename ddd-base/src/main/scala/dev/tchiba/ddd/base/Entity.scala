package dev.tchiba.ddd.base

/**
 * DDDのエンティティ
 *
 * @tparam ID 識別子の型
 */
trait Entity[ID <: EntityId[_]] {

  def id: ID

  def canEqual(that: Any): Boolean

  override def equals(other: Any): Boolean = other match {
    case that: Entity[_] => that.canEqual(this) && id == that.id
    case _               => false
  }

  override def hashCode(): Int = 31 * id.##
}
