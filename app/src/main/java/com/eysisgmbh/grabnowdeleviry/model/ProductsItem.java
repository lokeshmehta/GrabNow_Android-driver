package com.eysisgmbh.grabnowdeleviry.model;

import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("image")
	private String image;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("cat_title")
	private String catTitle;

	@SerializedName("description")
	private String description;

	@SerializedName("title")
	private String title;

	@SerializedName("regular_price")
	private String regularPrice;

	@SerializedName("measurement_volume")
	private int measurementVolume;

	@SerializedName("variation_id")
	private int variationId;

	@SerializedName("sub_cat_title")
	private String subCatTitle;

	@SerializedName("tax_percentage")
	private String taxPercentage;

	@SerializedName("measurement_unit")
	private String measurementUnit;

	@SerializedName("id")
	private int id;

	@SerializedName("deposit_price")
	private String depositPrice;

	@SerializedName("sku")
	private String sku;

	public String getImage(){
		return image;
	}

	public int getQuantity(){
		return quantity;
	}

	public String getCatTitle(){
		return catTitle;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}

	public String getRegularPrice(){
		return regularPrice;
	}

	public int getMeasurementVolume(){
		return measurementVolume;
	}

	public int getVariationId(){
		return variationId;
	}

	public String getSubCatTitle(){
		return subCatTitle;
	}

	public String getTaxPercentage(){
		return taxPercentage;
	}

	public String getMeasurementUnit(){
		return measurementUnit;
	}

	public int getId(){
		return id;
	}

	public String getDepositPrice(){
		return depositPrice;
	}

	public String getSku(){
		return sku;
	}
}