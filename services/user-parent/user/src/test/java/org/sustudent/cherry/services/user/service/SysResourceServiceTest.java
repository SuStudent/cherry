//package org.sustudent.cherry.services.user.service;
//
//import static org.junit.Assert.*;
//
//import com.fasterxml.jackson.core.JsonParser.Feature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import org.apache.commons.collections4.MapUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.sustudent.cherry.services.user.UserApplicationTest;
//import org.sustudent.cherry.services.user.entity.SysResource;
//import org.sustudent.cherry.services.user.enums.ResourceType;
//
//public class SysResourceServiceTest extends UserApplicationTest {
//
//
//  @Autowired
//  private SysResourceService resourceService;
//
//  public String json() {
//    String str = "{\n"
//        + "  path: '/charts',\n"
//        + "  redirect: 'noRedirect',\n"
//        + "  name: 'Charts',\n"
//        + "  meta: {\n"
//        + "    title: 'Charts',\n"
//        + "    icon: 'chart'\n"
//        + "  },\n"
//        + "  children: [\n"
//        + "    {\n"
//        + "      path: 'keyboard',\n"
//        + "  \n"
//        + "      name: 'KeyboardChart',\n"
//        + "      meta: { title: 'Keyboard Chart', noCache: true }\n"
//        + "    },\n"
//        + "    {\n"
//        + "      path: 'line',\n"
//        + "      name: 'LineChart',\n"
//        + "      meta: { title: 'Line Chart', noCache: true }\n"
//        + "    },\n"
//        + "    {\n"
//        + "      path: 'mix-chart',\n"
//        + "  \n"
//        + "      name: 'MixChart',\n"
//        + "      meta: { title: 'Mix Chart', noCache: true }\n"
//        + "    }\n"
//        + "  ]\n"
//        + "}";
//
//    return str;
//  }
//
//  @Test
//  public void insert() throws IOException {
//
//    ObjectMapper om = new ObjectMapper();
//    om.configure(Feature.ALLOW_SINGLE_QUOTES, true);
//    List<Map<String, Object>> datas = om.readValue(json(), List.class);
//    datas.forEach(item -> {
//      SysResource resource = new SysResource();
//      resource.setType(ResourceType.MENU);
//      resource.
//    });
//
//
//      SysResource resource = new SysResource();
//      resource.setKey("dashboard");
//      Map<String, Object> meta = (Map<String, Object>) MapUtils.getMap(value, "meta");
//      resource.setIcon(MapUtils.getString(meta, "icon"));
//      resource.setTitle(MapUtils.getString(meta, "title"));
//      resource.setNavigable(MapUtils.getBooleanValue(meta, "show", true));
//      resource.setComponent(MapUtils.getString(value, "component"));
//      resource.setPath(MapUtils.getString(value, "path"));
//
//      Long jsonParentId = MapUtils.getLong(value, "parentId");
//      if (jsonParentId.equals(0L)) {
//        resource.setParentId(0L);
//      } else {
//        resource.setParentId(idMap.get(jsonParentId));
//      }
//
//      resource.setType(ResourceType.MENU);
//      resourceService.saveOrUpdate(resource);
//
//
//  }
//
//  public static void main(String[] args) throws IOException {
//    new SysResourceServiceTest().insert();
//  }
//}