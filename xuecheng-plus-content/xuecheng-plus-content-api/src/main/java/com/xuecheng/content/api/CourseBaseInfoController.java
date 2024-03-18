package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 */
@Api(value = "课程信息管理接口", tags = "课程信息管理接口")
@RestController
@RequestMapping("/course")
public class CourseBaseInfoController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;


    /**
     * 课程分页查询
     * @param queryCourseParamsDto
     * @param pageParams
     * @return
     */
    @ApiOperation("课程查询接口")
    @PostMapping("/list")
    public PageResult<CourseBase> list(@RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto,
                                       PageParams pageParams) {
        return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);
    }


    /**
     * 添加课程基本信息
     * @param addCourseDto
     * @return
     */
    @PostMapping("course")
    public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCourseDto) {

        return null;
    }
}
