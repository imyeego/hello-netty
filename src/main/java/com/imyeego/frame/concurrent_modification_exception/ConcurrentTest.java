package com.imyeego.frame.concurrent_modification_exception;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.imyeego.frame.bean.Student;
import com.imyeego.frame.generics.ICallback;
import com.imyeego.kotlin.Bean;
import com.sun.istack.internal.NotNull;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

public class ConcurrentTest {
    private static volatile int i = 0;
    private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(5);
    static NumberFormat format = new DecimalFormat("0.0");


    public static void main(String[] args) {
//        removeByForeach();
//        removeByIterator();
//        String day = "2020-1-3 9:00";
//        Date oldDate = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
//        try {
//            oldDate = sdf.parse(day);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String s = sdf.format(new Date());
//        System.out.println(new Date().after(oldDate));

//        String time = "5:00-7:00";
//        String[] times = time.split("-");
//        for (String s : times) {
//            System.out.println(s);
//        }
//
//        System.out.println(time);


//        String date = format("06月09日");
//        System.out.println(date);

//        String s = "liuzhao";
//        System.out.println(s.substring(5, 7));
//        System.out.println(Calendar.getInstance(Locale.CHINA).getTimeInMillis());


//        testSocket();
//        testGson();
//        testDate()
        testTemp();
//        removeChinese("文00i9o8小违纪哦欸经哦就");
//        testReflection();
//        testCallable();
//        int a = 100;
//        System.out.println(a >> 1);
//        System.out.println(a);
//        testGenerics();
//        testSwitch();
//        testException();
//        testThreadPool();
//        testForkJoin();
//        testMerge();
//        if (StaticTest.is) {
//            System.out.println("---------");
//        }
//        System.out.println(StaticTest.i);
//        testMerge();

//        System.out.println(hide("114699362", 3, 12));
    }

    public static String hide(String source, int start, int length) {
        char[] c = source.toCharArray();
        for (int i = start; i < start + length; i ++) {
            c[i] = '*';
        }

        return new String(c);
    }

    private static void testReflection() {

//        Login login = Login.instance();
//
//        try {
//            Field field = login.getClass().getDeclaredField("loginBean");
//            field.setAccessible(true);
//
//            try {
//                field.set(login, null);
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } finally {
//                field.setAccessible(false);
//            }
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(Login.loginBean);
        String org = "110108002";
        System.out.println(org.substring(org.length() - 2));
        System.out.println(org);
    }

    private static void testMerge() {
        int[] array = new int[]{8,-1, 5, 11, 13, 0, 2, 5, 6, 10, 4, -9, 1, 3, 3};
        merge(array, 1, 5, 10);
        System.out.println(Arrays.toString(array));
    }

    private static void testForkJoin() {
        ForkJoinPool pool = ForkJoinPool.commonPool();
//        CountTask countTask = new CountTask(1, 10000L);
//        ForkJoinTask<Long> result = pool.submit(countTask);
//        System.out.println("start compute ...");
//        try {
//            long start = System.currentTimeMillis();
//            System.out.println(result.get());
//            System.out.println("time cost " + (System.currentTimeMillis() - start) / 1000 + "s");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        long[] array = new long[60];
        for (int i = 0; i < array.length; i++) {
            array[i] = (long) (Math.random() * 100);
        }
        System.out.println(Arrays.toString(array));
        pool.invoke(new SortTask(array));
        System.out.println(Arrays.toString(array));
    }

    public static void merge(int[] array, int lo, int mid, int hi) {
        int[] buf = Arrays.copyOfRange(array, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
            array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
        }
    }

    static class SortTask extends RecursiveAction {

        static final int THRESHOLD = 12;

        final long[] array;
        final int lo, hi;

        public SortTask(long[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        public SortTask(long[] array) {
            this(array, 0, array.length);
        }

        public void sortSequentially(int lo, int hi) {
            Arrays.sort(array, lo, hi);
        }

        public void merge(int lo, int mid, int hi) {
            long[] buf = Arrays.copyOfRange(array, lo, mid);
            for (int i = 0, j = lo, k = mid; i < buf.length; j++) {
                array[j] = (k == hi || buf[i] < array[k]) ? buf[i++] : array[k++];
            }
        }

        @Override
        protected void compute() {
            if (hi - lo < THRESHOLD) {
                sortSequentially(lo, hi);
                System.out.println(Arrays.toString(array));
            } else {
                int mid = (lo + hi) >>> 1;
                invokeAll(new SortTask(array, lo, mid), new SortTask(array, mid, hi));
                merge(lo, mid, hi);
            }
        }
    }

    public static class CountTask extends RecursiveTask<Long> {
        private long start;
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        public static final int threadhold = 100;

        @Override
        protected Long compute() {
            long sum = 0;
            // 如果任务足够小, 就直接执行
            boolean canCompute = (end - start) <= threadhold;
            if (canCompute) {
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                System.out.println(Thread.currentThread().getName() + ": ∑(" + start + "~" + end + ")=" + sum);
            } else {
                //任务大于阈值, 分裂为2个任务
                long middle = (start + end) / 2;
                CountTask countTask1 = new CountTask(start, middle);
                CountTask countTask2 = new CountTask(middle + 1, end);

                // 开启线程
                countTask1.fork();
                countTask2.fork();
//                invokeAll(countTask1, countTask2);

                // 结果合并
                sum = countTask1.join() + countTask2.join();
            }
            return sum;
        }
    }

    private static void testThreadPool() {
        /*ExecutorService frExecutor = new ThreadPoolExecutor(1, 5, 0, TimeUnit.MILLISECONDS, queue);
        while (true) {
            frExecutor.execute(new FaceRunnable(++i));
        }*/

//        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.execute(() -> {
//            System.out.println("liuzhao");
//            System.out.println(Thread.currentThread().toString());
//            System.out.println(service.toString());
//        });
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        service.execute(() -> {
//            System.out.println("liuzhao");
//            System.out.println(Thread.currentThread().toString());
//            System.out.println(service.toString());
//
//        });
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        System.out.println(uuid.length());

    }

    static class FaceRunnable implements Runnable {
        int data;

        public FaceRunnable(int data) {
            this.data = data;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": " + data);
        }
    }


    private static void testException() {
        int a = 1;
        while (true) {
            if (a == 1) throw new IllegalArgumentException("connect: The address can't be null");
        }
    }


    private static void testThread() {
        Thread.yield();
    }

    private static void testGenerics() {
        ICallback<Student> list = new ICallback<Student>() {
            @Override
            public void onSuccess(Student student) {

            }
        };
        Type[] types = list.getClass().getGenericInterfaces();
        Type type = ((ParameterizedType) types[0]).getActualTypeArguments()[0];
        Class<?> clazz = (Class<?>) type;
        System.out.println(clazz.getTypeName());
    }

    private static void testGson() {
//        String response = "[{\"result\":\"success\"}]";
        String response = "[{\"id\":0,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\",\"classTh\":null}]";
//        String json = "{\"id\":0,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\",\"classTh\":null,\"code\":\"3\"}";
//        String json = "\"id\":0,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\",\"classTh\":null,\"code\":\"3\"}";
        String json = "{\"fhzt\":\"200\",\"orgname\":\"咸阳渭城中学\",\"level\":\"1\",\"taskcode\":\"20200618\",\"orgcodeentify\":\"123\",\"remark\":null,\"harddata\":null,\"id\":\"02660b0ec1cd429d940b0eea1696f837\",\"schoolcode\":\"610001\",\"schoolname\":\"咸阳渭城中学\",\"account\":\"admin\"}";
//        String json = "{\"id\":0,\"name\":\"Jess\",\"gender\":1,\"grade\":\"3\"}";
//        Student student = new Student();
//        student.setId(0L);
//        student.setName("Jess");
//        student.setGender(1);
//        student.setGrade("3");
//        student.setClassTh("2");
//        student.setIsUpload("");
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
//                .serializeNulls()
                .create();
//        builder.serializeNulls();
//        String json = builder.create().toJson(student);
//        JsonParser jsonParser = new JsonParser();
//        JsonArray jsonElements = jsonParser.parse(response).getAsJsonArray();//获取JsonArray对象
//        for (JsonElement element : jsonElements) {
//            Student bean = builder.create().fromJson(element, Student.class);
//            System.out.println(bean.getName());
//        }
//        System.out.println(json);
//        List<Student> list = gson.fromJson(response, new TypeToken<List<Student>>() {}.getType());
//        List<Student> list = null;
//        for (Student s : list) {
//            System.out.println(s.getName());
//        }

//        System.out.println(System.currentTimeMillis());

//        LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
//        if (loginBean != null && "200".equals(loginBean.getFhzt())) {
//            System.out.println(loginBean.getTaskcode());
//        }
//        try {
//            Student student = gson.fromJson(json, Student.class);
//            System.out.println(student.toString());
//        } catch (JsonSyntaxException e) {
//            System.out.println("json格式不对");
//        }
//        try {
//            Class<?> clazz = Class.forName("java.lang.Integer");
//            System.out.println(clazz.getName());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    private static void testTemp() {
//        for (int j = 0; j < 50; j++) {
//            System.out.println(randomTemp());
//            try {
//                Thread.sleep(2_000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        System.out.println(String.format("%%%s%%", "liu zhao"));
//        System.out.println(new Date().getSeconds());

//        System.out.println(randomTemp());

        Set<String> set = Collections.singleton("2");
        set.add("1");
        set.add("3");
        for (String s : set) {
            System.out.println(s);
        }
    }


    private static String randomTemp() {
        float temp = new Random().nextFloat() * 0.8f + 36f;

        return format.format(temp);
    }

    private static void testSocket() {
        String str = "[{\"SN\":\"18:93:7F:78:70:96\",\"aim\":\"download_sfrz\",\"version\":\"0.1\"}]";
        String str2 = "[{\"SN\":\"54:E1:AD:F1:8E:D5\",\"aim\":\"upload_sfrz_result\",\"version\":\"0.1\"},{\"devSN\":\"75862851220127035165\",\"zwtp\":\"\",\"rlppl\":\"0.0\",\"rlzp\":\"\",\"rzfs\":\"123\",\"rzjg\":\"2\",\"rzsj\":\"2019-06-26 09:57:15\",\"sfzzp\":\"\",\"zjhm\":\"410523198611280035\",\"zwppl\":\"0.0\"}]\n";
        String str1 = "[{\"SN\":\"54:E1:AD:F1:8E:D5\",\"aim\":\"upload_sfrz_result\",\"version\":\"0.1\"},{\"zjhm\":\"610124198810271029\",\"rzsj\":\"2019-07-08 08:25:25\",\"rzfs\":\"12\",\"rzjg\":\"1\",\"devSN\":\"\",\"zwppl\":\"\", \"rlppl\":\"0.36\",\"rlzp\":\"\",\"zwtp\":\"\",\"sfzzp\":\"\"}]";
        Socket client = null;
        try {
            client = new Socket();
            SocketAddress address = new InetSocketAddress("222.91.163.154", 12789);
//            SocketAddress address = new InetSocketAddress("192.168.10.101", 8888);
            client.connect(address, 3000);
            if (client.isConnected()) System.out.println("连接成功");
            client.setSoTimeout(5_000);
            //构建IO
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write(str + "\r\n");
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();
            System.out.println("服务器："+mess);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    System.out.println("连接已关闭");
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void removeChinese(String str) {

        String REGEX_CHINESE = "[^(0-9)]";

        String string = str.replaceAll(REGEX_CHINESE, "");
        System.out.println(string);
    }

    private static void testDate() {

        List<String> list = new ArrayList<>();
        list.add("2020-07-07 09:00:00");
        list.add("2020-07-10 11:00:00");
        list.add("2020-07-10 15:30:00");
        list.add("2020-07-09 15:30:00");
        list.add("2020-07-09 11:00:00");
        list.add("2020-07-09 08:00:00");
        list.add("2020-07-10 08:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-08 09:00:00");
        list.add("2020-07-08 09:00:00");
        list.add("2020-07-08 09:00:00");
        list.add("2020-07-08 09:00:00");
        list.add("2020-07-08 09:00:00");
        list.add("2020-07-08 09:00:00");
        list.add("2020-07-08 15:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-07 09:00:00");
        list.add("2020-07-07 15:00:00");
        list.add("2020-07-07 09:00:00");

        long current = System.currentTimeMillis();
        String realBegin = "";
        Date realDate = new Date(current);
        boolean first = true;
        for (String time : list) {
            Date date = DateUtil.getDateByFormat(time, "yyyy-MM-dd HH:mm:ss");
            if (date.getTime() > current) {
                if (first) {
                    realBegin = time;
                    realDate = DateUtil.getDateByFormat(realBegin, "yyyy-MM-dd HH:mm:ss");
                    first = false;
                } else if (date.getTime() < realDate.getTime()){
                    realBegin = time;
                    realDate = DateUtil.getDateByFormat(realBegin, "yyyy-MM-dd HH:mm:ss");
                }

            }
        }

        System.out.println("current time:" + realBegin);
    }

    private static void testSwitch() {
        String i = "liuzhao";
        switch (i) {
            case "liuzhao":
                System.out.println(0);
            case "Jay Chou":
                System.out.println(1);
                break;
            default:
                System.out.println("您的输入是 " + i);
        }
    }

    private static Runnable task = () -> {
        try {
            Thread.sleep(5000);
            System.out.println(++i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    private static void removeByForeach(@NotNull String s) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 5, 8));
        for (Integer integer : list) {
            if (integer.intValue() == 2) {
                list.remove(integer);
            }
        }
    }

    private static void removeByIterator() {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 3, 5, 8));
        //                list.remove(integer); //incorrect!this can throw ConcurrentModificationException
        list.removeIf(integer -> integer == 2);
        list.forEach(System.out::println);

    }

    private static String format(String date) {
        char[] dateArray = date.toCharArray();
        if (date.charAt(0) == '0' && date.charAt(3) == '0') {
            char[] chars = new char[4];
            int j = -1;
            for (int i = 0; i < dateArray.length; i++) {
                if (i == 0 || i == 3) {
                    continue;
                }
                chars[++j] = dateArray[i];

            }
            return new String(chars);
        }
        if (date.charAt(0) == '0' && date.charAt(3) != '0') {
            char[] chars = new char[5];
            int j = -1;
            for (int i = 0; i < dateArray.length; i++) {
                if (i == 0) {
                    continue;
                }
                chars[++j] = dateArray[i];

            }
            return new String(chars);
        }

        if (date.charAt(0) != '0' && date.charAt(3) == '0') {
            char[] chars = new char[5];
            int j = -1;
            for (int i = 0; i < dateArray.length; i++) {
                if (i == 3) {
                    continue;
                }
                chars[++j] = dateArray[i];

            }
            return new String(chars);
        }

        return date;
    }

    private static void testCallable() {
        ExecutorService service = Executors.newCachedThreadPool();
        /*Future<String> future = service.submit(() -> {
           Thread.sleep(5000);
           return "hello callable";
        });
        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
        System.out.println(result);*/
        service.execute(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello runnable first");
        });

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }


    }

    public class LoginBean {

        /**
         * fhzt : 200
         * orgname : 竞业达
         * level : 1
         * taskcode :
         * orgcodeentify : 86
         * remark : null
         * id : aded89d8a94540f2904df622722d339a
         * schoolcode : 86
         * schoolname : 竞业达
         * account : jyd
         */

        private String fhzt;
        private String orgname;
        private String level;
        private String taskcode;
        private String orgcodeentify;
        private String remark;
        private String id;
        private String schoolcode;
        private String schoolname;
        private String account;


        public String getFhzt() {
            return fhzt;
        }

        public void setFhzt(String fhzt) {
            this.fhzt = fhzt;
        }

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTaskcode() {
            return taskcode;
        }

        public void setTaskcode(String taskcode) {
            this.taskcode = taskcode;
        }

        public String getOrgcodeentify() {
            return orgcodeentify;
        }

        public void setOrgcodeentify(String orgcodeentify) {
            this.orgcodeentify = orgcodeentify;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSchoolcode() {
            return schoolcode;
        }

        public void setSchoolcode(String schoolcode) {
            this.schoolcode = schoolcode;
        }

        public String getSchoolname() {
            return schoolname;
        }

        public void setSchoolname(String schoolname) {
            this.schoolname = schoolname;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }


}
