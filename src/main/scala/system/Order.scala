package system
import java.sql.Date
import org.apache.spark.sql.types._

object Order extends Enumeration {


  val CUSTOMER_ID, ORDER_ID, PRODUCT_ID, NUMBER_OF_PRODUCT, ORDER_DATE, STATUS = Value

  val structType = StructType(
    Seq(
      StructField(CUSTOMER_ID.toString, IntegerType),
      StructField(ORDER_ID.toString, IntegerType),
      StructField(PRODUCT_ID.toString, IntegerType),
      StructField(NUMBER_OF_PRODUCT.toString, IntegerType),
      StructField(ORDER_DATE.toString, DateType),
      StructField(STATUS.toString, StringType)
    )
  )
}

case class Order(customerID: Int, orderID: Int, productID: Int, numberOfProduct: Int, orderDate: Date, status: String)