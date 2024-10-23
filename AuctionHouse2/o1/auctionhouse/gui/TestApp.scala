////////////////// NOTE TO STUDENTS //////////////////////////
// For the purposes of our course, it’s not necessary
// that you understand or even look at the code in this file.
//////////////////////////////////////////////////////////////

package o1.auctionhouse.gui

import scala.swing.*
import scala.swing.event.*
import o1.auctionhouse.*
import FlowPanel.Alignment
import o1.gui.swingops.*
import o1.gui.layout.*
import o1.gui.Dialog.*
import o1.gui.O1AppDefaults
import java.util.Locale.US
import scala.language.adhocExtensions // enable extension of Swing classes

/** The singleton object `TestApp` represents a simple application that a programmer can use
  * to experiment with the main functionality of the classes from package `o1.auctionhouse`.
  *
  * **NOTE TO STUDENTS: In this course, you don’t need to understand how this object works
  * on the inside. It’s enough to know that you can use this file to start the program.** */
object TestApp extends SimpleSwingApplication, O1AppDefaults:

  def top = new MainFrame:

    title = """A Simple UI for Playing Around with an "Auction House""""
    location = Point(100, 100)

    val house = AuctionHouse("ReBay")

    val itemTabs = TabbedPane()
    val statusLine = Label()

    val addFPS = Button("Add a fixed-price item")      ( newItemTab(makeFPS()) )
    val addDA  = Button("Add a Dutch-style auction")   ( newItemTab(makeDA())  )
    val addEA  = Button("Add an English-style auction")( newItemTab(makeEA())  )
    private def newItemTab(makePanel: =>Option[ItemPanel[?]]) =
      makePanel.foreach(this.addTab)
      updateStatus()

    val advanceButton = Button("Next day")( this.advanceDay() )
    val buttonRow = FlowPanel(FlowPanel.Alignment.Center)(addFPS, addDA, addEA, advanceButton)

    listenTo(itemTabs.selection)
    reactions += { case SelectionChanged(_) => changeDefaultItem() }
    private def changeDefaultItem() =
      defaultButton = itemTabs.selection.page.content.asInstanceOf[ItemPanel[?]].defaultButton

    this.addTab(FPSPanel(FixedPriceSale("Example item", 10_000, 3)))
    this.addTab(DAPanel(DutchAuction("Dutch auction example", 100_000, 1000, 70_000)))
    this.addTab(EAPanel(EnglishAuction("English auction example", 50_000, 10)))

    contents = new EasyPanel:
      placeNW(statusLine, (0, 0), OneSlot, Slight,         (5, 5,  5, 5))
      placeNW(buttonRow,  (0, 1), OneSlot, Slight,         NoBorder)
      placeNW(itemTabs,   (0, 2), OneSlot, FillBoth(1, 1), NoBorder)

    this.resizable = false
    this.updateStatus()
    this.pack()


    private def advanceDay() =
      this.house.nextDay()
      this.itemTabs.pages.foreach( _.content.asInstanceOf[ItemPanel[?]].refresh() )

    def updateStatus() =
      val line = s"""${house.name}. Total: %.2f €. Average: %.2f €. Open items: ${house.numberOfOpenItems}. Priciest: ${house.priciest.map( _.description ) getOrElse "(none)"}."""
      this.statusLine.text = line.formatLocal(US, (house.totalPrice / 100.0), (house.averagePrice / 100.0))

    def addTab(panel: ItemPanel[ItemForSale]) =
      this.house.addItem(panel.item)
      val itemCount = this.itemTabs.pages.size
      this.itemTabs.pages += TabbedPane.Page("Item #" + (itemCount + 1), panel, panel.tooltipText)
      this.itemTabs.selection.index = itemCount
      panel.requestFocus()
      this.pack()

    private def makeFPS() =
       for
         description <- descriptionParam()
         price       <- intParam("Price (in cents)")
         duration    <- intParam("Duration (in days)")
       yield FPSPanel(FixedPriceSale(description, price, duration))

    private def makeDA() =
       for
         description <- descriptionParam()
         start       <- intParam("Starting price (in cents)")
         decrement   <- intParam("Decrement (in cents)")
         minimum     <- intParam("Minimum price (in cents)")
       yield DAPanel(DutchAuction(description, start, decrement, minimum))

    private def makeEA() =
       for
         description <- descriptionParam()
         start       <- intParam("Starting price (in cents)")
         duration    <- intParam("Duration (in days)")
       yield EAPanel(EnglishAuction(description, start, duration))

    def descriptionParam(): Option[String] =
      requestNonEmptyLine("Item description: ", "Please enter a non-empty description.", RelativeTo(buttonRow))

    def intParam(prompt: String): Option[Int] =
      requestInt(prompt + ": ", _ >= 0, "Please enter a reasonable integer value.", RelativeTo(buttonRow)).map(Integer.valueOf)

    abstract class ItemPanel[+SaleType <: ItemForSale](val item: SaleType) extends EasyPanel:
      val tooltipText = item.description

      def defaultButton: Button

      val open, expired, buyer = Label()
      val title = Label(s"<html><b>${item.description}</b> (${item.getClass.getSimpleName})</html>")
      val customerSelector = ComboBox(Seq("Melchior", "Caspar", "Balthazar"))
      // customerSelector.makeEditable()
      val buyAction = FlowPanel(Alignment.Left)(Label("Select customer: "), customerSelector)

      placeNW(title,     (0, 0), ThreeWide, NoFill(1, 0), (20, 20, 8,  8))
      placeNW(open,      (0, 1), OneSlot,   NoFill(1, 0), (0,  20, 0,  0))
      placeNW(expired,   (1, 1), OneSlot,   NoFill(1, 0), (0,  0,  0,  0))
      placeNW(buyer,     (0, 4), ThreeWide, NoFill(1, 0), (0,  20, 0,  0))
      placeNW(buyAction, (0, 5), ThreeWide, NoFill(1, 0), (5,  20, 10, 0))

      def refresh() =
        this.open.text    = s"<html>Open: ${if this.item.isOpen then "yes" else "<font color=\"red\">no</font>"}</html>"
        this.buyer.text   = s"<html>Buyer: ${this.item.buyer.map( name => s"""<font color="green">$name</font>""" ) getOrElse "(none)"}</html>"
        this.expired.text = s"<html>Expired: ${if this.item.isExpired then "<font color=\"red\">yes</font>" else "no"}</html>"
        updateStatus()

    end ItemPanel


    private abstract class IPPanel(item: InstantPurchase) extends ItemPanel(item):

      val buyButton = new Button("Buy"):
        listenTo(this)
        reactions += {
          case ButtonClicked(_) =>
            val result = item.buy(customerSelector.selection.item)
            if !result then display("Failed to buy the item.", RelativeTo(this))
            refresh()
        }

      buyAction.contents += buyButton
      def defaultButton = this.buyButton

    end IPPanel


    private class FPSPanel(item: FixedPriceSale) extends IPPanel(item):
      val daysRemaining = Label()
      val price = Label("Price: %.2f €".formatLocal(US, this.item.price / 100.0))
      refresh()
      placeNW(price,         (0, 2), OneSlot, NoFill(1, 0), (0,  20, 0, 0))
      placeNW(daysRemaining, (1, 2), OneSlot, NoFill(1, 0), (0,  0,  0, 0))

      override def refresh() =
        super.refresh()
        this.daysRemaining.text = s"Days remaining: ${this.item.daysLeft}"

    end FPSPanel

    private class DAPanel(item: DutchAuction) extends IPPanel(item):
      val priceRatio, currentPrice = Label()
      val startingPrice = Label("Starting price: %.2f €".formatLocal(US, this.item.startingPrice / 100.0))
      val minimumPrice = Label("Minimum price: %.2f €".formatLocal(US, this.item.minimumPrice / 100.0))
      refresh()
      placeNW(startingPrice, (0, 2), OneSlot, Slight, (0,  20, 0, 0))
      placeNW(minimumPrice,  (1, 2), OneSlot, Slight, (0,  0,  0, 0))
      placeNW(currentPrice,  (0, 3), OneSlot, Slight, (0,  20, 0, 0))
      placeNW(priceRatio,    (1, 3), OneSlot, Slight, (0,  0,  0, 0))

      override def refresh() =
        super.refresh()
        this.currentPrice.text = "Price now: %.2f €".formatLocal(US, this.item.price / 100.0)
        this.priceRatio.text   = "Price/starting: %.2f".formatLocal(US, this.item.priceRatio)

    end DAPanel

    private class EAPanel(item: EnglishAuction) extends ItemPanel(item):
      val requiredBid, currentPrice, daysRemaining = Label()
      val startingPrice = Label("Starting price: %.2f €".formatLocal(US, this.item.startingPrice / 100.0))
      val amountField = TextField((this.item.price * 1.1).toInt.toString)
      amountField.columns = 8
      val bidButton = new Button("Place bid"):
        listenTo(this)
        reactions += {
          case ButtonClicked(_) =>
            val result = EAPanel.this.item.bid(customerSelector.selection.item, amountField.text.toIntOption getOrElse 0)
            if !result then display("Didn't become the highest bidder.", RelativeTo(this))
            refresh()
        }
      buyAction.contents += Label("Amount (in cents): ") += amountField += bidButton
      refresh()
      placeNW(startingPrice, (0, 2), OneSlot, Slight, (0,  20, 0, 0))
      placeNW(daysRemaining, (1, 2), OneSlot, Slight, (0,  0,  0, 0))
      placeNW(currentPrice,  (0, 3), OneSlot, Slight, (0,  20, 0, 0))
      placeNW(requiredBid,   (1, 3), OneSlot, Slight, (0,  0,  0, 0))

      override def refresh() =
        super.refresh()
        this.currentPrice.text =   "Price now: %.2f €".formatLocal(US, this.item.price / 100.0)
        this.requiredBid.text =    "Bid at least: %.2f €".formatLocal(US, this.item.requiredBid / 100.0)
        this.daysRemaining.text = s"Days remaining: ${this.item.daysLeft}"

      def defaultButton = this.bidButton

      override def requestFocus() =
        this.amountField.requestFocus()

    end EAPanel

  end top

end TestApp
