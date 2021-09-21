package com.example.clip.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PaymentRequest {

	@NotNull
    private Long userId;
	@NotNull
    private BigDecimal amount;
}
