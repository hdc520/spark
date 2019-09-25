from pyspark import SparkContext
sc=SparkContext('local','union')
d1=[('k1',1),('k2',2),('k3',3)]
d2=[('k1',3),('k2',4),('k3',8)]
rdd1=sc.parallelize(d1)
rdd2=sc.parallelize(d2)
rdd=rdd1.union(rdd2)
rdd.foreach(print)
RDD=rdd.reduceByKey(lambda x,y:x+y)
print("-------------------------------")
RDD.sortBy(keyfunc=(lambda x:x[1]),ascending=False).foreach(print)
