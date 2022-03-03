package dev.tchiba.onlineBookStore.domain.models.client

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must

class CustomerIdTest extends AnyWordSpec with must.Matchers {

  "CustomerIdTest" should {
    "underlying value must be positive" in {
      CustomerId(-5).isLeft mustBe true
    }
  }
}
