import java.io.{File, PrintWriter}

import scala.collection.mutable.{ArrayBuffer, Map}


class PlaneFlightsData(fileData: List[String], name: String) {
  protected val expectedHead = List("YEAR", "QUARTER", "MONTH", "DAY_OF_MONTH", "DAY_OF_WEEK", "FL_DATE", "ORIGIN", "DEST")
  val head: List[String] = fileData.head.split(",").toList.map(_.replace("\"", ""))
  protected val data = ArrayBuffer[FlightData]()
  val _fileData = fileData.drop(1)

  if (checkHead(head, expectedHead)) {
    for (line <- _fileData) {
      val split_data = line.split(",").toList.map(_.replace("\"", ""))
      data.append(new FlightData(split_data))

    }
  }
  val airPorts: List[String] = (data.map(_.dest) ++ data.map(_.origin)).toList.distinct

  def checkHead(head: List[String], expectedHead: List[String]): Boolean = {
    var res: Boolean = true
    for (i <- head.indices) {
      if (head(i) != expectedHead(i)) {
        res = false
      }

    }
    res
  }

  def doTask1(): Unit = {
    val fileWriter = new PrintWriter(new File("output/%s_task1.txt".format(name)))
    for (port <- airPorts) {
      fileWriter.println("%s %d".format(port, data.filter(_.dest == port).size))
    }
    fileWriter.close()
  }

  def doTask2(): Unit = {
    val fileWriter = new PrintWriter(new File("output/%s_task2.txt".format(name)))
    for (port <- airPorts) {
      val outcome = data.filter(_.origin == port).size
      val income = data.filter(_.dest == port).size
      if (outcome - income > 0) {
        fileWriter.println("%s +%d".format(port, (outcome - income)))
      } else if (outcome - income < 0) {
        fileWriter.println("%s %d".format(port, (outcome - income)))
      }
    }
    fileWriter.close()
  }

  def doTask3(): Unit = {
    val weeks = data.map(_.week).distinct
    val fileWriter = new PrintWriter(new File("output/%s_task3.txt".format(name)))
    for (w <- weeks) {
      fileWriter.println("W%s".format(w))
      for (port <- airPorts) {
        fileWriter.println("\t%s %d".format(port, data.filter(_.week == w).filter(_.dest == port).size))
      }
    }
    fileWriter.close()
  }


}
