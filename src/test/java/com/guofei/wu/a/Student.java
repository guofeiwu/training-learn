package com.guofei.wu.a;

public class Student implements Cloneable {
        private int number;

        private Address address;

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public Object clone() {
            Student stu = null;
            try {
                stu = (Student) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            Address clone = (Address) address.clone();
            stu.setAddress(clone);
            return stu;
        }
    }