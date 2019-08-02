package com.imyeego.nio;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
