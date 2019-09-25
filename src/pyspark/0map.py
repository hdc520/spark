from pyspark import SparkContext
sc=SparkContext('local','map')
a=[0,1,2,3,4,5,6,7,8,9]
Input=sc.parallelize(a)
rdd=Input.map(lambda x:x+1)
rdd.foreach(print)