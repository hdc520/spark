# python中变量不需要声明，每个变量声明前都必须要赋值，变量赋值后才会被创建
# 赋值
a=b=c=1
print(a,b,c)
a,b,c=1,2.0,"hdc"
print(a,b,c)
# 利用type或者isinstance判断类型，区别在于type不会认为子类是一种父类类型，isinstance认为子类是父类类型
print(type("hdc"))
print(isinstance("hdc",int)) #false

# python３中标准数据类型
# 不可变数据类型：Number,String,Tuple；
# 可变数据类型：List,Set,Dictionary

# Number：int，float，bool，complex；
a,b,c,d=20,1.0,True,4+3j
print(a,b,c,d)
# 数值运算，混合计算时会把整型变换成浮点数
print("加法：",3+4)
print("减法：",3-4)
print("乘法：",3*4)
print("除法得整数：",3//4)
print("除法得小数：",3/4)
print("取模：",3%4)

#元组Tuple解析，与list相似，元素不能修改，且元素写在小括号里
tuple=('abcd',123,2.34,True,'hdc')
tinyTuple=(987,'520')
print(tuple)
print(tuple[1]) #输出第２个元素
print(tuple[1:3]) #左闭右开，(123, 2.34)
print(tuple[1:]) #(2.34, True, 'hdc')
print(tuple*2) #('abcd', 123, 2.34, True, 'hdc', 'abcd', 123, 2.34, True, 'hdc')
print(tuple+tinyTuple) #('abcd', 123, 2.34, True, 'hdc', 987, '520')
print(tuple[1:4:2]) #其中２表示步长和从左往右的方向，(123, True)
print(tuple[-1::-1]) #其中-1表示步长和从右往左的方向，('hdc', True, 2.34, 123, 'abcd')
# 创建只有一个元素的元组t=(1,)必须要加逗号，不然会被识别为int型

# List解析，list其实跟String区别不大，主要区别在于list可变，String不可变
list=['abcd',123,2.34,True,'hdc']
tinylist=[987,'520']
print(list)
print(list[1]) #输出第２个元素
print(list[1:3]) #左闭右开，[123, 2.34　
print(list[1:]) #[2.34, True, 'hdc']
print(list*2) #['abcd', 123, 2.34, True, 'hdc', 'abcd', 123, 2.34, True, 'hdc']
print(list+tinylist) #['abcd', 123, 2.34, True, 'hdc', 987, '520']
print(list[1:4:2]) #其中２表示步长和从左往右的方向，[123, True]
print(list[-1::-1]) #其中-1表示步长和从右往左的方向，['hdc', True, 2.34, 123, 'abcd']
# 删除列表元素
del list[2]
# 嵌套列表
a=['a','b','c']
n=[1,2,3]
x=[a,n]
print(x[1])
print(x[0][1])
# 列表常用方法：len(list),max(list),list(Tuple)将元组转换为列表
# 			　list.append(elem)添加元素，list.count(elem)判断某个元素出现的次数
# 列表推导式：
a=[x for x in "hahahdc520" if x not in "hdc"]

# Set解析，可以使用{}或者set()函数创建，空集必须用set()而不是{ },因为{ }用来创建空字典
student={'Tom', 'Jim', 'Mary', 'Tom', 'Jack', 'Rose'}
#重复的元素会被自动去掉
print(student) #{'Jim', 'Rose', 'Mary', 'Jack', 'Tom'}
#set的集合运算
a={'Tom', 'Jim', 'Mary'}
b={'Mary', 'Tom', 'Jack', 'Rose'}
print(a-b) #a和b的差集，{'Jim'}
print(a|b) #a和b的并集，{'Jim', 'Mary', 'Rose', 'Tom', 'Jack'}
print(a&b) #a和b的交集，{'Mary', 'Tom'}
print(a^b) #a和b中并不存在的元素，{'Jim', 'Rose', 'Jack'}
#集合推导式：
a={x for x in "hahahdc520" if x not in "hdc"} 
# 基本操作：s.add(x)添加元素，s.update([3,5],[5,6])
		# s.remove(a)移除元素a，没有则会报错；s.discard(b)移除元素b，没有不会报错
		# len(s)求集合长度

# map解析，使用{}标识，是一个无序的键值对即(key,value)的集合
dict={}
dict['hdc']=520
dict['school']="BJUT"
tinyDict={'name':'hdc',"age":24}
print(dict['hdc']) #520
print(dict) #{'hdc': 520, 'school': 'BJUT'}
print(dict.keys()) #dict_keys(['hdc', 'school'])
print(dict.values()) #dict_values([520, 'BJUT'])
# dict的遍历
# 遍历key
for k in dict.keys():
	print("--- ",k)
# 遍历value
for v in dict.values():
	print("---",v)
# 遍历k,v
for k,v in dict.items():
	print(k,v)

# 判断元素是否在以上基本类型中的通用模板
if 123 in list:
	print("YES")
else:
	print("No")

if 123 not in list:
	print("YES")
else:
	print("No")
