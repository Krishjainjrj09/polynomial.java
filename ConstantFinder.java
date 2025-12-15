import java.io.FileReader;
import java.math.BigInteger;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConstantFinder {
    public static void main(String[] args) throws Exception {

      
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(new FileReader("input.json"));

        JSONObject keys = (JSONObject) json.get("keys");
        int k = Integer.parseInt(keys.get("k").toString());

        BigInteger constant = BigInteger.ONE;

        Iterator<?> itr = json.keySet().iterator();
        while (itr.hasNext()) {
            String key = itr.next().toString();

            if (key.equals("keys")) continue;

            JSONObject obj = (JSONObject) json.get(key);

            int base = Integer.parseInt(obj.get("base").toString());
            String value = obj.get("value").toString();

            BigInteger decodedValue = new BigInteger(value, base);
            constant = constant.multiply(decodedValue);
        }

      
        if (k % 2 != 0) {
            constant = constant.negate();
        }

        
        System.out.println(constant);
    }
}
