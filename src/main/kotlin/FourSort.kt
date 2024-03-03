package org.example
import kotlin.random.Random


fun <T : Comparable<T>> SelectionSort(list: MutableList<T>): MutableList<T>{
    //iterate through the list from least to largest index
    for (i in 0 .. list.size - 1){

        var minIndex = i

        //iterate through the rest of the list from the index i, returning the index of the minimum
        for (i2 in (i + 1) .. (list.size-1)) {
            if (list[i2]<list[minIndex]) {
                minIndex = i2
            }
        }

        //if the minIndex is not located at i, swap the values stored at i and minIndex
        if (minIndex != i) {
            Swap(list, i, minIndex)
        }


    }
    return list
}

fun <T: Comparable<T>> MergeSort(list: MutableList<T>): MutableList<T>{
    val flooredHalf = list.size/2
    val firstHalf = SelectionSort<T>(list.subList(0, flooredHalf))
    val secondHalf = SelectionSort<T>(list.subList(flooredHalf+1,list.size))
    var i1 = 0
    var i2 = 0
    var currentI = 0

    while ((i1<firstHalf.size) and (i2<secondHalf.size)){
     if (firstHalf[i1]>secondHalf[i2]){
         list[currentI] = firstHalf[i1]
         currentI++
         i1++
     }else{
         list[currentI] = secondHalf[i2]
         currentI++
         i2++
     }
    }

    if (i1==firstHalf.size){

        //if all the values from the first list have already been sorted into the new list
        //place the rest of the second list in order into the new list
        while (i2<secondHalf.size){
            list[currentI] = secondHalf[i2]
            currentI++
            i2++
        }

    }else{
        //otherwise
        //place the rest of the first list in order into the new list
        while (i1<secondHalf.size){
            list[currentI] = firstHalf[i1]
            currentI++
            i1++
        }
    }

    return list
}

fun <T : Comparable<T>>  QuickSort(list: MutableList<T>): MutableList<T>{
    QuickSortRecursive(list, 0, list.size-1)
    return list
}
fun <T : Comparable<T>>  QuickSortRecursive(list:MutableList<T>, first:Int, last:Int){
    if (first<last){
        //randomly choose a pivotIndex
        val pivotIndex = Random.nextInt(first, last)

        //sort list to place pivot in correct position
        val newPivotIndex = Partition(list, first, last, pivotIndex)

        // Recursively sort the sub-arrays
        QuickSortRecursive<T>(list, last, newPivotIndex - 1)
        QuickSortRecursive<T>(list, newPivotIndex + 1, last)
    }
}

fun <T : Comparable<T>>  Swap(list:MutableList<T>, toSwapA: Int, toSwapB: Int){
    val temp = list[toSwapA]
    list[toSwapA] = list[toSwapB]
    list[toSwapB] = temp
}
fun <T : Comparable<T>>  Partition(list: MutableList<T>, first: Int, last: Int, pivotIndex:Int) : Int {

    //swap pivot with last position
    Swap(list, pivotIndex, last)

    //move pivot to last position
    val pivot = list[last]
    var pivotFinalPosition = first

    //iterates through the indexes in the partition
    for (currentIndex in first..(last - 1)) {

        //swap the values less than or equal to the pivot to be in front of the pivotFinalPosition
        if (list[currentIndex] <= pivot) {
            Swap(list, pivotFinalPosition, currentIndex)
            pivotFinalPosition++
        }

    }

    //swap the pivot into the final position
    Swap(list, pivotFinalPosition, last)

    //return the final position of the pivot
    return pivotFinalPosition


}

fun <T : Comparable<T>> InsertionSort(list: MutableList<T>): MutableList<T> {
    // Iterate through elements in array from second element onward
    for (i in 1 until list.size) {
        // Current element to be inserted into the sorted part of the array
        val currentElement = list[i]

        // Index of the last element in the sorted part of the array
        var lastSortedIndex = i - 1

        // increase position of all elements greater than the currentElement by one
        while (lastSortedIndex >= 0 && list[lastSortedIndex] > currentElement) {
            list[lastSortedIndex + 1] = list[lastSortedIndex]
            lastSortedIndex--
        }

        // place currentElement into place
        list[lastSortedIndex + 1] = currentElement
    }
    return list
}

