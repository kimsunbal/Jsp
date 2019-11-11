package blog.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
	private int statusCode;//1||-1
	private String status;//ok||fail
	private String statusMsg;//Parsing error||Page not found
}
