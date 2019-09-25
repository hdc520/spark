from pyspark import SparkContext
a=[('g1',2),('g2',4),('g3',3),('g4',8)]
sc=SparkContext('local','add_indices')
rdd1=sc.parallelize(a)
rdd1.collect()
rdd2=rdd1.map(lambda x : (x[1],x[0]))
rdd2.foreach(print)
rdd2.collect()
rddSort=rdd2.sortByKey(False)
rddSort.collect()
rddIndices=rddSort.zipWithIndex()
rddIndices.foreach(print)