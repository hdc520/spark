from pyspark import SparkContext
sc=SparkContext("local","filter")
a=[1,2,3,4,5,6,8,45,67,89,23]
numRDD=sc.parallelize(a)
outRDD=numRDD.filter(lambda x:x%2==1)
outRDD.foreach(print)