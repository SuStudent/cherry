package org.sustudent.cherry.services.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sustudent.cherry.common.core.model.Page;
import org.sustudent.cherry.common.core.model.ResponseResult;
import org.sustudent.cherry.common.core.web.BaseController;
import org.sustudent.cherry.services.user.entity.SysDictionary;
import org.sustudent.cherry.services.user.entity.SysDictionaryItem;
import org.sustudent.cherry.services.user.service.SysDictionaryItemService;
import org.sustudent.cherry.services.user.service.SysDictionarySerivce;

/**
 * @author yiyi.su
 * @version 1.0.0
 * @ClassName SysDictionaryController.java
 * @Description SysDictionaryController
 * @createTime 2019/12/21/ 19:52:00
 */
@RestController
@RequestMapping("/sysDict")
public class SysDictionaryController extends BaseController {

  @Autowired
  private SysDictionarySerivce sysDictService;

  @Autowired
  private SysDictionaryItemService sysDictItemService;

  @GetMapping("/findPage")
  public ResponseResult findPage(SysDictionary sysDictionary, HttpServletRequest request){
    Page<SysDictionary> page = getPage(request,SysDictionary.class);
    sysDictService.findPage(page,sysDictionary);
    return success(page);
  }

  @PostMapping("/save")
  public ResponseResult save(@RequestBody SysDictionary sysDictionary){
    sysDictService.saveOrUpdate(sysDictionary);
    return success(sysDictionary);
  }

  @PostMapping("/delete")
  public ResponseResult delete(@RequestBody SysDictionary sysDictionary){
    sysDictService.delete(sysDictionary);
    return success();
  }

  @GetMapping("/findDictItems")
  public ResponseResult findDictItems(SysDictionaryItem sysDictionaryItem){
    return success(sysDictItemService.findDictItems(sysDictionaryItem));
  }

  @PostMapping("/saveItem")
  public ResponseResult saveItem(@RequestBody SysDictionaryItem sysDictionaryItem){
    sysDictItemService.saveOrUpdate(sysDictionaryItem);
    return success(sysDictionaryItem);
  }

  @PostMapping("/deleteItem")
  public ResponseResult deleteItem(@RequestBody SysDictionaryItem sysDictionaryItem){
    sysDictItemService.delete(sysDictionaryItem);
    return success();
  }
}
