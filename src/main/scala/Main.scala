object Main {
  def main(args: Array[String]): Unit = {
    println("Basic scala program")
    val seq=Seq(1,2,3,4,5,6,7,8,9)
    seq.filter(x=>x%2!=0).foreach(x=>println(x))

    val objects=Seq("Geeks", "for", "Geeks")
    val result = objects.map(_.toUpperCase())
    for (name <- result if name.startsWith("G")) println(name)
  }

}
