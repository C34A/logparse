import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

object LogParse {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
//            println(Arrays.toString(args)
            val s: Scanner
            s = if (args.size == 0) {
                Scanner(File("Player.log"))
            } else {
                Scanner(File(args[0]))
            }
            parse(s)
        } catch (e: Exception) {
            println("failed to get file (probably)")
            println(e)
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            e.printStackTrace(pw)
            val sStackTrace = sw.toString() // stack trace as a string
            println(sStackTrace)
        }
    }

    fun parse(s: Scanner) {
//        var currentTileset = ""
//        var currentMap = ""
        var match = MatchLog()
        var currentRound = RoundLog()
        match.addRound(currentRound)
        while (s.hasNextLine()) {
            val line = s.nextLine()
            try {
                if (line.contains("Done Loading Game Level")) {
                    val newMap = line.substring(line.indexOf(']') + 2, line.lastIndexOf('[') - 1)
                    if (newMap != currentRound.map) {
//                        println("round change!")
                        val tileset = line.substring(line.indexOf('[') + 1, line.indexOf(']'))
                        currentRound = RoundLog(tileset, newMap)
                        match.addRound(currentRound)
//                        println("new tileset: $tileset\nnew map: $newMap")
                    }
                }
                if (line.substring(0, 6) == "Killer") {
                    val killerSide = line.substring(line.indexOf(':') + 2, line.indexOf(','))
                    val victimSide = line.substring(line.lastIndexOf(':') + 2)
                    var killInfoLine = s.nextLine()
//                    println("$killerSide killed $victimSide")

                    killInfoLine = killInfoLine.replace(Regex("<[^<||^>]*>"), "<>");
//                    var killInfoList = ("<$killInfoLine").split("((?<=<)|(?=>))".toRegex())
                    var killInfoList = ("<$killInfoLine").split("<>".toRegex())
                    killInfoList = killInfoList.filterIndexed { ix, element ->
                        ix != 0 && element.isNotBlank()
                    }

                    var killerName: String? = killInfoList[0].trim();
                    var victimName: String? = killInfoList[2].trim();

                    val killLog = KillLog(if(killerName!=null) killerName else "unknown", killerSide,
                            if(victimName!=null) victimName else "unknown", victimSide)
                    currentRound.addKill(killLog)
//                    println(killLog)
//                    TODO()
//                    currentRound.addKill(KillLog())
                }
            } catch (e: Exception) {
            }
        }
        println(match)
    }
}