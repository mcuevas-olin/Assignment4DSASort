# Assignment 4 Sorting Algorithms
Ale Cuevas
## Sorting Algorithm Benchmarking
I calculated the average runtime of four different sorting algorithms with different length `MutableLists`.

| Algorithm |	1000	|	5000	|	10000	|	15000	|	100000	|	150000|
| ----- | -------- | ----- | ------ | ------- | ------ | ------- | -------- |
| InsertionSort |	1	|	19	|	80	|	185	|	10960|		25412|
| SelectionSort |	0	|	16	|	66	|	151	|	7873	|	18012|
| QuickSort |	0	|	0	|	0	|	0	|	1	|	2	|
| MergeSort |	1	|	12	|	47	|	105	|	5092	|	11710|	

The lists were of length 1000, 5000, 10000, 15000, 100000, and 150000. Each list was filled by randomly chosen integers
from 1 to 1000 using kotlin's Random function from the random library.

`fun generateRandomList(size: Int): MutableList<Int> {
return MutableList(size) { Random.nextInt(1, 1000) }
}`

For each length list, it would create and sort a new list 100 times. It would measure how long it took in milliseconds,
and calculate the mean time it took in milliseconds.

`fun printBenchmarkTimes() {

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
}`

The results would be printed out as follows:

Algorithm	1000		5000		10000		15000		100000		150000

InsertionSort	1		19		80		185		10960		25412		

SelectionSort	0		16		66		151		7873		18012		

QuickSort	0		0		0		0		1		2		

MergeSort	1		12		47		105		5092		11710


Judging by the results above, the difference in using any of the 4 sorting algorithms in a list with about 1000 or fewer
elements is negligible. Once there are around 5000 elements, then the differences between the algorithms becomes
apparent, with Quick Sort being the fastest showing no increase in time. The second fastest is Merge Sort, third fastest is
selection sort, and insertion sort is the slowest of all. This trend continues as the lists get bigger. It is important 
to note that, especially as the array sizes get bigger the fastest algorithm QuickSort, is faster by several orders of 
magnitude compared to the second fastest, MergeSort.

In summary, if your list has less than 1000 elements, it does not matter what sorting algorithm you use. Otherwise, your
best bet by far would be implementing the Quick Sort algorithm. Despite the greater complexity in implementing it, its 
performance far outstrips the merge sort, especially as the list size gets larger.

## New Frontiers in Sorting

Extracellular electrophysiological recording is used to measure neuron activity in live subjects. It can directly measure 
neuron spikes with a relatively fast sampling rate. However, despite the capablities of having high sampling rates, the 
data is noisy. It is difficult identify and characterize different spikes from the data. This process is called "Spike Sorting".
It is an example of a blind source separation problem, making it difficult to validate the potentially very ambiguous results.

The paper "Comparing Spike Sorting Algorithms on Simulated Extracellular Multi-Electrode Array Recordings" summarizes the
general process:

> "Overall, the primary processing pipeline of each spike sorting algorithm is akin as follows:
    1) Retrieve raw extracellular recordings;
    2) Preprocess raw extracellular recordings;
    3) Detect and align spikes from the voltage trace;
    4) Extract spike features;
    5) Cluster spikes;
    6) Generate spike templates; 
    7) Reconstruct extracellular potentials and spike trains.
"

The general purpose of that paper was to provide an analysis of different spike sorting algorithms using some simulated
recordings with known ground truths. As a result, one could identify and compare the different biases present in the 
different algorithms to be able to make a better informed decision on which one to use, as well as what areas could be 
improved upon for future.

Many different findings were reported, such as the Kilosort Family of sorting algorthms being much more biased towards 
"oversplitting", resulting in more false positives, yet higher specificity. Other findings include things such as ground truths with high SNR units are 
more likely to be detected across different algorithms, and that faster drifting causes deterioration of data. It does emphasize
that each algorithm has its uses, there is no one size fits all perfect algorithm. Depending on the experiment and the data,
you may purposely select an algorithm biased towards one thing, and for a different experiment choose one biased in the opposite direction.

In the end, this paper does exactly as the title describes. It analyzes and compares different spike sorting algorithms.

### References 
C. Bao and A. Charles, "Comparing Spike Sorting Algorithms on Simulated Extracellular Multi-Electrode Array Recordings," 2023 IEEE International Conference on Bioinformatics and Biomedicine (BIBM), Istanbul, Turkiye, 2023, pp. 3280-3287, doi: 10.1109/BIBM58861.2023.10385769.