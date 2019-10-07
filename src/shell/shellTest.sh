#!/bin/sh

# 定义变量时不需要加$，使用变量时，一定要加$
Script="and you?"
str="hdc520"
a=(Ada Coffe Action Java)
# for循环
for skill in Ada Coffe Action Java
do
    echo "I am good at $skill $Script"
done

# while 循环
count=1
while (($count<=5))
do
	echo $count
	let "count++"
done

#until 循环执行一系列命令直至条件为 true 时停止。
count=1
until [ ! $count -lt 4 ] 
do
	echo $count
	let "count++"
done

# case 语句
aNum=1
case $aNum in
	1) echo "选择１"
	;;
	2) echo "选择２"
	;;
	*) echo "没有选择"
	;;
esac

# 设置只读变量，关键词为readonly
write_name="hdc"
readonly write_name
# write_name="hdc520" 编译报错

# 利用unset删除变量,unset不能删除只读变量
echo $str
unset str
echo $str

# shell变量分为局部变量，环境变量，shell变量
# 局部变量：脚本和命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量
# 环境变量：所有程序都能访问环境变量，shell脚本也可以定义环境变量
# shell变量：由shell程序设置的特殊变量，shell变量中有一部分是环境变量，有一部分是局部变量

# shell中单引号，双引号，反引号,反斜杠的区别
# 单引号''：单引号里任何字符串都会原样输出,反斜杠\例外
test_str='hdc$write_name'
echo $test_str
test_str='hdc$write_name\520' #\520 被转义成P
echo $test_str

#双引号""：当shell碰到第一个单引号时，会忽略掉其后直到右引号的大多数字符，如$,\,`不会被忽略
test_str="hdc520$test_str" #此时test_str会接受最新的变量
echo $test_str
test_str="hdc520\520"
echo $test_str

# 反引号：将命令插入到另一条命令中
echo today is `date`

#反斜杠\：转义字符

# 字符串
your_name="hdc"
your_school="BJUT"

# 拼接字符串
your_Info=$your_name$your_school
echo $your_Info
echo "hello,$your_name"

# 获取字符串长度
string="hdc520"
echo ${#string}

# 提取子字符串
string="hdc I love you"
echo ${string:2:3}

# 查找子串
string="hdc I love you"
echo `expr index "$string" Ie`

# shell数组：只有一维数组
array_name=("hdc" 24 8 'love')
echo ${array_name[0]}
# 获取所有元素
echo ${array_name[*]}
# 或者数组长度
echo ${#array_name[*]}
# 获取数组单个元素个数
echo ${#array_name[1]}

# shell传递参数
echo "shell传递参数实例"
echo "执行的文件名：$0"
echo "传递参数的个数：$#"
echo "显示所有参数$*"
echo "第一个参数为：$1"
echo "第二个参数为：$2"
echo "第三个参数为：$3"

# shell运算，等号＝两边不能有空格
# 原生bash不支持简单的数学运算，但是可以通过awk，epr来实现，或者$[a+b]
echo "-------shell运算-------"
a=10
b=20

echo $[a+b]
val=`expr 2 + 2`
echo "a + b : $val"

val=`expr 3 - 1`
echo "a - b : $val"

val=`expr 3 \* 1`
echo "a * b : $val"

val=`expr 8 / 4`
echo "a / b : $val"

# 判断语句

if [[ $a == $b ]]; then
	echo "YES"
else
	echo "NO"
fi

# 比较大小
if [ $a -eq $b ]
then
	echo "a等于b"
else
	echo "a不等于b"
fi

if [ $a -eq $b ]
then
	echo "等于"
elif [ $a -gt $b ]
then
	echo "大于"
else
	echo "小于"
fi

# 布尔运算符
a=10
b=20

if [ $a == $b ]
then
	echo "!="
elif [ $a -lt $b -o $b -gt 15 ] # -o 为或运算符
then 
	echo "-o"
elif [ $a -lt 9 -a $b -lt 10]　# -a 为与运算符
then
	echo "-a"
else
	echo "NO"
fi

if [ $a == $b ]
then
	echo "不等于"
elif [[ $a -lt $b || $b -gt 15 ]]
then 
	echo "||"
elif [[ $a -lt 9 && $b -lt 10 ]]
then
	echo "&&"
else
	echo "NO"
fi

# 字符串运算符
# = 检测字符串是否为相等，!=　检测字符串是否不相等，-z 检测字符串长度是否为０，为０返回true
# -n 检测字符串长度是否为０，不为０返回true，$　检测字符串是否为空，不为空返回true
a="hdc"
b="hdc520"
if [ -z $a ]
then
	echo "-z 检测字符串长度是否为０，为０返回true"
elif [ -n $b ]
then 
	echo "-n 检测字符串长度是否为０，不为０返回true"
else
	echo "其他"
fi

if [ $a ]
then
	echo "$检测字符串是否为空，不为空返回true"
else
	echo "$检测字符串是否为空，为空返回false"
fi

# shell的输入
echo "请输入name"
# read name
echo $name

# echo "hdc\n520" sh命令下会换行，bash命令下不会换行
# echo -e "hdc\n520" 任何命令下都会换行
# echo "hdc"　>　/data 覆盖写入
# echo "hdc520">> /data 追加写入

# 输出命令printf跟C/C++中类似，只是没有括号，break和continue
printf "hdc\n"

# test命令，本人将其看成[]
num1=100
num2=200
if [ $num1 -eq $num2 ]
then
	echo "两个数相等"
else
	echo "两个数不相等"
fi

# shell函数
# 无返回值函数
demoFun(){
	echo "这是我的第一个函数"
}
demoFun
# 返回值函数
funReturn(){
	echo "相加运算"
	aNum=10
	bNum=11
	return $[aNum*bNum]
}
funReturn
echo "两树之和为$?"

# 传参到函数
funParam(){
	echo "第一个参数为$1"
	echo "第二个参数为$2"
	echo "第11个参数为${11}"
	echo "第12个参数为${12}"
	echo "参数个数有$#个"
	echo "输出所有参数$*"
}
funParam 1 2 3 4 5 6 7 8 9 11 12 13

# 输入输出重定向
# 输出重定向其实就是　echo $hdc > data 或者　echo $hdc >> data