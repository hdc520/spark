from pyspark import SparkContext
sc=SparkContext('local','DNAtest')
ones=sc.textFile("/data/DNA.txt")
rdd=ones.flatMap(lambda x:[(c,1) for c in list(x)])
baseCount=rdd.reduceByKey(lambda x,y:x+y).filter(lambda x:x[1]%2!=0)
baseCount.foreach(print)
print('-------------------------def----------------------------')
def mapper(seq):
	freq=dict()
	for x in list(seq):
		if x in freq:
			freq[x]+=1
		else:
			freq[x]=1
	kv=[(x,freq[x]) for x in freq]
	return kv
RDD=ones.flatMap(mapper)
RDD.foreach(print)
print('-------------------------------------------------------')
RDD.reduceByKey(lambda x,y:x+y).foreach(print)
