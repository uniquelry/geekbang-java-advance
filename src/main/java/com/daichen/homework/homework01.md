# 第一题

> 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件

```java
/**
 * @author daichen
 * @version v1.0
 * @create 2021/3/20 2:50 下午
 * @description 自定义 ClassLoader
 */
public class CustomClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            CustomClassLoader loader = new CustomClassLoader();
            Class<?> aClass = loader.findClass("Hello");
            Object obj = aClass.getDeclaredConstructor().newInstance();
            Method method = aClass.getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] classBytes = getClassBytes();
        inverseCode(classBytes);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    /**
     * x = 255 - x
     *
     * @param bytes
     * @return
     */
    private void inverseCode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
    }

    /**
     * 读取 .class 文件获取字节数组
     *
     * @return
     */
    private byte[] getClassBytes() {
        // 文件路径
        String filePath = "/Users/liuruiyu/Desktop/study_notes/geek_college/homework/Hello/Hello.xlass";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
            // 获取文件字节大小
            int length = fis.available();
            // 读取文件字节到一个数组中
            byte[] input = new byte[length];
            fis.read(input);
            return input;
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        } finally {
            // 关闭输入流
            closeFis(fis);
        }
    }

    private void closeFis(FileInputStream fis) {
        if (fis == null) {
            return;
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

# 第二题

> 画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系

![img.png](week01/JavaRelationImage.png)
