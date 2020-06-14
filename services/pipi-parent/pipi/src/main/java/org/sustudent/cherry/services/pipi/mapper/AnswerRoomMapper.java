package org.sustudent.cherry.services.pipi.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.repository.BaseMapper;
import org.sustudent.cherry.services.pipi.entity.AnswerRoom;
import org.sustudent.cherry.services.pipi.model.AnswerRoomVO;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName AnswerRoomMapper.java
 * @Description AnswerRoomMapper
 * @createTime 2020/06/14/ 16:30:00
 */
public interface AnswerRoomMapper extends BaseMapper<AnswerRoom> {

  List<AnswerRoom> findRoomPage(
      @Param("page") Page<AnswerRoom> page, @Param("answerRoomVO") AnswerRoomVO answerRoomVO);
}
