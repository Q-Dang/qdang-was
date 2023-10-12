package com.qdang.application.noticeboard.port.in.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PinNoticeBoardCommand {

	private Long userId;
	private Long noticeBoardId;
	private Boolean pin;

	public static PinNoticeBoardCommand of(
			Long userId,
			Long noticeBoardId,
			Boolean pin) {
		return new PinNoticeBoardCommand(
				userId,
				noticeBoardId,
				pin);
	}
}