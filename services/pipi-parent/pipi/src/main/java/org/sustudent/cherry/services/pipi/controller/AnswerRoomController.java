package org.sustudent.cherry.services.pipi.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.web.BaseController;
import org.sustudent.cherry.common.security.utils.ContextUtils;
import org.sustudent.cherry.services.pipi.entity.AnswerRoom;
import org.sustudent.cherry.services.pipi.model.AnswerRoomVO;
import org.sustudent.cherry.services.pipi.service.AnswerRoomService;
import org.sustudent.cherry.services.user.api.feign.UserServiceClient;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName AnswerRoomController.java
 * @Description AnswerRoomController
 * @createTime 2020/06/14/ 16:31:00
 */
@RestController
@RequestMapping("/answerRoom")
public class AnswerRoomController extends BaseController {

  @Autowired
  private AnswerRoomService answerRoomService;

  @Autowired
  private UserServiceClient userServiceClient;

  @GetMapping("/findRoomPage")
  public ResponseResult<Page<AnswerRoom>> findRoomPage(HttpServletRequest request,
      AnswerRoomVO answerRoomVO) {
    System.out.println( SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    userServiceClient.findUserByUsername("admin");
    Page<AnswerRoom> page = getPage(request, AnswerRoom.class);
    answerRoomService.findRoomPage(page, answerRoomVO);
    return success(page);
  }

  @PostMapping("/save")
  public ResponseResult<AnswerRoom> save(@RequestBody AnswerRoom answerRoom) {
    answerRoomService.saveOrUpdate(answerRoom);
    return success(answerRoom);
  }
}
