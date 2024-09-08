package com.briup.product_source.service.impl;

import com.briup.product_source.bean.basic.FenceHouse;
import com.briup.product_source.bean.basic.Hurdles;
import com.briup.product_source.bean.ext.FenceHouseExt;

import com.briup.product_source.dao.basic.FenceHouseMapper;
import com.briup.product_source.dao.ext.FenceHouseExtMapper;
import com.briup.product_source.dao.ext.HurdlesExtMapper;
import com.briup.product_source.service.FenceHouseService;
import com.briup.product_source.util.BriupAssert;
import com.briup.product_source.util.ResultCode;
import com.briup.product_source.util.SnowflakeIdGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//导入断言工具类
import static com.briup.product_source.util.ResultCode.*;

@Service
public class FenceHouseServiceImpl implements FenceHouseService {
    @Autowired
    private FenceHouseExtMapper fenceHouseExtMapper;
    @Autowired
    private FenceHouseMapper fenceHouseMapper; // 注入基本的 FenceHouseMapper
    @Autowired
    private HurdlesExtMapper hurdlesExtMapper;
    @Autowired
    private SnowflakeIdGenerator snowflakeIdGenerator;
    @Override
    public PageInfo<FenceHouse> findByPage(int pageNum, int pageSize, String fhName) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<FenceHouse> list = fenceHouseExtMapper.selectByName(fhName);
        return new PageInfo<>(list);
    }

    @Override
    public FenceHouseExt findById(String id) {
        List<FenceHouseExt> results = fenceHouseExtMapper.findFenceHouseWithHurdles("851d58388f974d95b6c67d93189b7222");
        // 如果没有结果，返回 null 或者抛出异常
        if (results.isEmpty()) {
            return null; // 或者可以抛出自定义异常
        }
        // 创建一个新的 FenceHouseExt 对象来存储合并后的信息
        FenceHouseExt combinedFenceHouseExt = new FenceHouseExt();
        // 初始化栏圈信息列表
        List<Hurdles> allHurdles = new ArrayList<>();
        for (FenceHouseExt result : results) {
            // 合并栏舍信息
            combinedFenceHouseExt.setFhId(result.getFhId());
            combinedFenceHouseExt.setFhName(result.getFhName());
            combinedFenceHouseExt.setFhDesc(result.getFhDesc());
            combinedFenceHouseExt.setFhTime(result.getFhTime());
            // 合并栏圈信息
            if (result.getHurdles() != null) {
                allHurdles.addAll(result.getHurdles());
            }
        }
        // 设置合并后的栏圈信息
        combinedFenceHouseExt.setHurdles(allHurdles);
        return combinedFenceHouseExt; // 返回合并后的栏舍信息
    }

    @Override
    public void saveOrUpdate(FenceHouse fenceHouse) {
        //无论是新增还是修改，必须提供栏舍名称和描述信息
        //判断名字是否为空
        BriupAssert.hasText(fenceHouse.getFhName(),NAME_NOT_EMPTY);
        //判断描述信息是否为空
        BriupAssert.hasText(fenceHouse.getFhDesc(),DESC_NOT_EMPTY);
        // 如果 ID 为空，则生成一个新的 ID
        if (!StringUtils.hasText(fenceHouse.getFhId())) {
            // 使用雪花算法生成唯一 ID
            Long newId = SnowflakeIdGenerator.generateId();
            fenceHouse.setFhId(String.valueOf(newId));
            // 检查名称的唯一性
            BriupAssert.repeat(fenceHouseExtMapper.countByName(fenceHouse.getFhName()),FH_NAME_REPEAT);
            fenceHouse.setFhTime(new Date(System.currentTimeMillis()));
            // 执行插入操作
            fenceHouseMapper.insertSelective(fenceHouse);
        } else {
            // 如果 ID 不为空，检查数据库中是否存在该 ID
            BriupAssert.notNull(fenceHouseMapper.selectByPrimaryKey(fenceHouse.getFhId()),ID_NOT_EXIST);
            // 检查名称的唯一性
            BriupAssert.repeat(fenceHouseExtMapper.countByName(fenceHouse.getFhName()),FH_NAME_REPEAT);
            // 执行更新操作
            fenceHouseMapper.updateByPrimaryKeySelective(fenceHouse);
        }
    }

    @Override
    public void deleteById(String id) {
        //当前id对应的数据是否存在？
        FenceHouse fh = fenceHouseExtMapper.selectByPrimaryKey(id);
        BriupAssert.notNull(fh,ResultCode.DATA_NOT_EXIST);

        /*
            数据删除，一个主键值作为其他表外键值 删除失败。数据约束放在代码层面
            栏舍和栏圈存在主外关系：
            当删除栏舍时，方案1：级联删除  同时删除对应的栏圈信息，及其动物信息
                        方案2：级联置空  同时将对应的栏圈信息外键值设置为null
         */

        /*
            执行删除栏舍时，先关联的对应的栏圈信息置空
            update  栏圈表 set f_hence_id = null where f_hence_id = id
         */
        hurdlesExtMapper.updateFenceId(id);
        //关联的动物信息：不需要修改，栏圈数据没有删除
        //执行删除栏舍信息
        fenceHouseExtMapper.deleteByPrimaryKey(id);
    }


    //rollbackFor = Exception.class 当异常是Exception类及其子类异常，都会进行事务回滚
    @Transactional(rollbackFor = Exception.class) //事务管理：实现当前方法中操作同时成功，同时失败
    public void deleteByBatch(List<String> ids) throws Exception{
        /*
            连接对象被Spring管理：使用数据库连接池 Hi...Pool
            事务手动提交 conn.setAutoCommit(false);
            delete  id=1
            delete  id=2
            发送异常：不执行剩余代码，进行全局异常处理 事务rollback;
            delete  id=3
            delete
            事务commit;
         */
        /*
                                                                                                   ：新建一个事务名字叫批量删除: Spring事务特点 PROPAGATION_REQUIRED,ISOLATION_DEFAULT
2024-08-26 16:17:37.199 DEBUG 18704 --- [nio-8888-exec-1] o.s.jdbc.support.JdbcTransactionManager  : 获取一个连接代理对象Connection
2024-08-26 16:17:37.201 DEBUG 18704 --- [nio-8888-exec-1] o.s.jdbc.support.JdbcTransactionManager  : 切换jdbc 连接对象模式为手动提交模式
2024-08-26 16:17:37.206 DEBUG 18704 --- [nio-8888-exec-1] c.b.p.d.b.F.deleteByPrimaryKey           : ==>  delete id = 1 删除第一条数据
2024-08-26 16:17:37.208 DEBUG 18704 --- [nio-8888-exec-1] c.b.p.d.b.F.deleteByPrimaryKey           : ==>  delete id = 2 删除第二条件数据
																										   发送算术异常：停止执行剩余代码。抛出异常
2024-08-26 16:17:37.210 DEBUG 18704 --- [nio-8888-exec-1] o.s.jdbc.support.JdbcTransactionManager  : 将当前事务回滚
2024-08-26 16:17:37.210 DEBUG 18704 --- [nio-8888-exec-1] o.s.jdbc.support.JdbcTransactionManager  : 连接代理对象Connection实现事务的回滚
2024-08-26 16:17:37.210 DEBUG 18704 --- [nio-8888-exec-1] o.s.jdbc.support.JdbcTransactionManager  : 结束事务释放连接对象


         */

        /*
            1.如何实现批量删除？
            方法1：循环直接删除
            方法2：mybatis动态标签<foreach> 执行效率高
         */
        //判断是否有关联的栏圈信息，如果有，提示无法删除
        for (int i = 0; i < ids.size(); i++) {
            //模拟当批量删除时，发生异常： 事务操作部分成功，部分失败
            if(i == 2){//当删除第三条数据时，发送异常
                //默认事务管理只对运行时异常生效,编译异常无法生效
                //System.out.println(1/0);
                Class.forName("a.b.c");
            }
            hurdlesExtMapper.deleteByPrimaryKey(ids.get(i));
        }
        ////方式1：可以实现批量删除  发送N条sql
        //ids.forEach(id ->{
        //    fhMapper.deleteByPrimaryKey(id);
        //});
        ////方式2：减少网络通信 发送1条sql
        //fhExtMapper.deleteByIds(ids);
    }
}
