
import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager(null);
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        String curvePath = "Curve.js";
        String path = Main.class.getClassLoader().getResource("").getPath();

        FileReader reader = null;
        try {
            reader = new FileReader(path + curvePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            engine.eval(reader);
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }

        Object Curve = engine.get("BybitWeb3Util.Curve");

        if (engine instanceof Invocable) {
            Invocable invoke = (Invocable) engine;
            try {
                Para p = new Para();
                p.setUrl("https://mainnet.infura.io/v3/9aa3d95b3bc440fa88ea12eaa4456161");
                invoke.invokeMethod(Curve, "init", "JsonRpc", p);
                Object tvl = invoke.invokeMethod(Curve, "getTVL");
                System.out.println(tvl);
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

        }

    }
}