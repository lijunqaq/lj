package com.example.lj.controller.system;


import com.example.lj.dao.entity.Dept;
import com.example.lj.dto.ResponseBean;
import com.example.lj.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 〈dept控制器〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/9
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */


@RestController
@RequestMapping("/v1/system/dept")
@Api(tags = "dept",description = "部门相关api接口")
public class DeptController {

    @Autowired
    private DeptService deptService;
//
//    /**
//     *  分页查询
//     * @param pageSize
//     * @param pageNo
//     * @return Pagination
//     */
//    @GetMapping
//    public Pagination page(Integer pageSize, Integer pageNo , String keyWord){
//        Pagination result=null;
//        result=deptService.findBypage(pageSize,pageNo,keyWord);
//        return result;
//    }

    /**
     * 查询所有部门
     * @param
     * @param
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "1.01 查询所有部门", httpMethod = "GET", notes = "1.01 查询所有部门")
    public ResponseBean<List<Dept>> findAll(){
        return deptService.findAllDept();
    }

//    /**
//     * 添加部门
//     * @param dept
//     * @return
//     */
//    @PostMapping
//    public String  addDept(@RequestBody Dept dept){
//        deptService.addDept(dept);
//        return "ok";
//    }
//    /**
//     * 删除部门
//     * @param groupId
//     * @return
//     */
//    @DeleteMapping
//    public String  deleteDept(@RequestBody String[] groupId){
//        deptService.deleteDept(groupId);
//        return "ok";
//    }
//    /**
//     * 更新部门
//     * @param dept
//     * @return
//     */
//    @PutMapping
//    public String  updateDept(@RequestBody Dept dept){
//        deptService.updateDept(dept);
//        return "ok";
//    }


}
