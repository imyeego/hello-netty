package com.imyeego.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    private static SocketChannel socketChannel = null;
    private static DatagramChannel datagramChannel = null;
    private static InetSocketAddress address = new InetSocketAddress("localhost", 8888);
    private static boolean timerSetted, timeOuted, udpConnected;
    private static Timer timer = new Timer();
    private static Queue<String> requestQueue = new LinkedList<>();
    private static Queue<String> udpRequestQueue = new LinkedList<>();
    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException{

        service.execute(udpClient);
        List<String> list = new ArrayList<>();
        list.add("Facebook");
        list.add("Apple");
        list.add("Google");
        list.add("Microsoft");
        list.add("Amazon");


        for (String s : list) {
            udpRequestQueue.offer(s);
            Thread.sleep(2000);
        }

    }

    static void connect() throws SocketTimeoutException{
        if (!timerSetted) {
            timerSetted = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        timer.cancel();
                        timeOuted = true;
                        throw new SocketTimeoutException("连接超时...");
                    } catch (SocketTimeoutException e) {
                        e.printStackTrace();
                    }
                }
            }, 15_000);
        }
        if (timeOuted) return;
        try {
            socketChannel = SocketChannel.open(address);
            if (socketChannel.isConnected()) {
                timer.cancel();
                timerSetted = false;
                System.out.println("连接成功");
            }
            socketChannel.socket().setKeepAlive(true);
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            connect();
        }

    }

    private static void connectUDP() {
        if (udpConnected) return;
        try {
            datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);
            datagramChannel.connect(address);
        } catch (IOException e) {
            connectUDP();
        }

    }

    static void log(String msg) {
        System.out.println(msg);
    }

    private static Runnable nioClient = () -> {
        try {
            connect();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }
        Selector selector = null;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (socketChannel != null) {
            try {
                int ops = socketChannel.validOps();
                SelectionKey key = socketChannel.register(selector, ops);
                int var;

                do {
                    var = selector.selectNow();
                    if (var != -1) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            iterator.remove();
                            if (selectionKey.isReadable()) {
                                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                                if (socketChannel.read(byteBuffer) != -1) {
                                    String result = new String(byteBuffer.array()).trim();
                                    log("Message Received TCP: " + result);
                                }
                            }


                            if (selectionKey.isWritable()) {
                                if (requestQueue.peek() != null) {
                                    byte[] request = (requestQueue.poll() + "\r\n").getBytes();
                                    ByteBuffer buffer = ByteBuffer.wrap(request);
                                    socketChannel.write(buffer);
                                    log("sending tcp: " + new String(request));
                                }
                            }
                        }
                    }

                } while (true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    };

    private static Runnable udpClient = () -> {
        connectUDP();
        Selector selector = null;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (datagramChannel != null) {
            try {
                int ops = datagramChannel.validOps();
                SelectionKey key = datagramChannel.register(selector, ops);
                int var;
                while (true) {
                    var = selector.selectNow();
                    if (var != -1) {
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            iterator.remove();
                            if (selectionKey.isReadable()) {
                                DatagramChannel socketChannel = (DatagramChannel) selectionKey.channel();
                                ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                                socketChannel.receive(byteBuffer);
                                if (byteBuffer.position() != 0) {
                                    String result = new String(byteBuffer.array()).trim();
                                    log("Message Received UDP: " + result);
                                }

                            }


                            if (selectionKey.isWritable()) {
                                if (udpRequestQueue.peek() != null) {
                                    byte[] request = (udpRequestQueue.poll() + "\r\n").getBytes();
                                    ByteBuffer buffer = ByteBuffer.wrap(request);
                                    datagramChannel.send(buffer, address);
                                    log("sending udp: " + new String(request));
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };
}
