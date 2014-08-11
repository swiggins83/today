package com.swiggins.today.model;

public class Weather {

    public Condition currentCondition = new Condition();
    public Temperature temperature = new Temperature();

    public byte[] iconData;

    public class Condition {
        private String condition;
        private String description;
        private String icon;

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getCondition() {
            return condition;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return icon;
        }
    }

    public class Temperature {
        private float temp;
        private float min;
        private float max;

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public int getTemp() {
            return (int) Math.floor((temp - 275.15) * (1.8) + 32) ;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public int getMin() {
            return (int) Math.floor((min - 275.15) * (1.8) + 32);
        }

        public void setMax(float max) {
            this.max = max;
        }

        public int getMax() { return (int) Math.floor((max - 275.15) * (1.8) + 32); }

        @Override
        public String toString() {
            return "" + getTemp();
        }
    }
}
