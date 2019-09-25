from pyspark import SparkContext
sc=SparkContext('local','cartesian')
a=[('k1','v1'),('k2','v2')]
b=[('k3','v3'),('k4','v4'),('k5','v5')]
A=sc.parallelize(a)
B=sc.parallelize(b)
rdd=A.cartesian(B)
rdd.foreach(print)