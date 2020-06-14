package org.sustudent.cherry.services.pipi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.service.BaseService;
import org.sustudent.cherry.services.pipi.entity.AnswerRoom;
import org.sustudent.cherry.services.pipi.mapper.AnswerRoomMapper;
import org.sustudent.cherry.services.pipi.model.AnswerRoomVO;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName AnswerRoomService.java
 * @Description AnswerRoomService
 * @createTime 2020/06/14/ 16:30:00
 */
@Service
@Transactional
public class AnswerRoomService extends BaseService<AnswerRoomMapper, AnswerRoom> {

  public void findRoomPage(Page<AnswerRoom> page, AnswerRoomVO answerRoomVO) {
    mapper.findRoomPage(page,answerRoomVO);

  }
}
