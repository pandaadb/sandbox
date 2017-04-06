package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelReadWriteTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Path path = Paths.get("/tmp/test");
		if(!path.toFile().exists()) path.toFile().createNewFile();
		
		final FileChannel channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE);
		
		// writer
		new Thread(() -> {
			for(int i = 0; i< 10; i++ ) {
				try {
					channel.write( ByteBuffer.wrap(("test writing " + i + " \n").getBytes()));
					channel.force(true);
					Thread.sleep(1000);
					System.err.println("Written");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(() -> {
			ByteBuffer buffer = ByteBuffer.allocate(1000);
			while(channel.isOpen()) {
				try {
					int read = channel.read(buffer);
					while(read != -1) {
						buffer.flip();
						while (buffer.hasRemaining()) {
							System.out.print((char)buffer.get());
						}
						buffer.clear();
						read = channel.read(buffer);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		Thread.sleep(10000);
	}
}
