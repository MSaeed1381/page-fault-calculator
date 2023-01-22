import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class Lru {
    int numberOfFaults = 0;
    Placement placement;
    int size;
    int counter = 0;
    Queue<Integer> queue;

    public Lru(int memorySize){
        queue = new ConcurrentLinkedQueue<>();
        this.size = memorySize;
        placement = new Placement(size);
    }

    public void requestToMemory(int address){
        if (!queue.contains(address)){
            numberOfFaults++;
            int output;
            if (counter >= size){
                output = queue.remove();
                placement.replace(address, output);
            } else {
                placement.sitDown(address);
            }
            queue.add(address);
            counter++;
        }else {
            queue.remove(address);
            queue.add(address);
        }
        System.out.print("LRU: ");
        placement.print();
    }

    public int getNumberOfFaults() {
        return numberOfFaults;
    }
}