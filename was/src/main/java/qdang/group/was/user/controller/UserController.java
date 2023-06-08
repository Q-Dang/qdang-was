package qdang.group.was.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qdang.group.was.global.response.ApiResponse;
import qdang.group.was.global.response.SuccessType;
import qdang.group.was.user.controller.dto.request.LoginRequest;
import qdang.group.was.user.controller.dto.request.SignUpRequest;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 API Document")
public class UserController {

}
