import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client1 {
	private static final String DNS_SERVER_ADDRESS = "8.8.8.8";
	private static final int DNS_SERVER_PORT = 53;
	public static void main(String[] args) throws IOException, InterruptedException {
		DatagramSocket datagramSocket = new DatagramSocket();
		datagramSocket.setSoTimeout(1000);
		datagramSocket.connect(InetAddress.getByName(DNS_SERVER_ADDRESS), DNS_SERVER_PORT); // 连接指定服务器和端口
		DatagramPacket packet = null;
		for (int i = 0; i < 5; i++) {
			// 发送:
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("请输入网址：");
//			String string1 = scanner.nextLine();

			String cmd = new String[] { "date", "time", "datetime", "weather", "hello" }[i];
			byte[] data = cmd.getBytes(); //转化呈字节数组

			packet = new DatagramPacket(data, data.length);
			datagramSocket.send(packet);
			// 接收:
			byte[] buffer = new byte[1024];
			packet = new DatagramPacket(buffer, buffer.length);
			datagramSocket.receive(packet);
			String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
			System.out.println(packet.getData());
			System.out.println(cmd + " >>> " + resp);

		}
		datagramSocket.disconnect();
		System.out.println("disconnected.");
	}
}
