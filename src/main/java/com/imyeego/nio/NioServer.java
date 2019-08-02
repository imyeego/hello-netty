package com.imyeego.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {

    private static Queue<Response> responseQueue = new LinkedList<>();
    private static InetSocketAddress address = new InetSocketAddress("localhost", 8888);
    private static ExecutorService service = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {

        service.execute(tcpServer);

        service.execute(udpServer);

    }

    private static Runnable tcpServer = () -> {
        Selector selector = null;
        ServerSocketChannel channel = null;
        try {
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            channel.bind(address);
            channel.configureBlocking(false);

            int ops = channel.validOps();
            SelectionKey key = channel.register(selector, ops, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int var = 0;
        while (true) {
            try {
                var = selector.selectNow();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (var != 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isAcceptable()) {
                        try {
                            SocketChannel socketChannel = channel.accept();
                            socketChannel.configureBlocking(false);

                            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                            log("Connection Accept: " + socketChannel.getRemoteAddress());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(256);

                        try {
                            if (socketChannel.read(byteBuffer) != -1) {
                                String result = new String(byteBuffer.array()).trim();
                                responseQueue.offer(new Response("response: " + result, socketChannel.getRemoteAddress()));
                                log("Message Received: " + result);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (selectionKey.isWritable()) {
                        if (responseQueue.peek() != null) {
                            Response response = responseQueue.poll();
                            byte[] bytes = (response.message + "\r\n").getBytes();
                            ByteBuffer buffer = ByteBuffer.wrap(bytes);
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            try {
                                socketChannel.write(buffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }
            } else {
                // do anything no channels are't activated
            }
        }
    };

    private static Runnable udpServer = () -> {
        Selector selector = null;
        DatagramChannel channel = null;
        try {
            selector = Selector.open();
            channel = DatagramChannel.open();
            channel.bind(address);
            channel.configureBlocking(false);

            int ops = channel.validOps();
            SelectionKey key = channel.register(selector, ops, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int var = 0;
        while (true) {
            try {
                assert selector != null;
                var = selector.selectNow();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (var != 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isReadable()) {
                        DatagramChannel socketChannel = (DatagramChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                        SocketAddress socketAddress = null;
                        try {
                            socketAddress = socketChannel.receive(byteBuffer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (byteBuffer.position() != 0 && socketAddress != null) {
                            String result = new String(byteBuffer.array()).trim();
                            responseQueue.offer(new Response("response " + result, socketAddress));
                            log("Message Received: " + result);
                        }
                    }

                    if (selectionKey.isWritable()) {
                        if (responseQueue.peek() != null) {
                            Response response = responseQueue.poll();
                            byte[] bytes = (response.message + "\r\n").getBytes();
                            ByteBuffer buffer = ByteBuffer.wrap(bytes);
                            DatagramChannel dataChannel = (DatagramChannel) selectionKey.channel();
                            try {
                                dataChannel.send(buffer, response.address);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                // do anything no channels are't activated
            }
        }
    };

    static void log(String msg) {
        System.out.println(msg);
    }

    static class Response {
        String message;
        SocketAddress address;

        public Response(String message, SocketAddress address) {
            this.message = message;
            this.address = address;
        }
    }
}
