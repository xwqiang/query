import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import org.nutz.http.Http;

public class Queries {

    public static void main(String[] args) {
        String query = Http.get("http://localhost:8084/auth.html").getContent();
        System.out.println(parse(query));
    }

    @SuppressWarnings("rawtypes")
    public static Query parse(String queryString) {
        if (queryString.equals("{}")) {
            return null;
        }
        if (queryString.startsWith("\"")) {
            return new Value(queryString.substring(1, queryString.length() - 1));
        }
        Map json = new Gson().fromJson(queryString, Map.class);
        Query[] queryWrapper = new Query[1];
        buildQuery(json, queryWrapper);
        return queryWrapper[0];
    }

    @SuppressWarnings("rawtypes")
    private static void buildQuery(Object o, Query[] queryWrapper) {
        if (o instanceof Map) {
            Map map = (Map) o;
            Entry e = (Entry) map.entrySet().iterator().next();
            if (e.getKey().equals("not")) {
                Query[] subWrapper = new Query[1];
                buildQuery(e.getValue(), subWrapper);
                queryWrapper[0] = subWrapper[0].not();
            } else if (e.getKey().equals("and") || e.getKey().equals("or")) {
                Query[] leftWrapper = new Query[1];
                Query[] rightWrapper = new Query[1];
                Iterator listIter = ((List) e.getValue()).iterator();
                buildQuery(listIter.next(), leftWrapper);
                buildQuery(listIter.next(), rightWrapper);
                if (leftWrapper[0] == null) {
                    queryWrapper[0] = rightWrapper[0];
                } else if (rightWrapper[0] == null) {
                    queryWrapper[0] = leftWrapper[0];
                } else {
                    String op = (String) e.getKey();
                    if (op.equals("and")) {
                        queryWrapper[0] = new And(leftWrapper[0], rightWrapper[0]);
                    } else if (op.equals("or")) {
                        queryWrapper[0] = new Or(leftWrapper[0], rightWrapper[0]);
                    } else {
                        throw new IllegalArgumentException(op);
                    }
                }
            }
        } else {
            queryWrapper[0] = new Value((String) o);
        }
    }

    public static interface Query {

        Query not();

    }

    public static class And implements Query {

        private final Query a;
        private final Query b;

        public And(Query a, Query b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Query not() {
            return new Or(a.not(), b.not());
        }

        @Override
        public String toString() {
            return "(" + a + " and " + b + ")";
        }

    }

    public static class Or implements Query {

        private final Query a;
        private final Query b;

        public Or(Query a, Query b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Query not() {
            return new And(a.not(), b.not());
        }

        @Override
        public String toString() {
            return "(" + a + " or " + b + ")";
        }

    }

    public static class Value implements Query {

        private final String value;

        public Value(String value) {
            this.value = value;
        }

        @Override
        public Query not() {
            if (value.endsWith("!")) {
                return new Value(value.substring(0, value.length() - 1));
            } else {
                return new Value(value + "!");
            }
        }

        @Override
        public String toString() {
            return "\"" + value + "\"";
        }

    }

}