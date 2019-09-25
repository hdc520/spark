from pyspark import SparkContext
sc=SparkContext('local','combineByKey')
Input=[("k1", 1), ("k1", 2), ("k1", 3), ("k1", 4), ("k1", 5), 
       ("k2", 6), ("k2", 7), ("k2", 8), 
       ("k3", 10), ("k3", 12)]
rdd=sc.parallelize(Input)
sumCount=rdd.combineByKey(
	(lambda x:(x,1)),	#类似于初始化
	(lambda x,y:(x[0]+y,x[1]+1)),#初始化后将每个分区内的value求值
	(lambda x,y:(x[0]+y[0],x[1]+y[1]))#将不同分区中的value合并，其中x,y均为list
	)
sumCount.foreach(print)
print("------------------------------------")
avg1=sumCount.mapValues(lambda x:x[0]/x[1])
avg1.foreach(print)
print("------------------------------------")
avg2=sumCount.map(lambda x:(x[0],x[1][0]/x[1][1]))
avg2.foreach(print)

data = [
         ("A", 2.), ("A", 4.), ("A", 9.),
         ("B", 10.), ("B", 20.),
         ("Z", 3.), ("Z", 5.), ("Z", 8.), ("Z", 12.)
        ]
InputRDD=sc.parallelize(data)
sumRDD=InputRDD.combineByKey(
	(lambda x:(x,1)),
	(lambda x,y:(x[0]+y,x[1]+1)),
	(lambda x,y:(x[0]+y[0],x[1]+y[1]))
	)
sumRDD.foreach(print)
