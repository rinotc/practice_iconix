package dev.tchiba.onlineBookStore.domain.models.client

import dev.tchiba.ddd.base.Entity

/**
 * 顧客
 *
 * @param id   顧客ID
 * @param name 顧客名称
 */
final class Client(
    val id: ClientId,
    val name: String
) extends Entity[ClientId] {
  override def canEqual(that: Any): Boolean = that.isInstanceOf[Client]
}
