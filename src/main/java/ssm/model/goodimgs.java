package ssm.model;

public class goodimgs {

    private int id;
    private int goodid;
    private String url;


    public int getId() {
        return id;
    }

    public int getGoodid() {
        return goodid;
    }

    public String getUrl() {
        return url;
    }

    private goodimgs(Builder builder) {
        id = builder.id;
        goodid = builder.goodid;
        url = builder.url;
    }

    public static final class Builder {
        private int id;
        private int goodid;
        private String url;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder goodid(int val) {
            goodid = val;
            return this;
        }

        public Builder url(String val) {
            url = val;
            return this;
        }

        public goodimgs build() {
            return new goodimgs(this);
        }
    }
}
