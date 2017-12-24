package ssm.model;

public class goods {

    private int id;
    private String name;
    private String type;
    private float price_before;
    private float price_sale;
    private boolean status;
    private String description;
    private int express;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public float getPrice_before() {
        return price_before;
    }

    public float getPrice_sale() {
        return price_sale;
    }

    public boolean isStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public int getExpress() {
        return express;
    }





    private goods(Builder builder) {
        id = builder.id;
        type = builder.type;
        price_before = builder.price_before;
        price_sale = builder.price_sale;
        status = builder.status;
        description = builder.description;
        express = builder.express;
    }


    public static final class Builder {
        private int id;
        private String type;
        private float price_before;
        private float price_sale;
        private boolean status;
        private String description;
        private int express;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder price_before(float val) {
            price_before = val;
            return this;
        }

        public Builder price_sale(float val) {
            price_sale = val;
            return this;
        }

        public Builder status(boolean val) {
            status = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder express(int val) {
            express = val;
            return this;
        }

        public goods build() {
            return new goods(this);
        }
    }
}
