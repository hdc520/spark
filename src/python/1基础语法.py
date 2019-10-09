# 一行写不完，可以用反斜杠\来实现多行语句
string="hdc"+\
"520"
print(string)

string=["hdc","520",
"BJUT"
]
print(type(string))

'''字符串注意事项
python中单引号和双引号作用完全相同
使用三引号可以指定一个多行字符串
转义符'\'在其前面加上r则不会发生转义，如r"this is a line with \n"其中\n会显示，并不是换行
字符串使用+拼接字符串，使用*运算符重复
字符串有两种索引方式，从左往右以０开始，从右往左以-1(倒数第二个)开始，或者说左闭右开
没有字符类型，一个字符就是长度为１的字符串．
字符串截取方式如下：变量名[头下标：尾下标：步长]
'''
str="hdc520,BJUT"
print(str[0:])
print(str[0])
print(str[1:3])
print(str*2)
print(str+"连接字符串")
print(str+"\n换行示例")
print("在字符串前面添加一个 r，表示原始字符串，不会发生转义")
# 字符串格式化输出
print("my name is %s,and is %d"%('hdc',10))
# format的基本使用，大括号里及其里面的字符(俗称格式化字段)将会被format中的参数替换
# 在括号中数字	用于指向传入对象在format中的位置
print("{0}和{1}".format("hdc","520"))
print("{1}和{0}".format("520","hdc"))
# format若使用了关键字参数，那么它们的值会指向使用该名字的参数
print("姓名：{name}，年龄：{age}".format(age="520",name="hdc"))

# input输入
input("请输入你的名字：")
# 同一行利用分号显示多条语句
# print输出是默认换行的，如果要实现end=""则不会换行

# import与from...import的区别
# import是导入所有的模块，如import sys
# from...import导入模块中特定的成员，如from sys argv,path 
# is与==的区别：is用于判断两个变量引用对象是否为同一个，＝＝用于引用变量的值是否相等
# a=[1,2,3],b=a,a is b =>True，a==b =>True
# a=[1,2,3],b=[1,2,3],a is b =>False，a==b =>True
