from pyspark import SparkContext
sc=SparkContext('local','Join')
R=sc.textFile("/data/R.txt")
S=sc.textFile("/data/S.txt")
r=R.map(lambda line:line.split(','))
s=S.map(lambda line:line.split(','))
RjoinS=r.join(s)
RjoinS.foreach(print)