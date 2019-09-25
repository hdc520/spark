from pyspark import SparkContext
sc=SparkContext('local','sort')
InputRDD=sc.textFile("/data/word",2)
rdd=InputRDD.flatMap(lambda x:x.split(' ')).map(lambda x:(x,1)).reduceByKey(lambda x,y:x+y)
rddSort=rdd.sortByKey(False)
rddSort.foreach(print)
print("-------------------------")
rddSort.map(lambda x:(x[0],x[1]+1)).foreach(print)