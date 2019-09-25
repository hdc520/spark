from pyspark import SparkContext
a=[1,2,3,4]
sc=SparkContext('local','fold')
rdd=sc.parallelize(a)
rdd1=rdd.fold((1),(lambda x,y:x*y))
print(rdd1)

rdd2=rdd.reduce(lambda x,y:x+y)
print(rdd2)
