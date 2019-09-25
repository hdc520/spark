from pyspark import SparkContext
sc=SparkContext('local','bigrams')
InputRDD=sc.textFile('/data/word')
InputRDD.foreach(print)
bigrams=InputRDD.map(lambda s:s.split(" ")).flatMap(lambda s: [((s[i],s[i+1]),1) for i in range (0, len(s)-1)])
bigrams.foreach(print)