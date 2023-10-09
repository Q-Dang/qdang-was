package com.qdang.adapter.noticeboard.response;

import com.qdang.application.noticeboard.domain.vo.NoticeBoardPinInfo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetNoticeBoardListResponse {

	private List<NoticeBoardPinInfoDto> noticeBoardList;

	public static GetNoticeBoardListResponse from(List<NoticeBoardPinInfo> noticeBoardPinInfoList) {
		return GetNoticeBoardListResponse.builder()
				.noticeBoardList(
						noticeBoardPinInfoList
								.stream()
								.map(noticeBoardPinInfo ->
										NoticeBoardPinInfoDto.from(noticeBoardPinInfo))
								.collect(Collectors.toList()))
				.build();
	}
}
