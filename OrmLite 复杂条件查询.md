###### 注：本片文章参考于http://blog.csdn.net/industriously/article/details/50790624

### OrmLite 三篇文章
1. OrmLite 入门使用
2. OrmLite 复杂条件查询
3. OrmLite 封装框架使用

### 简介
这篇文章主要介绍 OrmLite 框架中使用到的一些查询方法.


### WEHRE子句
在SQL语句中，经常会用到where语句，where 进行条件筛选。
dao.queryBuilder.()where()方法返回一个where对象，where中提供了很多方法来进行条件筛选,下边逐个讲where中的方法。

#### 方法 ：eq(columnName,value)    等于（=）equals

使用示范：mDao.queryBuilder().where().eq("id", 2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` = 2


#### 方法 ：lt(columnName,value)    小于（<） less than
使用示范：mDao.queryBuilder().where().lt("id", 2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` < 2

#### 方法 ：gt(columnName,value)    大于（>） greater than
使用示范：mDao.queryBuilder().where().gt("id", 2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` > 2

#### 方法 ：ge(columnName,value)    大于等于（>=）greater-than or equals-to
使用示范：mDao.queryBuilder().where().ge("id", 2).query();     
对应SQL：SELECT * FROM `t_person` WHERE `id` >= 2

#### 方法 ：le(columnName,value)    小于等于（<=）less than or equals-to
使用示范：mDao.queryBuilder().where().le("id", 2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` <= 2

#### 方法 ：ne(columnName,value)    不等于（<>）not-equal-to
使用示范：mDao.queryBuilder().where().ne("id", 2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` <> 2


#### 方法 ：in(columnName,object…)     在指定列中匹配object数组所对应的值，返回匹配到的结果行集合,in还有几个重载方法，需要的话可以去看文档或源码
使用示范：mDao.queryBuilder().where().in("id", 1，2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` IN (1，2 )


#### 方法 ：notIn(columnName,object…)     在指定列中匹配object数组所对应的值，返回没有匹配到的结果行集合notIn还有几个重载方法，需要的话可以去看文档或源码
使用示范：mDao.queryBuilder().where().notIn("id",1,2).query();    
对应SQL：SELECT * FROM `t_person` WHERE `id` NOT IN (1 ,2 )

#### 方法 ：like(columnName,pattern)    使用%通配符来匹配，指定行数据，返回匹配到的结果
使用示范：mDao.queryBuilder().where().like("LastName", "A%").query(); 匹配A开头的LastName   
mDao.queryBuilder().where().like("LastName", “%s").query(); 匹配s结尾的LastName   
mDao.queryBuilder().where().like("LastName", “%art%").query(); 匹配中间为art的LastName   
对应SQL：SELECT * FROM \`t_person\` WHERE \`LastName\` LIKE 'A%'


#### 方法 ：between(columnName,low,high)    获取指定范围内的结果
使用示范：mDao.queryBuilder().where().between("id", 1, 2).query();   获取id是1到2之间的结果  
对应SQL：SELECT * FROM \`t_person\` WHERE \`id\` BETWEEN 1 AND 2


#### 方法and()，or()用来组合上述where子语句。进行与，或操作。

#### 方法 ：and()    where子句与操作
使用示范：mDao.queryBuilder().where().lt("id", 3).and().gt("id", 1).query();  
对应SQL：SELECT * FROM \`t_person\` WHERE (\`id\` < 3 AND \`id\` > 1 )

#### 方法 ：or()    where子句或操作
使用示范：mDao.queryBuilder().where().eq("id", 1).or().eq("id", 2).query();   
对应SQL：SELECT * FROM \`t_person\` WHERE (\`id\` = 1 OR \`id\` = 2 )

### ORDER BY
根据指定列名排序，降序，升序  
使用示范：mDao.queryBuilder().orderBy("id", false).query(); //参数false表示降序，true表示升序。  
对应SQL：SELECT * FROM \`t_person\` ORDER BY \`id\` DESC（降序）

### DISTINCT
过滤指定列不重复数据行，重复的只返回一次。   
使用示范：mDao.queryBuilder().selectColumns("City").distinct().query();  
对应SQL：SELECT DISTINCT \`City\` FROM \`t_person\` 

### GROUP BY
按照指定列分组  
使用示范：mDao.queryBuilder().groupBy("city").query();  
对应SQL：SELECT * FROM `t_person` GROUP BY `city`

### offset Limit
offset跳过指定的行数  
limit限制获取指定行数   
使用示范：mDao.queryBuilder().offset(2).limit(2).query();  可以用来分页   
对应SQL：SELECT * FROM `t_person` LIMIT 2 OFFSET 2

### Having
等同于sql中的Having，针对分组数据，进行聚合函数（SUM, COUNT, MAX, AVG）运算。  
使用示范：mPersonList = mDao.queryBuilder().groupBy("City").having("SUM(id)>4").query()  
对应SQL：SELECT * FROM `t_person` GROUP BY `City` HAVING SUM(id)>4

### countOf
返回查询结果的总数   
使用示范：mDao.queryBuilder().countOf()  
对应SQL：SELECT COUNT(*) FROM `t_person`

### iterator
返回一个结果集的迭代器。    
使用示范：Iterator<Person> iterator = mDao.queryBuilder().iterator();   
queryForFirst
返回所有行的第一行。
使用示范：mDao.queryBuilder().queryForFirst();
