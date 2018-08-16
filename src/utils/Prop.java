package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Prop {

    private static class Holder {
        private static final Prop INSTANCE = new Prop();
    }

    private Properties properties = null;

    private Prop() {
        try {
            // Class<?> clazz = Prop.class;
            // クラスパス経由(例：srcのした)
            // InputStream inputestream = clazz.getClassLoader().getResourceAsStream("config.properties");
            // スタティック経由(クラスと同じフォルダ)
            // InputStream inputestream = clazz.getResourceAsStream("config.properties");
            // ファイル(例：projectのした)
            InputStream inputestream = new FileInputStream(new File("config.properties"));
            properties = new Properties();
            properties.load(inputestream);
        } catch (Throwable t) {
            // properties = null;
            t.printStackTrace();
        }
    }

    public static final Prop me() {
        return Holder.INSTANCE;
    }

    public String get(String key) {
        return this.properties.getProperty(key);
    }

}
