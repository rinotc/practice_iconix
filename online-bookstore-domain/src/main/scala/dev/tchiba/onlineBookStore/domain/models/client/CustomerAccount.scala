package dev.tchiba.onlineBookStore.domain.models.client

import dev.tchiba.ddd.base.Entity

/**
 * 顧客アカウント
 *
 * @param id   顧客ID
 * @param name 顧客名称
 */
final class CustomerAccount(
    val id: CustomerId,
    val name: String
) extends Entity[CustomerId] {

  override def canEqual(that: Any): Boolean = that.isInstanceOf[CustomerAccount]
}
