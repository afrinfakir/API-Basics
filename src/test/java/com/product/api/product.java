package com.product.api;

public class product {
	private int id;
	private String title;
	private double price;
	private String Description;
	private String category;
	private String image;
	private Rating rating;

	public product() {

	}

	public product(int id, String title, double price, String description, String category, String image,
			Rating rating) {
		this.id = id;
		this.title = title;
		this.price = price;
		Description = description;
		this.category = category;
		this.image = image;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	// Rating class
	public static class Rating {
		private double rate;
		private int count;

		public Rating() {
		}

		public Rating(double rate, int count) {

			this.rate = rate;
			this.count = count;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}
}