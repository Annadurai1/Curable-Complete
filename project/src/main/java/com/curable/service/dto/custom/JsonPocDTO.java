package com.curable.service.dto.custom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class JsonPocDTO {
	@JsonProperty("title")
	public String title;
	@JsonProperty("author")
	public String author;
	@JsonProperty("publisher")
	public String publisher;
	@JsonProperty("priceInCents")
	public String priceInCents;
	@JsonProperty("url")
	public String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPriceInCents() {
		return priceInCents;
	}

	public void setPriceInCents(String priceInCents) {
		this.priceInCents = priceInCents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
