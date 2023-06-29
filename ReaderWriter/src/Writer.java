import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Writer {
    public static void main(String[] args) {
        try {
            System.out.println("Writer");
            RandomAccessFile file = new RandomAccessFile("file", "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0L, 16L);
            buffer.putInt(0);
            buffer.rewind();

            while(true) {
                do {
                    Thread.sleep(2000L);
                    file.seek(0L);
                } while(file.readInt() != 0);

                buffer.putInt((int)(Math.random() * 1000.0D));
                buffer.putInt((int)(Math.random() * 1000.0D));
                buffer.flip();
                System.out.println(buffer.getInt() + " " + buffer.getInt());
                buffer.clear();
            }
        } catch (InterruptedException | IOException var4) {
            System.err.println("Writing error");
            var4.printStackTrace();
        }
    }
}