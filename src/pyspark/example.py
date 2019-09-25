from pyspark import SparkContext
sc=SparkContext('local','test')
textFile=sc.textFile("/data/word")
wordCount=textFile.flatMap(lambda line:line.split(" ")).map(lambda word:(word,1)).reduceByKey(lambda a,b:a+b)
wordCount.saveAsTextFile('/home/hdc/count.txt')