package specs;

public interface IStockageData{

    boolean storeDatatoDB(RawData rawData);
    boolean storeDatatoDisk(RawData rawData, String path);
}