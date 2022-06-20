package system

import java.sql.Date

import org.apache.spark.sql.types._

object Customer extends Enumeration {

  val ID, NAME, EMAIL, JOIN_DATE, STATUS = Value

  val structType = StructType(
    Seq(
      StructField(ID.toString, IntegerType),
      StructField(NAME.toString, StringType),
      StructField(EMAIL.toString, StringType),
      StructField(JOIN_DATE.toString, DateType),
      StructField(STATUS.toString, StringType)
    )
  )
}

case class Customer(id: Int,
                    name: String,
                    email: String,
                    joinDate: Date,
                    status: String)
