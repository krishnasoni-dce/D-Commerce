package com.technokryon.ecommerce.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDownload {

	Integer pdAgId;
	String pdTkecmpId;
	String pdIsSharable;
	String pdUrl;
	String pdFile;
	String pdTitle;
}
