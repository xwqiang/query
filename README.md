1. 表达式转化为json
```

Query not = new Not(new UserInfo(1L, "bob"));

Query or = new Or.Builder(new UserInfo(2L, "zhou"), new UserInfo(3L, "qiang")).build();

Query and = new And(not, or);

System.out.println(new Not(and).toJson());

```
2. json 转化为表达式

```
        
String query = "{\"and\":[{\"not\":{\"id\":12,\"name\":\"test\"}},{\"or\":[{\"id\":12,\"name\":\"bob\"},{\"id\":12,\"name\":\"zhouzhou\"}]}]}";

Query q = Query.fromJson(query, UserInfo.class);

System.out.println(q.toJson());

```

3. 布尔值判断

```
    public static void main(String[] args) {

        System.out.println(simpleQuery().bool());

        System.out.println(complexQuery().bool(new UserBooleanConvertor()));

    }

    public static Query simpleQuery() {

        Query not = new Not.Builder("true").build();

        Query or = new Or.Builder("true", "false").build();

        Query and = new And(not, or);

        return and;
    }

    public static Query complexQuery() {

        UserInfo zhou = new UserInfo(1L, "zhou");

        UserInfo bob = new UserInfo(12L, "bob");

        Query not = new Not.Builder(zhou).build();

        Query or = new Or.Builder(zhou, bob).build();

        Query and = new And(not, or);

        return and;
    }
  
```