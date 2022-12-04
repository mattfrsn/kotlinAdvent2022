import java.io.File

fun main() {
    val values = mapOf<String, Int>(
        "X" to 1, // pt1 Rock / 2 lose
        "Y" to 2, // pt1 Paper / 2 draw
        "Z" to 3 // pt1 Sissors / 2 win
    )

    fun determineGameResult(theirs: String, mine: String): Int {
        return when(theirs.single() to mine.single()) {
            'B' to 'X', 'C' to 'Y', 'A' to 'Z' -> 0
            'B' to 'Y', 'C' to 'Z', 'A' to 'X' -> 3
            'B' to 'Z', 'C' to 'X', 'A' to 'Y' -> 6
            else -> error("Before you wreck yourself")
        }
    }

    fun determineGuess(game: List<String>): Char {
        val theirGuess = game[0].single()
        return when(game[1].single()) {
            'X' -> // lose
                when(theirGuess){
                    'B' -> 'X'
                    'C' -> 'Y'
                    'A' -> 'Z'
                    else -> error("Check input")
                }
            'Y' -> // draw
                when(theirGuess){
                    'B' -> 'Y'
                    'C' -> 'Z'
                    'A' -> 'X'
                    else -> error("Check input")
                }
            'Z' -> // win
                when(theirGuess){
                    'B' -> 'Z'
                    'C' -> 'X'
                    'A' -> 'Y'
                    else -> error("Check input")
                }
            else -> error("Check your input")
        }
    }

    fun rockPaperSissors(input: List<String>, part: Int) {
        var score = 0
        require(part in 1..2) {
            "part must be 1 or 2"
        }
        input.map { currentGame ->
            currentGame.split(" ").let {
                val myGuess = if(part == 1) it[1] else determineGuess(it).toString()
                val myScore = values[myGuess] ?: error("Check yourself")
                val gameScore = determineGameResult(it[0], myGuess)
                score += myScore + gameScore!!
            }
        }
        println(score)
    }

    val input = File("src/input.txt").readLines()
    rockPaperSissors(input, 1)
    rockPaperSissors(input, 2)
}
