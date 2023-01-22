import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class Fifo {
    int numberOfFaults = 0;
    Placement placement;
    int size;
    int counter = 0;
    Queue<Integer> queue;

    public Fifo(int memorySize){
        queue = new ConcurrentLinkedQueue<>();
        this.size = memorySize;
        placement = new Placement(size);
    }

    public void requestToMemory(int address){
        if (!queue.contains(address)) {
            numberOfFaults += 1;
            int output;
            if (counter >= size){
                output = queue.remove();
                placement.replace(address, output);
            } else{
                placement.sitDown(address);
            }
            queue.add(address);
            counter++;
        }

        System.out.print("FIFO: ");
        placement.print();
    }

    public int getNumberOfFaults() {
        return numberOfFaults;
    }
}