package com.eysisgmbh.grabnowdeleviry.model;

import com.google.gson.annotations.SerializedName;

public class ChangeStatusModel{

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


	public class OrdersData{

		@SerializedName("id")
		private int id;

		@SerializedName("status")
		private String status;

		public int getId(){
			return id;
		}

		public String getStatus(){
			return status;
		}
	}
	public class Data{

		@SerializedName("orders_data")
		private OrdersData ordersData;

		public OrdersData getOrdersData(){
			return ordersData;
		}
	}
}