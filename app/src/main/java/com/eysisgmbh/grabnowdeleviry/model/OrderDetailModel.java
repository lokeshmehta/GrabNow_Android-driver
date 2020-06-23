package com.eysisgmbh.grabnowdeleviry.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailModel {

    @SerializedName("data")
    private Data data;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public class CustomerDetail {

        @SerializedName("mobile_no")
        private long mobileNo;

        @SerializedName("last_name")
        private String lastName;

        @SerializedName("profile_picture")
        private String profilePicture;

        @SerializedName("mobile_country_code")
        private int mobileCountryCode;

        @SerializedName("first_name")
        private String firstName;

        @SerializedName("email")
        private String email;

        public long getMobileNo() {
            return mobileNo;
        }

        public String getLastName() {
            return lastName;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public int getMobileCountryCode() {
            return mobileCountryCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getEmail() {
            return email;
        }
    }

    public class Data {

        @SerializedName("order_detail")
        private OrderDetail orderDetail;

        public OrderDetail getOrderDetail() {
            return orderDetail;
        }
    }

    public class OrderDetail {

        @SerializedName("floor_no")
        private int floorNo;

        @SerializedName("total_paid_amount")
        private String totalPaidAmount;

        @SerializedName("address")
        private String address;

        @SerializedName("address_type")
        private String addressType;

        @SerializedName("lng")
        private String lng;

        @SerializedName("offer_discount")
        private String offerDiscount;

        @SerializedName("payment_status")
        private String paymentStatus;

        @SerializedName("promocode")
        private String promocode;

        @SerializedName("building_no")
        private String buildingNo;

        @SerializedName("customer_order_note")
        private String customerOrderNote;

        @SerializedName("total_cart_amount")
        private String totalCartAmount;

        @SerializedName("bag_fee")
        private String bagFee;

        @SerializedName("products")
        private List<ProductsItem> products;

        @SerializedName("delivery_fee")
        private String deliveryFee;

        @SerializedName("order_status")
        private int orderStatus;

        @SerializedName("customer_detail")
        private CustomerDetail customerDetail;

        @SerializedName("address_title")
        private String addressTitle;

        @SerializedName("invoice_id")
        private String invoiceId;

        @SerializedName("id")
        private int id;

        @SerializedName("apt_no")
        private int aptNo;

        @SerializedName("lat")
        private String lat;

        @SerializedName("direction")
        private String direction;

        public int getFloorNo() {
            return floorNo;
        }

        public String getTotalPaidAmount() {
            return totalPaidAmount;
        }

        public String getAddress() {
            return address;
        }

        public String getAddressType() {
            return addressType;
        }

        public String getLng() {
            return lng;
        }

        public String getOfferDiscount() {
            return offerDiscount;
        }

        public String getPaymentStatus() {
            return paymentStatus;
        }

        public String getPromocode() {
            return promocode;
        }

        public String getBuildingNo() {
            return buildingNo;
        }

        public String getCustomerOrderNote() {
            return customerOrderNote;
        }

        public String getTotalCartAmount() {
            return totalCartAmount;
        }

        public String getBagFee() {
            return bagFee;
        }

        public List<ProductsItem> getProducts() {
            return products;
        }

        public String getDeliveryFee() {
            return deliveryFee;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public CustomerDetail getCustomerDetail() {
            return customerDetail;
        }

        public String getAddressTitle() {
            return addressTitle;
        }

        public String getInvoiceId() {
            return invoiceId;
        }

        public int getId() {
            return id;
        }

        public int getAptNo() {
            return aptNo;
        }

        public String getLat() {
            return lat;
        }

        public String getDirection() {
            return direction;
        }
    }
}