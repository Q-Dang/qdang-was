package com.qdang.adapter.post;

import com.qdang.global.http.WebAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebAdapter(path = "/posts")
@RequiredArgsConstructor
public class PostController implements PostWebAdapter {

}
