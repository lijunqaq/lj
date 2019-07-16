package com.example.lj.controller.system;

import com.example.lj.dao.entity.Role;
import com.example.lj.dto.ResponseBean;
import com.example.lj.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈role控制器〉
 *
 * @author coach tam
 * @email 1077@qq.com
 * @create 2018/8/9
 * @since 1.0.0
 * 〈坚持灵活 灵活坚持〉
 */


@RestController
@RequestMapping("/v1/system/role")
@Api(tags = "role",description = "权限相关api接口")
public class RoleController {

    @Autowired
    private RoleService roleService;

//    /**
//     *  分页查询
//     * @param pageSize
//     * @param pageNo
//     * @return Pagination
//     */
//    @GetMapping
//    public Pagination page(Integer pageSize, Integer pageNo,String name ){
//        Pagination result=null;
//        result=roleService.findBypage(pageSize,pageNo,name);
//        return result;
//    }

    /**
     * 查询所有角色
     * @param
     * @param
     * @return
     */
    @GetMapping("/all")
    public ResponseBean<List<Role>> findAll(){
        return roleService.findAllRole();
    }

//    /**
//     * 添加角色
//     * @param role
//     * @return
//     */
//    @PostMapping
//    public String  addRole(@RequestBody Role role){
//      //将 modules  --> moduleSet
//        if(role!= null && role.getModualIds()!= null){
//            HashSet<Module> moduleSet = new HashSet<>();
//            for(int i=0;i<role.getModualIds().length;i++){
//                Module module = new Module();
//                module.setId(role.getModualIds()[i]);
//                moduleSet.add(module);
//            }
//            role.setModuleSet(moduleSet);
//
//
//        }
//
//        roleService.addRole(role);
//        return "ok";
//    }
//    /**
//     * 删除角色
//     * @param groupId
//     * @return
//     */
//    @DeleteMapping
//    public String  deleteRole(@RequestBody String[] groupId){
//        roleService.deleteRole(groupId);
//        return "ok";
//    }
//    /**
//     * 更新角色
//     * @param role
//     * @return
//     */
//    @PutMapping
//    public String  updateRole(@RequestBody Role role){
//
//        //将 modules  --> moduleSet
//        if(role!= null && role.getModualIds()!= null){
//            HashSet<Module> moduleSet = new HashSet<>();
//            for(int i=0;i<role.getModualIds().length;i++){
//                Module module = new Module();
//                module.setId(role.getModualIds()[i]);
//                moduleSet.add(module);
//            }
//            role.setModuleSet(moduleSet);
//
//
//        }
//        roleService.updateRole(role);
//        return "ok";
//    }


}
