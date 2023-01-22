import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class SecondChance {
    int numberOfFaults = 0;
    Placement placement;
    int size;
    int counter = 0;
    Queue<Integer> queue;
    int[] seen = new int[101];

    public SecondChance(int memorySize){
        queue = new ConcurrentLinkedQueue<>();
        this.size = memorySize;
        placement = new Placement(size);
    }


    public void requestToMemory(int address){
        if (!queue.contains(address)){
            numberOfFaults++;
            if (counter >= size){
                int head = queue.remove();
                while (seen[head] == 1){
                    seen[head] = 0;
                    queue.add(head);
                    head = queue.remove();
                }
                placement.replace(address, head);
            } else {
                placement.sitDown(address);
            }
            queue.add(address);
            counter++;

        }else {
            seen[address] = 1;
        }
        System.out.print("Second Chance: ");
        placement.print();
    }

    public int getNumberOfFaults() {
        return numberOfFaults;
    }
}