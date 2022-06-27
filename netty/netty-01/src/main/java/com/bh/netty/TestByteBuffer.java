package com.bh.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer 正确使用姿势：
 * 1. 向buffer写入数据，例如调用channel.read(buffer)
 * 2. 调用flip 切换到读模式
 * 3. 从buffer读取数据，例如调用buffer.get()
 * 4. 调用clear() 或compact()切换到写模式
 * 5. 重复1~4
 *
 * ByteBuffer的结构：
 * 1. capacity
 * 2. position
 * 3. limit
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        /**
         * 1. 输入输出流
         * 2. RandomAccessFile
         */
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            /**
             * read 返回值：当返回值为-1的时候，文件没有内容可读取了
             */

            while (true){
                int len = channel.read(buffer);
                if(len ==-1){
                    break;
                }
                /**
                 * 打印buffer的内容
                 * 1.flip 切换到读模式
                 * 2.hasRemaining 是否还有剩余未读取的数据
                 */
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte result = buffer.get();
                    log.info("读取到的字节：{}",(char)result);
                }
                //切换为写模式
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
