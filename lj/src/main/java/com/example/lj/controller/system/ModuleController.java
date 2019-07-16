//package com.example.lj.controller.system;
//
//import com.yaorange.jk.entity.Module;
//import com.yaorange.jk.entity.Role;
//import com.yaorange.jk.entity.vo.ZtreeVO;
//import com.yaorange.jk.service.ModuleService;
//import com.yaorange.jk.service.RoleService;
//import com.yaorange.jk.utils.Pagination;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
///**
// * 〈module控制器〉
// *
// * @author coach tam
// * @email 1077@qq.com
// * @create 2018/8/9
// * @since 1.0.0
// * 〈坚持灵活 灵活坚持〉
// */
//
//
//@RestController
//@RequestMapping("/system/module")
//public class ModuleController {
//@Autowired
//private ModuleService moduleService;
//    @Autowired
//    private RoleService roleService;
//    /**
//     *  分页查询
//     * @param pageSize
//     * @param pageNo
//     * @return Pagination
//     */
//    @GetMapping
//    public Pagination page(Integer pageSize, Integer pageNo,String keyWord){
//        Pagination result=null;
//        result=moduleService.findBypage(pageSize,pageNo,keyWord);
//        return result;
//    }
//
//    //查询父模块
//    @GetMapping("/getParent/{ctype}")
//    public List<Module> getParent(@PathVariable("ctype") Integer ctype){
//        List<Module> result=null;
//        result=moduleService.getParent(ctype);
//        return result;
//    }
//    /**
//     * 添加模块
//     * @param
//     * @return
//     */
//    @PostMapping
//    public String  addModule(@RequestBody Module module){
//        moduleService.addModule(module);
//        return "ok";
//    }
//    /**
//     * 删除模块
//     * @param groupId
//     * @return
//     */
//    @DeleteMapping
//    public String  deleteModule(@RequestBody String[] groupId){
//        moduleService.deleteModule(groupId);
//        return "ok";
//    }
//    /**
//     * 更新模块
//     * @param module
//     * @return
//     */
//    @PutMapping
//    public String  updateModule(@RequestBody Module module){
//        moduleService.updateModule(module);
//        return "ok";
//    }
//    /**
//     *
//     *
//     * @author lj
//     * @date   得到所有模块
//     * @param
//     * @return
//     */
//
//    @GetMapping("/allZtree")
//    public List<ZtreeVO> allZtree(){
//        List<ZtreeVO> ztreeList = new ArrayList<>();
//        List<Module>  moduleList=moduleService.findAll();
//        for(Module module:moduleList){
//            ZtreeVO ztreeVO = new ZtreeVO();
//            ztreeVO.setId(module.getId());
//            ztreeVO.setpId(module.getParentId());
//            ztreeVO.setName(module.getName());
//            ztreeVO.setChecked(false);
//            ztreeVO.setOpen(true);
//            ztreeList.add(ztreeVO);
//        }
//
//        return ztreeList;
//    }
//
//    @GetMapping("/CheckZtree/{id}")
//    public List<ZtreeVO>  CheckZtree(@PathVariable("id") String roleId){
//
//        List<ZtreeVO> ztreeList = new ArrayList<>();
//        //得到所有的模块
//        List<Module>  moduleList=moduleService.findAll();
//        //得到角色拥有的模块
//        Role role = roleService.findById(Role.class, roleId);
//        Set<Module> moduleSet = role.getModuleSet();
//        //moduleList  --> ztreeList
//        for(Module module:moduleList){
//            ZtreeVO ztreeVO = new ZtreeVO();
//            ztreeVO.setId(module.getId());
//            ztreeVO.setpId(module.getParentId());
//            ztreeVO.setName(module.getName());
//            //选中
//                if(moduleSet.contains(module)){
//                    ztreeVO.setChecked(true);
//                }else{
//                    ztreeVO.setChecked(false);
//                }
//
//            ztreeVO.setOpen(true);
//            ztreeList.add(ztreeVO);
//        }
//
//
//        return ztreeList;
//    }
//}
