package com.imyeego.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ClassUtil {

    //系统文件分隔符
    private static String separator = System.getProperty("file.separator");

    //运行时的classpath
    private static String[] classPaths;
    private static String path;

    static {
        //windows和*inux路径分隔符不一样
        String osName = System.getProperty("os.name");
        String classPath = System.getProperty("java.class.path");
        if (osName.contains("Windows") || osName.contains("windows")) {
            classPaths = classPath.split(";");

        } else {
            classPaths = classPath.split(":");

        }
        path = Arrays.stream(classPaths).filter(s -> !s.endsWith(".jar")).collect(Collectors.toList()).remove(0);
    }

    /**
     * 获取一个接口或者父类的所有子类(不含接口和抽象类)
     *
     * @param clazz 接口类或者父类
     * @return
     */
    public static List<Class> getAllClassBySubClass(Class clazz, String... packages) {

        return getAllClassBySubClass(clazz, false, packages);
    }

    /**
     * 获取一个接口或者父类的所有子类(不含接口和抽象类)
     *
     * @param clazz     接口类或者父类
     * @param findInJar 是否需要从jar包中查找
     * @param packages  限定寻找的包名，前缀匹配模式 findInJar为true时建议一定要限制包名提升速度和避免出错！
     * @return
     */
    public static List<Class> getAllClassBySubClass(Class clazz, boolean findInJar, String... packages) {

        List<Class> ret = getClasspathAllClass(findInJar, packages).stream()
                .filter(c -> !c.isInterface())
                .filter(c -> !Modifier.isAbstract(c.getModifiers()))
                .filter(c -> clazz.isAssignableFrom(c))
                .collect(Collectors.toList());

        return ret;
    }


    /**
     * 获取所有classpath下所有全限定名以packages开头的的class
     *
     * @return
     */
    private static List<Class> getClasspathAllClass(boolean findInJar, String... packages) {
        List<Class> ret = new LinkedList<>();

        File file = new File(path);
        ret.addAll(findClass(file, path, findInJar, packages));
        return ret;
    }

    /**
     * 递归查找class
     *
     * @param file
     * @param classpath
     * @return
     */
    private static List<Class> findClass(File file, String classpath, boolean findInJar, String... packages) {
        List<Class> ret = new LinkedList<>();
        if (!file.exists()) {
            return ret;
        }

        //是文件夹 递归查找
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    ret.addAll(findClass(f, classpath, findInJar, packages));
                }
            }
        } else if (file.isFile()) {
            //是普通字节码文件
            String fileName = file.getName();
            String fullyQualifiedName = getFullyQualifiedName(file, classpath);
            if (fileName.endsWith(".class") && isInPackages(fullyQualifiedName, packages)) {
                try {
                    ret.add(Class.forName(fullyQualifiedName));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                //jar包
            } else if (findInJar && fileName.endsWith(".jar")) {
                try {
                    JarFile jarFile = new JarFile(file);
                    //枚举jar包内所有文件
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        String jarClassName = entries.nextElement().getName();
                        //jar包里的字节码文件
                        if (jarClassName.endsWith(".class") && isInPackages(jarClassName, packages)) {
                            fullyQualifiedName = jarClassName
                                    .replace(".class", "")
                                    .replaceAll("/", ".");
                            try {
                                ret.add(Class.forName(fullyQualifiedName));
                            } catch (ClassNotFoundException e) {
                                //
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }


    /**
     * 获取普通字节码文件的全限定名
     *
     * @param file
     * @param classpath
     * @return
     */
    private static String getFullyQualifiedName(File file, String classpath) {
        String filePath = file.getPath();
        return filePath.replace(classpath, "")
                .replaceAll("\\\\", ".")
                .replaceFirst(".", "")
                .replace(".class", "");
    }

    private static boolean isInPackages(String fileName, String... packages) {
        if (packages.length == 0) {
            return true;
        }

        for (String p : packages) {
            if (fileName.startsWith(p)) {
                return true;
            }
        }

        return false;
    }
}
