package com.xuecheng.content.service.impl;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.service.CourseCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程分类 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements CourseCategoryService {

    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        // 查询数据
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        // 转换成map，已备使用，清楚根节点
        Map<String, CourseCategoryTreeDto> mapTemp = courseCategoryTreeDtos
                .stream()
                .filter(item -> !id.equals(item.getId())) // 排除根节点
                .collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2 // 重复策略
                ));

        List<CourseCategoryTreeDto> categoryTreeList = new ArrayList<>();

        courseCategoryTreeDtos
                .stream()
                .filter(item -> !id.equals(item.getId()))
                .forEach(item -> {
                    if (item.getParentid().equals(id)) { // 如果父id等于传入id
                        categoryTreeList.add(item); // 集合中加入该子节点
                    }
                    CourseCategoryTreeDto courseCategoryTreeDto = mapTemp.get(item.getParentid()); // 从map里面拿
                    if (courseCategoryTreeDto != null) {
                        if (courseCategoryTreeDto.getChildrenTreeNodes() == null) {
                            courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                        }
                        courseCategoryTreeDto.getChildrenTreeNodes().add(item); // 将子节点放入父节点
                    }
                });


        return categoryTreeList;
    }
}

