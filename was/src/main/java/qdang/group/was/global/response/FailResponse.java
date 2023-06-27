package qdang.group.was.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FailResponse {

	private int code;
	private String message;
}
