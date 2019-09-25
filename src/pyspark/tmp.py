from pyspark import SparkContext
sc=SparkContext('filter')
num=[1,5,9,2,4,6,3,8,0]
numRDD=sc.parallelize(num,2)
resRDD=numRDD.filter(lambda x:x%2==0).collect()
print(type(resRDD),resRDD)