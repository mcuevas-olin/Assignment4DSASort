package org.example

import kotlin.random.Random
import kotlin.system.measureTimeMillis

//fun generateRandomList makes a mutableList

fun generateRandomList(size: Int): MutableList<Int> {
    return MutableList(size) { Random.nextInt(1, 1000) }
}
fun printBenchmarkTimes() {

    val sortingAlgorithms = listOf<(MutableList<Int>) -> MutableList<Int>>(::InsertionSort, ::SelectionSort, ::QuickSort, ::MergeSort)
    val algorithmNames = listOf(
        "InsertionSort",
        "SelectionSort",
        "QuickSort",
        "MergeSort"
    )

    val listSizes = listOf(1000, 5000, 10000, 15000,100000,150000)

    println("Algorithm\t1000\t\t5000\t\t10000\t\t15000\t\t100000\t\t150000")


    for ((algorithm, algorithmName) in sortingAlgorithms.zip(algorithmNames)) {
        print("$algorithmName\t")

        for (size in listSizes) {


            val sampleSize = 100
            var elapsedTime: Long = 0


            for (i in 1..sampleSize){

                val testList = generateRandomList(size)

                elapsedTime = elapsedTime + measureTimeMillis{algorithm(testList)}

            }
            elapsedTime = elapsedTime/sampleSize
            print("$elapsedTime\t\t")
        }

        println()
    }
}