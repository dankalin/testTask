package system
import org.apache.spark.sql.types._

object Product extends Enumeration {

  val ID, NAME, PRICE, NUMBER_OF_PRODUCTS = Value

  val structType = StructType(
    Seq(
      StructField(ID.toString, IntegerType),
      StructField(NAME.toString, StringType),
      StructField(PRICE.toString, DoubleType),
      StructField(NUMBER_OF_PRODUCTS.toString, IntegerType)
    )
  )
}

case class Product(id: Int, name: String, price: Double, numberOfProducts: Int)