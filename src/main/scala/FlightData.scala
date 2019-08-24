class FlightData(splitedData:List[String]) {
  val year: String = splitedData(0)
  val quarter: String = splitedData(1)
  val month: String = splitedData(2)
  val day: String = splitedData(3)
  val week: String = splitedData(4)
  val fl_date: String = splitedData(5)
  val origin: String = splitedData(6)
  val dest: String = splitedData(7)

}
