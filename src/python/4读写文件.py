# 读写文件格式：
f=open(filename,mode)
# filename:文件路径＋文件名；mode：决定打开文件的模式即只读r，写入w，追加a；默认为只读
f.write("hdc") #文件写入
f.read() #读取全部文件内容
f.read(size) #读取size大小的文件
f.readline() #读取一行
f.readlines() #返回文件中包含的所有行
f.tell() #返回文件对象当前所处的位置
f.seek(x,0) #从起始位置开始移动x个字符
f.seek(x,1) #从当前位置往后移动x个字符
f.seek(-x,2) #从文件结尾往前移动x个字符
