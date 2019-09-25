from pyspark import SparkContext
sc=SparkContext("local","average")
nums=sc.parallelize([1,2,3,4,5,6,7,8,9,0,10])
sum=nums.map(lambda x:(x,1)).fold((0,0),(lambda x,y:(x[0]+y[0],x[1]+y[1])))
print(sum[0])
avg=sum[0]*1.0/sum[1]
print(avg)