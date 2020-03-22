package org.acme.shopping

import org.scalatest.{Matchers, FlatSpec}
import org.acme.shopping.Item._

class CheckoutSystemTest extends FlatSpec with Matchers {
  
  it should "handle empty shopping basket" in {
    CheckoutSystem.getBasketTotal(Seq.empty) should be(0.0)
  }
  
  it should "calculate basket total correctly for single item" in {
    CheckoutSystem.getBasketTotal(Seq(Apple)) should be(0.60)
  }
  
  it should "calculate basket total correctly for multiple items" in {
    CheckoutSystem.getBasketTotal(Seq(Apple,Orange,Apple)) should be(1.45)
  }
  
}