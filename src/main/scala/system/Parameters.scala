package system

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

object Parameters {
  val OUTPUT_PATH = "./output/"

  val PATH_CUSTOMER = "./datasets/customer.csv"
  val PATH_ORDER = "./datasets/order.csv"
  val PATH_PRODUCT = "./datasets/product.csv"

  val TABLE_CUSTOMER = "customer"
  val TABLE_ORDER = "order"
  val TABLE_PRODUCT = "product"

  private def createTable(name: String, structType: StructType, path: String, delimiter: String = "\\t")
                         (implicit spark: SparkSession): Unit = {
    spark.read
      .format("csv")
      .options(
        Map(
          "delimiter" -> delimiter,
          "nullValue" -> "\\N"
        )
      ).schema(structType).load(path).createOrReplaceTempView(name)
  }

  def initTables(implicit spark: SparkSession): Unit = {
    createTable(Parameters.TABLE_ORDER, Order.structType, Parameters.PATH_ORDER)
    createTable(Parameters.TABLE_PRODUCT, Product.structType, Parameters.PATH_PRODUCT)
    createTable(Parameters.TABLE_CUSTOMER, Customer.structType, Parameters.PATH_CUSTOMER)
  }
}
