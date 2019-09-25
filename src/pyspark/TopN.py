from pyspark import SparkContext
sc=SparkContext('local','TopN')
nums=[2,6,9,7,4,6,8]
numRDD=sc.parallelize(nums)

print('----------------take--------------')
firstNum=numRDD.take(2)		#list
print(firstNum)

print('----------------TopN默认为降序--------------')
max=numRDD.top(1)	#max类型为list[9]
print(type(max))

Top3=numRDD.top(3,key=lambda x:-x)
print(Top3)

print("---------------takeOrdered默认为升序--------")
min=numRDD.takeOrdered(1)
print(type(min))	#list

min3=numRDD.takeOrdered(3)
print(min3)

Top4=numRDD.takeOrdered(4,key=lambda x:-x)
print(Top4)