
public class Memory {

	public static final int TOTAL_MEMORY = 256;
    public int memAvailable = 0;
    public int memUsed = 0;

    public Memory() {

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

    public int getTotalMemory() {
        return TOTAL_MEMORY;
    }

    public int getMemoryUsed() {
        return memUsed;
    }

    public int getAvailableMemory() {
        return TOTAL_MEMORY - memUsed;
    }

}


//
