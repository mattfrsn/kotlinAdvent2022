import java.io.File

fun main() {
    fun Char.toScore(): Int =
        if (this.isUpperCase()) {
            this - 'A' + 27
        } else {
            this - 'a' + 1
        }

    fun part1(input: List<String>): Long {
        val total = input.map { rucksack ->
            rucksack.substring(0, rucksack.length / 2) to rucksack.substring(rucksack.length / 2)
        }
            .flatMap { (first, second) -> first.toSet() intersect second.toSet() }
            .sumOf { it.toScore() }.toLong()

        return total
    }

    fun part2(input: List<String>): Long {
        val total = input.chunked(3).map { elfGroup ->
            elfGroup.zipWithNext()
                .map { (first, second) ->
                    first.toSet() intersect second.toSet()
                }
        }
            .flatMap { sharedItems -> sharedItems[0] intersect sharedItems[1] }
            .sumOf { it.toScore() }.toLong()

        return total
    }

    val input = File("src/input.txt").readLines()
    println(part1(input))
    println(part2(input))
}