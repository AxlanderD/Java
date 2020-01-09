## MySQL

### 关键字

#### 一些最重要的语法

- select ：选择提取数据
- update ：更新数据
- delete ：删除数据
- insert into ：向数据库插入数据
- create database ：创建数据库
- alter database ：修改数据库
- create table ：创建新表
- alter table ：更改数据库表
- drop table ：删除表
- create index ：创建索引
- drop index ：删除索引
- drop删除结构（删除数据库，数据表，数据字段），delete删除数值
- distinct : 用于列出不同的值，例：`select` `distinct` from 列名1，列名2 from tableName;
<!-- 查询 -->
- `select` 列名1，列名2... from tableName `where` condition `order by` 列名1，列名2 `DESC`(降序排列)/`ASC`(升序排列) 
<!-- 增加 -->
-  `insert into` tablename （列名1，列名2...）
`values` (value1,value2,value3....);
<!-- 修改 -->
- `update` tableName `set` 列名1 = value1，列名2 = value2 `where` condition;
<!-- 删除 -->
- `delete` from tableName `where` condition;
<!-- like子句 -->
- like 子句, % 字符表示任意字符。例如 select * from tableName where name like `%com` 查询记录中以 com 结尾的记录;
通配符 % 表示任意多字符<br>
_ 表示任意单一字符<br>
[^GHF] 通配符,匹配以G、H、F为首的记录
<!-- IN关键字 -->
- `IN` 可以在where子句中规定多个值,例如 `select` * from tableName where name `IN`(value1,value2);  in和=的不同之处在于 in可以选择多个值，而 = 只能选择一个值
<!-- BETWEEN关键字 -->
- `BETWEEN` 用于选取在两个数值范围内的值。例:`select` * from tableName `BETWEEN` value1 `and` value2
<!-- AS关键字 -->
- `AS`，起一个别名。例:`select` 列名1 AS n,列名2 AS m from tableName<br>
```
<!-- 组合列起别名 -->
SELECT name, CONCAT(url, ', ', alexa, ', ', country) AS site_info
FROM Websites;
<!-- 给表起别名 -->
SELECT w.name, w.url, a.count, a.date 
FROM Websites AS w, access_log AS a 
WHERE a.site_id=w.id and w.name="菜鸟教程";
```
<!-- join连接 -->
- 一共有四种连接：1.`inner join`(内联) 2.`left join`(左联) 3.`right join`(右联) 4.`full join`(全连接)；主要是决定左表还是右表为基准表,基准表表长为行数，左表为基准表的时候返回左表所有行，右表没有匹配记录的返回null。
```
select websites.id,websites.`name`,app.web_id,app.app_name
FROM websites
LEFT JOIN
app
ON
websites.id = app.web_id;
```
<!-- union子句 -->
- union 子句,`union`操作用于连接两个以上的 select 语句的结果组合到一个结果中。每张表返回的结果集应该有数目相同的列。仅返回不同的值。将两张表的结果组合起来
```
SELECT country, name FROM Websites
WHERE country='CN'
UNION
SELECT country, app_name FROM apps
WHERE country='CN'
ORDER BY country;
```
<!-- Mysql语法 -->
- `limit` 限制范围 用于规定返回的记录条数

- `insert into` tableNew `select` * from tableOld 从旧表复制到新表或<br>
`insert into` tableNew (列名) `select` 列名 from tableOld 从旧表中复制某些字段到新表中 


- `create` database databaseName;创建数据库
- `create` table tableName(<br>
    字段1 字段类型(size) 约束，<br>
    字段2 字段类型(size) 约束,<br>
    字段3 字段类型(size) 约束<br>
    );创建表<br>
    约束种类： `NOT NULL`（不为空）、`unique`（值独特性）、`primary key`（主键）、`foreign key`（外键）、`check`、`default`(默认值)
- `create index` index_name `ON` table_name (列名) 
- `truncate table` tableName

<!-- MySQL语法 -->
- 通配符 ^



### 术语概念
- 冗余 ：存储两倍数据，相当于是进行数据容灾备份，以效率为代价提升安全性
- 索引 ：相当于是书籍的目录，可以让sql语句更快的找到要找的内容。快速找到特定的内容
- 主键 ：主键在一个表中具有唯一性
- 外键 ：用于关联两个表
- 复合键 ：多个列作为一个索引值