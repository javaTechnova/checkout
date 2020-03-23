package org.acme.shopping

import org.acme.shopping.Item._

object CheckoutSystem {

  val items = Map(
    Apple -> ItemPriced(Apple, 0.6),
    Orange -> ItemPriced(Orange, 0.25))

  def getBasketTotal(basket: Seq[Item],offers: Map[Item,Offer[ItemPriced]] = Map.empty): Double = {
   if(offers.isEmpty)
     calculateTotal(deriveItem(basket))
   else{
      val grouped = deriveItem(basket).groupBy(f => f)
      val discountedBasket = grouped.flatMap(i => {
        offers.get(i._1.name) match {
          case Some(offer) => offer.discountedItems(i._2)
          case None => i._2
        }})
        calculateTotal(discountedBasket.toSeq)
      }
  }
  
  def deriveItem(basket: Seq[Item]) :Seq[ItemPriced] = {
    basket.map(items.get(_).get)
  }
  
  def calculateTotal(basket: Seq[ItemPriced]) :Double = {
    basket.foldLeft(0.0)((total,item) => total+item.price)
  }
}