package com.imyeego.protocol;

import com.google.protobuf.MessageLite;
import com.imyeego.util.ClassUtil;
import io.netty.buffer.ByteBuf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketCodec {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();
    static {
        List<Class> classes = ClassUtil.getAllClassBySubClass(Packet.class, true
                , "com.imyeego.protocol.request"
                ,"com.imyeego.protocol.response");

        classes.stream().forEach(clazz-> {
            try {
                Object object = clazz.newInstance();
                packetTypeMap.put((Byte) clazz.getMethod("getCommand", null).invoke(object, new Class[]{}), clazz);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    public Packet decode(ByteBuf byteBuf){
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = Serializer.DEFAULT;
        if (requestType != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
