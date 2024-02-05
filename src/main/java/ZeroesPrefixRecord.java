public class ZeroesPrefixRecord {
    enum Adjustment {INCREASED, DECREASED, STAYS}
    private int prefixSize;
    private Adjustment adjustment;

    public ZeroesPrefixRecord(int prefixSize, Adjustment increased) {
        this.prefixSize = prefixSize;
        this.adjustment = increased;
    }

    public int getPrefixSize() {
        return prefixSize;
    }

    public Adjustment getAdjustment() {
        return adjustment;
    }
}
