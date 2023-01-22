class SharedMemoryBuffer {
    int [] sharedMemory;
    int index = -1;
    int fifoHead = 0;
    int lruHead = 0;
    int scoHead = 0;
    static SharedMemoryBuffer instance;
    private SharedMemoryBuffer(){
        sharedMemory = new int[101];
    }

    public static SharedMemoryBuffer getInstance() {
        if (instance == null)
            instance = new SharedMemoryBuffer();
        return instance;
    }
    public synchronized void write(int address){
        System.out.println("person "+address + " enter!");
        sharedMemory[++index] = address;
    }
    public synchronized int fifoRead(){
        return sharedMemory[fifoHead++];
    }
    public synchronized int lruRead(){
        return sharedMemory[lruHead++];
    }
    public synchronized int scRead(){
        return sharedMemory[scoHead++];
    }

    public boolean isFinished(){
        return (sharedMemory[scoHead] == -1 && sharedMemory[lruHead] == -1 && sharedMemory[fifoHead] == -1);
    }

}