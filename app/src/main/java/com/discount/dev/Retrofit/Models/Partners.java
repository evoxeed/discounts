package com.discount.dev.Retrofit.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Partners {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("logo")
    @Expose
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }


//    @SerializedName("cities")
//    @Expose
//    private List<Integer> cities = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public String getInfo() {
        return info;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

//    public List<Integer> getCities() {
//        return cities;
//    }

//    public void setCities(List<Integer> cities) {
//        this.cities = cities;
//    }


    public class Address {

        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("house")
        @Expose
        private String house;
        @SerializedName("contact_phone")
        @Expose
        private String contactPhone;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }
    }
}