package com.qdang.application.noticeboard.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Hashtag {

	private Long id;
	private String name;

	public static Hashtag newHashtag(String name) {
		return Hashtag.builder()
				.name(name)
				.build();
	}

	public static Hashtag init(Long id) {
		return Hashtag.builder()
				.id(id)
				.build();
	}
}
