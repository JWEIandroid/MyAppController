package ssm.model;

public class RecordResponse {

    //买入订单
    private Buyrecord buyrecord;

    //卖出订单
    private salerecord salerecord;

    //收藏订单
    private ForkRecord forkRecord;

    //发布订单
    private reportrecord reportrecord;


    public Buyrecord getBuyrecord() {
        return buyrecord;
    }

    public void setBuyrecord(Buyrecord buyrecord) {
        this.buyrecord = buyrecord;
    }

    public ssm.model.salerecord getSalerecord() {
        return salerecord;
    }

    public void setSalerecord(ssm.model.salerecord salerecord) {
        this.salerecord = salerecord;
    }

    public ForkRecord getForkRecord() {
        return forkRecord;
    }

    public void setForkRecord(ForkRecord forkRecord) {
        this.forkRecord = forkRecord;
    }

    public ssm.model.reportrecord getReportrecord() {
        return reportrecord;
    }

    public void setReportrecord(ssm.model.reportrecord reportrecord) {
        this.reportrecord = reportrecord;
    }
}
