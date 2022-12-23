import java.io.File

fun main() {
    fun part1(input: List<String>): Int {
        val ranges = input.map { elfAssignments ->
            elfAssignments.asRanges()
        }
        return ranges.count { it.first.fullyOverlaps(it.second) || it.second.fullyOverlaps(it.first) }
    }

    fun part2(input: List<String>): Int {
        val ranges = input.map { elfAssignments ->
            elfAssignments.asRanges()
        }
        return ranges.count { it.first.overlaps(it.second) || it.second.overlaps(it.first) }
    }

    val input = File("src/input.txt").readLines()
    println(part1(input))
    println(part2(input))
}

private infix fun IntRange.fullyOverlaps(otherRange: IntRange): Boolean =
    first <= otherRange.first && last >= otherRange.last


private infix fun IntRange.overlaps(otherRange: IntRange): Boolean =
    first <= otherRange.last && otherRange.first <= last

private fun String.asIntRange(): IntRange =
    substringBefore("-").toInt()..substringAfter("-").toInt()

private fun String.asRanges(): Pair<IntRange, IntRange> =
    substringBefore(",").asIntRange() to substringAfter(",").asIntRange()