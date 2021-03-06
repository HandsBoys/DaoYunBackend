package com.dy.service;

import com.dy.domain.SysCourseStudents;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dy.dto.client.CourseDto;
import com.dy.dto.system.student.SysStudentDto;

import java.util.List;

/**
 *
 */
public interface SysCourseStudentsService extends IService<SysCourseStudents> {

    /**
     * 当前登录用户加入班课
     * @param courseId
     * @return 是否成功
     */
    int joinCourse(Long courseId);

    /**
     * 退出班课
     * @param courseId 班课id
     * @param studentId 学生id
     * @return 是否成功
     */
    int quitCourse(Long courseId, Long studentId);

    /**
     * 根据班课id和学生id获取记录
     * @param courseId
     * @param studentId
     * @return 一条记录
     */
    SysCourseStudents getRecord(Long courseId, Long studentId);

    /**
     * 获取一个班课下所有学生的信息
     * @param courseId
     * @return 学生信息对象的列表
     */
    List<SysStudentDto> getStudents(Long courseId);

    /**
     * 将学生加入班课
     * @param courseId
     * @param studentId
     * @return
     */
    int addStudent(Long courseId, Long studentId);

    /**
     * 修改学生分数
     * @param courseId
     * @param studentId
     * @param score
     * @return
     */
    int updateScore(Long courseId, Long studentId, Long score);

    /**
     * 将学生批量退出班课
     * @param courseId
     * @param studentIds
     * @return
     */
    int removeStudents(Long courseId, Long[] studentIds);

    List<Long> getStudentIds(Long courseId);

    /**
     * 判断学生是否加入班课
     * @param courseId
     * @param studentId
     * @return true:学生已经加入班课;false:学生未加入班课
     */
    boolean checkStudentIsInCourse(Long courseId, Long studentId);

    /**
     * 给学生加分
     * @param courseId 课程id
     * @param studentId 学生id
     * @param score 增加的分数
     * @return 是否成功
     */
    int addScore(Long courseId, Long studentId, Long score);

    /**
     * 给学生减分
     * @param courseId 课程id
     * @param studentId 学生id
     * @param score 分数
     * @return 是否成功
     */
    int reduceScore(Long courseId, Long studentId, Long score);

    /**
     * 获取学生的分数
     * @param courseId 课程id
     * @param studentId 学生id
     * @return 学生分数
     */
    Long getScore(Long courseId, Long studentId);
}
