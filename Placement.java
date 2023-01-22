public class Placement {
    int size;
    int[] places;
    int counter = 0;
    public Placement(int size){
        this.size = size;
        places = new int[size];
    }

    void sitDown(int address){
        if (counter < size){
            places[counter++] = address;
        }
    }
    void replace(int newAddress, int address){
        int index  = findIndex(address);
        if (index != -1){
            places[index] = newAddress;
        }
    }
    int findIndex(int address){
        for (int i = 0; i < size; i++) {
            if (places[i] == address){
                return i;
            }
        }
        return -1;
    }
    void print(){
        for (int i = 0; i < size; i++) {
            if (places[i] != 0){
                System.out.print("..("+(i+1) + ") " + places[i]);
            } else {
                System.out.print("..("+(i+1) + ") " + "null");
            }
        }
        System.out.println();
    }


}
