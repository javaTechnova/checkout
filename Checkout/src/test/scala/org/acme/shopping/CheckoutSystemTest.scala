package org.acme.shopping

import org.scalatest.{Matchers, FlatSpec}
import org.acme.shopping.Item._

class CheckoutSystemTest extends FlatSpec with Matchers {
  
  val offers = Map(
    Apple -> new DiscountedOffer("Buy 1 get 1 free", 2, 1),
    Orange -> new DiscountedOffer("3 for the price of 2", 3, 2))
  
  it should "handle empty shopping basket" in {
    CheckoutSystem.getBasketTotal(Seq.empty) should be(0.0)
  }
  
  it should "calculate basket total correctly for single item" in {
    CheckoutSystem.getBasketTotal(Seq(Apple)) should be(0.60)
  }
  
  it should "calculate basket total correctly for multiple items" in {
    CheckoutSystem.getBasketTotal(Seq(Apple,Orange,Apple)) should be(1.45)
  }
  
  it should "apply buy One and get One free offer correctly" in {
    val offers = Map(Apple -> new DiscountedOffer("Buy 1 get 1 free", 2, 1))
    CheckoutSystem.getBasketTotal(Seq(Apple,Orange,Apple),offers) should be(0.85)
  }
  
  it should "apply buy Three get two offer correctly" in {
    val offers = Map(Orange -> new DiscountedOffer("3 for the price of 2", 3, 2))
    CheckoutSystem.getBasketTotal(Seq(Apple,Orange,Orange,Apple,Orange),offers) should be(1.70)
  }
  
  it should "apply multiple discounts on different items correctly" in {
    CheckoutSystem.getBasketTotal(Seq(Apple,Orange,Apple,Orange,Orange,Apple,Apple,Apple),offers) should be(2.30)
  }
  
  it should "calculate basket total correctly even when discounts are not applicable" in {
    CheckoutSystem.getBasketTotal(Seq(Apple,Orange,Orange),offers) should be(1.10)
  }
  
  it should "handle offers correctly on empty basket" in {
    CheckoutSystem.getBasketTotal(Seq.empty,offers) should be(0.0)
  }
}