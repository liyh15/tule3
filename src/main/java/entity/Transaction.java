package entity;

import javax.validation.constraints.NotNull;

public class Transaction {
   
	@NotNull(message="²»ÄÜÎª¿Õ")
	private Long product;

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}
	
}
