package com.eysisgmbh.grabnowdeleviry.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderModel{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}

	public class Data{

		@SerializedName("orders_data")
		private List<OrdersDataItem> ordersData;

		public List<OrdersDataItem> getOrdersData(){
			return ordersData;
		}
	}

	public class OrdersDataItem{

		@SerializedName("floor_no")
		private int floorNo;

		@SerializedName("total_paid_amount")
		private String totalPaidAmount;

		@SerializedName("address")
		private String address;

		@SerializedName("offer_discount")
		private String offerDiscount;

		@SerializedName("total_order_amount")
		private String totalOrderAmount;

		@SerializedName("created_at")
		private String createdAt;

		@SerializedName("building_no")
		private String buildingNo;

		@SerializedName("customer_order_note")
		private String customerOrderNote;

		@SerializedName("bag_fee")
		private String bagFee;

		@SerializedName("delivery_fee")
		private String deliveryFee;

		@SerializedName("order_status")
		private String orderStatus;

		@SerializedName("updated_at")
		private String updatedAt;

		@SerializedName("address_title")
		private String addressTitle;

		@SerializedName("invoice_id")
		private String invoiceId;

		@SerializedName("id")
		private int id;

		@SerializedName("apt_no")
		private int aptNo;

		@SerializedName("direction")
		private String direction;

		public int getFloorNo(){
			return floorNo;
		}

		public String getTotalPaidAmount(){
			return totalPaidAmount;
		}

		public String getAddress(){
			return address;
		}

		public String getOfferDiscount(){
			return offerDiscount;
		}

		public String getTotalOrderAmount(){
			return totalOrderAmount;
		}

		public String getCreatedAt(){
			return createdAt;
		}

		public String getBuildingNo(){
			return buildingNo;
		}

		public String getCustomerOrderNote(){
			return customerOrderNote;
		}

		public String getBagFee(){
			return bagFee;
		}

		public String getDeliveryFee(){
			return deliveryFee;
		}

		public String getOrderStatus(){
			return orderStatus;
		}

		public String getUpdatedAt(){
			return updatedAt;
		}

		public String getAddressTitle(){
			return addressTitle;
		}

		public String getInvoiceId(){
			return invoiceId;
		}

		public int getId(){
			return id;
		}

		public int getAptNo(){
			return aptNo;
		}

		public String getDirection(){
			return direction;
		}
	}
}