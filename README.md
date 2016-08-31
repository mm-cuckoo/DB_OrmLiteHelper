### 介绍 
封装后的工程方便对OrmLite 的使用
### 集成方法
##### 创建 Helper 类，继承 OrmDatabaseHelper 类
1. 实现 createTables(List tables)
    > 方法说明:  
    > 在该方法中设置数据库中对用的表的映射Bean,tables 是一个集合，通过该集合设置数据库中对应表映射的 Bean  
  

```
    @Override
    public void createTables(List tables) {
        tables.add(UserInfo.class);
        tables.add(BookInfo.class);
    }
```

2. 实现 updateTables(List tables)
    > 方法说明:  
    > 在该方法中设置更新数据库中对用的表的映射Bean，tables 是一个集合，通过该集合设置数据库中对应更新表映射的 Bean   


```
    @Override
    public void updateTables(List tables) {
        tables.add(UserInfo.class);
        tables.add(BookInfo.class);
    }
```

#### 示例代码：

```
public class MyDbHelper extends OrmDatabaseHelper {

    private static final String DEF_DB_NAME = "def_db";
    private static final int DB_VERSION = 2;


    public MyDbHelper(Context context) {
        super(context, DEF_DB_NAME, null, DB_VERSION);
        /**
         * 参数说明：
         * context：上下文。
         * databaseName： 数据库名。
         * factory： 游标实例，多数时候设置成NULL。
         * databaseVersion：数据库版本，当数据库版本升高时，会调用onUpgrade（）方法。
         */
    }

    @Override
    public void createTables(List tables) {
        tables.add(UserInfo.class);
        tables.add(BookInfo.class);
    }

    @Override
    public void updateTables(List tables) {
        tables.add(UserInfo.class);
        tables.add(BookInfo.class);
    }
}

```

#### 创建 Dao 类，继承 OrmDaoUtils类
1. 实现 getHelper() 
    > 方法说明：   
    > 该方法 OrmDatabaseHelper 实例对象。


```
public class MyDao extends OrmDaoUtils {
    public MyDao(Class cls) throws SQLException {
        super(cls);
    }

    @Override
    protected OrmDatabaseHelper getHelper() {
        //此处的Context 建议使用Application 中的Context
        return new MyDbHelper(AppApplication.sCtx);
    }
}
```
### Dao 使用
以 UserInfo 这个 Bean 为示例。

```
 private MyDao mDao;
```
##### 插入操作
1.  单条插入
2.  批量插入
```
//这是一个批量插入的操作
List<UserInfo> users = new ArrayList<UserInfo>();
for(int i = 1 ; i < 100 ; i ++){
    UserInfo info = new UserInfo("张三" + i,"beijing");
    users.add(info);
}

try {
    mDao = new MyDao(UserInfo.class);
    int insertNumber = mDao.insert(users);
    //insertNumber 是插入成功的条数
} catch (SQLException e) {
    e.printStackTrace();
}

```
3.  批量事物插入

##### 修改操作
1. 单条修改
2. 多条件修改单字段
3. 多条件修改多字段

##### 删除操作
1. 单条删除
2. 批量删除
3. 根据ID删除
4. 事物批量删除
5. 事物通过ID批量删除

##### 查询操作
1. 全部查询
2. 单条查询通过ID
3. 条件查询
4. 升序、降序查询
