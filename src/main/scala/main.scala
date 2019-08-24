import java.io.{File, FileInputStream}
import java.util.zip.GZIPInputStream
import scala.io.Source.fromInputStream
object PlaneFlightsParser {
  def main(args: Array[String]): Unit = {
    val FileList: List[File] = getListOfFiles("resources", ".csv.gz")
    for (file <- FileList) {
      val fileData:List[String] =readGZIPFile(file)
      val flightsData: PlaneFlightsData = new PlaneFlightsData(fileData, file.getName)
      flightsData.doTask1
      flightsData.doTask2
      flightsData.doTask3

    }
  }

  def getListOfFiles(dir: String, extension: String): List[File] = {
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(f => f.isFile && f.getName.endsWith(extension)).toList
    } else {
      List[File]()
    }
  }

  def readGZIPFile(file: File): List[String] = {
    val inputStream: GZIPInputStream = new GZIPInputStream(new FileInputStream(file))
    val output: List[String] = fromInputStream(inputStream).getLines().toList
    output
  }
}