import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{row_number, sum}
import system.Parameters
object Main extends App{
  val spark: SparkSession = SparkSession.builder()
    .master("local[*]")
    .appName("testTask")
    .getOrCreate()
  Logger.getLogger("org").setLevel(Level.WARN)

  import spark.implicits._
  Parameters.initTables(spark)
  val order=spark.table("order")
  val product=spark.table("product").select($"id",$"name".as("product_name"))
  val customer=spark.table("customer").select($"id",$"name".as("customer_name"))
  val windowFunc = Window.partitionBy($"customer_id").orderBy($"sum_num_of_product".desc) //нумерую самые популярные товары
  val sum_product = order.groupBy($"customer_id", $"product_id")
   .agg(sum($"number_of_product").as("sum_num_of_product"))
   .select($"customer_id", $"product_id",
     row_number().over(windowFunc).as("num_row"))
    .filter($"num_row"===1) //выбираю самый популярный товар у пользователя
  val res=sum_product.join(product,
  $"product_id"===$"id","inner")
    .select($"product_name",$"customer_id")
    .join(customer,$"customer_id"===$"id","inner")
    .select($"customer_name",$"product_name").coalesce(1)
    .write
    .option("header","true")
    .option("sep","\\t")
    .mode("overwrite")
    .csv(Parameters.OUTPUT_PATH)

}
