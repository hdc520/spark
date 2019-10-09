# print最后默认end来决定什么来结尾
print("hdc",end=",")
print("520",end="\n")

# 条件控制：无&&和||符号，用and和or代替
age=18
if age<=18:
	print("少年")
elif age>18 and age<=36:
	print("青年")
else:
	print("中年")

# 循环语句：python中没有do...while语句
n=10
sum=0
count=1
while count<n:
	sum+=count
	count+=1
print("%d的累加和为%d"%(n,sum))

for i in range(0,10,2):
	if(i==8):
		print(i)
	else:
		print(i,end=",")
print(list(range(5)))
# pass充当占位符

# 迭代器和生成器
# 迭代器是可以记住遍历的位置的对象，迭代器对象从集合的第一个元素开始访问，直到所有的元素被访问完结束
# 迭代器只能往前不会后退有两个基本的方法：iter()和next()
list=[1,2,3,4]
it=iter(list) #创建迭代器对象，迭代器对象可以使用for语句进行遍历
print(next(it))
for x in it:
	print(x,end=" ")
# 创建一个迭代器，把一个类作为迭代器使用需要在类中实现两个方法__iter__()与__next__()

# 生成器，使用yield的函数被称为生成器(generator)，生成器是一个返回迭代器的函数
def fibonaci(n): #生成器函数－斐波那契
	a,b,count=0,1,0
	while True:
		if count>n:
			return
		yield a
		a,b=b,a+b
		count+=1
f=fibonaci(10) #此时f相当于上面的it
for i in f:
	print(i,end=" ")
print()
# 函数：def 函数名（参数列表）:函数体
# 无返回值函数
def hello(str):
	print("hello,%s"%(str))
hello("hdc")
# 返回值函数
def sum(a,b):
	return a+b,1
print(type(sum(1,3)))
# 不需要指定参数顺序,但是需要指定参数
def printInfo(name,age):
	print("姓名：",name)
	print("年龄：",age)
printInfo(age=520,name="hdc")
# 默认参数，若没有使用默认参数，则会使用默认值
def printInfo(name,age=35):
	print("姓名：",name)
	print("年龄：",age)
printInfo(age=520,name="hdc")
printInfo("hdc")
# 不定长参数1，使用*,处理比当初声明时更多的参数，将多余的参数以元组的形式导入
def printInfo(arg1,*VarTuple):
	print(arg1)
	print(VarTuple)
printInfo(1,2,3,4,"5")
# 不定长参数2，使用**,处理比当初声明时更多的参数，将多余的参数以字典Map的形式导入
def printInfo(arg1,**VarDict):
	print(arg1)
	print(VarDict)
printInfo(1,a=2,b=3)
# 参数列表中单独出现*,*后面的参数必须要用关键字传入
def f(a,b,*,c,d):
	return a+b+c+d
# f(1,2,3)错误
print(f(1,2,c=3,d=4))
# 匿名函数，python使用lambda来创建匿名函数
# lambda 只是一个表达式，lambda 函数拥有自己的命名空间，且不能访问自己参数列表之外或全局命名空间里的参数。
sum=lambda x,y:x+y
print(sum(10,20))
print(sum(23,78))