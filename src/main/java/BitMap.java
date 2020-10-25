/**
 * @author Rhett
 * @title: BitMap
 * @description: TODO
 * @date 2020/10/25 16:33
 */
public class BitMap {
    public static void main(String[] args) {

    }
    private byte[] bits;
    private int capacity;

    public BitMap(int capacity){
        this.capacity=capacity;
        bits=new byte[(capacity >>3)+1];
    }

    public void add(int num){
        int arrayIndex=num>>3;
        int position= num&0x07;
        bits[arrayIndex] |= 1<<position;
    }
    public boolean contain(int num){
        int arrayIndex=num>>3;
        int position=num&0x07;
        return (bits[arrayIndex]&(1<<position))!=0;
    }
    public void clear(int num){
        int arrayIndex=num>>3;
        int position =num &0x07;
        bits[arrayIndex] &=~(1<<position);
    }
}
