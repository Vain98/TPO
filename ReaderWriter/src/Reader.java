import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Reader {
    public static void main(String[] args) {
        try {
            int[] array = new int[2];
            System.out.println("Reader");
            RandomAccessFile file = new RandomAccessFile("file", "rw");
            FileChannel channel = file.getChannel();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0L, 16L);

            int readCount = (int)file.length();

            while(true) {
                Thread.sleep(2000L);

                for(int i = 0; i < 2; ++i) {
                    array[i] = buffer.getInt();
                }

                System.out.println( array[0] + " + " + array[1] + " = " + (array[0] + array[1]));
                buffer.flip();
                buffer.putInt(0);
                buffer.rewind();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Reading error");
            e.printStackTrace();
        }
    }
}