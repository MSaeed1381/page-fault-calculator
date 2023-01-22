class PageFault implements Runnable{
    int memorySize;
    public PageFault(int memorySize){
        this.memorySize = memorySize;
    }
    @Override
    public void run() {
        Fifo fifo = new Fifo(memorySize);
        Lru lru = new Lru(memorySize);
        SecondChance secondChance = new SecondChance(memorySize);
        SharedMemoryBuffer buffer = SharedMemoryBuffer.getInstance();
        do {
            if (buffer.sharedMemory[buffer.fifoHead] > 0 && buffer.fifoHead <= buffer.index) {
                fifo.requestToMemory(buffer.fifoRead());
            }
            if (buffer.sharedMemory[buffer.lruHead] > 0 && buffer.lruHead <= buffer.index) {
                lru.requestToMemory(buffer.lruRead());
            }
            if (buffer.sharedMemory[buffer.scoHead] > 0 && buffer.scoHead <= buffer.index) {
                secondChance.requestToMemory(buffer.scRead());
            }
        } while (!buffer.isFinished());

        // print
        System.out.println("LRU: " + lru.getNumberOfFaults() +" FIFO: " + fifo.getNumberOfFaults() + " Second-chance: " + secondChance.getNumberOfFaults());

    }

}