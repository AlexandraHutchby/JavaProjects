package NoiseRemoving.code;

public class SortArray <E extends Comparable<E>>{
    E[] array;
    int pivotIndex;
    int headIndex;
    int tailIndex;

    public SortArray(E[] array){
        this.array = array;
    }
    
    public void setArray(E[] array){
        this.array = array;
    }

    /*
     * This function is called from the other classes to sort the array
     */
    public void quickSort(){
        if(this.array.length > 0){
            quickSort(this.array, 0, this.array.length-1);
        }
    }

    private void quickSort(E[] list, int h, int t){
        if(h < t){ //while the start is less than the end
            E pivot = list[h];
            pivotIndex = h; //pivot index
            headIndex = h;
            tailIndex = t;
            while(headIndex < tailIndex){ //while the tail is less than the head
                //while the tail is greater than the pivot continue
                while(headIndex < tailIndex && (list[tailIndex].compareTo(pivot)) >= 0){
                    tailIndex--;
                }

                if(headIndex < tailIndex){
                    swap(); //swap the head and the tail
                    pivotIndex = tailIndex;
                }

                //while the head is smaller than the pivot
                while(headIndex < tailIndex && (list[headIndex].compareTo(pivot)) <= 0){
                    headIndex++;
                }

                if(headIndex < tailIndex){ 
                    swap(); //swap the head and the tail
                    pivotIndex = headIndex;
                }
            }
            quickSort(list, h, pivotIndex-1); //sort from the left of the pivot
            quickSort(list, pivotIndex+1, t); //sort from the right of the pivot
        }
    }

    /*
     * This function will swap the head and the tail to make it in order
     */
    private void swap(){
        E temp = array[tailIndex];
        array[tailIndex] = array[headIndex];
        array[headIndex] = temp;
    }
}
