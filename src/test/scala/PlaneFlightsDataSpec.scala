import PlaneFlightsParser.readGZIPFile
import org.specs2._
import org.specs2.mutable.Specification
import java.io.File

import scala.io.Source
class PlaneFlightsDataSpec extends Specification {
  "PlaneFlightsData(Positive)" should {
    val fileData:List[String] = Source.fromFile("test_data/test1.csv").getLines().toList
    val flightsData: PlaneFlightsData = new PlaneFlightsData(fileData, "test")
    "Has correct values for head" in {
      flightsData.head must_===List("YEAR", "QUARTER", "MONTH", "DAY_OF_MONTH", "DAY_OF_WEEK", "FL_DATE", "ORIGIN", "DEST")
    }
    "Has correct values for Airports" in {
      flightsData.airPorts must_=== List("LAX", "KBP", "JFK")
    }
  }

}
