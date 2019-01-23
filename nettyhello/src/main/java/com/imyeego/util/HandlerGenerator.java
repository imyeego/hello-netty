package com.imyeego.util;

import com.google.protobuf.MessageLite;
import com.imyeego.protocol.Packet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerGenerator {

    public static String rootPath = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception{
        HandlerGenerator generator = new HandlerGenerator();
        generator.run("com.imyeego.handler", true);
    }

    private void run(String packagePath, boolean override) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        URL resource = HandlerGenerator.class.getResource("/template.ftl");
        cfg.setDirectoryForTemplateLoading(Paths.get(resource.toURI()).toFile().getParentFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, String> dataModal = new HashMap<>();
        String handlerFilePath = rootPath + "/nettyhello/src/main/java/" + packagePath.replace('.', '/') + "/";
        List<Class> classes = ClassUtil.getAllClassBySubClass(Packet.class, false, "com.imyeego.protocol.request");
        classes.stream()
                .forEach(claz -> {
                    try {
                        dataModal.put("className", claz.getSimpleName());
                        dataModal.put("lowerClassName", StringUtil.lowerFirst(claz.getSimpleName()));
                        dataModal.put("packagePath", packagePath);
                        Template handlerTemplate = cfg.getTemplate("template.ftl");
                        File file = new File(handlerFilePath + claz.getSimpleName() + "Handler.java");
                        if (override || !file.exists()) {
                            Writer out = new OutputStreamWriter(new FileOutputStream(file));
                            handlerTemplate.process(dataModal, out);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
