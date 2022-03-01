package dev.tchiba.onlineBookStore.domain.models.client

import dev.tchiba.ddd.base.EntityId

import java.util.UUID

/**
 * 顧客ID
 */
final class ClientId(val value: UUID) extends EntityId[UUID] {

  override def canEqual(that: Any): Boolean = that.isInstanceOf[ClientId]

  override def toString = s"ClientId($value)"
}
