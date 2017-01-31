package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class LargeFileTest {

	
	public static void main(String[] args) throws IOException {
		RandomAccessFile f = new RandomAccessFile("/home/artur/Downloads/en-gb_windows_10_multiple_editions_version_1607_updated_jul_2016_x64_dvd_9058848.iso", "r");
		FileChannel channel = f.getChannel();
		MappedByteBuffer map = channel.map(MapMode.READ_ONLY, 0, channel.size());
		System.out.println("Mapped");
		map.load();
		System.out.println("loaded");
	}
}
