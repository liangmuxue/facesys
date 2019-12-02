package com.ss.facesys.web.manage.system.controller;

import com.ss.controller.AbstractController;
import com.ss.facesys.data.system.client.IPersonTestService;
import com.ss.facesys.data.system.common.model.PersonTest;
import com.ss.response.ResponseEntity;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/mongoTest"})
public class MongoTestContorller extends AbstractController {

    public static final Log LOG = LogFactory.getLog(MongoTestContorller.class);


    @Resource
    private IPersonTestService personTestService;


    @RequestMapping(value = {"insertPerson"}, method = {RequestMethod.POST})
    public ResponseEntity<Map<String, String>> insertPerson(HttpServletRequest request) throws Exception {
        try {
            ResponseEntity<Map<String, String>> resp = createSuccResponse();
            int length = 5;
            for (int i = 0; i < length; i++) {


                PersonTest p = new PersonTest("name" + (i + 1), i + 1);
                this.personTestService.insert(p);
                LOG.debug("添加成功");
            }
            return resp;
        } catch (Exception e) {

            LOG.error(e.toString(), e);
            throw e;
        }
    }


    @RequestMapping(value = {"listAllPerson"}, method = {RequestMethod.POST})
    public ResponseEntity<List<PersonTest>> listAllPerson(HttpServletRequest request) throws Exception {
        try {
            ResponseEntity<List<PersonTest>> resp = createSuccResponse();

            List<PersonTest> list = this.personTestService.findAll();

            LOG.debug("查询结果如下:");
            for (PersonTest p : list) {
                LOG.debug(p.toString());
            }

            resp.setData(list);
            return resp;
        } catch (Exception e) {

            LOG.error("listAll error!", e);
            throw e;
        }
    }

}
