package com.eysisgmbh.grabnowdeleviry.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel implements Serializable{

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

	public class Data implements Serializable {

		@SerializedName("mobile_verified")
		private boolean mobileVerified;

		@SerializedName("api_token")
		private String apiToken;

		@SerializedName("mobile_no")
		private long mobileNo;

		@SerializedName("last_name")
		private String lastName;

		@SerializedName("is_email_notification")
		private boolean isEmailNotification;

		@SerializedName("is_notification")
		private boolean isNotification;

		@SerializedName("profile_picture")
		private String profilePicture;

		@SerializedName("id")
		private int id;

		@SerializedName("mobile_country_code")
		private int mobileCountryCode;

		@SerializedName("token_type")
		private String tokenType;

		@SerializedName("first_name")
		private String firstName;

		@SerializedName("email")
		private String email;

		public boolean isMobileVerified(){
			return mobileVerified;
		}

		public String getApiToken(){
			return apiToken;
		}

		public long getMobileNo(){
			return mobileNo;
		}

		public String getLastName(){
			return lastName;
		}

		public boolean isIsEmailNotification(){
			return isEmailNotification;
		}

		public boolean isIsNotification(){
			return isNotification;
		}

		public String getProfilePicture(){
			return profilePicture;
		}

		public int getId(){
			return id;
		}

		public int getMobileCountryCode(){
			return mobileCountryCode;
		}

		public String getTokenType(){
			return tokenType;
		}

		public String getFirstName(){
			return firstName;
		}

		public String getEmail(){
			return email;
		}
	}
}