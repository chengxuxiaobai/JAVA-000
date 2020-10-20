import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

	public static void main(String[] args) {
		try {
			Class hello = new HelloClassLoader().findClass("Hello");
			Method helloMethod = hello.getMethod("hello");
			helloMethod.invoke(hello.newInstance());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File helloxlass = new File("C:\\AppData\\bladex\\Test\\src\\Hello.xlass");
		byte[] classByte = new byte[(int)helloxlass.length()];
		FileInputStream in = null;
		try {
			in = new FileInputStream(helloxlass);
			in.read(classByte);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < helloxlass.length(); i++) {
			int num = classByte[i];
			num = 255 - num;
			classByte[i] = (byte) num;
		}
		return defineClass(name, classByte, 0, classByte.length);
	}

}
