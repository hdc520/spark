查询语言    HQL     SQL
数据存储位置  HDFS    Raw Device或者Local FS
数据格式    用户定义    系统决定
数据更新    不支持     支持
索引  无   有
执行  Mapreduce   Executor
执行延迟    高   低
可扩展性    高   低
数据规模    大   小

set character_set_client=utf8;

首先登陆mysql的交互shell，输入下面的命令查看当前数据库的编码方式
show variables like '%character%';

    输入下面的命令，打开第一个配置文件

    sudo vim /etc/mysql/conf.d/mysql.cnf

    在 [mysql] 标签的下一行添加下面的配置

    default-character-set=utf8

    输入下面的命令，打开第二个配置文件

    sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf

    找到 [mysqld] 标签，在其下一行添加下面的配置

    character-set-server=utf8

    配置文件修改成功之后，输入下面的命令重启mysql服务

    sudo service mysql restart

    重启之后再去查看数据库的默认编码方式

show variables like '%character%';

修改列名
alter table score change column scoreore SId s_id;

Mysql 版本5.7.20

where中不能直接使用字段的别名，group by、having、order by 可以直接使用

Oracle 版本12c　where、group by、having中不能直接使用字段的别名，order by可以直接使用

Hive 版本1.3.0

where、group by、having中不能直接使用字段的别名，order by可以直接使用

select s_id,group_concat(c_id order by c_id separator '|') from score group by s_id;
(1).Hive不支持join的非等值连接,不支持or
分别举例如下及实现解决办法。
  不支持不等值连接
       错误:select * from a inner join b on a.id<>b.id
       替代方法:select * from a inner join b on a.id=b.id and a.id is null;
 不支持or
       错误:select * from a inner join b on a.id=b.id or a.name=b.name
       替代方法:select * from a inner join b on a.id=b.id
                union all
                select * from a inner join b on a.name=b.name
  两个sql union all的字段名必须一样或者列别名要一样。
        
(2).分号字符:不能智能识别concat(‘;’,key)，只会将‘；’当做SQL结束符号。
    •分号是SQL语句结束标记，在HiveQL中也是，但是在HiveQL中，对分号的识别没有那么智慧，例如：
        •select concat(key,concat(';',key)) from dual;
    •但HiveQL在解析语句时提示：
        FAILED: Parse Error: line 0:-1 mismatched input '<EOF>' expecting ) in function specification
    •解决的办法是，使用分号的八进制的ASCII码进行转义，那么上述语句应写成：
        •select concat(key,concat('\073',key)) from dual;

(3).不支持INSERT INTO 表 Values（）, UPDATE, DELETE等操作.这样的话，就不要很复杂的锁机制来读写数据。
    INSERT INTO syntax is only available starting in version 0.8。INSERT INTO就是在表或分区中追加数据。

(4).HiveQL中String类型的字段若是空(empty)字符串, 即长度为0, 那么对它进行IS NULL的判断结果是False，使用left join可以进行筛选行。

(5).不支持 ‘< dt <’这种格式的范围查找，可以用dt in(”,”)或者between替代。

(6).Hive不支持将数据插入现有的表或分区中，仅支持覆盖重写整个表，示例如下：
    INSERT OVERWRITE TABLE t1 SELECT * FROM t2;
    
(7).group by的字段,必须是select后面的字段，select后面的字段不能比group by的字段多.
    如果select后面有聚合函数,则该select语句中必须有group by语句
    而且group by后面不能使用别名
    
(8).hive的0.13版之前select , where 及 having 之后不能跟子查询语句(一般使用left join、right join 或者inner join替代)

(9).先join(及inner join) 然后left join或right join

(10).hive不支持group_concat方法,可用 concat_ws('|', collect_set(str)) 实现

(11).not in 和 <> 不起作用,可用left join tmp on tableName.id = tmp.id where tmp.id is null 替代实现
... ...
