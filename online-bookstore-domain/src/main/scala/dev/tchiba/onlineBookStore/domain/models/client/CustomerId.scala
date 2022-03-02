package dev.tchiba.onlineBookStore.domain.models.client

import dev.tchiba.ddd.base.EntityId

/**
 * 顧客ID
 */
final class CustomerId(val value: Long) extends EntityId[Long] {

  override def canEqual(that: Any): Boolean = that.isInstanceOf[CustomerId]

  override def toString = s"ClientId($value)"
}
