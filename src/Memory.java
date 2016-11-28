
public class Memory {

    public int memTotal = 256;
    public int memUsed = 0;

    public Memory(int mem) {
        memTotal = mem;
    }

    public Memory() {

    }

    public void setFullMemory(int value) {
        memTotal = value;
    }

    public int addMemUsed(int value) {
        memUsed = memUsed + value;
        return memUsed;
    }

    public int subtractMemUsed(int value) {
        memUsed = memUsed - value;
        if(memUsed < 0)
            memUsed = 0;
        return memUsed;
    }

    public int getFullMemory() {
        return memTotal;
    }

    public int getMemoryUsed() {
        return memUsed;
    }

    public int getAvailableMemory() {
        return memTotal - memUsed;
    }

}


//
