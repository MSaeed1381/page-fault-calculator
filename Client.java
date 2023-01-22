import java.io.DataInputStream;
import java.net.Socket;


public class Client {
    static final int port = 8080;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", port);
            DataInputStream din = new DataInputStream(socket.getInputStream());
            int memorySize = din.readInt();
            System.out.println("memory size: " + memorySize);
            SharedMemoryBuffer buffer = SharedMemoryBuffer.getInstance();
            Thread thread = new Thread(new PageFault(memorySize));
            thread.start();
            int data;
            while (true){
                data = din.readInt();
                if (data == 0){
                    buffer.write(-1);
                    break;
                }
                buffer.write(data);
            }
            thread.join();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
