1. 表达式转化为json
```
Query not = new Not(new Value(new UserInfo()));

Query or = new Or(new Value(new UserInfo()), new Value(new UserInfo()));

Query and = new And(not, or);

Visitor<Object> v = new PrintVisitor();

Object value = and.accept(v);

System.out.println(GsonUtil.toJson(value));
```
2. json 转化为表达式

```
String query = "{\"and\":[{\"not\":{\"id\":12,\"name\":\"test\"}},{\"or\":[{\"id\":12,\"name\":\"bob\"},{\"id\":12,\"name\":\"zhouzhou\"}]}]}";

ParseClient parseClient = new ParseClient();

Query q = parseClient.parse(query, UserInfo.class);

Object json = q.accept(new PrintVisitor());

System.out.println(json);
```

3. 布尔值判断

```
Query not = new Not.Builder("true").build();

Query or = new Or.Builder("true","false").build();

Query and = new And(not, or);

Visitor<Boolean> v = new BooleanVisitor();

boolean value = and.accept(v);

System.out.println(value);
```