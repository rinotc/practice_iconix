package dev.tchiba.onlineBookStore.domain.models.client

import dev.tchiba.ddd.base.EntityId
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Positive
import eu.timepit.refined.refineV

/**
 * 顧客ID
 */
final class CustomerId(val value: Long Refined Positive) extends EntityId[Long Refined Positive] {

  override def canEqual(that: Any): Boolean = that.isInstanceOf[CustomerId]

  override def toString = s"ClientId($value)"
}

object CustomerId {
  def apply(value: Long Refined Positive) = new CustomerId(value)

  def apply(value: Long): Either[String, CustomerId] = refineV[Positive](value).map(apply)
}
