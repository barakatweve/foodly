package com.example.becks.materia.muso;

/**
 * Created by becks on 5/8/16.
 */
public class musoGetSet {

        String name,address;
        String id;
        musoGetSet(String name){
            this.setName(name);
            this.setAddress(address);
            this.setId(id);

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public String getName() {
            return name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setId(String id) {
            this.id=id;
        }

        public String getId() {
            return id;
        }

}
