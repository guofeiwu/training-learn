package com.guofei.wu.a;

public class Address implements Cloneable {
        private String add;

        public String getAdd() {
            return add;
        }

        public void setAdd(String add) {
            this.add = add;
        }

        @Override
        protected Object clone() {
            Address address = null;
            try {
                address = (Address) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return address;
        }
    }