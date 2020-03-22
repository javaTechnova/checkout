package org.acme.shopping

import org.acme.shopping.Item._

object CheckoutSystem {
  
  val items = Map(Apple -> ItemPriced(Apple,0.6),
                  Orange -> ItemPriced(Orange,0.25))
  
  def getBasketTotal(basket:Seq[Item]): Double = {
    basket.foldLeft(0.0)((total,item) => {
      total+items.get(item).get.price
    })
  }
}