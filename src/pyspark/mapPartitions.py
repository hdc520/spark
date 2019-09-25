from pyspark import SparkContext
sc=SparkContext('local','mapPartitions')
nums=[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
numRDD=sc.parallelize(nums,3)
print("numPartitions:",numRDD.getNumPartitions())

def out(iterator):
	for x in iterator:
		print(x)
	print("==========")

numRDD.foreachPartition(out)

def adder(iterator):
	yield sum(iterator)

numRDD.mapPartitions(adder).foreach(print)

print("---------------------min---and---max-----------------------")

def MinAndMax(list):
	min=9
	max=0
	for x in list:
		if(min>x):
			min=x
		if(max<x):
			max=x
	return (min,max)
minmaxList=numRDD.mapPartitions(MinAndMax).collect()
print(minmaxList)

RDD=numRDD.filter(lambda x:
	min=9
	max=0
	if(min>x):
		min=x
	if(max<x):
		max=x
	)
print(RDD.collect())
